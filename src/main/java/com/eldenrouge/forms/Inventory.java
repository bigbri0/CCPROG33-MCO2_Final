package com.eldenrouge.forms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.eldenrouge.util.GameStats;
import com.eldenrouge.util.custom.Button;
import com.eldenrouge.util.custom.UIHelper;
import com.eldenrouge.util.Item;
import com.eldenrouge.util.PlayerInfo;
/**
 * This class represents the Inventory screen of the EldenRouge game.
 * It allows the player to view and manage their inventory of items.
 */

public class Inventory {
    /**
     * The Model class holds the data for the Inventory screen.
     */
    public static class Model {
        private Item item;
        private int itemIndex;
        private String name;
        private PlayerInfo playerInfo;
        private List<Item> items;

        /**
         * Constructor for the Model class.
         * It initializes the item index.
         */
        public Model() {
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
    /**
     * The View class is responsible for the visual representation of the Inventory screen.
     */
    public static class View extends JPanel {
        /**
         * Constructor for the View class.
         * It initializes the components of the Inventory screen.
         *
         * @param playerInfo the player's information
         */
        private JLabel titleLbl;
        private JLabel subtitleLbl;

        private JPanel headerPnl;
        private JPanel contentPnl;


        private JLabel itemLabel;
        private JPanel itemPanel;

        private JLabel itemImage;
        private JLabel itemName;
        private JLabel itemInfo;


        private Button itemNextButton;
        private Button itemPreviousButton;

        private Button backBtn;
        private Button continueButton;

        private Model model;

        public View(PlayerInfo playerInfo) {
            model = new Model();
            model.setPlayerInfo(playerInfo);
            model.update();

            initComponents();

        }

        private void initComponents() {
            setBackground(UIHelper.colorBackground);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridy = 0;
            gbc.gridwidth = 2;
            headerPnl = new JPanel(new GridBagLayout());
            headerPnl.setOpaque(false);
            add(headerPnl, gbc);

            {
                GridBagConstraints gbcHeader = new GridBagConstraints();
                gbcHeader.gridx = 0;
                gbcHeader.gridy = 0;
                gbcHeader.weightx = 0;
                gbcHeader.gridheight = 2;
                gbcHeader.insets.set(10, 40, 10, 40);

                backBtn = new Button("Back") {
                    @Override
                    protected void paintComponent(Graphics grphcs) {
                        super.paintComponent(grphcs);

                        Graphics2D g = (Graphics2D) grphcs;
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                        int[] x = { 35, 15, 35 };
                        int[] y = { 15, 25, 35 };
                        g.setColor(UIHelper.colorOnPrimaryContainer);
                        g.fillPolygon(x, y, 3);

                    }
                };
                backBtn.setPreferredSize(new Dimension(115, 50));
                backBtn.setHorizontalAlignment(JLabel.RIGHT);
                backBtn.setFont(new Font("Mantinia", Font.BOLD, 15));
                backBtn.setText("BACK");
                backBtn.addActionListener(new Controller(Controller.ActionState.BACK, model, this));
                headerPnl.add(backBtn, gbcHeader);

                gbcHeader.gridy = 0;
                gbcHeader.gridx = 1;
                gbcHeader.gridheight = 1;
                gbcHeader.weightx = 1;

                gbcHeader.insets.set(25, 0, 0, 0);
                titleLbl = new JLabel();
                titleLbl.setText("Elden rougE");
                titleLbl.setFont(new Font("Mantinia", Font.BOLD, 75));
                titleLbl.setForeground(UIHelper.colorTertiary);
                headerPnl.add(titleLbl, gbcHeader);

                gbcHeader.insets.set(-40, 0, 15, 0);
                gbcHeader.gridy++;
                subtitleLbl = new JLabel();
                subtitleLbl.setText("inventory");
                subtitleLbl.setFont(new Font("Mantinia", Font.BOLD, 28));
                subtitleLbl.setForeground(UIHelper.colorPrimary);
                headerPnl.add(subtitleLbl, gbcHeader);

                gbcHeader.gridx++;
                gbcHeader.gridy = 0;
                gbcHeader.weightx = 0;
                gbcHeader.gridheight = 2;
                gbcHeader.insets.set(10, 40, 10, 40);
                JLabel emptyLabel = new JLabel("");
                emptyLabel.setPreferredSize(new Dimension(115, 50));
                headerPnl.add(emptyLabel, gbcHeader);

            }

            gbc.insets.set(-30, 0, 0, 0);
            gbc.weighty = 1;
            gbc.gridy++;
            contentPnl = new JPanel() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    g.setColor(UIHelper.colorSecondary);
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                    g.setColor(UIHelper.colorOnSecondaryContainer);
                    g.fillRoundRect(0, 0, getWidth(), getHeight() - 50, 20, 20);
                }
            };
            contentPnl.setOpaque(false);
            contentPnl.setLayout(new GridBagLayout());
            add(contentPnl, gbc);

            GridBagConstraints gbcContainer = new GridBagConstraints();

            gbcContainer.insets.set(15, 10, 5, 10);
            gbcContainer.gridy = 0;
            gbcContainer.gridx = 0;
            gbcContainer.weightx = 1;
            gbcContainer.gridwidth = 3;
            gbcContainer.anchor = GridBagConstraints.CENTER;

            itemName = new JLabel();
            itemName.setFont(new Font("Mantinia", 1, 25));
            itemName.setForeground(UIHelper.colorOnPrimary);
            contentPnl.add(itemName, gbcContainer);

            gbcContainer.insets.set(0, 10, 20, 10);
            gbcContainer.gridy = 1;
            gbcContainer.gridx = 0;
            gbcContainer.weightx = 0;
            gbcContainer.gridwidth = 1;
            gbcContainer.insets.set(10, 15, 15, 5);
            itemPreviousButton = new Button() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    super.paintComponent(grphcs);

                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    int[] x = { 35, 15, 35 };
                    int[] y = { 15, 25, 35 };
                    g.setColor(UIHelper.colorOnPrimaryContainer);
                    g.fillPolygon(x, y, 3);
                }
            };
            itemPreviousButton.setPreferredSize(new Dimension(50, 50));
            itemPreviousButton.addActionListener(new Controller(Controller.ActionState.PREVIOUS, model, this));
            contentPnl.add(itemPreviousButton, gbcContainer);

