package org.chat.hal2023;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to connect to the database.
 *  - URL: jdbc:sqlserver://chatbotuser.database.windows.net:1433;database=Chatbot_userCred;encrypt=true;trustServerCertificate=false;loginTimeout=30;
 *  - USER: groepC
 *  - PASSWORD: groepjeC2024 (Password for groepC)
 *  - Driver: com.microsoft.sqlserver.jdbc.SQLServerDriver
 *
 */
public class DataBaseUtils {

    private static final String URL = "jdbc:sqlserver://chatbotuser.database.windows.net:1433;database=Chatbot_userCred;encrypt=true;trustServerCertificate=false;loginTimeout=30;";
    private static final String USER = "groepC";
    private static final String PASSWORD = "groepjeC2024";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
