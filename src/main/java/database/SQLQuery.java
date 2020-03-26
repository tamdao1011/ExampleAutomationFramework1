package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static database.SQLCommon.closeConnection;
import static database.SQLCommon.connectDB;

public class SQLQuery {

    public static String[] getDataFromRegistrationInvalidTable(String valueType) {
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

}
