
package irrgarten;

public class TestP4 {
    public static void main(String[] args) {
        
        Player jugador = new Player('1', 10, 10);
        Monster monstruo = new Monster("Monstruo 1", 10, 10);
        Weapon arma = new Weapon(20, 10);
        Shield escudo = new Shield(20, 10);
        
        System.out.println(jugador.toString());
        System.out.println(monstruo.toString());
        System.out.println(arma.toString());
        System.out.println(escudo.toString());
        
        jugador.receiveReward();
        System.out.println(jugador.toString());
        jugador.receiveReward();
        jugador.receiveReward();
        jugador.receiveReward();
        jugador.receiveReward();
        jugador.receiveReward();
        jugador.receiveReward();
        jugador.receiveReward();
        System.out.println(jugador.toString());
    }
}
