
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<T extends CombatElement> {
    
    private ArrayList<T> cardDeck = new ArrayList<>();

    public CardDeck() {
    }
    
    protected abstract void addCards();
    
    protected void addCard(T card){
        cardDeck.add(card);
    }
    
    public T nextCard(){
        if (cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck);
            return cardDeck.remove(0);
        } else {
            return cardDeck.remove(0);
        }
    }
}
