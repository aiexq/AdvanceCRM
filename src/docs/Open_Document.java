package docs;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;

public class Open_Document {
    public static void openDoc(String docpath) throws IOException, InterruptedException {

        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:/Users/Admin/IdeaProjects/democrm/" + docpath);
        p.waitFor();
        System.out.println("Done!");
    }
}
