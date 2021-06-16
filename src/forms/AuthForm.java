package forms;

import utilities.configFiles.Auth;
import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthForm extends JFrame{
    private JPanel panel1;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton button2;
    private JButton button3;
    public JTextField loginField1;

    public AuthForm(){
        setContentPane(panel1);
        FormConfig.setParams(this, "Авторизация", 350, 500, WindowConstants.EXIT_ON_CLOSE);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DBHandler.openConnection();



                if(Auth.validate(loginField1.getText(), passwordField.getPassword())){

                    ResultSet rules = DBHandler.execQuery("SELECT id, login, isManager, isAdmin from executors where login = '"+ loginField1.getText() +"'");
                    try{
                        while (rules.next()){
                            int authId = rules.getInt(1);
                            String authLogin = rules.getString(2);
                            int isManager = rules.getInt(3);
                            int isAdmin = rules.getInt(4);

                            MainForm mainForm = new MainForm(authLogin, isManager, isAdmin, authId);
                            mainForm.setVisible(true);
                            mainForm.pack();
                            dispose();
                        }
                    }catch (SQLException throwables){
                        throwables.printStackTrace();
                    }
                }
                DBHandler.closeConnection();
            }
        });

    }


}
