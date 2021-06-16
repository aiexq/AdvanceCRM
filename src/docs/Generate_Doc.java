package docs;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import utilities.configFiles.DBHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Generate_Doc {
    public static void newDoc(int numDoc) throws IOException {

        DBHandler.openConnection();



        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("src/docs/outputDoc/Акт об оказанных услугах по заказу №"+ numDoc +".docx"));

        XWPFParagraph paragraph = document.createParagraph();

        XWPFRun title = paragraph.createRun();
        title.setText("Акт оказанных услуг №"+numDoc);
        String textDoc = "Общество с ограниченной\n ответственностью";
        System.out.println(textDoc);
        XWPFRun entry = paragraph.createRun();
        entry.setText("Общество с ограниченной ответственностью ...., именуемое в дальнейшем «Заказчик», в лице " +
                "Генерального директора ..... и ...., именуемый в дальнейшем" +
                " «Исполнитель», с другой стороны, именуемые в дальнейшем «Стороны», составили настоящий Акт об оказанных услугах " +
                "о нижеследующем:");
        entry.setText(textDoc);
        XWPFRun executorData = paragraph.createRun();
        executorData.setText("");

        XWPFRun clientData = paragraph.createRun();
        clientData.setText("");

        document.write(out);
        out.close();


        DBHandler.execQuery("UPDATE `prequest` " +
                "set `docpath` = 'src/docs/outputDoc/Акт об оказанных услугах по заказу №"+ numDoc +".docx' " +
                "where `id` = "+ numDoc+"");
        DBHandler.execQuery("UPDATE `prequest` " +
                "set `status` = 'Завершен' " +
                "where `id` = "+ numDoc+"");
        DBHandler.execQuery("UPDATE `prequest` " +
                "set changeDate = null " +
                "where `id` = "+ numDoc+"");
        DBHandler.closeConnection();



    }
}
