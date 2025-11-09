
package Main;

import irrgarten.Game;
import UI.TextUI;
import controller.Controller;

public class Main {
    public static void main(String[] args) {
        
        Game juego = new Game(2, true);
        TextUI vista = new TextUI();
        Controller controlador = new Controller(juego, vista);
        
        controlador.play();
    }
}
