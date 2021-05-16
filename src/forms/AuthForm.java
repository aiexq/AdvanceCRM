package forms;

import utilities.configFiles.Auth;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


                if(Auth.validate(loginField1.getText(), passwordField.getPassword())){
                    TaskFrom taskFrom = new TaskFrom(loginField1.getText());
                    taskFrom.setVisible(true);
                    taskFrom.pack();

                    dispose();
                }
            }
        });

    }


}
