package irrgarten;

public class Shield {
    private float protection;
    private int uses;
    
    //Constructor
    private Shield(float protection, int uses){
        protection = this.protection;
        uses = this.uses;
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
        return "S[" + protection + ", " + uses + ']';
    }
    
    
}
