
package irrgarten;

public class TestP1 {
    public static void main(String[] args) {
        
        Weapon w1 = new Weapon(1, 5);
        System.out.println(w1.toString());
        Weapon w2 = new Weapon(3, 3);
        System.out.println(w2.toString());
        Weapon w3 = new Weapon(2, 4);
        System.out.println(w3.toString());
        
        Shield s1 = new Shield(2, 3);
        System.out.println(s1.toString());
        Shield s2 = new Shield(1, 5);
        System.out.println(s2.toString());
        Shield s3 = new Shield(2, 4);
        System.out.println(s3.toString());
        
        Dice dado = new Dice();
        
        System.out.println("Prueba shieldsReward");
        for(int i=0; i<10; i++){
            System.out.println(dado.shieldsReward());
        }
        
        System.out.println("Prueba healthReward");
        for(int i=0; i<10; i++){
            System.out.println(dado.healthReward());
        }
        
        System.out.println("Prueba resurrectPlayer");
        for(int i=0; i<10; i++){
            System.out.println(dado.resurrectPlayer());
        }
        
        System.out.println("Prueba discardElement");
        for(int i=0; i<10; i++){
            System.out.println(dado.discardElement(5));
        }
        
        System.out.println("Prueba randomStrength");
        for(int i=0; i<10; i++){
            System.out.println(dado.randomStrength());
        }
    }
}
