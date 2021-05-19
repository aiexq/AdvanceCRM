package createTeam;

import createTeam.tables.ExecutorsDevTable;
import createTeam.tables.ExecutorsPmTable;
import executorsForms.ExecutorTasksForm;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoseDevForm extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JButton choseBtn;

    public ChoseDevForm(int prequest, int projectManager){
        setContentPane(panel1);
        FormConfig.setParams(this, "Выбор разработчика", 350, 200, WindowConstants.DISPOSE_ON_CLOSE);

        ExecutorsDevTable.refreshTableExecutorsDev(table1);

        choseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if(selectedRow>=0){
                    ChoseDesignerForm choseDesignerForm = new ChoseDesignerForm(prequest, projectManager,
                            Integer.parseInt(table1.getValueAt(selectedRow,0).toString()));
                    choseDesignerForm.setVisible(true);
                    choseDesignerForm.pack();
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
