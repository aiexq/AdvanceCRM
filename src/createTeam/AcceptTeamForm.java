package createTeam;

import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptTeamForm extends JFrame{
    private JPanel panel1;
    private JButton acceptBtn;
    private JLabel pmLabel;
    private JLabel devLabel;
    private JLabel designLabel;
    private JLabel prequestLabel;

    public AcceptTeamForm(int prequest, int projectManager, int developer, int designer){
        setContentPane(panel1);
        FormConfig.setParams(this, "Утвердить команду", 400, 250, WindowConstants.DISPOSE_ON_CLOSE);

        pmLabel.setText(String.valueOf(projectManager));
        devLabel.setText(String.valueOf(developer));
        designLabel.setText(String.valueOf(designer));
        prequestLabel.setText(String.valueOf(prequest));

        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHandler.openConnection();
                DBHandler.execQuery("INSERT INTO teams (id, pm_id, dev_id, designer_id, prequest_id) " +
                        "values (null, '" +projectManager+ "', '"+developer+"', '"+designer+"', '"+prequest+"')");
                DBHandler.closeConnection();
                dispose();
            }
        });

    }
}
