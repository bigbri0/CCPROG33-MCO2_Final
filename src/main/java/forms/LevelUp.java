package forms;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;


import util.custom.Button;
import util.custom.UIHelper;
import util.PlayerInfo;

public class LevelUp {
    public static class Model {
        private PlayerInfo playerInfo;

        public Model() {

        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }
    }

    public static class View extends JPanel {
        private JLabel titleLbl;
        private JLabel subtitleLbl;

        private JPanel headerPnl;
        private JPanel contentPnl;
        private Button healthButton;
        private Button enduranceButton;
        private Button dexterityButton;
        private Button strengthButton;
        private Button intelligenceButton;
        private Button faithButton;

        private JPanel playerInfoPnl;
        private JLabel playerImg;
        private JLabel playerName;
        private JLabel playerInfoLbl;

        private Button backBtn;

        private Model model;

        public View(PlayerInfo playerInfo) {
            model = new Model();
            model.setPlayerInfo(playerInfo);
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
                subtitleLbl.setText("level up statistics");
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
                gbcContainer.gridx = 0;
                gbcContainer.gridy = 0;
                gbcContainer.weightx = 1;
                gbcContainer.weighty = 1;
                gbcContainer.insets.set(5, 0, 5, 0);
                gbcContainer.anchor = GridBagConstraints.CENTER;

                healthButton = new Button("Health");
                healthButton.setPreferredSize(new java.awt.Dimension(350, 50));
                healthButton.addActionListener(new Controller(Controller.ActionState.HEALTH, model, this));
                contentPnl.add(healthButton, gbcContainer);

                gbcContainer.gridy++;
                enduranceButton = new Button("Endurance");
                enduranceButton.setPreferredSize(new java.awt.Dimension(350, 50));
                enduranceButton.addActionListener(new Controller(Controller.ActionState.ENDURANCE, model, this));
                contentPnl.add(enduranceButton, gbcContainer);


                gbcContainer.gridy++;
                dexterityButton = new Button("Dexterity");
                dexterityButton.setPreferredSize(new java.awt.Dimension(350, 50));
                dexterityButton.addActionListener(new Controller(Controller.ActionState.DEXTERITY, model, this));
                contentPnl.add(dexterityButton, gbcContainer);

                gbcContainer.gridy++;
                strengthButton = new Button("Strength");
                strengthButton.setPreferredSize(new java.awt.Dimension(350, 50));
                strengthButton.addActionListener(new Controller(Controller.ActionState.STRENGTH, model, this));
                contentPnl.add(strengthButton, gbcContainer);

                gbcContainer.gridy++;
                intelligenceButton = new Button("Intelligence");
                intelligenceButton.setPreferredSize(new java.awt.Dimension(350, 50));
                intelligenceButton.addActionListener(new Controller(Controller.ActionState.INTELLIGENCE, model, this));
                contentPnl.add(intelligenceButton, gbcContainer);

                gbcContainer.gridy++;
                faithButton = new Button("Faith");
                faithButton.setPreferredSize(new java.awt.Dimension(350, 50));
                faithButton.addActionListener(new Controller(Controller.ActionState.FAITH, model, this));
                contentPnl.add(faithButton, gbcContainer);


            }
            