            gbcContainer.gridx++;
            gbcContainer.weightx = 1;
            itemPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    super.paintComponent(grphcs);

                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    g.setColor(UIHelper.colorOnSecondary);
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                }
            };
            itemPanel.setOpaque(false);
            itemPanel.setPreferredSize(new Dimension(510, 335));
            itemPanel.setLayout(new GridBagLayout());
            contentPnl.add(itemPanel, gbcContainer);
            {
                GridBagConstraints gbcitemPanel = new GridBagConstraints();
                gbcitemPanel.gridy = 0;
                gbcitemPanel.gridx = 0;
                gbcitemPanel.gridwidth = 1;
                gbcitemPanel.weightx = 0;
                gbcitemPanel.weighty = 1;
                gbcitemPanel.insets.set(0, 140, 0, 0);
                itemImage = new JLabel();
                itemPanel.add(itemImage, gbcitemPanel);

                gbcitemPanel.insets.set(0, 0, 0, 0);
                gbcitemPanel.fill = GridBagConstraints.HORIZONTAL;
                gbcitemPanel.anchor = GridBagConstraints.EAST;
                itemInfo = new JLabel();
                itemInfo.setFont(new Font("Mantinia", Font.PLAIN, 20));
                itemInfo.setForeground(UIHelper.colorOnPrimaryContainer);
                itemPanel.add(itemInfo, gbcitemPanel);


            }

            gbcContainer.gridx++;
            gbcContainer.weightx = 0;
            gbcContainer.insets.set(30, 5, 15, 15);
            gbcContainer.anchor = GridBagConstraints.CENTER;
            itemNextButton = new Button() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    super.paintComponent(grphcs);

                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    int[] x = { 15, 35, 15 };
                    int[] y = { 15, 25, 35 };
                    g.setColor(UIHelper.colorOnPrimaryContainer);
                    g.fillPolygon(x, y, 3);
                }
            };
            itemNextButton.setPreferredSize(new Dimension(50, 50));
            itemNextButton.addActionListener(new Controller(Controller.ActionState.NEXT, model, this));
            contentPnl.add(itemNextButton, gbcContainer);

            gbcContainer.gridy++;
            gbcContainer.gridx = 1;
            gbcContainer.insets.set(10, 0, 10, 0);
            gbcContainer.gridwidth = 1;
            gbcContainer.fill = GridBagConstraints.NONE;
            gbcContainer.anchor = GridBagConstraints.CENTER;
            itemLabel = new JLabel();
            itemLabel.setFont(new Font("Mantinia", Font.BOLD, 20));
            itemLabel.setForeground(UIHelper.colorOnPrimary);
            contentPnl.add(itemLabel, gbcContainer);

            gbc.gridy++;
            gbc.gridx = 0;
            continueButton = new Button("Equip Item");
            continueButton.setPreferredSize(new Dimension(350, 50));
            continueButton.addActionListener(new Controller(Controller.ActionState.EQUIP, model, this));

            gbc.insets.set(15, 0, 15, 0);
            add(continueButton, gbc);


            updateItem();

        }

        public void updateItem() {
            Item item = model.getItem();
            ImageIcon icon =  new ImageIcon(
                    new ImageIcon("src/main/resources/Images/"
                            + item.getName() + ".png")
                            .getImage()
                            .getScaledInstance(250, 250, Image.SCALE_SMOOTH));

            itemImage.setIcon(icon);
            itemName.setText(item.getName());
            if (item.getType() == Item.ItemType.SWORD)
                itemLabel.setText("Sword");
            else if (item.getType() == Item.ItemType.KATANA)
                itemLabel.setText("Katana");
            else if (item.getType() == Item.ItemType.WHIP)
                itemLabel.setText("Whip");
            else if (item.getType() == Item.ItemType.GREATSWORD)
                itemLabel.setText("Greatsword");
            else if (item.getType() == Item.ItemType.STAVE)
                itemLabel.setText("Stave");
            else if (item.getType() == Item.ItemType.SEAL)
                itemLabel.setText("Seal");
            else
                itemLabel.setText("None");

            String priceTxt = "Price: <b>%d</b>".formatted(item.getPrice());

            for (Item i : model.getPlayerInfo().getInventory()) {
                if (item.getName().equals(i.getName())) {
                    priceTxt = "Owned";
                }
            }

            itemInfo.setText("<html>Health: <b>%d</b> <br> Dexterity: <b>%d</b> <br> Intelligence: <b>%d</b> <br> Endurance: <b>%d</b> <br> Strength: <b>%d</b> <br> Faith: <b>%d</b> <br>%s</html>"
                    .formatted(
                            item.getHealth(),
                            item.getDexterity(),
                            item.getIntelligence(),
                            item.getEndurance(),
                            item.getStrength(),
                            item.getFaith(),
                            priceTxt));

        }

        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }

        public Model getModel() {
            return model;
        }

        public void setEquipText(String text) {
            continueButton.setText(text);
        }

        public String getEquipText() {
            return continueButton.getText();
        }



    }
    /**
     * The Controller class handles the actions performed on the Inventory screen.
     */
    public static class Controller implements ActionListener {
        private ActionState actionState;
        private Model model;
        private View view;
        /**
         * Enum representing the different actions that can be performed on the Inventory screen.
         */
        enum ActionState {
            NEXT, PREVIOUS, BACK, EQUIP
        }
        /**
         * Constructor for the Controller class.
         *
         * @param actionState the action to be performed
         * @param model the model of the Inventory screen
         * @param view the view of the Inventory screen
         */
        public Controller(ActionState actionState, Model model, View view) {
            super();
            this.actionState = actionState;
            this.view = view;
            this.model = model;

            this.model.getPlayerInfo().addPropertyChangeListener(evt -> {
                view.updateItem();
                model.update();
            });
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (actionState) {
                case NEXT:
                    Item next = model.nextItem();
                    if (next.getName().equals(model.playerInfo.getEquippedWeapon().getName())) {
                        view.setEquipText("Unequip Item");
                    } else {
                        view.setEquipText("Equip Item");
                    }

                    break;
                case PREVIOUS:
                    Item previous = model.previousItem();
                    if (previous.getName().equals(model.playerInfo.getEquippedWeapon().getName())) {
                        view.setEquipText("Unequip Item");
                    } else {
                        view.setEquipText("Equip Item");
                    }

                    break;
                case BACK:
                    view.updateItem();
                    view.getFrame().setView("GameLobby");
                    break;
                case EQUIP:
                    if (view.getEquipText().equals("Unequip Item")) {
                        int result = JOptionPane.showConfirmDialog(view, "Are you sure you want to unequip this item?");
                        if (result == JOptionPane.YES_NO_OPTION) {
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() + model.getItem().getPrice());
                            model.getPlayerInfo().setEquippedWeapon(new Item("None", Item.ItemType.NONE, 0, 0, 0, 0, 0, 0, 0));
                            view.setEquipText("Equip Item");
                            view.updateItem();

                        }
                    } else {
                        if (model.getPlayerInfo().getRunes() >= model.getItem().getPrice()) {
                            boolean owned = false;
                            for (Item i : model.getPlayerInfo().getInventory()) {
                                if (model.getItem().getName().equals(i.getName())) {
                                    owned = true;
                                }
                            }
                            if (!owned) {
                                model.getPlayerInfo().getInventory().add(model.getItem());
                                model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - model.getItem().getPrice());
                            }
                            model.getPlayerInfo().setEquippedWeapon(model.getItem());


                            view.setEquipText("Unequip Item");
                            view.updateItem();
                            JOptionPane.showMessageDialog(view, "Item Equipped");

                        } else {
                            JOptionPane.showMessageDialog(view, "Not enough runes");
                        }
                    }






                    break;
                default:
                    break;
            }
            view.updateItem();

        }
    }
}