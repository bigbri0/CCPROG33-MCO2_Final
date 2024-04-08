package com.eldenrouge.forms;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import com.eldenrouge.util.custom.Button;
import com.eldenrouge.util.custom.UIHelper;
import com.eldenrouge.util.PlayerInfo;

/**
 * This class represents the Fast Travel screen of the EldenRouge game.
 * It allows the player to fast travel to different areas in the game.
 * @Authors Roj Friginal, Brian Santamaria
 */


public class FastTravel {

    /**
     * The Model class is responsible for the data of the Fast Travel screen.
     */
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
        private JLabel titleLbl, subtitleLbl, playerImg, playerName, playerInfoLbl;
        private JPanel headerPnl, contentPnl, playerInfoPnl;
        private Model model;
        private Button stormVeilCastleButton, rayaLucariaAcademyButton, theEldenThroneButton, backBtn;

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
                subtitleLbl.setText("fast travel");
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
                GridBagConstraints gbc2 = new GridBagConstraints();
                gbc2.insets.set(5, 10, 5, 10);
                gbc2.gridy = 0;
                stormVeilCastleButton = new Button("Storm Veil Castle");
                stormVeilCastleButton.setPreferredSize(new Dimension(350, 50));
                stormVeilCastleButton.addActionListener(new Controller(Controller.ActionState.STORM_VEIL_CASTLE, model, this));
                contentPnl.add(stormVeilCastleButton, gbc2);

                gbc2.gridy++;
                rayaLucariaAcademyButton = new Button("Raya Lucaria Academy");
                rayaLucariaAcademyButton.setPreferredSize(new Dimension(350, 50));
                rayaLucariaAcademyButton.addActionListener(new Controller(Controller.ActionState.RAYA_LUCARIA_ACADEMY, model, this));
                contentPnl.add(rayaLucariaAcademyButton, gbc2);

                gbc2.gridy++;
                theEldenThroneButton = new Button("The Elden Throne");
                theEldenThroneButton.setPreferredSize(new Dimension(350, 50));
                theEldenThroneButton.addActionListener(new Controller(Controller.ActionState.THE_ELDEN_THRONE, model, this));
                contentPnl.add(theEldenThroneButton, gbc2);

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

        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }

        public Model getModel() {
            return model;
        }
    }

    public static class Controller implements ActionListener {
        private ActionState actionState;
        
        private Model model;
        private View view;

        public enum ActionState {
            STORM_VEIL_CASTLE,
            RAYA_LUCARIA_ACADEMY,
            THE_ELDEN_THRONE,
            BACK
        }

        public Controller(ActionState actionState, Model model, View view) {
            super();
            this.actionState = actionState;
            this.view = view;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionState) {
                case STORM_VEIL_CASTLE:
                    model.getPlayerInfo().firePropertyChange("selectedAreaFloor", null, 0);
                    model.getPlayerInfo().setCurrentHealth(model.getPlayerInfo().getMaximumHealth());
                    view.getFrame().setView("Area");
                    break;
                case RAYA_LUCARIA_ACADEMY:
                    model.getPlayerInfo().firePropertyChange("selectedAreaFloor", null, 1);
                    model.getPlayerInfo().setCurrentHealth(model.getPlayerInfo().getMaximumHealth());
                    view.getFrame().setView("Area");
                    break;
                case THE_ELDEN_THRONE:
                    if (model.getPlayerInfo().isFirstBossDefeated() && model.getPlayerInfo().isSecondBossDefeated()) {
                        model.getPlayerInfo().firePropertyChange("selectedAreaFloor", null, 2);
                        model.getPlayerInfo().setCurrentHealth(model.getPlayerInfo().getMaximumHealth());
                        view.getFrame().setView("Area");
                    } else {
                        JOptionPane.showMessageDialog(view, "You must defeat the first and second boss before you can enter The Elden Throne.");
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

