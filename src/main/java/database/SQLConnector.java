package database;

import java.sql.*;

public class SQLConnector {

    public static String[] query(String valueType) {
        Connection connection = connectDB();
        Statement stmt;
        String query = "select * from registration_invalid where valuetype =\"" + valueType + "\";";
        String[] result = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                result = new String[]{rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getString("re-enter password")};
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return result;
    }

    private static Connection connectDB() {
        String dbUrl = "jdbc:mysql://localhost:3306/tam";
        String username = "root";
        String password = "123456";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            if (connection != null) {
                System.out.println("Connected to the database test1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
