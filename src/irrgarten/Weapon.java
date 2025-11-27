package irrgarten;

public class Weapon extends CombatElement{
    
    //Constructor
    public Weapon(float power, int uses){
        super(power, uses);
    }
    
    public float attack(){
        return produceEffect();
    }
    
    @Override
    public String toString() {
        return "W[power=" + super.toString();
    }
 
}
