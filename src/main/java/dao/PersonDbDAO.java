package dao;

import domain.Person;
import domain.Role;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий для таблицы persons (Сотрудники).
 * При выборке заполняет навигационное свойство role путём обращения к RoleDbDAO.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
public class PersonDbDAO implements RepositoryDAO<Person> {

    private static final String SELECT_ALL =
            "SELECT id, roleid, firstname, lastname, phone, email " +
            "FROM persons ORDER BY lastname ASC";
    private static final String SELECT_BY_ID =
            "SELECT id, roleid, firstname, lastname, phone, email " +
            "FROM persons WHERE id = ?";
    private static final String INSERT =
            "INSERT INTO persons(roleid, firstname, lastname, phone, email) " +
            "VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE persons SET roleid = ?, firstname = ?, lastname = ?, " +
            "phone = ?, email = ? WHERE id = ?";
    private static final String DELETE =
            "DELETE FROM persons WHERE id = ?";

    private final ConnectionBuilder builder = new DbConnectionBuilder();
    private final RoleDbDAO roleDao = new RoleDbDAO();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Person person) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setLong(1, person.getIdRole());
            pst.setString(2, person.getFirstName());
            pst.setString(3, person.getLastName());
            pst.setString(4, person.getPhone());
            pst.setString(5, person.getEmail());
            pst.executeUpdate();
            try (ResultSet gk = pst.getGeneratedKeys()) {
                if (gk.next()) {
                    return gk.getLong("id");
                }
            }
            return -1L;
        } catch (SQLException e) {
            throw new DAOException("Не удалось добавить сотрудника", e);
        }
    }

    @Override
    public void update(Person person) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setLong(1, person.getIdRole());
            pst.setString(2, person.getFirstName());
            pst.setString(3, person.getLastName());
            pst.setString(4, person.getPhone());
            pst.setString(5, person.getEmail());
            pst.setLong(6, person.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Не удалось обновить сотрудника", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Не удалось удалить сотрудника", e);
        }
    }

    @Override
    public Person findById(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return fillPerson(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Не удалось найти сотрудника", e);
        }
    }

    @Override
    public List<Person> findAll() throws DAOException {
        List<Person> list = new LinkedList<>();
        List<Role> roles = roleDao.findAll();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Person p = fillPerson(rs);
                p.setRole(roleDao.findById(p.getIdRole(), roles));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Не удалось получить список сотрудников", e);
        }
    }

    private Person fillPerson(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setIdRole(rs.getLong("roleid"));
        person.setFirstName(rs.getString("firstname"));
        person.setLastName(rs.getString("lastname"));
        person.setPhone(rs.getString("phone"));
        person.setEmail(rs.getString("email"));
        return person;
    }
}
