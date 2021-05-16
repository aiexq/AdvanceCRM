package ExecutorsForms;

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

    public CreateExecutor(JTable table){
        setContentPane(panel1);
        FormConfig.setParams(this, "Создать пользователя", 800, 500, DISPOSE_ON_CLOSE);

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHandler.openConnection();
                DBHandler.execQuery("INSERT INTO executors (id, login, f_name, l_name, competence, password) " +
                        "values (null, '"+ loginTextField.getText() +"', '"+fnameTextField.getText()+"', '"+lnameTextField.getText()+"', '"+skillsTextField.getText()+"', '"+passwordTextField.getText()+"')");
                ExecutorsTable.refreshTableExecutors(table);
                DBHandler.closeConnection();
                dispose();
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
