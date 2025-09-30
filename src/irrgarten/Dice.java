package irrgarten;

import java.util.Random;

public class Dice {
    private static int MAX_USES = 5; //número máximo de usos de armas y escudos
    private static float MAX_INTELLIGENCE = (float) 10.0; //valor máximo para la inteligencia de jugadores y monstruos
    private static float MAX_STRENGTH = (float) 10.0; //valor máximo para la fuerza de jugadores y monstruos
    private static float RESURRECT_PROB = (float) 0.3; //probabilidad de que un jugador sea resucitado en cada turno
    private static int WEAPONS_REWARD = 2; //numero máximo de armas recibidas al ganar un combate
    private static int SHIELDS_REWARD = 3; //numero máximo de escudos recibidos al ganar un combate
    private static int HEALTH_REWARD = 5; //numero máximo de unidades de salud recibidas al ganar un combate
    private static int MAX_ATTACK = 3; //máxima potencia de las armas
    private static int MAX_SHIELD = 2; //máxima potencia de los escudos
    
    Random generator = new Random();
    
    //MÉTODOS
    public int randomPos(int max){
        return generator.nextInt(max);
    }
    
    public int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    public float randomIntelligence(){
        return generator.nextFloat(MAX_INTELLIGENCE);
    }
    
    public float randomStrength(){
        return generator.nextFloat(MAX_STRENGTH);
    }
    
    public boolean resurrectPlayer(){
        return generator.nextBoolean(RESURRECTION_PROB);
    }
    
    public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD);
    }
    
    public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD);
    }
    
    public int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
    }

    public float weaponPower(){
        return generator.nextFloat(MAX_ATTACK);
    }
    
    public float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }
    
    public int usesLeft(){
        return generator.nextInt(MAX_USES);
    } 

    
    public float intensity(float competence){
        return generator.nextFloat(competence);
    }

    public boolean discardElement(int usesLeft){
        if(usesLeft == MAX_USES){
            return false;
        } else if(usesLeft == 0){
            return true;
        } else{
            //implementar
        }
    } 
            
    /*este método devuelve true con una probabilidad
    inversamente proporcional a lo cercano que esté el parámetro del número máximo de usos que
    puede tener un arma o escudo. Como casos extremos, si el número de usos es el máximo devolverá
    false y si es 0 devolverá true. Es decir, las armas o escudos con más usos posibles es menos
    probable que sean descartados*/

}
