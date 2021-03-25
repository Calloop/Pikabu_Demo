package ru.calloop.pikabu_demo;

public class AccountModel {

    private int id;
    private String login;
    private String phone;
    private String email;
    private String password;

    public AccountModel() {
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public AccountModel(int id, String login, String phone, String email, String password)
    {
        this.id = id;
        this.login = login;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
