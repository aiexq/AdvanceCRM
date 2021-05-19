package executorsForms;

import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;
import utilities.tables.ExecutorsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateExecutor extends JFrame{
    private JTextField loginTextField;
    private JPanel panel1;
    private JTextField fnameTextField;
    private JTextField lnameTextField;
    private JTextField skillsTextField;
    private JTextField passwordTextField;
    private JButton exitBtn;
    private JButton createBtn;
    private JCheckBox managerCheck;
    private JButton testBtn;

    public CreateExecutor(JTable table){
        setContentPane(panel1);
        FormConfig.setParams(this, "Создать пользователя", 800, 500, DISPOSE_ON_CLOSE);

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHandler.openConnection();

                if (managerCheck.isSelected()){
                    DBHandler.execQuery("INSERT INTO executors (id, login, f_name, l_name, competence, password, isManager) values(null, '"+ loginTextField.getText() +"', '"+fnameTextField.getText()+"', '"+lnameTextField.getText()+"', '"+skillsTextField.getText()+"', '"+passwordTextField.getText()+"', 'True')");
                    ExecutorsTable.refreshTableExecutors(table);
                }
                else{
                    DBHandler.execQuery("INSERT INTO executors (id, login, f_name, l_name, competence, password) " +
                            "values (null, '"+ loginTextField.getText() +"', '"+fnameTextField.getText()+"', '"+lnameTextField.getText()+"', '"+skillsTextField.getText()+"', '"+passwordTextField.getText()+"')");
                    ExecutorsTable.refreshTableExecutors(table);
                }

                DBHandler.closeConnection();
                dispose();
            }
        });

        testBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(managerCheck.isSelected());
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
