package ExecutorsForms;

import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecutorTasksForm extends JFrame{
    private JPanel panel1;
    private JTable ExecutorsTasksTable;
    private JButton exitBtn;


    public ExecutorTasksForm(int id){
        setContentPane(panel1);
        FormConfig.setParams(this, "Title", 800, 500, WindowConstants.DISPOSE_ON_CLOSE);

        DefaultTableModel executorsTasksModel = new DefaultTableModel();

        executorsTasksModel.setColumnIdentifiers(new String[]{"id", "email", "login"});

        DBHandler.openConnection();

        ResultSet resultSet = DBHandler.execQuery("SELECT id, name, company from prequest where executor_id = " + id);
        try {
            while(resultSet.next()){
                executorsTasksModel.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)

                });
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DBHandler.closeConnection();
        ExecutorsTasksTable.setModel(executorsTasksModel);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}
