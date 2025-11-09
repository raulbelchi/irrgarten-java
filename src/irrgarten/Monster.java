
package irrgarten;

public class Monster {
    
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;

    public Monster(String name, float intelligence, float strength) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        health = INITIAL_HEALTH;
        //Inicializamos los atributos row y col fuera del tablero
        row = -1;
        col = -1;
    }
    
    public boolean dead(){
        if(health<=0){
            return true;
        } else{
            return false;
        }
    }
    
    public float attack(){
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack){
        boolean isDead = dead();
        
        if(!isDead){
            float defensiveEnergy = Dice.intensity(intelligence);
            
            if(defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        //REVISAR
        return "Monster{" + "name=" + name + ", intelligence=" + intelligence + ", strength=" + strength + ", health=" + health + ", row=" + row + ", col=" + col + '}';
    }
    
    public void gotWounded(){
        health -= 1;
    }
 
}
