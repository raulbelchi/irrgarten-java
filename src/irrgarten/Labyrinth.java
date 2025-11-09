
package irrgarten;

import static irrgarten.Directions.LEFT;
import java.util.ArrayList;

public class Labyrinth {
    
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    //Implementación de las clases "square"
    private Monster[][] monsters;
    private Player[][] players;
    private char[][] labyrinth;

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols];
        labyrinth = new char[nRows][nCols];
        
        //Rellenamos el laberinto con carácteres vacíos y los muros exteriores
        for(int i=0; i<nRows; i++){
            for(int j=0; j<nCols; j++){
                if(i == exitRow && j == exitCol){
                    labyrinth[i][j] = EXIT_CHAR;
                } else if(i == 0 || i == (nRows-1) || j == 0 || j == (nCols-1)){
                    labyrinth[i][j] = BLOCK_CHAR;
                } else{
                    labyrinth[i][j] = EMPTY_CHAR;
                }
            }
        }
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        Player p;
        int[] pos;
        
        for(int i = 0; i < players.size(); i++){
            p = players.get(i);
            pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
    public boolean haveAWinner(){
        //Devuelve si hay un jugador en la casilla de salida
        return players[exitRow][exitCol] != null; 
    }

    @Override
    public String toString() {
        String  laberinto = "";
        
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                laberinto += labyrinth[i][j] + " ";
            }
            laberinto += "\n"; // Salto de línea al final de cada fila
        }
        
        return laberinto;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && labyrinth[row][col] == '-'){
            labyrinth[row][col] = 'M';
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        int incRow;
        int incCol;
        
        if (orientation == Orientation.VERTICAL) {
            incRow = 1;
            incCol = 0;
        } else {
            incRow = 0;
            incCol = 1;
        }

        int row = startRow;
        int col = startCol;

        while (posOK(row, col) && emptyPos(row, col) && length > 0) {
            labyrinth[row][col] = BLOCK_CHAR;

            length--;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> output = new ArrayList<>();
        
        if(canStepOn(row+1, col)){
            output.add(Directions.DOWN);
        }
        if(canStepOn(row-1, col)){
            output.add(Directions.UP);
        }
        if(canStepOn(row, col+1)){
            output.add(Directions.RIGHT);
        }
        if(canStepOn(row, col-1)){
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
    private boolean posOK(int row, int col){
        if(row<0 || row>=nRows || col<0 || col>=nCols){
            return false;
        } else{
            return true;
        }
    }    
        
    private boolean emptyPos(int row, int col){
        if(labyrinth[row][col] == '-'){
            return true;
        } else{
            return false;
        }
    }
            
    private boolean monsterPos(int row, int col){
        if(labyrinth[row][col] == 'M'){
            return true;
        } else{
            return false;
        }
    }
                
    private boolean exitPos(int row, int col){
        if(labyrinth[row][col] == 'E'){
            return true;
        } else{
            return false;
        }
    }
                    
    private boolean combatPos(int row, int col){
        if(labyrinth[row][col] == 'C'){
            return true;
        } else{
            return false;
        }
    }
                        
    private boolean canStepOn(int row, int col){
        if(posOK(row, col)){
            if(labyrinth[row][col] == '-' || labyrinth[row][col] == 'M' || labyrinth[row][col] == 'E'){
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
    }
    
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(labyrinth[row][col] == 'C'){
                labyrinth[row][col] = 'M';
            } else{
                labyrinth[row][col] = '-';
            }
        }
    }
    
    private  int[] dir2Pos(int row, int col, Directions direction){
        switch(direction){
            case LEFT:
                return new int[]{row, col-1};
            case RIGHT:
                return new int[]{row, col+1};
            case UP:
                return new int[]{row-1, col};
            case DOWN:
                return new int[]{row+1, col};
            default:
                System.out.println("Dirección inválida");
                return new int[]{row, col}; //No se mueve
        }
    }
    
    private int[] randomEmptyPos(){
        int fila = Dice.randomPos(nRows);
        int columna = Dice.randomPos(nCols);
        
        while(labyrinth[fila][columna] != '-'){
            fila = Dice.randomPos(nRows);
            columna = Dice.randomPos(nCols);
        }
        
        return new int[]{fila, columna};
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        
        if(canStepOn(row, col)){
            
            if(posOK(oldRow, oldCol)){
                Player p = players[oldRow][oldCol];
                
                if(p == player){
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }          
            }
            
            boolean monsterPos = monsterPos(row, col);
            
            if(monsterPos){
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            } else{
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }
            players[row][col] = player;
            player.setPos(row, col);
        }
        return output;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnCols() {
        return nCols;
    }
    
}
