
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

    public Game(int nplayers, boolean debug){        
        //Creamos los jugadores
        for(int i = 0; i < nplayers; i++){
            players.add(new Player(Integer.toString(i).charAt(0), Dice.randomIntelligence(), Dice.randomStrength())); //Hay que convertir el i a char
        }
        
        currentPlayer = players.get(0);
        
        //Creamos el laberinto, lo configuramos y repartimos los jugadores
        labyrinth = new Labyrinth(10, 10, 5, 5);
        
        //Por si queremos modo debug o no
        if(!debug){
            this.configureLabyrinth();
        } else{
            this.configureLabyrinthDebug();
        }

        labyrinth.spreadPlayers(players);
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        log = "";
        boolean dead = currentPlayer.dead();
        Directions direction;
        Monster monster;
        GameCharacter winner;
        boolean endGame;
        
        if(!dead){
            direction = actualDirection(preferredDirection);
            
            if(direction != preferredDirection){
                logPlayerNoOrders();
            }
            
            monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null){
                logNoMonster();
            } else{
                winner = combat(monster);
                manageReward(winner);
            }
        } else{
            manageResurrection();
        }
        
        endGame = finished();
        
        if(!endGame){
            nextPlayer();
        }
        
        return endGame;
    }
    
    public GameState getGameState(){    
        return new GameState (labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(),  log);
    }
    
    private void configureLabyrinth(){
        labyrinth.addBlock(Orientation.VERTICAL, 1, 2, 3);
        labyrinth.addBlock(Orientation.HORIZONTAL, 3, 3, 4);
        labyrinth.addBlock(Orientation.VERTICAL, 4, 3, 4);
        labyrinth.addBlock(Orientation.VERTICAL, 3, 8, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 2, 5, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 8, 5, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 7, 2, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 6, 5, 1);

        
        //Añade monstruos al laberinto en posiciones aleatorias
        Monster monstruo1 = new Monster("monstruo1", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(monstruo1);
        labyrinth.addMonster(Dice.randomPos(labyrinth.getnRows()), Dice.randomPos(labyrinth.getnCols()), monstruo1);
        Monster monstruo2 = new Monster("monstruo2", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(monstruo2);
        labyrinth.addMonster(Dice.randomPos(labyrinth.getnRows()), Dice.randomPos(labyrinth.getnCols()), monstruo2); 
    }
    
        private void configureLabyrinthDebug(){   
        //Añade monstruos al laberinto
        Monster monstruo1 = new Monster("monstruo1", 1000, 1000);
        monsters.add(monstruo1);
        labyrinth.addMonster(5, 4, monstruo1);
        Monster monstruo2 = new Monster("monstruo2", 1000, 1000);
        monsters.add(monstruo2);
        labyrinth.addMonster(5, 7, monstruo2); 
    }
    
    private void nextPlayer() {
        // Operador cíclico
        currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();

        currentPlayer = players.get(this.currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }
    
    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        float  monsterAttack;
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && rounds < MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            rounds++;
            monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if(!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        logRounds(rounds, MAX_ROUNDS);
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        } else{
            logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        
        if(resurrect){
            FuzzyPlayer fuzzyPlayer = new FuzzyPlayer(currentPlayer);
            fuzzyPlayer.resurrect();
            currentPlayer = fuzzyPlayer;
            players.set(currentPlayerIndex, fuzzyPlayer);
            labyrinth.setFuzzyPlayer(fuzzyPlayer);
            logResurrected();
        } else{
            logPlayerSkipTurn();
        }
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
        log += "El jugador se ha desplazado a una celda vacia.\n";
    }
                
    private void logRounds(int rounds, int max){
        log += "Se han producido " + rounds + "/" + max + "rondas de combate.\n";
    }
}
