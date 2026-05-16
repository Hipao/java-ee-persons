package dao;

import domain.Role;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий для таблицы roles (Должности).
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
public class RoleDbDAO implements RepositoryDAO<Role> {

    private static final String SELECT_ALL =
            "SELECT id, rolename FROM roles ORDER BY rolename ASC";
    private static final String SELECT_BY_ID =
            "SELECT id, rolename FROM roles WHERE id = ?";
    private static final String INSERT =
            "INSERT INTO roles(rolename) VALUES(?)";
    private static final String UPDATE =
            "UPDATE roles SET rolename = ? WHERE id = ?";
    private static final String DELETE =
            "DELETE FROM roles WHERE id = ?";

    private final ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Role role) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setString(1, role.getNamerole());
            pst.executeUpdate();
            try (ResultSet gk = pst.getGeneratedKeys()) {
                if (gk.next()) {
                    return gk.getLong("id");
                }
            }
            return -1L;
        } catch (SQLException e) {
            throw new DAOException("Не удалось добавить должность", e);
        }
    }

    @Override
    public void update(Role role) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, role.getNamerole());
            pst.setLong(2, role.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Не удалось обновить должность", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Не удалось удалить должность", e);
        }
    }

    @Override
    public Role findById(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? fillRole(rs) : null;
            }
        } catch (SQLException e) {
            throw new DAOException("Не удалось найти должность", e);
        }
    }

    @Override
    public List<Role> findAll() throws DAOException {
        List<Role> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillRole(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Не удалось получить список должностей", e);
        }
    }

    /** Найти роль по идентификатору в готовом списке (без обращения к БД). */
    public Role findById(Long id, List<Role> roles) {
        if (roles == null) return null;
        for (Role r : roles) {
            if (r.getId() != null && r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    private Role fillRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setNamerole(rs.getString("rolename"));
        return role;
    }
}
