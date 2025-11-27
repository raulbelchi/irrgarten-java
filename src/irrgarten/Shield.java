package irrgarten;

public class Shield extends CombatElement{

    //Constructor
    public Shield(float protection, int uses){
        super(protection, uses);
    }
    
    public float protect(){
        return produceEffect();
    }

    @Override
    public String toString() {
        return "S[protection=" + super.toString();
    }
}
