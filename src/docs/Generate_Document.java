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


public class Generate_Document {



    public static void newDoc(int numDoc) throws IOException {

        DBHandler.openConnection();



        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("src/docs/outputDoc/Акт оказания услуг №"+ numDoc +".docx"));

        XWPFParagraph paragraph = document.createParagraph();

        XWPFRun title = paragraph.createRun();
        title.setText("Акт оказания услуг №"+numDoc);

        XWPFRun entry = paragraph.createRun();
        entry.setText("Общество с ограниченной ответственностью «ММИР.ПРО», именуемое в дальнейшем «Заказчик», в лице " +
                "Генерального директора Салихова Артема Викторовича и Савин Михаил Александрович, именуемый в дальнейшем" +
                " «Исполнитель», с другой стороны, именуемые в дальнейшем «Стороны», составили настоящий Акт оказания услуг " +
                "о нижеследующем:");

        XWPFRun executorData = paragraph.createRun();
        executorData.setText("");

        XWPFRun clientData = paragraph.createRun();
        clientData.setText("");

        document.write(out);
        out.close();


        DBHandler.execQuery("UPDATE `prequest` " +
                "set `docpath` = 'src/docs/outputDoc/Акт оказания услуг №"+ numDoc +".docx' " +
                "where `id` = "+ numDoc+"");
        DBHandler.closeConnection();



    }
}
