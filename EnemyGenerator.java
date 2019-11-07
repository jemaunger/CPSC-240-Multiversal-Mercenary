import java.util.Random;
import java.util.ArrayList;
//stores and radomly generates enemies and spreads them out throughout the "&"'s on the board.
public class EnemyGenerator {
    private Random rng = new Random();
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
//Randomly generator 13 different enemies to be spread throughout the board.
    EnemyGenerator(){
        enemyList.add(new Enemy("Goblin",  10, 25));
        enemyList.add(new Enemy("Demon",10, 50));
        enemyList.add(new Enemy("Thanos", 50, 75));
        enemyList.add(new Enemy("Voldemort", 50, 125));
        enemyList.add(new Enemy("Ogre", 25,100));
        enemyList.add(new Enemy("Evil Wizard", 25, 150));
        enemyList.add(new Enemy("Alien", 10,25));
        enemyList.add(new Enemy("Dragon", 50, 200));
        enemyList.add(new Enemy("Godzilla", 50, 200));
        enemyList.add(new Enemy("Night Walker", 50,150));
        enemyList.add(new Enemy("Alaskan Bull Worm", 25,40));
        enemyList.add(new Enemy("Bob-omb",10,10));
        enemyList.add(new Enemy("Zombie",10,40));

    }
    public Enemy generate(){
        return enemyList.get(rng.nextInt(13));
    }
}
