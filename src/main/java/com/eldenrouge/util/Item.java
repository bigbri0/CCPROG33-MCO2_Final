package com.eldenrouge.util;

/**
 * This class represents an item in the EldenRouge game.
 * An item has a name, type, and various attributes that affect the player's stats.
 * @Authors Roj Friginal, Brian Santamaria
 */
public class Item {
    private String name;
    private ItemType type;
    private int dexterity;
    private int health;
    private int intelligence;
    private int endurance;
    private int strength;
    private int damage;
    private int price;
    /**
     * Enum representing the different types of items in the game.
     */
    public static enum ItemType {
        SWORD,
        KATANA,
        WHIP,
        GREATSWORD,
        STAVE,
        SEAL,
        NONE
    }
    /**
     * Constructors for the Item class.
     *
     * @param name the name of the item
     * @param type the type of the item
     * @param dexterity the dexterity attribute of the item
     * @param health the health attribute of the item
     * @param intelligence the intelligence attribute of the item
     * @param endurance the endurance attribute of the item
     * @param strength the strength attribute of the item
     * @param damage the damage attribute of the item
     * @param price the price of the item
     */
    public Item(String name, ItemType type, int dexterity, int health, int intelligence, int endurance, int strength, int damage, int price) {
        this.name = name;
        this.type = type;
        this.dexterity = dexterity;
        this.health = health;
        this.intelligence = intelligence;
        this.endurance = endurance;
        this.strength = strength;
        this.damage = damage;
        this.price = price;
    }

    // Getters and setters for the item's attributes
    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getHealth() {
        return health;
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

    public int getPrice() {
        return price;
    }


}
