package forms;
import java.awt.*;
import javax.swing.*;

import util.custom.Button;
import util.custom.UIHelper;
import util.Item;
import util.PlayerInfo;

public class InventoryView extends JPanel {
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

    private Inventory model;

    public InventoryView(PlayerInfo playerInfo) {
        model = new Inventory();
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
            backBtn.addActionListener(new InventoryController(InventoryController.ActionState.BACK, model, this));
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
        itemPreviousButton.addActionListener(new InventoryController(InventoryController.ActionState.PREVIOUS, model, this));
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
        itemNextButton.addActionListener(new InventoryController(InventoryController.ActionState.NEXT, model, this));
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
        continueButton.addActionListener(new InventoryController(InventoryController.ActionState.EQUIP, model, this));

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

    public Inventory getModel() {
        return model;
    }

    public void setEquipText(String text) {
        continueButton.setText(text);
    }

    public String getEquipText() {
        return continueButton.getText();
    }



}
