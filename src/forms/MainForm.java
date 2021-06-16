package forms;

import createTeam.ChosePmForm;
import docs.Generate_Doc;
import executorsForms.CreateExecutor;
import forms.prequestsForms.ShowDetailsNewTaskForm;
import forms.prequestsForms.ShowDetailsTaskForm;
import forms.prequestsForms.ShowDetailsFinalTaskForm;
import org.apache.poi.ss.formula.ptg.AreaI;
import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;
import utilities.tables.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class MainForm extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTabbedPane tabbedPane1;
    private JTable table2;
    private JTable table3;
    private JButton createExecutorButton;
    private JButton editExecutorBtn;
    private JButton showDetailsClientBtn;
    private JButton showDetailsTaskBtn;
    private JButton createTeamBtn;
    private JTable table5;
    private JButton checkTaskBtn;
    private JButton deletePrequest;
    private JTable table6;
    private JButton showDetailsFinalTaskBtn;
    private JButton endPrequestBtn;
    private JPanel executorsPane;
    private JPanel clientsPane;
    private JPanel newPrequestsPane;
    private JPanel activePrequestsPane;
    private JPanel finalPrequestsPane;

    public MainForm(String login, int isManager,  int isAdmin, int id){
        setContentPane(panel1);
        FormConfig.setParams(this, "Главная", 800, 500, WindowConstants.EXIT_ON_CLOSE);

        ActiveTaskTable.refreshTableActiveTasks(table1);
        ExecutorsTable.refreshTableExecutors(table2);
        UsersTable.refreshTableUsers(table3);
        NewTaskTable.refreshTableNewTasks(table5);
        FinalTaskTable.refreshTableFinalTasks(table6);

        System.out.println(login);
        System.out.println(isManager);
        System.out.println(isAdmin);
        System.out.println(id);



        if (isManager == 0 & isAdmin == 0){

            createExecutorButton.setVisible(false);
            editExecutorBtn.setVisible(false);
            showDetailsClientBtn.setVisible(false);
            createTeamBtn.setVisible(false);
            deletePrequest.setVisible(false);
            endPrequestBtn.setVisible(false);

            ActiveTaskTable.refreshTableActiveTasksForExecutor(table1, id);

        }

        if(isManager == 1 & isAdmin == 0){
            ActiveTaskTable.refreshTableActiveTasks(table1, id);
        }

        if(isManager >=0 & isAdmin == 0){
            FinalTaskTable.refreshTableFinalTasksForExecutor(table6, id);
        }

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
                int selectedRow = table5.getSelectedRow();
                if(selectedRow>=0){

                    ChosePmForm chosePmForm = new ChosePmForm(
                            table1,//Таблица активных заказов
                            table5,//таблица новых заказов
                            Integer.parseInt(table5.getValueAt(selectedRow,0).toString())
                    );
                    chosePmForm.setVisible(true);
                    chosePmForm.pack();


//

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
                    try {
                        showDetailsTaskForm = new ShowDetailsTaskForm(table1,
                                Integer.parseInt(table1.getValueAt(selectedRow,0).toString()),
                                isAdmin, isManager);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
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



        endPrequestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if(selectedRow>=0){

                    try {
                        Generate_Doc.newDoc(Integer.parseInt(table1.getValueAt(selectedRow,0).toString()));
                        FinalTaskTable.refreshTableFinalTasks(table6);
                        ActiveTaskTable.refreshTableActiveTasks(table1);

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


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



        deletePrequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table5.getSelectedRow();
                if (selectedRow>=0){
                    DBHandler.openConnection();
                    DBHandler.execQuery("DELETE from prequest where id = "+ Integer.parseInt(table5.getValueAt(selectedRow, 0).toString()));
                    DBHandler.closeConnection();
                    NewTaskTable.refreshTableNewTasks(table5);
                }else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Необходимо выбрать строку",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        showDetailsFinalTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table6.getSelectedRow();
                if (selectedRow >= 0){
                    ShowDetailsFinalTaskForm showDetailsFinalTaskForm = null;
                    try {
                        showDetailsFinalTaskForm = new ShowDetailsFinalTaskForm(Integer.parseInt(table6.getValueAt(selectedRow, 0).toString()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    showDetailsFinalTaskForm.setVisible(true);
                    showDetailsFinalTaskForm.pack();
                }



            }
        });

        checkTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table5.getSelectedRow();
                if (selectedRow>=0){
                    try {
                        ShowDetailsNewTaskForm showDetailsNewTaskForm = new ShowDetailsNewTaskForm(Integer.parseInt(table5.getValueAt(selectedRow, 0).toString()));
                        showDetailsNewTaskForm.setVisible(true);
                        showDetailsNewTaskForm.pack();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

            }
        });
        
        

    }

}
