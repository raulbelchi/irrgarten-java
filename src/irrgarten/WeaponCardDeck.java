
package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon>{

    @Override
    protected void addCards(){
        Weapon w;
        
        for(int i = 0; i<5; i++){
            w = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            addCard(w);
        }
    }
    
}
