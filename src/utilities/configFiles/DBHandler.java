package utilities.configFiles;

import utilities.configFiles.Configuration;

import java.sql.*;

public class DBHandler {
    private static Connection connection;

    public static boolean openConnection(){
        try{
            connection = DriverManager.getConnection(
                    Configuration.DBURL,
                    Configuration.DBUSER,
                    Configuration.DBPASSWORD
            );
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean closeConnection(){
        try{
            connection.close();
            return true;
        } catch (SQLException throwables){
            throwables.printStackTrace();
            return false;
        }

    }

    public static ResultSet execQuery(String query){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(query);

            if(query.contains("SELECT"))
                resultSet = preparedStatement.executeQuery();
            else
                preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
