package forms.prequestsForms;

import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDetailsNewTaskForm extends JFrame {
    private JPanel panel1;
    private JButton exitBtn;
    private JButton openIploadBtn;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel companyLabel;
    private JLabel uploadNul;

    public ShowDetailsNewTaskForm(int prequest) throws SQLException {
        setContentPane(panel1);
        FormConfig.setParams(this, "Просмотр заказа", 400, 400, WindowConstants.DISPOSE_ON_CLOSE);

        DBHandler.openConnection();

        ResultSet resultSet;
        resultSet = DBHandler.execQuery("SELECT name, email, contactno, company, uploadpath from prequest where id = "+prequest+" and inProcess is null");

        while(resultSet.next()){
            String name = resultSet.getString(1);
            String email = resultSet.getString(2);
            String contactno = resultSet.getString(3);
            String company = resultSet.getString(4);
            String uploadpath = resultSet.getString(5);

            if(uploadpath == null){
                uploadNul.setVisible(true);
                openIploadBtn.setVisible(false);
            }
            else{
                uploadNul.setVisible(false);
                openIploadBtn.setVisible(true);
            }

            System.out.println(uploadpath);

            nameLabel.setText(name);
            emailLabel.setText(email);
            phoneLabel.setText(contactno);
            companyLabel.setText(company);

        }
        DBHandler.closeConnection();

        openIploadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DBHandler.openConnection();
                    ResultSet rs = DBHandler.execQuery("SELECT uploadpath from prequest where id = "+ prequest);
                    while (rs.next()){
                        String path = rs.getString(1);
                        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:/Users/Admin/IdeaProjects/democrm_admin/" + path);
                        p.waitFor();
                    }
                    DBHandler.closeConnection();
                } catch (SQLException | InterruptedException | IOException throwables){
                    throwables.printStackTrace();
                }
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
