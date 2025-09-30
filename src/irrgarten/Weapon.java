package irrgarten;


public class Weapon {
    
    private float power;
    private int uses;
    
    //Constructor
    public Weapon(float power, int uses){
        power = this.power;
        uses = this.uses;
    }
    
    public float attack(){
        if(uses>0){
            return power;
        } else {
            return 0;
        }
    }
    
}
