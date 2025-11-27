
package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter{
    
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private char number;
    private int consecutiveHits = 0;
    
    private ArrayList<Weapon> weapons = new ArrayList();
    private ArrayList<Shield> shields = new ArrayList();
    
    private WeaponCardDeck weaponCardDeck = new WeaponCardDeck();
    private ShieldCardDeck shieldCardDeck = new ShieldCardDeck();
    
    public Player(char number, float intelligence, float strength) {
        super("Player #" + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        weaponCardDeck.addCards();
        shieldCardDeck.addCards();
    }
    
    public Player(Player other){
        super("Player #" + other.getNumber(), other.getIntelligence(), other.getStrength(), other.getHealth());
        this.number = other.number;
        this.setPos(other.getRow(), other.getCol());
        this.consecutiveHits = 0;
        weaponCardDeck.addCards();
        shieldCardDeck.addCards();
    }
    
    public void resurrect(){
        weapons.clear();
        shields.clear();
        super.setHealth(INITIAL_HEALTH);
        consecutiveHits = 0;
    }

    public char getNumber() {
        return number;
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
    
    @Override
    public float attack(){
        return super.getStrength() + sumWeapons();
    }
    
    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i = 1; i<= wReward; i++){
            receiveWeapon(weaponCardDeck.nextCard());
        }
        
        for(int i = 1; i<= sReward; i++){
            receiveShield(shieldCardDeck.nextCard());
        }
        
        int extraHealth = Dice.healthReward();
        super.setHealth(super.getHealth() + extraHealth);
    }
    
    @Override
    public String toString() {
        return "Player" + super.toString() + ", number=" + number + ", consecutiveHits=" + consecutiveHits + "\nweapons=" + weapons + "\nshields=" + shields + "\n}";
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
    
    protected float sumWeapons(){
        float total = 0;
        for(Weapon w : weapons){
            total += w.attack();
        }
        return total;
    }
    
    protected float sumShields(){
        float total = 0;
        for(Shield s : shields){
            total += s.protect();
        }
        return total;
    }
    
    protected float defensiveEnergy(){
        return super.getIntelligence() + sumShields();
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
    
    private void incConsecutiveHits(){
        consecutiveHits += 1;
    }
}
