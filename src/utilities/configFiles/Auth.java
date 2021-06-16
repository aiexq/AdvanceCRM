package utilities.configFiles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {

    public static boolean validate(String login, char[] password){
        JTable table1 = new JTable();
        DBHandler.openConnection();
        ResultSet resultSet;



        resultSet = DBHandler.execQuery("SELECT count(*) from executors where login = '"+login+"' and password = '"+ String.valueOf(password) + "'  ");



        try {
            while (resultSet.next()){
                int count = resultSet.getInt(1);

                if (count > 0 & count < 2){
                    return true;
                }
                else {
                    return false;
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }


        return false;
    }


}
