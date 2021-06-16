package createTeam;

import createTeam.tables.ExecutorsPmTable;
import executorsForms.ExecutorTasksForm;
import utilities.configFiles.DBHandler;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChosePmForm extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JButton choseBtn;

    public ChosePmForm(JTable tableActive, JTable tableNew, int prequest){
        setContentPane(panel1);
        FormConfig.setParams(this, "Выбор менеджера", 350, 350, WindowConstants.DISPOSE_ON_CLOSE);
        ExecutorsPmTable.refreshTableExecutorsPm(table1);



        choseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table1.getSelectedRow();
                if(selectedRow>=0){

                    ChoseDevForm choseDevForm = new ChoseDevForm(
                            tableActive,
                            tableNew,
                            prequest,
                            Integer.parseInt(table1.getValueAt(selectedRow,0).toString())
                    );
                    choseDevForm.setVisible(true);
                    choseDevForm.pack();
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
