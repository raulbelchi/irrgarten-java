
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
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if(size > 0 && !contained){
            return validMoves.get(0);
        } else{
            return direction;
        }
    }
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i = 1; i<= wReward; i++){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }
        
        for(int i = 1; i<= sReward; i++){
            Shield snew = newShield();
            receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        health += extraHealth;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", number=" + number + ", intelligence=" + intelligence + ", strength=" + strength + ", health=" + health + ", row=" + row + ", col=" + col + ", consecutiveHits=" + consecutiveHits + "\nweapons=" + weapons + "\nshields=" + shields + '}';
    }
            
    private void receiveWeapon(Weapon w){
        Weapon wi;
        boolean discard;
        
        for (int i = 0; i < weapons.size(); i++){
            wi = weapons.get(i);
            discard = wi.discard();
            
            if(discard){
                weapons.remove(wi);
            }
        }
        
        int size = weapons.size();
        
        if(size < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s){
        Shield si;
        boolean discard;
        
        for (int i = 0; i < shields.size(); i++){
            si = shields.get(i);
            discard = si.discard();
            
            if(discard){
                shields.remove(si);
            }
        }
        
        int size = shields.size();
        
        if(size < MAX_SHIELDS){
            shields.add(s);
        }
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
        float defense = defensiveEnergy();
        boolean lose;
        
        if(defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        } else{
            resetHits();
        }
        
        if(consecutiveHits == HITS2LOSE || dead()){
            resetHits();
            lose = true;
        } else{
            lose = false;
        }
        
        return lose;
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
