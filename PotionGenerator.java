import java.util.Random;
import java.util.ArrayList;

public class PotionGenerator {
	Random random = new Random();
	ArrayList<Potion> potionList = new ArrayList<Potion>();

	PotionGenerator() {
		potionList.add(new Potion("Greater Healing Tonic", 10));
		potionList.add(new Potion("Invisibility Potion", 10));
		potionList.add(new Potion("Major Energy Potion", 15));
		potionList.add(new Potion("Might Energy Potion", 15));
		potionList.add(new Potion("Exalted Healing Tonic", 20));
		potionList.add(new Potion("Heroic Warding Potion", 20));
		potionList.add(new Potion("Mightly Healing Tonic", 25));
		potionList.add(new Potion("Basic Healing Philter", 25));
		potionList.add(new Potion("Glorified Healing Potion", 30));
		potionList.add(new Potion("Elixir of Freedom", 30));
		potionList.add(new Potion("Vial of Life Resistance", 35));

	}
	//randomly generates potion and spreads it out.
	public Potion generate(){
		return potionList.get(random.nextInt(potionList.size()));
	}
}
