package com.eldenrouge.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.eldenrouge.util.Item.ItemType;
/**
 * This class represents a player in the EldenRouge game.
 * It holds the player's information such as name, level, current health, runes, job class, and inventory.
 * @Authors Roj Friginal, Brian Santamaria

 */
public class PlayerInfo {
    /**
     * This class represents a player in the EldenRouge game.
     * It holds the player's information such as name, level, current health, runes, job class, and inventory.
     */

    private String name;
    private int level;
    private int currentHealth;
    private int runes;
    private JobClass jobClass;
    private List<Item> inventory;
    private Item equippedWeapon;
    private PropertyChangeSupport propertyChangeSupport;

    private boolean isFirstBossDefeated;
    private boolean isSecondBossDefeated;

    private int selectedAreaFloor;

    /**
     * Constructor for the PlayerInfo class.
     *
     * @param name the player's name
     * @param level the player's level
     * @param runes the player's runes
     * @param jobClass the player's job class
     * @param inventory the player's inventory
     */
    public PlayerInfo(String name, int level, int runes, JobClass jobClass, List<Item> inventory) {
        this.name = name;
        this.level = level;
        this.runes = runes;
        this.jobClass = jobClass;
        this.inventory = inventory;
        this.equippedWeapon = new Item("None", ItemType.NONE, 0, 0, 0, 0, 0, 0, 0);
        this.currentHealth = getMaximumHealth();
        this.isFirstBossDefeated = false;
        this.isSecondBossDefeated = false;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }
    /**
     * Constructor for the PlayerInfo class.
     *
     * @param name the player's name
     * @param jobClass the player's job class
     */
    public PlayerInfo(String name, JobClass jobClass) {
        this.name = name;
        this.level = 1;
        this.runes = 0;
        this.jobClass = jobClass;
        this.inventory = new ArrayList<>();
        this.equippedWeapon = new Item("None", ItemType.NONE, 0, 0, 0, 0, 0, 0, 0);
        this.currentHealth = getMaximumHealth();
        this.isFirstBossDefeated = false;
        this.isSecondBossDefeated = false;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PlayerInfo() {
        this.name = "";
        this.level = 1;
        this.runes = 0;
        this.jobClass = new JobClass("Warrior", 100, 10, 10, 10, 10, 10);
        this.inventory = new ArrayList<>();
        this.equippedWeapon = new Item("None", ItemType.NONE, 0, 0, 0, 0, 0, 0, 0);
        this.currentHealth = getMaximumHealth();
        this.isFirstBossDefeated = false;
        this.isSecondBossDefeated = false;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void replacePlayerInfo(PlayerInfo newPlayerInfo) {
        this.name = newPlayerInfo.getName();
        this.level = newPlayerInfo.getLevel();
        this.runes = newPlayerInfo.getRunes();
        this.jobClass = newPlayerInfo.getJobClass();        
        this.inventory = newPlayerInfo.getInventory();
        this.currentHealth = getMaximumHealth();
        propertyChangeSupport.firePropertyChange("new player info", null, newPlayerInfo);
    }

    public boolean isFirstBossDefeated() {
        return isFirstBossDefeated;
    }

    public void setFirstBossDefeated(boolean firstBossDefeated) {
        boolean oldFirstBossDefeated = this.isFirstBossDefeated;
        this.isFirstBossDefeated = firstBossDefeated;
        propertyChangeSupport.firePropertyChange("firstBossDefeated", oldFirstBossDefeated, firstBossDefeated);
    }

    public boolean isSecondBossDefeated() {
        return isSecondBossDefeated;
    }

    public void setSecondBossDefeated(boolean secondBossDefeated) {
        boolean oldSecondBossDefeated = this.isSecondBossDefeated;
        this.isSecondBossDefeated = secondBossDefeated;
        propertyChangeSupport.firePropertyChange("secondBossDefeated", oldSecondBossDefeated, secondBossDefeated);
    }

    public int getMaximumHealth() {
        return 100 * (jobClass.getHealth() + equippedWeapon.getHealth()) / 2;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        double oldCurrentHealth = this.currentHealth;
        this.currentHealth = currentHealth;
        propertyChangeSupport.firePropertyChange("currentHealth", oldCurrentHealth, currentHealth);
    }

    public PlayerInfo clone() {
        return new PlayerInfo(name, level, runes, jobClass, inventory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldName, name);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        int oldLevel = this.level;
        this.level = level;
        propertyChangeSupport.firePropertyChange("level", oldLevel, level);
    }

    public int getRunes() {
        return runes;
    }

    public void setRunes(int runes) {
        int oldRunes = this.runes;
        this.runes = runes;
        propertyChangeSupport.firePropertyChange("runes", oldRunes, runes);
    }

    public JobClass getJobClass() {
        return jobClass;
    }

    public void setJobClass(JobClass jobClass) {
        JobClass oldJobClass = this.jobClass;
        this.jobClass = jobClass;
        propertyChangeSupport.firePropertyChange("jobClass", oldJobClass, jobClass);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        List<Item> oldInventory = this.inventory;
        this.inventory = inventory;
        propertyChangeSupport.firePropertyChange("inventory", oldInventory, inventory);
    }

    public boolean addItem(Item item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
            propertyChangeSupport.firePropertyChange("inventory", null, inventory);
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
            propertyChangeSupport.firePropertyChange("inventory", null, inventory);
            return true;
        }
        return false;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Item equippedWeapon) {
        Item oldEquippedWeapon = this.equippedWeapon;
        this.equippedWeapon = equippedWeapon;
        propertyChangeSupport.firePropertyChange("equippedWeapon", oldEquippedWeapon, equippedWeapon);
    }

    public void equipWeapon(Item weapon) {
        if (inventory.contains(weapon)) {
            equippedWeapon = weapon;
            propertyChangeSupport.firePropertyChange("equippedWeapon", null, equippedWeapon);
        }
    }

    public void equipWeapon(int index) {
        if (index >= 0 && index < inventory.size()) {
            equippedWeapon = inventory.get(index);
            propertyChangeSupport.firePropertyChange("equippedWeapon", null, equippedWeapon);
        }
    }

    public void addRunes(int runes) {
        int oldRunes = this.runes;
        this.runes += runes;
        propertyChangeSupport.firePropertyChange("runes", oldRunes, this.runes);
    }

    public boolean removeRunes(int runes) {
        if (this.runes >= runes) {
            int oldRunes = this.runes;
            this.runes -= runes;
            propertyChangeSupport.firePropertyChange("runes", oldRunes, this.runes);
            return true;
        }
        return false;
    }

    public void levelUp() {
        int oldLevel = this.level;
        this.level++;
        propertyChangeSupport.firePropertyChange("level", oldLevel, this.level);
    }

    public void setStats(int health, int dexterity, int intelligence, int endurance, int strength, int damage) {
        jobClass.setHealth(health);
        jobClass.setDexterity(dexterity);
        jobClass.setIntelligence(intelligence);
        jobClass.setEndurance(endurance);
        jobClass.setStrength(strength);
        jobClass.setFaith(damage);
    }

    public void setStats(JobClass jobClass) {
        JobClass oldJobClass = this.jobClass;
        this.jobClass = jobClass;
        propertyChangeSupport.firePropertyChange("jobClass", oldJobClass, jobClass);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public int getSelectedAreaFloor() {
        return selectedAreaFloor;
    }

    public void setSelectedAreaFloor(int selectedAreaFloor) {
        this.selectedAreaFloor = selectedAreaFloor;
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    @Override
    public String toString() {
        return "PlayerInfo{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", runes=" + runes +
                ", jobClass=" + jobClass +
                ", inventory=" + inventory +
                '}';
    }
}
