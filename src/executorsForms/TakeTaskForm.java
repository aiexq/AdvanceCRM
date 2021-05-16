package executorsForms;

import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;
import utilities.tables.TakeTaskTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class TakeTaskForm extends  JFrame{
    private JPanel panel1;
    private JTable tasksTable;
    private JButton takeBtn;
    private JButton exitBtn;

    public TakeTaskForm(int id){
        setContentPane(panel1);
        FormConfig.setParams(this, "Выдать задачу", 800, 500, WindowConstants.DISPOSE_ON_CLOSE);

        DefaultTableModel takeTaskModel = new DefaultTableModel();

        takeTaskModel.setColumnIdentifiers(new String[]{"id", "name", "email", "contctno", "posting_date"});

        DBHandler.openConnection();

        ResultSet resultSet = DBHandler.execQuery("SELECT id, name, email, contactno, posting_date " +
                "from prequest where executor_id is null");
        try {
            while(resultSet.next()){
                takeTaskModel.addRow(new String[]{
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

        DBHandler.closeConnection();

        tasksTable.setModel(takeTaskModel);

        takeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = tasksTable.getSelectedRow();
                if(selectedRow>=0){
                    DBHandler.openConnection();
                    DBHandler.execQuery("UPDATE prequest SET executor_id = "+ id +" where id = "+ Integer.parseInt(tasksTable.getValueAt(selectedRow,0).toString()));
                    TakeTaskTable.refreshTableTasks(tasksTable, 0);
                    DBHandler.closeConnection();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Необходимо выбрать строку",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

                System.out.println(Integer.parseInt(tasksTable.getValueAt(selectedRow,0).toString()));


            }

        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



    }

}
