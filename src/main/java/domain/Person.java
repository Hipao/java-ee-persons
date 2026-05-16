package domain;

import java.util.Objects;

/**
 * Модель сущности «Сотрудник».
 * <p>
 * Соответствует таблице persons в базе данных.
 * Содержит персональные данные сотрудника и ссылку на его должность (Role).
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
public class Person {

    /** Идентификатор сотрудника (первичный ключ). */
    private Long id;

    /** Имя. */
    private String firstName;

    /** Фамилия. */
    private String lastName;

    /** Телефон. */
    private String phone;

    /** Адрес электронной почты. */
    private String email;

    /** Внешний ключ — ссылка на сущность Role. */
    private Long idRole;

    /** Навигационное свойство — связанная должность. */
    private Role role;

    public Person() {
    }

    public Person(String firstName, String lastName, String phone, String email, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        if (role != null) {
            this.idRole = role.getId();
        }
    }

    public Person(String firstName, String lastName, String phone, String email,
                  Long idRole, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.idRole = idRole;
        this.role = role;
    }

    public Person(Long id, String firstName, String lastName, String phone, String email,
                  Long idRole, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.idRole = idRole;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    /**
     * Возвращает наименование должности сотрудника или прочерк, если должность не задана.
     */
    public String getRole() {
        return role != null ? role.getNamerole() : "—";
    }

    public void setRole(Role role) {
        this.role = role;
        if (role != null) {
            this.idRole = role.getId();
        }
    }

    /** Возвращает связанный объект должности (для использования в шаблонах). */
    public Role getRoleEntity() {
        return role;
    }

    /** Полное имя сотрудника (Фамилия Имя). */
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (lastName != null) sb.append(lastName);
        if (firstName != null) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(firstName);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Person {Id = " + id
                + ", firstName = " + firstName
                + ", lastName = " + lastName
                + ", phone = " + phone
                + ", email = " + email
                + ", namerole = " + getRole()
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
