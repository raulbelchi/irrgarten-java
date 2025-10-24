
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
    private Monster[][] monsters = new Monster[nRows][nCols];
    private Player[][] players = new Player[nRows][nCols];
    private char[][] labyrinth = new char[nRows][nCols];

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    public boolean haveAWinner(){
        //Devuelve si hay un jugador en la casilla de salida
        return players[exitRow][exitCol] != null; 
    }

    @Override
    public String toString() {
        return "Labyrinth{" + "nRows=" + nRows + ", nCols=" + nCols + ", exitRow=" + exitRow + ", exitCol=" + exitCol + '}';
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && labyrinth[row][col] == '-'){
            labyrinth[row][col] = 'M';
            monster.setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
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
    
    private int[] dir2Pos(int row, int col, Directions direction){
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
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }

    public int getnRows() {
        return nRows;
    }

    public int getnCols() {
        return nCols;
    }
    
}
