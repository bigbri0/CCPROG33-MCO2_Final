package forms;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import forms.MainMenu.View;
import util.PlayerInfo;
import util.custom.Button;
import util.custom.UIHelper;

public class GameLobbyView extends JPanel {
    private GameLobby model;
    private JLabel titleLbl, subtitleLbl, playerImg, playerName, playerInfoLbl;
    private JPanel contentPnl, playerInfoPnl;
    private Button fastTravelButton, levelUpButton, inventoryButton, quitButton;

    public GameLobbyView(PlayerInfo playerInfo) {
        model = new GameLobby();
        model.setPlayerInfo(playerInfo);
        initComponents();

    }

    private void initComponents() {
        
        setBackground(UIHelper.colorBackground);
        setLayout(new GridBagLayout());
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.insets.set(25, 0, 0, 0);
            titleLbl = new JLabel();
            titleLbl.setText("Elden rougE");
            titleLbl.setFont(new Font("Mantinia", Font.BOLD, 75));
            titleLbl.setForeground(UIHelper.colorTertiary);
            add(titleLbl, gbc);

            gbc.insets.set(-40, 0, 15, 0);
            gbc.gridy++;
            subtitleLbl = new JLabel();
            subtitleLbl.setText("game lobby");
            subtitleLbl.setFont(new Font("Mantinia", Font.BOLD, 28));
            subtitleLbl.setForeground(UIHelper.colorPrimary);
            add(subtitleLbl, gbc);

            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.insets.set(0, 0, 0, 0);
            gbc.weighty = 1;
            gbc.weightx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            playerInfoPnl = new JPanel() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    super.paintComponent(grphcs);

                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    g.setColor(UIHelper.colorTertiaryContainer);
                    g.fillRoundRect(-20, 0, getWidth() + 20, getHeight(), 20, 20);

                    g.setColor(UIHelper.colorOnTertiaryContainer);
                    g.fillRoundRect(-20, 0, getWidth() + 20, getHeight() - 45, 20, 20);

                }
            };
            playerInfoPnl.setOpaque(false);
            playerInfoPnl.setLayout(new GridBagLayout());
            add(playerInfoPnl, gbc);

            {

                GridBagConstraints gbcPlayerInfo = new GridBagConstraints();

                gbcPlayerInfo.insets.set(0, 50, 2, 30);
                gbcPlayerInfo.anchor = GridBagConstraints.WEST;
                playerInfoLbl = new JLabel();
                playerInfoLbl.setFont(new Font("Mantinia", Font.PLAIN, 20));
                playerInfoLbl.setForeground(UIHelper.colorTertiaryContainer);
                playerInfoPnl.add(playerInfoLbl, gbcPlayerInfo);


                gbcPlayerInfo.insets.set(0, -20, 0, 0);
                gbcPlayerInfo.anchor = GridBagConstraints.WEST;
                playerImg = new JLabel(){
                @Override
                protected void paintComponent(Graphics grphcs) {
                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    g.setClip(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

                    super.paintComponent(grphcs);

                }
            };
                playerInfoPnl.add(playerImg, gbcPlayerInfo);

                gbcPlayerInfo.gridy = 1;
                gbcPlayerInfo.gridx = 0;
                gbcPlayerInfo.gridwidth = 2;
                gbcPlayerInfo.anchor = GridBagConstraints.CENTER;
                gbcPlayerInfo.insets.set(2, 10, 2, 10);
                playerName = new JLabel();
                playerName.setFont(new Font("Mantinia", 1, 30));
                playerName.setForeground(UIHelper.colorOnTertiaryContainer);
                playerInfoPnl.add(playerName, gbcPlayerInfo);

            }

            gbc.insets.set(30, 0, 0, 0);
            gbc.weighty = 1;
            gbc.gridx = 1;
            contentPnl = new JPanel();
            contentPnl.setOpaque(false);
            contentPnl.setLayout(new GridBagLayout());
            add(contentPnl, gbc);

            {

                GridBagConstraints gbcContainer = new GridBagConstraints();
                gbcContainer.gridy = 0;
                gbcContainer.gridx = 1;
                gbcContainer.gridwidth = 1;
                gbcContainer.insets.set(5, 10, 5, 10);
                gbcContainer.weighty = 0;

                fastTravelButton = new Button("Fast Travel");
                fastTravelButton.setPreferredSize(new Dimension(350, 50));
                fastTravelButton.setText("Fast Travel");
                fastTravelButton.addActionListener(new GameLobbyController(GameLobbyController.ActionState.FAST_TRAVEL, this));
                contentPnl.add(fastTravelButton, gbcContainer);

                gbcContainer.gridy++;
                levelUpButton = new Button("Level Up");
                levelUpButton.setPreferredSize(new Dimension(350, 50));
                levelUpButton.addActionListener(new GameLobbyController(GameLobbyController.ActionState.LEVEL_UP, this));
                contentPnl.add(levelUpButton, gbcContainer);

                gbcContainer.gridy++;
                inventoryButton = new Button("Inventory");
                inventoryButton.setPreferredSize(new Dimension(350, 50));
                inventoryButton.addActionListener(new GameLobbyController(GameLobbyController.ActionState.INVENTORY, this));
                contentPnl.add(inventoryButton, gbcContainer);

                gbcContainer.gridy++;
                quitButton = new Button("Quit Game");
                quitButton.setPreferredSize(new Dimension(350, 50));
                quitButton.addActionListener(new GameLobbyController(GameLobbyController.ActionState.QUIT, this));
                contentPnl.add(quitButton, gbcContainer);

            }  



        model.getPlayerInfo().addPropertyChangeListener(
            evt -> {
                PlayerInfo playerInfo = model.getPlayerInfo();
                playerImg.setIcon(new ImageIcon("src/main/resources/Images/"+ model.getPlayerInfo().getJobClass().getName() + ".png"));
                playerName.setText(playerInfo.getName());

                playerInfoLbl.setText("<html><b style='font-size: 24px;'>%s</b> <br> Level: <b>%d</b> <br> Runes: <b>%d</b> <br> Health: <b>%d</b> <br> Dexterity: <b>%d</b> <br> Strength: <b>%d</b> <br> Intelligence: <b>%d</b> <br> Endurance: <b>%d</b> <br> Faith: <b>%d</b></html>"
                    .formatted(
                        playerInfo.getJobClass().getName(), 
                        playerInfo.getLevel(), 
                        playerInfo.getRunes(), 
                        playerInfo.getJobClass().getHealth(),
                        playerInfo.getJobClass().getDexterity(),
                        playerInfo.getJobClass().getStrength(),
                        playerInfo.getJobClass().getIntelligence(),
                        playerInfo.getJobClass().getEndurance(),
                        playerInfo.getJobClass().getFaith()));
            
            });
        
        }

        
    }
    

    public MainMenu.View getFrame() {
        return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
    }

    public GameLobby getModel() {
        return model;
    }

}