            model.getPlayerInfo().addPropertyChangeListener(
            evt -> {
                PlayerInfo playerInfo = model.getPlayerInfo();
                playerImg.setIcon(new ImageIcon("src/main/resources/Images/"+ model.getPlayerInfo().getJobClass().getName() + ".png"));

                playerName.setText(playerInfo.getName());
                playerInfoLbl.setText(
                        "<html><b style='font-size: 24px;'>%s</b> <br> Level: <b>%d</b> <br> Runes: <b>%d</b> <br> Health: <b>%d</b> <br> Dexterity: <b>%d</b> <br> Strength: <b>%d</b> <br> Intelligence: <b>%d</b> <br> Endurance: <b>%d</b> <br> Faith: <b>%d</b></html>"
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

        public Model getModel() {
            return model;
        }

        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }

    }

    public static class Controller implements ActionListener {
        private ActionState actionState;

        private Model model;
        private View view;

        public enum ActionState {
            BACK,
            HEALTH,
            ENDURANCE,
            DEXTERITY,
            STRENGTH,
            INTELLIGENCE,
            FAITH
        }

        public Controller(ActionState actionState, Model model, View view) {
            super();
            this.actionState = actionState;
            this.view = view;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int levelUpCost = model.getPlayerInfo().getLevel() * 100 / 2;
            int dialogResult;
            switch (actionState) {
                case HEALTH:
                    dialogResult = JOptionPane.showConfirmDialog(null, "Level Up Health for " + levelUpCost + " Runes?",
                            "Level Up", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                            model.getPlayerInfo().getJobClass()
                                    .setHealth(model.getPlayerInfo().getJobClass().getHealth() + 1);
                            model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                            model.getPlayerInfo().firePropertyChange("levelUpHealth",
                                    model.getPlayerInfo().getJobClass().getHealth() - 1,
                                    model.getPlayerInfo().getJobClass().getHealth());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case ENDURANCE:
                    dialogResult = JOptionPane.showConfirmDialog(null,
                            "Level Up Endurance for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                            model.getPlayerInfo().getJobClass()
                                    .setEndurance(model.getPlayerInfo().getJobClass().getEndurance() + 1);
                            model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                            model.getPlayerInfo().firePropertyChange("levelUpEndurance",
                                    model.getPlayerInfo().getJobClass().getEndurance() - 1,
                                    model.getPlayerInfo().getJobClass().getEndurance());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case DEXTERITY:
                    dialogResult = JOptionPane.showConfirmDialog(null,
                            "Level Up Dexterity for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                            model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                            model.getPlayerInfo().getJobClass()
                                    .setDexterity(model.getPlayerInfo().getJobClass().getDexterity() + 1);
                            model.getPlayerInfo().firePropertyChange("levelUpDexterity",
                                    model.getPlayerInfo().getJobClass().getDexterity() - 1,
                                    model.getPlayerInfo().getJobClass().getDexterity());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case STRENGTH:
                    dialogResult = JOptionPane.showConfirmDialog(null,
                            "Level Up Strength for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                            model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                            model.getPlayerInfo().getJobClass()
                                    .setStrength(model.getPlayerInfo().getJobClass().getStrength() + 1);
                            model.getPlayerInfo().firePropertyChange("levelUpStrength",
                                    model.getPlayerInfo().getJobClass().getStrength() - 1,
                                    model.getPlayerInfo().getJobClass().getStrength());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case INTELLIGENCE:
                    dialogResult = JOptionPane.showConfirmDialog(null,
                            "Level Up Intelligence for " + levelUpCost + " Runes?", "Level Up",
                            JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                            model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                            model.getPlayerInfo().getJobClass()
                                    .setIntelligence(model.getPlayerInfo().getJobClass().getIntelligence() + 1);
                            model.getPlayerInfo().firePropertyChange("levelUpIntelligence",
                                    model.getPlayerInfo().getJobClass().getIntelligence() - 1,
                                    model.getPlayerInfo().getJobClass().getIntelligence());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case FAITH:
                    dialogResult = JOptionPane.showConfirmDialog(null,
                            "Level Up Faith for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                            model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                            model.getPlayerInfo().getJobClass()
                                    .setFaith(model.getPlayerInfo().getJobClass().getFaith() + 1);
                            model.getPlayerInfo().firePropertyChange("levelUpFaith",
                                    model.getPlayerInfo().getJobClass().getFaith() - 1,
                                    model.getPlayerInfo().getJobClass().getFaith());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                
                    break;
                case BACK:
                    view.getFrame().setView("GameLobby");
                    break;
            }

        }

        public View getView() {
            return view;
        }
    }
}
