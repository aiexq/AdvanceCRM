package utilities.tables;

import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TakeTaskTable {
    public static void refreshTableTasks(JTable table, int limit){
        DBHandler.openConnection();
        ResultSet resultSet;
        if (limit>0)
            resultSet = DBHandler.execQuery("SELECT id, name, email, company, contactno, posting_date from prequest"+ limit);
        else
            resultSet = DBHandler.execQuery("SELECT id, name, email, company, contactno, posting_date from prequest");
        setTable(resultSet,table);
    }
    private static void setTable(ResultSet resultSet, JTable table){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Имя заказчика", "e-mail заказчика", "Название компании заказчика", "Номер заказчика", "Дата создания заказа"});

        try {
            while (resultSet.next()){
                model.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setModel(model);

        DBHandler.closeConnection();
    }
}
