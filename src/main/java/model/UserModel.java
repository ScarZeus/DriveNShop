package model;

import jakarta.persistence.*;


import javax.crypto.SecretKey;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String user_name;
    private String email;
    private String password;
    @Column(nullable = true)
    private String employee_id;
    @Enumerated(EnumType.STRING)
    private Role role;
    private SecretKey keyToAccess;

    public UserModel(long user_id, String user_name, String email, String password, String employee_id, Role role, SecretKey keyToAccess) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.employee_id = employee_id;
        this.role = role;
        this.keyToAccess = keyToAccess;
    }

    public UserModel() {
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public SecretKey getKeyToAccess() {
        return keyToAccess;
    }

    public void setKeyToAccess(SecretKey keyToAccess) {
        this.keyToAccess = keyToAccess;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", employee_id='" + employee_id + '\'' +
                ", role=" + role +
                ", keyToAccess=" + keyToAccess +
                '}';
    }
}
