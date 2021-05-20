package forms;

import docs.Generate_Document;
import docs.Open_Document;
import docs.tables.TeamOnTaskTable;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utilities.configFiles.FormConfig;
import utilities.tables.ActiveTaskTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShowDetailsTaskForm extends JFrame{
    private JPanel panel1;
    private JButton createDocBtn;
    private JButton exitBtn;
    private JLabel clienNameLabel;
    private JLabel emailClientLabel;
    private JLabel phoneClientLabel;
    private JLabel companyClirentLabel;
    private JButton openTeamBtn;
    private JButton openDocBtn;
    private JLabel nullTeam;
    private JLabel nullDoc;

    public ShowDetailsTaskForm(int prequest, String name, String email, String company, String phone, String docpaht, int team){
        setContentPane(panel1);
        FormConfig.setParams(this, "Детали заказа", 400, 400, WindowConstants.DISPOSE_ON_CLOSE);



        createDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Generate_Document.newDoc(prequest);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        clienNameLabel.setText(name);
        emailClientLabel.setText(email);
        phoneClientLabel.setText(phone);
        companyClirentLabel.setText(company);

        if (docpaht.equals("")){
            nullDoc.setVisible(true);
            openDocBtn.setVisible(false);
        }
        else {
            createDocBtn.setVisible(false);
        }

        openDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Open_Document.openDoc(docpaht);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });



    }

}
