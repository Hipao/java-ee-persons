package domain;

import java.util.Objects;

/**
 * Модель сущности «Должность».
 * <p>
 * Соответствует таблице roles в базе данных.
 * Содержит первичный ключ и наименование должности.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
public class Role {

    /** Идентификатор должности (первичный ключ). */
    private Long id;

    /** Наименование должности. */
    private String namerole;

    public Role() {
    }

    public Role(String namerole) {
        this.namerole = namerole;
    }

    public Role(Long id, String namerole) {
        this.id = id;
        this.namerole = namerole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamerole() {
        return namerole;
    }

    public void setNamerole(String namerole) {
        this.namerole = namerole;
    }

    @Override
    public String toString() {
        return "Role {Id = " + id + ", NameRole = " + namerole + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
