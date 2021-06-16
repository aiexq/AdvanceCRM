package createTeam;

import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;
import utilities.tables.ActiveTaskTable;
import utilities.tables.NewTaskTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AcceptTeamForm extends JFrame{
    private JPanel panel1;
    private JButton acceptBtn;
    private JLabel pmLabel;
    private JLabel devLabel;
    private JLabel designLabel;
    private JLabel prequestLabel;

    public AcceptTeamForm(JTable tableActive, JTable tableNew, int prequest, int projectManager, int developer, int designer){
        setContentPane(panel1);
        FormConfig.setParams(this, "Утвердить команду", 400, 400, WindowConstants.DISPOSE_ON_CLOSE);


        pmLabel.setText(String.valueOf(projectManager));
        devLabel.setText(String.valueOf(developer));
        designLabel.setText(String.valueOf(designer));
        prequestLabel.setText(String.valueOf(prequest));

        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DBHandler.openConnection();
                DBHandler.execQuery("INSERT into teams (id, prequest_id, executor_id, role) values (null, + "+ prequest +", "+ projectManager +", 'pm')");
                DBHandler.execQuery("INSERT into teams (id, prequest_id, executor_id, role) values (null, + "+ prequest +", "+ developer +", 'dev')");
                DBHandler.execQuery("INSERT into teams (id, prequest_id, executor_id, role) values (null, + "+ prequest +", "+ designer +", 'des')");
                DBHandler.execQuery("UPDATE prequest set inProcess = " + projectManager + " where id = "+ prequest +"");
                DBHandler.execQuery("UPDATE prequest set status = 'Принят к исполнению' where id = "+ prequest);
                DBHandler.closeConnection();
                dispose();

                ActiveTaskTable.refreshTableActiveTasks(tableActive);
                NewTaskTable.refreshTableNewTasks(tableNew);

            }
        });

    }
}
