
package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player{
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        return Dice.nextStep(direction, validMoves, super.getIntelligence());
    }
    
    @Override
    public float attack(){
        return Dice.intensity(super.getStrength()) + sumWeapons();
    }
    
    @Override
    protected float defensiveEnergy(){
        return Dice.intensity(super.getIntelligence()) + sumShields();
    }

    @Override
    public String toString() {
        return "Fuzzy" + super.toString();
    }
    
}
