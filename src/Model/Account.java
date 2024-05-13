package Model;
public class Account {
    private String name;
    private String username;
    private String password;
    private String email;
    private String securityQuestion1;
    private String securityQuestion2;
    private String securityQuestionAnswer1;
    private String securityQuestionAnswer2;
    private String role;
    public Account() {
    }

    public Account(String name, String username, String password, String email, String securityQuestion1,
                   String securityQuestion2,String securityQuestionAnswer1,String securityQuestionAnswer2,String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.securityQuestion1 = securityQuestion1;
        this.securityQuestion2 = securityQuestion2;
        this.securityQuestionAnswer1 =securityQuestionAnswer1;
        this.securityQuestionAnswer2 =securityQuestionAnswer2;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSecurityQuestion1() {
        return securityQuestion1;
    }

    public String getSecurityQuestion2() {
        return securityQuestion2;
    }

    public String getSecurityQuestionAnswer1() {
        return securityQuestionAnswer1;
    }
    public String getSecurityQuestionAnswer2() {
        return securityQuestionAnswer2;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole(){
        return role;
    }

    @Override
    public String toString() {
        return "Name=" + name + " , Username=" + username + " , Password=" + password + " , Email=" + email
                + " , SecurityQuestion1=" + securityQuestion1 + " , SecurityQuestion2=" + securityQuestion2+" , Role="+role;
    }
}
