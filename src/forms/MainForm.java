package forms;

import createTeam.ChoseDevForm;
import createTeam.ChosePmForm;
import executorsForms.CreateExecutor;
import utilities.configFiles.FormConfig;
import utilities.tables.ExecutorsTable;
import utilities.tables.TaskTable;
import utilities.tables.UsersTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    private JButton showDetailsTaskBtn;
    private JButton createTeamBtn;

    public MainForm(String login){
        setContentPane(panel1);
        FormConfig.setParams(this, "Главная", 800, 500, WindowConstants.EXIT_ON_CLOSE);

        TaskTable.refreshTableTasks(table1);
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

        createTeamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if(selectedRow>=0){

                    ChosePmForm chosePmForm = new ChosePmForm(
                            Integer.parseInt(table1.getValueAt(selectedRow,0).toString()));
                    chosePmForm.setVisible(true);
                    chosePmForm.pack();
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Необходимо выбрать строку",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE
                    );
                }



            }
        });

        showDetailsTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if(selectedRow>=0){

                    ShowDetailsTaskForm showDetailsTaskForm = null;
                    showDetailsTaskForm = new ShowDetailsTaskForm(
                            Integer.parseInt(table1.getValueAt(selectedRow,0).toString()),//idprequest
                            table1.getValueAt(selectedRow, 1).toString(),//name
                            table1.getValueAt(selectedRow, 2).toString(),//email
                            table1.getValueAt(selectedRow, 3).toString(),//company
                            table1.getValueAt(selectedRow, 4).toString(),//phone
                            table1.getValueAt(selectedRow, 6).toString(),//docpath
                            Integer.parseInt(table1.getValueAt(selectedRow, 7).toString()));//teamid
                    showDetailsTaskForm.setVisible(true);
                    showDetailsTaskForm.pack();
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Необходимо выбрать строку",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        
        

    }

}
