
package irrgarten;

import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Labyrinth labyrinth;
    private Player currentPlayer;

    public Game(int nplayers){        
        //Creamos los jugadores
        for(int i = 0; i < nplayers; i++){
            players.add(new Player((char)(i+1), Dice.randomIntelligence(), Dice.randomStrength()));
        }
        
        //Creamos el laberinto, lo configuramos y repartimos los jugadores
        labyrinth = new Labyrinth(10, 10, 5, 5);
        configureLabyrinth();
        //labyrinth.spreadPlayers(players);
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirecrion){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState(){    
        return new GameState (labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(),  log);
    }
    
    private void configureLabyrinth(){
        //labyrinth.addBlock(Orientation.HORIZONTAL, 0, 0, 10);
        
        //Añade monstruos al laberinto en posiciones aleatorias
        Monster monstruo1 = new Monster("monstruo1", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(monstruo1);
        labyrinth.addMonster(Dice.randomPos(labyrinth.getnRows()), Dice.randomPos(labyrinth.getnCols()), monstruo1);
        Monster monstruo2 = new Monster("monstruo1", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(monstruo2);
        labyrinth.addMonster(Dice.randomPos(labyrinth.getnRows()), Dice.randomPos(labyrinth.getnCols()), monstruo2); 
    }
    
    private void nextPlayer() {

    }
    
    private Directions actualDirection(Directions preferredDirection){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection(){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon(){
        log += "El jugador ha ganado el combate.\n";
    }
    
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate.\n";
    }
    
    private void logResurrected(){
        log += "El jugador ha resucitado.\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "El jugador ha perdido el turno por estar muerto.\n";
    }
    
    private void logPlayerNoOrders(){
        log += "No es posible realizar esa acción.\n";
    }
    
    private void logNoMonster(){
        log += "El jugador se ha desplazado a una celda vacía o no ha sido posible desplazarse.\n";
    }
                
    private void logRounds(int rounds, int max){
        log += "Se han producido " + rounds + "/" + max + "rondas de combate.\n";
    }
}
