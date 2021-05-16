package utilities.tables;

import utilities.configFiles.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.ref.PhantomReference;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesTable {
    private static void setTable(ResultSet resultSet, JTable table){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"id", "Наименование", "Стоимость в час"});

        try{
            while (resultSet.next()){
                model.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                });
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        table.setModel(model);
        DBHandler.closeConnection();

    }

    public static void refreshServiceTable(JTable table){
        DBHandler.openConnection();
        ResultSet resultSet;

        resultSet = DBHandler.execQuery("SELECT * FROM services");
        setTable(resultSet, table);

    }
}
