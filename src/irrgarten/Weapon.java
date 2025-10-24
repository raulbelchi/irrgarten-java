package irrgarten;

public class Weapon {
    
    private float power;
    private int uses;
    
    //Constructor
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }

    @Override
    public String toString() {
        return "W[" + power + ", " + uses + "]";
    }
    
    public float attack(){
        if(uses>0){
            uses -= 1;
            return power;
        } else {
            return 0;
        }
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
 
}
