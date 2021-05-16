package utilities.tables;


import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTable {
    public static void refreshTableUsers(JTable table){
        DBHandler.openConnection();
        ResultSet resultSet;
        resultSet = DBHandler.execQuery("SELECT id, name, email, mobile from user");
        setTable(resultSet,table);
    }
    private static void setTable(ResultSet resultSet, JTable table){

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Имя", "Почта", "Телефон"});

        try {
            while (resultSet.next()){
                model.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setModel(model);

        DBHandler.closeConnection();
    }

}
