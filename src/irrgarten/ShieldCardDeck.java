
package irrgarten;

public class ShieldCardDeck  extends CardDeck<Shield>{

    @Override
    protected void addCards(){
        Shield s;
        
        for(int i = 0; i<5; i++){
            s = new Shield(Dice.shieldPower(), Dice.usesLeft());
            addCard(s);
        }
    }
    
}
