package docs;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xwpf.usermodel.*;
import utilities.configFiles.DBHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Generate_Act {



    public static void newAct(int numDoc) throws IOException {

        DBHandler.openConnection();



        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("src/docs/outputAct/Смета по заказу №"+ numDoc +".docx"));

        XWPFParagraph paragraph = document.createParagraph();

        XWPFRun title = paragraph.createRun();
        title.setText("Акт оказания услуг №"+numDoc);

        XWPFRun entry = paragraph.createRun();
        entry.setText("Общество с ограниченной ответственностью ..., именуемое в дальнейшем «Заказчик», в лице " +
                "Генерального директора ... и ..., именуемый в дальнейшем" +
                " «Исполнитель», с другой стороны, именуемые в дальнейшем «Стороны», составили настоящий Акт оказания услуг " +
                "о нижеследующем:");

        XWPFRun executorData = paragraph.createRun();
        executorData.setText("");

        XWPFRun clientData = paragraph.createRun();
        clientData.setText("");

        document.write(out);
        out.close();


        DBHandler.execQuery("UPDATE `prequest` " +
                "set `actpath` = 'src/docs/outputAct/Смета по заказу №"+ numDoc +".docx' " +
                "where `id` = "+ numDoc+"");
        DBHandler.execQuery("UPDATE `prequest` " +
                "set changeDate = null " +
                "where `id` = "+ numDoc+"");
        DBHandler.closeConnection();



    }
}
