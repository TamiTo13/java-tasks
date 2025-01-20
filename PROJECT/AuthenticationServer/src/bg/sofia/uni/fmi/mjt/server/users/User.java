package bg.sofia.uni.fmi.mjt.server.users;

import bg.sofia.uni.fmi.mjt.server.executor.parser.password.PasswordManager;

import java.io.Serializable;

public class User implements Serializable {
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    private static final int FIRST_NAME = 2;
    private static final int LAST_NAME = 3;
    private static final int EMAIL = 4;
    private static final int STATUS = 5;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean adminStatus;

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        adminStatus = false;
    }

    public User(String username, String password, String firstName, String lastName, String email, boolean status) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        adminStatus = status;
    }

    public static User of(String line) {
        String[] components = line.split(";");
        return new User(components[USERNAME] , components[PASSWORD], components[FIRST_NAME],
                        components[LAST_NAME], components[EMAIL], components[STATUS].equals("true"));
    }

    public static String formatUser(User user) {
        return user.getUsername() + ";" +
                PasswordManager.hashPassword(user.getPassword()) + ";" +
                user.getFirstName() + ";" +
                user.getLastName() + ";" +
                user.getEmail() + ";" +
                user.isAdmin();
    }

    public boolean isAdmin() {
        return adminStatus;
    }

    public void makeAdmin() {
        adminStatus = true;
    }

    public void removeAsAdmin() {
        adminStatus = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
