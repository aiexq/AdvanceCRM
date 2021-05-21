package utilities.tables;


import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActiveTaskTable {
    public static void refreshTableActiveTasks(JTable table){
        DBHandler.openConnection();
        ResultSet resultSet;
        resultSet = DBHandler.execQuery("SELECT id, name, email, company, contactno, posting_date, docpath, team_id from prequest where team_id is not null");
        setTable(resultSet,table);
    }
    private static void setTable(ResultSet resultSet, JTable table){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Имя заказчика", "e-mail заказчика", "Название компании заказчика",
                "Номер заказчика", "Дата создания заказа", "Документ", "Команда"});

        try {
            while (resultSet.next()){
                model.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setModel(model);

        DBHandler.closeConnection();
    }

}