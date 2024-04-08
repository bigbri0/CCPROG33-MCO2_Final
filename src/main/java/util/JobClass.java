package util;
/**
 * This class represents a job class in the EldenRouge game.
 * A job class has a name and various attributes that affect the player's stats.
 */
public class JobClass {
    /**
     * Constructor for the JobClass class.
     *
     * @param name the name of the job class
     * @param health the health attribute of the job class
     * @param dexterity the dexterity attribute of the job class
     * @param intelligence the intelligence attribute of the job class
     * @param endurance the endurance attribute of the job class
     * @param strength the strength attribute of the job class
     * @param damage the damage attribute of the job class
     */
    private String name;
    private int health;
    private int dexterity;
    private int intelligence;
    private int endurance;
    private int strength;
    private int damage;

    public JobClass(String name, int health, int dexterity, int intelligence, int endurance, int strength, int damage) {
        this.name = name;
        this.health = health;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.endurance = endurance;
        this.strength = strength;
        this.damage = damage;
    }
    //Getters and setters for the job class's attributes
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getStrength() {
        return strength;
    }

    public int getFaith() {
        return damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setFaith(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "JobClass{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", endurance=" + endurance +
                ", strength=" + strength +
                ", damage=" + damage +
                '}';
    }
}
