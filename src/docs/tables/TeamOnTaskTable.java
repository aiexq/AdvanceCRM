package docs.tables;

import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamOnTaskTable {
    public static void refreshTableETeamOnTask(JTable table, int prequest_id){
        DBHandler.openConnection();
        ResultSet resultSet;

        resultSet = DBHandler.execQuery("SELECT team_id from prequest where id = "+ prequest_id);

        setTable(resultSet,table);
    }
    private static void setTable(ResultSet resultSet, JTable table){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"team_id"});

        try {
            while (resultSet.next()){
                model.addRow(new String[]{
                        resultSet.getString(1)
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setModel(model);

        DBHandler.closeConnection();
    }
}
