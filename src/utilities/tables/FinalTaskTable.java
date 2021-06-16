package utilities.tables;

import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinalTaskTable {
    public static void refreshTableFinalTasks(JTable table){
        DBHandler.openConnection();
        ResultSet resultSet;
        resultSet = DBHandler.execQuery("SELECT id, name, email, company, contactno, posting_date, docpath, actpath, inProcess from prequest where inProcess is not null and status = 'Завершен'");
        setTable(resultSet,table);
    }

    public static void refreshTableFinalTasksForExecutor(JTable table, int id){
        DBHandler.openConnection();
        ResultSet resultSet;
        resultSet = DBHandler.execQuery("SELECT DISTINCT prequest.id, prequest.name, prequest.email, prequest.contactno, prequest.company, prequest.posting_date, executors.id FROM prequest, executors, teams where teams.prequest_id = prequest.id and teams.executor_id = executors.id and status = 'Завершен' and teams.executor_id = " + id );
        setTable(resultSet,table);
    }

    private static void setTable(ResultSet resultSet, JTable table){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Имя заказчика", "e-mail заказчика", "Название компании заказчика",
                "Номер заказчика", "Дата создания заказа"});

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
