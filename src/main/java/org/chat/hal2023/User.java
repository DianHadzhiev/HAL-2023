package org.chat.hal2023;

public class User {

    /**
     * String that holds the username of the user.
     */
    private String username;

    /**
     * String that holds the password of the user.
     */
    private String password;

    /**
     * String that holds the email of the user.
     * Must be in a proper email format.
     */
    private String email;

    private static User instance;

    UserDAO userDAO = new UserDAO();

    private User() {
    }

    public static User getInstance() {
        if ( instance == null ) {
            instance = new User();
        }
        return instance;
    }

    /**
     * Username getter.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Password getter.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Email getter.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to put a new email in the email field of the user.
     * This new email must follow a proper email format.
     * @param email the new email
     * @return  true if the new email is put into the email field.
     *          false if the new email isn't put into the email field.
     */
    public boolean checkNewEmail(String email) {
        if (email == null) {
            return false;
        }
        if (email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            this.email = email;
            return true;
        }
        return false;
    }

    public boolean checkNewPassword(String password) {
        if (password == null) {
            return false;
        }
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean checkNewUsername(String username) {
        if (username == null) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9]+$");
    }

    public void setNewUsername(String text) {
        if (checkNewUsername(text)) {
            userDAO.updateUsername(username, text);
        }
    }

    public void setNewPassword(String text) {
        if (checkNewPassword(text)) {
            userDAO.updatePassword(password, text);
        }
    }

    public void setNewEmail(String text) {
        if (checkNewEmail(text)) {
            userDAO.updateEmail(email, text);
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

}

