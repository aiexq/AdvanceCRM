package createTeam.tables;

import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecutorsPmTable {
    public static void refreshTableExecutorsPm(JTable table){
        DBHandler.openConnection();
        ResultSet resultSet;

        resultSet = DBHandler.execQuery("SELECT id, login, f_name, l_name, competence from executors where isManager = 1");

        setTable(resultSet,table);
    }
    private static void setTable(ResultSet resultSet, JTable table){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Логин исполнителя", "Фамилия", "Имя", "Компетенции"});

        try {
            while (resultSet.next()){
                model.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setModel(model);

        DBHandler.closeConnection();
    }
}
