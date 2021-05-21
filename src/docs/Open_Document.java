package docs;

import java.io.IOException;

public class Open_Document {
    public static void openDoc(String docpath) throws IOException, InterruptedException {

        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:/Users/Admin/IdeaProjects/democrm_admin/" + docpath);
        p.waitFor();
        System.out.println("Done!");
    }
}