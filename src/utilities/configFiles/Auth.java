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


        resultSet = DBHandler.execQuery("SELECT id, name, password from admin where name = '"
                + login + "' and password = '" + String.valueOf(password) + "'");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Логин", "Пароль"});

        try {
            while (resultSet.next()){
                model.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table1.setModel(model);

        return table1.getRowCount() > 0 && table1.getRowCount() < 2;



    }


}
