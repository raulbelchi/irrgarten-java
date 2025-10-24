
package irrgarten;

import java.util.ArrayList;

public class Player {
    
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    
    private ArrayList<Weapon> weapons = new ArrayList();
    private ArrayList<Shield> shields = new ArrayList();

    public Player(char number, float intelligence, float strength) {
        name = "Player #" + number;
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        health = INITIAL_HEALTH;
    }
    
    public void resurrect(){
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
    }
    
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getNumber() {
        return number;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean dead(){   
        if(health<=0){
            return true;
        } else{
            return false;
        }
    }
    
    public Directions move(Directions direction, Directions[] validMoves){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    public boolean deffend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", number=" + number + ", intelligence=" + intelligence + ", strength=" + strength + ", health=" + health + ", row=" + row + ", col=" + col + ", consecutiveHits=" + consecutiveHits +  '}';
    }
            
    private void receiveWapon(Weapon w){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    private void receiveShield(Shield s){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    private float sumWeapons(){
        float total = 0;
        for(Weapon w : weapons){
            total += w.attack();
        }
        return total;
    }
    
    private float sumShields(){
        float total = 0;
        for(Shield s : shields){
            total += s.protect();
        }
        return total;
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields();
    }
    
    private boolean manageHit(float receivedAttack){
        //IMPLEMENTAR
        throw new UnsupportedOperationException();
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void gotWounded(){
        health -= 1;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits += 1;
    }
}
