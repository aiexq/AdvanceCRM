package forms;

import executorsForms.CreateExecutor;
import utilities.configFiles.FormConfig;
import utilities.tables.ExecutorsTable;
import utilities.tables.TaskTable;
import utilities.tables.UsersTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTabbedPane tabbedPane1;
    private JTable table2;
    private JTable table3;
    private JButton createExecutorButton;
    private JTable table4;
    private JButton создатьКомандуButton;
    private JButton удалитьКомандуButton;
    private JButton посмотретьДанныеButton;
    private JButton редактироватьИсполнителяButton;
    private JButton назначитьВКомандуButton;
    private JButton посмотретьДанныеButton1;
    private JButton посмотретьДеталиButton;

    public MainForm(String login){
        setContentPane(panel1);
        FormConfig.setParams(this, "Главная", 800, 500, WindowConstants.EXIT_ON_CLOSE);

        TaskTable.refreshTableTasks(table1, 0);
        ExecutorsTable.refreshTableExecutors(table2);
        UsersTable.refreshTableUsers(table3);

        createExecutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateExecutor createExecutor = new CreateExecutor(table2);
                createExecutor.setVisible(true);
                createExecutor.pack();
            }
        });
        
        

    }

}
