package createTeam;

import createTeam.tables.ExecutorsDesignTable;
import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoseDesignerForm extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JButton button1;

    public ChoseDesignerForm(JTable tableActive, JTable tableNew, int prequest, int projectManager, int developer){
        setContentPane(panel1);
        FormConfig.setParams(this, "Выбор дизайнера", 350, 200, WindowConstants.DISPOSE_ON_CLOSE);
        ExecutorsDesignTable.refreshTableExecutorsDesign(table1);



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if(selectedRow>=0){
                    AcceptTeamForm acceptTeamForm = new AcceptTeamForm(
                            tableActive,
                            tableNew,
                            prequest,
                            projectManager,
                            developer,
                            Integer.parseInt(table1.getValueAt(selectedRow,0).toString())
                    );
                    acceptTeamForm.setVisible(true);
                    acceptTeamForm.pack();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Необходимо выбрать строку",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

    }

}
