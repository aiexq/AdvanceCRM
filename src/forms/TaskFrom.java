package forms;

import executorsForms.CreateExecutor;
import executorsForms.ExecutorTasksForm;
import executorsForms.TakeTaskForm;
import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;
import utilities.tables.ExecutorsTable;
import utilities.tables.ServicesTable;
import utilities.tables.ActiveTaskTable;
import utilities.tables.UsersTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskFrom extends JFrame{
    private JPanel panel;
    private JTable taskTable;
    private JLabel loginLabel;
    private JButton myTaskBtn;
    private JButton addEmplBtn;
    private JButton upgradeTaskBtn;
    private JButton emplTasks;
    private JButton takeTask;
    private JButton closeTask;
    private JButton exitBtn;
    private JLabel userNameLbl;
    private JButton tableTaskVisibleBtn;
    private JButton tableUsersVisibleBtn;
    private JScrollPane scrollTask;
    private JScrollPane scrollUsers;
    private JTable usersTable;
    private JButton tableExecutorsVisibleBtn;
    private JTable executorsTable;
    private JScrollPane scrollExecutors;
    private JButton checkExecutorTasksBtn;
    private JButton takeTaskBtn;
    private JButton updateExecutorBtn;
    private JButton createExecutor;
    private JButton tableServicesVisibleBtn;
    private JTable serviceTable;
    private JScrollPane scrollServices;
    private JButton testBtn;

    public TaskFrom(String login){
        setContentPane(panel);
        FormConfig.setParams(this, "AdvanceCRM", 800, 500, WindowConstants.EXIT_ON_CLOSE);

        //Обновление данных таблицы задач
        ActiveTaskTable.refreshTableActiveTasks(taskTable);
        UsersTable.refreshTableUsers(usersTable);
        ExecutorsTable.refreshTableExecutors(executorsTable);
        ServicesTable.refreshServiceTable(serviceTable);

        scrollTask.setVisible(false);
        scrollUsers.setVisible(false);
        scrollExecutors.setVisible(false);
        scrollServices.setVisible(false);

        //Переключение между таблицами задачи-пользователи
        tableTaskVisibleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                scrollTask.setVisible(true);
                closeTask.setVisible(true);
                upgradeTaskBtn.setVisible(true);
                myTaskBtn.setVisible(true);
                scrollUsers.setVisible(false);
                emplTasks.setVisible(false);
                takeTask.setVisible(false);
                createExecutor.setVisible(false);
                scrollExecutors.setVisible(false);
                checkExecutorTasksBtn.setVisible(false);
                takeTaskBtn.setVisible(false);
                updateExecutorBtn.setVisible(false);
                scrollServices.setVisible(false);
                testBtn.setVisible(false);

            }
        });
        tableUsersVisibleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                scrollUsers.setVisible(true);
                emplTasks.setVisible(true);
                takeTask.setVisible(true);
                closeTask.setVisible(false);
                upgradeTaskBtn.setVisible(false);
                myTaskBtn.setVisible(false);
                scrollTask.setVisible(false);
                createExecutor.setVisible(false);
                scrollExecutors.setVisible(false);
                checkExecutorTasksBtn.setVisible(false);
                takeTaskBtn.setVisible(false);
                updateExecutorBtn.setVisible(false);
                scrollServices.setVisible(false);
                testBtn.setVisible(false);
            }
        });
        tableExecutorsVisibleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                scrollUsers.setVisible(false);
                emplTasks.setVisible(false);
                takeTask.setVisible(false);
                closeTask.setVisible(false);
                upgradeTaskBtn.setVisible(false);
                myTaskBtn.setVisible(false);
                scrollTask.setVisible(false);
                createExecutor.setVisible(true);
                scrollExecutors.setVisible(true);
                checkExecutorTasksBtn.setVisible(true);
                takeTaskBtn.setVisible(true);
                updateExecutorBtn.setVisible(true);
                scrollServices.setVisible(false);
                testBtn.setVisible(false);
            }
        });
        tableServicesVisibleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                scrollTask.setVisible(false);
                closeTask.setVisible(false);
                upgradeTaskBtn.setVisible(false);
                myTaskBtn.setVisible(false);
                scrollUsers.setVisible(false);
                emplTasks.setVisible(false);
                takeTask.setVisible(false);
                createExecutor.setVisible(false);
                scrollExecutors.setVisible(false);
                checkExecutorTasksBtn.setVisible(false);
                takeTaskBtn.setVisible(false);
                updateExecutorBtn.setVisible(false);
                scrollServices.setVisible(true);
                testBtn.setVisible(true);
            }
        });


        takeTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = executorsTable.getSelectedRow();
                if(selectedRow>=0){
                    TakeTaskForm takeTaskForm = new TakeTaskForm(
                            Integer.parseInt(executorsTable.getValueAt(selectedRow,0).toString()));

                    takeTaskForm.setVisible(true);
                    takeTaskForm.pack();
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Необходимо выбрать строку",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
                System.out.println( Integer.parseInt(executorsTable.getValueAt(selectedRow,0).toString()));

            }
        });

        userNameLbl.setText("Вы вошли как: " + login);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        checkExecutorTasksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = executorsTable.getSelectedRow();
                if(selectedRow>=0){
                    ExecutorTasksForm executorTasksForm = new ExecutorTasksForm(
                            Integer.parseInt(executorsTable.getValueAt(selectedRow,0).toString()));

                    executorTasksForm.setVisible(true);
                    executorTasksForm.pack();
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Необходимо выбрать строку",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
                //System.out.println( Integer.parseInt(executorsTable.getValueAt(selectedRow,0).toString()));


            }
        });

        //Завершение задачи
        closeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if(selectedRow>=0){
                    DBHandler.openConnection();
                    DBHandler.execQuery("DELETE FROM prequest where id = '" + Integer.parseInt(taskTable.getValueAt(selectedRow,0).toString())+ "'");
                    ActiveTaskTable.refreshTableActiveTasks(taskTable);
                    DBHandler.closeConnection();
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

        createExecutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateExecutor createExecutor = new CreateExecutor(executorsTable);
                createExecutor.setVisible(true);
                createExecutor.pack();
            }
        });




    }
}
