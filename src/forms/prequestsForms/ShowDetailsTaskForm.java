package forms.prequestsForms;

import docs.Generate_Act;
import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;
import utilities.tables.ActiveTaskTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public ShowDetailsTaskForm(JTable table, int prequest, int isAdmin, int isManager) throws SQLException {
        setContentPane(panel1);
        FormConfig.setParams(this, "Детали заказа", 400, 400, WindowConstants.DISPOSE_ON_CLOSE);

        if (isManager == 0 & isAdmin == 0){
            createDocBtn.setVisible(false);
        }


        DBHandler.openConnection();

        ResultSet identifiers;

        identifiers = DBHandler.execQuery("SELECT name, email, company, contactno, docpath FROM prequest where id = "+prequest+" and inProcess is not null");



        while (identifiers.next()){
            String name = identifiers.getString(1);
            String email = identifiers.getString(2);
            String company = identifiers.getString(3);
            String phone = identifiers.getString(4);
            String docpaht = identifiers.getString(5);

            clienNameLabel.setText(name);
            emailClientLabel.setText(email);
            phoneClientLabel.setText(phone);
            companyClirentLabel.setText(company);

            if (docpaht != null){
                nullDoc.setVisible(true);
                openDocBtn.setVisible(false);
            }
            else {
                createDocBtn.setVisible(false);
            }
        }

        DBHandler.closeConnection();

        createDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Generate_Act.newAct(prequest);
                    ActiveTaskTable.refreshTableActiveTasks(table);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                createDocBtn.setVisible(false);
                nullDoc.setVisible(false);
                openDocBtn.setVisible(true);
            }


        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        openDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    DBHandler.openConnection();
                    ResultSet rs = DBHandler.execQuery("SELECT actpath from prequest where id = "+ prequest);
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

        openTeamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowTeamOnPrequest showTeamOnPrequest = new ShowTeamOnPrequest(prequest);
                showTeamOnPrequest.setVisible(true);
                showTeamOnPrequest.pack();
            }
        });



    }

}
