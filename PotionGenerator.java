import java.util.Random;
import java.util.ArrayList;

public class PotionGenerator {
	Random random = new Random();
	ArrayList<Potion> potionList = new ArrayList<Potion>();

	PotionGenerator() {
		//Add potion here
	}
	//randomly generates food and spreads it out.
	public Potion generate(){
		return potionList.get(random.nextInt(potionList.size()));
	}
}
