package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLCommon {

    public static Connection connectDB() {

        // Load properties file
        File file = new File("src/main/java/database/dataFile.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();
        try {
            assert fileInput != null;
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Connect database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("username"), prop.getProperty("password"));
            if (connection != null) {
                System.out.println("Connected to the database test1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
