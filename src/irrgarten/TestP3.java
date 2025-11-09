
package irrgarten;

public class TestP3 {
    public static void main(String[] args) {
        Player p1 = new Player('0', Dice.randomIntelligence(), Dice.randomStrength());
        System.out.println(p1.toString());
        for (int i=0; i<3; i++){
            p1.receiveReward();
            System.out.println(p1.toString());
        }
    }
}
