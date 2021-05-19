import forms.AuthForm;
import forms.MainForm;

public class Application {
    public static void main(String[] args) {
        AuthForm authForm = new AuthForm();
        authForm.setVisible(true);
        authForm.pack();


    }
}
