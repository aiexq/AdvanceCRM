package forms.activePrequestsForms;

import utilities.configFiles.FormConfig;
import utilities.tables.TeamTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowTeamOnPrequest extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JButton button1;

    public ShowTeamOnPrequest(int prequest){
        setContentPane(panel1);
        FormConfig.setParams(this, "Команда заказа", 400, 400, WindowConstants.DISPOSE_ON_CLOSE);

        TeamTable.refreshTableTeam(table1, prequest);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }



}
