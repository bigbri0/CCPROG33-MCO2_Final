package com.eldenrouge.util;
/**
 * This class holds the game statistics for the EldenRouge game.
 * It includes arrays of different job classes and items available in the game.
 */
public class GameStats {
    /**
     * Array of job classes available in the game.
     */
    public static JobClass[] jobClasses = {
        new JobClass("Vagabond", 15, 13, 9, 11, 14, 9),
        new JobClass("Samurai", 12, 15, 9, 13, 12, 8),
        new JobClass("Warrior", 11, 16, 10, 11, 10, 8),
        new JobClass("Hero", 14, 9, 7, 12, 16, 8),
        new JobClass("Astrologer", 9, 12, 16, 9, 8, 7),
        new JobClass("Prophet", 10, 10, 7, 8, 11, 16)
    };
    /**
     * Array of sword items available in the game.
     */
    public static Item[] swords = {
        new Item("Short Sword", Item.ItemType.SWORD, 13, 0, 15, 15, 15, 15, 1000),
        new Item("Rogier's Rapier", Item.ItemType.SWORD, 18, 10, 35, 25, 35, 35, 2000),
        new Item("Coded Sword", Item.ItemType.SWORD, 21, 20, 40, 35, 40, 40, 4000),
        new Item("Sword of Night and Flame", Item.ItemType.SWORD, 25, 30, 55, 45, 55, 55, 8000)
    };
    /**
     * Array of katana items available in the game.
     */
    public static Item[] katanas = {
        new Item("Uchigatana", Item.ItemType.KATANA, 15, 20, 0, 35, 30, 0, 1875),
        new Item("Moonveil", Item.ItemType.KATANA, 20, 30, 0, 40, 45, 0, 3750),
        new Item("Rivers of Blood", Item.ItemType.KATANA, 25, 40, 0, 45, 60, 0, 7500),
        new Item("Hand of Malenia", Item.ItemType.KATANA, 30, 50, 0, 50, 75, 0, 15000)
    };
    /**
     * Array of whip items available in the game.
     */
    public static Item[] whips = {
        new Item("Whip", Item.ItemType.WHIP, 20, 15, 0, 60, 20, 0, 1500),
        new Item("Urumi", Item.ItemType.WHIP, 25, 20, 10, 70, 40, 0, 3000),
        new Item("Thorned Whip", Item.ItemType.WHIP, 30, 30, 0, 80, 50, 40, 5000),
        new Item("Hoslow's Petal Whip", Item.ItemType.WHIP, 35, 35, 20, 90, 55, 20, 10000)
    };
    /**
     * Array of great sword items available in the game.
     */
    public static Item[] greatSwords = {
        new Item("Claymore", Item.ItemType.GREATSWORD, 9, 15, 0, 10, 20, 0, 3000),
        new Item("Starscourge Greatsword", Item.ItemType.GREATSWORD, 14, 20, 0, 15, 40, 20, 6000),
        new Item("Inseparable Sword", Item.ItemType.GREATSWORD, 19, 25, 60, 20, 70, 60, 12000),
        new Item("Maliketh's Black Blade", Item.ItemType.GREATSWORD, 24, 30, 40, 25, 80, 60, 24000)
    };
    /**
     * Array of staff items available in the game.
     */
    public static Item[] staves = {
        new Item("Astrologer's Staff", Item.ItemType.STAVE, 12, 5, 25, 20, 5, 15, 2000),
        new Item("Albinauric Staff", Item.ItemType.STAVE, 14, 10, 45, 30, 10, 35, 4000),
        new Item("Staff of the Guilty", Item.ItemType.STAVE, 16, 15, 65, 40, 15, 60, 8000),
        new Item("Carian Regal Scepter", Item.ItemType.STAVE, 18, 25, 85, 50, 20, 75, 16000)
    };
    /**
     * Array of seal items available in the game.
     */
    public static Item[] seals = {
        new Item("Finger Seal", Item.ItemType.SEAL, 10, 10, 15, 45, 0, 20, 2500),
        new Item("Godslayer's Seal", Item.ItemType.SEAL, 12, 15, 35, 50, 0, 40, 5000),
        new Item("Golden Order Seal", Item.ItemType.SEAL, 14, 20, 65, 55, 0, 65, 10000),
        new Item("Dragon Communion Seal", Item.ItemType.SEAL, 18, 25, 75, 60, 0, 80, 15000)
    };
}
