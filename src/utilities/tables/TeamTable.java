package utilities.tables;

import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamTable {
    //SELECT executors.f_name, executors.l_name, executors.competence, executors.isManager, teams.role FROM teams, prequest, executors WHERE prequest.id = prequest_id and executors.id = executor_id and prequest_id = 50
    public static void refreshTableTeam(JTable table, int prequest){
        DBHandler.openConnection();
        ResultSet resultSet;
        resultSet = DBHandler.execQuery("SELECT executors.f_name, executors.l_name, executors.competence, teams.role FROM teams, prequest, executors WHERE prequest.id = prequest_id and executors.id = executor_id and prequest_id = "+ prequest);
        setTable(resultSet,table);
    }
    private static void setTable(ResultSet resultSet, JTable table){

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Фамилия", "Имя", "Компетенция", "Роль"});

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
