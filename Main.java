import Model.XadrezFacade;
import Controller.Control;

public class Main {
    public static void main(String[] args) {
        try {
            Control control = Control.getController();
            XadrezFacade facade = new XadrezFacade(control);
            facade.iniciarJogo();
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}