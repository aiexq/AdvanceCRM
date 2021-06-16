package forms.prequestsForms;

import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDetailsFinalTaskForm extends JFrame{

    private JPanel panel1;
    private JButton openTeamBtn;
    private JButton openActBtn;
    private JButton openDocBtn;
    private JButton exitBtn;
    private JLabel nameClientLabel;
    private JLabel emailClientLabel;
    private JLabel phoneClientLabel;
    private JLabel companyClientLabel;
    private JButton openUploadBtn;
    private JLabel nulUppload;

    public ShowDetailsFinalTaskForm(int prequest) throws SQLException {
        setContentPane(panel1);
        FormConfig.setParams(this, "Информация о заказе", 400, 400, WindowConstants.DISPOSE_ON_CLOSE);

        DBHandler.openConnection();
        ResultSet rs;
        rs = DBHandler.execQuery("SELECT name, email, contactno, company, docpath, actpath, uploadpath from prequest where id = "+prequest+" and status = 'Завершен'");

        while (rs.next()){
            String name = rs.getString(1);
            String email = rs.getString(2);
            String contactno = rs.getString(3);
            String company = rs.getString(4);
            String apath = rs.getString(5);
            String dpath = rs.getString(6);
            String upload = rs.getString(7);

            System.out.println(apath);
            System.out.println(dpath);

            if(upload == null){
                nulUppload.setVisible(true);
                openUploadBtn.setVisible(false);
            }
            else{
                nulUppload.setVisible(false);
                openUploadBtn.setVisible(true);
            }


            nameClientLabel.setText(name);
            phoneClientLabel.setText(contactno);
            companyClientLabel.setText(company);


        }

        DBHandler.closeConnection();



        //nameClientLabel.setText(name);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        openActBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DBHandler.openConnection();
                    ResultSet actrs = DBHandler.execQuery("SELECT actpath from prequest where id = "+ prequest);
                    while (actrs.next()){
                        String actpath = actrs.getString(1);
                        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:/Users/Admin/IdeaProjects/democrm_admin/" + actpath);
                        p.waitFor();
                    }
                    DBHandler.closeConnection();
                } catch (SQLException | InterruptedException | IOException throwables){
                    throwables.printStackTrace();
                }
            }
        });

        openDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DBHandler.openConnection();
                    ResultSet docrs = DBHandler.execQuery("SELECT docpath from prequest where id = "+ prequest);
                    while (docrs.next()){
                        String docpath = docrs.getString(1);
                        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:/Users/Admin/IdeaProjects/democrm_admin/" + docpath);
                        p.waitFor();
                    }
                    DBHandler.closeConnection();
                } catch (SQLException | InterruptedException | IOException throwables){
                    throwables.printStackTrace();
                }
            }
        });

        openUploadBtn.addActionListener(new ActionListener() {
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

    }

}
