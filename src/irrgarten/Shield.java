package irrgarten;

public class Shield {
    private float protection;
    private int uses;
    private Dice dado = new Dice();
    
    //Constructor
    public Shield(float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }
    
    public float protect(){
        if(uses>0){
            uses -=1;
            return protection;
        } else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "S[" + protection + ", " + uses + "]";
    }
    
    public boolean discard(){
        return dado.discardElement(uses);
    }
}
