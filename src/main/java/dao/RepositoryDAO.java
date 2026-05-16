package dao;

import exception.DAOException;
import java.util.List;

/**
 * Обобщённый CRUD-интерфейс репозитория сущностей.
 *
 * @param <T> тип сущности предметной области
 * @author Демидко М. Д., группа ПИZ-331
 */
public interface RepositoryDAO<T> {

    /** Сохранить новую сущность, вернуть сгенерированный идентификатор. */
    Long insert(T o) throws DAOException;

    /** Обновить существующую сущность. */
    void update(T o) throws DAOException;

    /** Удалить сущность по идентификатору. */
    void delete(Long id) throws DAOException;

    /** Найти сущность по идентификатору. */
    T findById(Long id) throws DAOException;

    /** Получить полный список сущностей. */
    List<T> findAll() throws DAOException;
}
