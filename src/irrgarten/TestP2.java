
package irrgarten;

public class TestP2 {
    public static void main(String[] args){
        Game juego = new Game(2, false);
        GameState estado = juego.getGameState();
        
        System.out.println(estado.getLabyrinth());
        System.out.println(estado.getPlayers());
        System.out.println(estado.getMonsters());
        System.out.println(estado.getCurrentPlayer());
        System.out.println(estado.isWinner());
        System.out.println(estado.getLog());
    }
}
