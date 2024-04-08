package forms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import util.GameStats;
import util.Item;
import util.PlayerInfo;
/**
 * This class represents the Inventory screen of the EldenRouge game.
 * It allows the player to view and manage their inventory of items.
 */

public class Inventory {
    /**
     * The Model class holds the data for the Inventory screen.
     */
        private Item item;
        private int itemIndex;
        private String name;
        public PlayerInfo playerInfo;
        private List<Item> items;

        /**
         * Constructor for the Model class.
         * It initializes the item index.
         */
        public Inventory() {
            itemIndex = 0;

        }
        /**
         * This method updates the list of items that the player can equip.
         */
        public void update() {
            items = new ArrayList<>();
            List<Item> allItems = new ArrayList<>();
            allItems.addAll(List.of(GameStats.swords));
            allItems.addAll(List.of(GameStats.katanas));
            allItems.addAll(List.of(GameStats.whips));
            allItems.addAll(List.of(GameStats.greatSwords));
            allItems.addAll(List.of(GameStats.staves));
            allItems.addAll(List.of(GameStats.seals));

            System.out.println(playerInfo.getJobClass().getDexterity() + " ");
            for (Item item : allItems) {
                System.out.println(item.getDexterity());
                if (item.getDexterity() <= playerInfo.getJobClass().getDexterity()) {
                    items.add(item);
                }
            }

            items.sort(Comparator.comparing(Item::getName));

            item = items.get(itemIndex);

        }

        public void setItemClass(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }

        public Item nextItem() {
            itemIndex++;
            if (itemIndex >= items.size()) {
                itemIndex = 0;
            }
            item = items.get(itemIndex);
            return item;
        }

        public Item previousItem() {
            itemIndex--;
            if (itemIndex < 0) {
                itemIndex = items.size() - 1;
            }
            item = items.get(itemIndex);
            return item;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }

    }
