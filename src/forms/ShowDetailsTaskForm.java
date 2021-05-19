package forms;

import docs.Generate_Document;
import utilities.configFiles.FormConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShowDetailsTaskForm extends JFrame{
    private JPanel panel1;
    private JButton createDocBtn;
    private JButton выйтиButton;

    public ShowDetailsTaskForm(int prequest){
        setContentPane(panel1);
        FormConfig.setParams(this, "Детали заказа", 400, 250, WindowConstants.DISPOSE_ON_CLOSE);



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
    }

}
