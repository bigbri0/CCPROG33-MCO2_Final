package com.eldenrouge.forms;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import com.eldenrouge.util.custom.Button;
import com.eldenrouge.util.custom.ProgressBar;
import com.eldenrouge.util.custom.UIHelper;
import com.eldenrouge.util.PlayerInfo;
/**
 * This class represents the Battle screen of the EldenRouge game.
 * It allows the player to engage in battles with enemies.
 * @Authors Roj Friginal, Brian Santamaria

 */
public class Battle {
    /**
     * The Model class holds the data for the Battle screen.
     */
    public static class Model {
        private PlayerInfo playerInfo;
        public List<List> enemyType;
        public List<List> bossList;

        public String enemyName;
        public int enemyHealth;
        public int maxEnemyHealth;
        public int enemyAttack;
        public double enemyStrengthDefense;
        public double enemyIntelligenceDefense;
        public double enemyFaithDefense;

        private int nextEnemyAttack;
        
        public int playerHealth;
        public int playerAttack;
        public double playerStrengthDefense;
        public double playerIntelligenceDefense;
        public double playerFaithDefense;

        public boolean isPlayerTurn;
        public boolean isBoss;

        public double enemyStrength = 1;


        public PropertyChangeSupport propertyChangeSupport;
        
        public Model() {
            enemyType = List.of(
                List.of(
                    List.of("Godrick Soldier", "Living Jar"),
                    List.of(20, 30), List.of(70, 80), 0.20, 0.15, 0.10),
                List.of(
                    List.of("Godrick Archer", "Glintstone Sorcerer"),
                    List.of(25, 35), List.of(110, 120), 0.50, 0.15, 0.30),
                List.of(
                    List.of("Godrick Knight", "Battlemage"),
                    List.of(70, 80), List.of(120, 130), 0.25, 0.25, 0.20)
            );
            bossList = List.of(
                List.of(
                    "Godrick the Grafted",
                    200, List.of(100, 300), 0.35, 0.20, 0.15),
                List.of(
                    "Rennala, Queen of the Full Moon",
                    400, List.of(200, 300), 0.15, 0.35, 0.25),
                List.of(
                    "The Elden Beast",
                    800, List.of(250, 500), 0.25, 0.50, 0.40)
            );

            propertyChangeSupport = new PropertyChangeSupport(this);

        }
        /**
         * This method sets the enemy for the battle.
         * @param floor the floor of the area
         */
        public void setBossEnemy(int floor) {
            List enemy = bossList.get((int) (Math.random() * bossList.size()));

            enemyName = (String) enemy.get(0);
            enemyHealth = (int) enemy.get(1);
            maxEnemyHealth = enemyHealth;
            enemyAttack = 0;
            enemyStrengthDefense = (double) enemy.get(3);
            enemyIntelligenceDefense = (double) enemy.get(4);
            enemyFaithDefense = (double) enemy.get(5);

            propertyChangeSupport.firePropertyChange("enemy", null, null);

            isPlayerTurn = true;

        }
        /**
         * This method randomizes the enemy for the battle.
         * @param floor the floor of the area
         */
        public void randomizeEnemy(int floor) {
            List enemy = enemyType.get((int) (Math.random() * enemyType.size()));

            if (floor != 2) {
                enemyName = (String) ((List)enemy.get(0)).get(floor);
                int enemyHealthMin = (int) ((List)enemy.get(1)).get(0);
                int enemyHealthMax = (int) ((List)enemy.get(1)).get(1);
                enemyHealth = (int) (enemyHealthMin + Math.random() * (enemyHealthMax - enemyHealthMin)) * (floor + 1);
                maxEnemyHealth = enemyHealth;

            }
            enemyAttack = 0;
            enemyStrengthDefense = (double) enemy.get(3);
            enemyIntelligenceDefense = (double) enemy.get(4);
            enemyFaithDefense = (double) enemy.get(5);

            propertyChangeSupport.firePropertyChange("enemy", null, null);

            isPlayerTurn = true;


        }

        /**
         * This method Gets the enemy attack for the battle.
         */
        public int getEnemyAttack() {
            List enemy = enemyType.get((int) (Math.random() * enemyType.size()));

            if (playerInfo.getSelectedAreaFloor() == 0) {
                enemy = bossList.get((int) (Math.random() * bossList.size()));
            }
            else if (playerInfo.getSelectedAreaFloor() == 1) {
                enemy = bossList.get((int) 2);
            }

            int enemyAttackMin = (int) ((List) enemy.get(2)).get(0);
            int enemyAttackMax = (int) ((List) enemy.get(2)).get(1);

            int attack = (int) (enemyAttackMin
                    + Math.random() * (enemyAttackMax - enemyAttackMin) * (playerInfo.getSelectedAreaFloor() + 1));

            attack *= enemyStrength;

            return attack;
        }
        /**
         * This method sets the enemy attack for the battle.
         */
        public int enemyAttack() {
            List enemy = enemyType.get((int) (Math.random() * enemyType.size()));

            if (playerInfo.getSelectedAreaFloor() == 2){
                enemy = bossList.get((int) 3);
            }

            int enemyAttackMin = (int) ((List) enemy.get(2)).get(0);
            int enemyAttackMax = (int) ((List) enemy.get(2)).get(2);

            int attack = (int) (enemyAttackMin + Math.random() * (enemyAttackMax - enemyAttackMin) * (playerInfo.getSelectedAreaFloor()+1));

            attack *= enemyStrength;
            playerInfo.setCurrentHealth(playerInfo.getCurrentHealth() - attack);

            return attack;
        }
        /**
         * This method sets the enemy attack for the battle.
         * */
        public int enemyAttack(int attack) {
            playerInfo.setCurrentHealth(playerInfo.getCurrentHealth() - attack);

            return attack;
        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
            propertyChangeSupport.firePropertyChange("playerInfo", null, null);
        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }

        public boolean isPlayerTurn() {
            return isPlayerTurn;
        }


        public String getEnemyName() {
            return enemyName;
        }

        public int getEnemyHealth() {
            return enemyHealth;
        }

        public int getMaximumEnemyHealth() {
            return maxEnemyHealth;
        }

        public double getEnemyStrengthDefense() {
            return enemyStrengthDefense;
        }

        public double getEnemyIntelligenceDefense() {
            return enemyIntelligenceDefense;
        }

        public double getEnemyFaithDefense() {
            return enemyFaithDefense;
        }

        public String getPlayerName() {
            return playerInfo.getName();
        }

        public int getPlayerHealth() {
            return ((playerInfo.getJobClass().getHealth()
                    + playerInfo.getEquippedWeapon().getHealth()) * 100);

        }

        public int getPlayerStrengthDamage() {
            return (int)((playerInfo.getJobClass().getStrength() + playerInfo.getEquippedWeapon().getStrength()) * (1 - getEnemyStrengthDefense()));
        }

        public int getPlayerIntelligenceDamage() {
            return (int)((playerInfo.getJobClass().getIntelligence() + playerInfo.getEquippedWeapon().getIntelligence()) * (1 - getEnemyIntelligenceDefense()));
        }

        public int getPlayerFaithDamage() {
            return (int)((playerInfo.getJobClass().getFaith() + playerInfo.getEquippedWeapon().getDexterity()) * (1 - getEnemyFaithDefense()));
        }


        public boolean isNextEnemyAttackDodged() {
            int dodgeRateCheck = (int) (Math.random() * 100);
            int dodgeRate = (20 + playerInfo.getJobClass().getEndurance() + playerInfo.getEquippedWeapon().getEndurance()) / 2;
            return dodgeRateCheck <= dodgeRate;
        }

        public int playerStrengthAttack() {
            enemyHealth -= getPlayerStrengthDamage();
            propertyChangeSupport.firePropertyChange("playerStrengthAttack", null, enemyHealth);

            return getPlayerStrengthDamage();
        }
        public int playerIntelligenceAttack() {
            enemyHealth -= getPlayerIntelligenceDamage();
            propertyChangeSupport.firePropertyChange("playerIntelligenceAttack", null, enemyHealth);

            return getPlayerIntelligenceDamage();
        }

        public int playerFaithAttack() {
            enemyHealth -= getPlayerFaithDamage();
            propertyChangeSupport.firePropertyChange("playerFaithAttack", null, enemyHealth);

            return getPlayerFaithDamage();
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.removePropertyChangeListener(listener);
        }


    }

    public static class View extends JPanel {
        private JLabel titleLbl;
        private JLabel subtitleLbl;

        private JPanel contentPnl;
        private JPanel playerPanel;
        private JLabel playerInfo;
        private JLabel playerImageLabel;
        private JLabel playerName;
        private ProgressBar playerHealthBar;

        private JPanel enemyPanel;
        private JLabel enemyInfo;
        private JLabel enemyImageLabel;
        private JLabel enemyNameLabel;
        private ProgressBar enemyHealthBar;

        private JPanel actionPanel;
        private JPanel actionButtonPanel;
        private JLabel actionButtonsLabel;
        private Button strengthButton;
        private Button intelligenceButton;
        private Button faithButton;
        private Button dodgeButton;

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
            gbc.insets.set(25, 0, 0, 0);
            titleLbl = new JLabel("Elden rougE");
            titleLbl.setFont(new Font("Mantinia", Font.BOLD, 75));
            titleLbl.setForeground(UIHelper.colorTertiary);
            add(titleLbl, gbc);

            gbc.insets.set(-40, 0, 15, 0);
            gbc.gridy++;
            subtitleLbl = new JLabel("battle");
            subtitleLbl.setFont(new Font("Mantinia", Font.BOLD, 28));
            subtitleLbl.setForeground(UIHelper.colorPrimary);
            add(subtitleLbl, gbc);

            gbc.insets.set(-30, 0, 0, 0);
            gbc.weighty = 1;
            gbc.gridy++;
            contentPnl = new JPanel();
            contentPnl.setOpaque(false);
            contentPnl.setLayout(new GridBagLayout());
            add(contentPnl, gbc);

            GridBagConstraints playerGbc = new GridBagConstraints();

            playerGbc.gridx = 0;
            playerGbc.gridy = 0;
            playerGbc.weightx = 1;
            playerGbc.insets.set(0, 20, 0, 20);
            playerGbc.fill = GridBagConstraints.HORIZONTAL;
            playerPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    if (model.isPlayerTurn()) {
                        g2d.setColor(UIHelper.colorOnTertiaryContainer);
                        playerInfo.setForeground(UIHelper.colorOnTertiary);
                        playerInfo.setText("Player's Turn");
                    } else {
                        g2d.setColor(UIHelper.colorSurfaceVariant);
                        playerInfo.setForeground(UIHelper.colorSurface);
                        playerInfo.setText("Waiting for Player's Turn");
                    }
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 22, 22);

                    g2d.setColor(UIHelper.colorTertiaryContainer);
                    g2d.fillRoundRect(2, 45, getWidth()-4, getHeight()-52, 20, 20);

                    g2d.setColor(UIHelper.colorOnTertiary);
                    g2d.fillRoundRect(2, 90, getWidth()-4, getHeight()-92, 20, 20);

                }
            };
            playerPanel.setOpaque(false);
            playerPanel.setLayout(new GridBagLayout());
            playerPanel.setPreferredSize(new java.awt.Dimension(530, 520));
            contentPnl.add(playerPanel, playerGbc);
            {
                playerGbc.gridy++;
                playerGbc.gridwidth = 2;
                playerGbc.insets.set(10, 20, 10, 20);
                playerGbc.fill = GridBagConstraints.NONE;
                playerGbc.anchor = GridBagConstraints.CENTER;
                playerInfo = new JLabel("Player Info");
                playerInfo.setOpaque(false);
                playerInfo.setFont(new Font("Mantinia", 1, 20));
                playerPanel.add(playerInfo, playerGbc);

                playerGbc.gridy++;
                playerGbc.fill = GridBagConstraints.NONE;
                playerGbc.anchor = GridBagConstraints.CENTER;
                playerGbc.insets.set(5, 20, 10, 20);
                playerName = new JLabel();
                playerName.setFont(new Font("Mantinia", 1, 25));
                playerName.setForeground(UIHelper.colorTertiary);
                playerPanel.add(playerName, playerGbc);

                playerGbc.gridy++;
                playerGbc.gridwidth = 1;
                playerGbc.insets.set(0, 0, 0, 0);
                playerImageLabel = new JLabel();
                playerGbc.fill = GridBagConstraints.NONE;
                playerGbc.anchor = GridBagConstraints.CENTER;
                playerPanel.add(playerImageLabel, playerGbc);

                playerGbc.gridx = 1;
                playerGbc.weighty = 1;
                playerGbc.gridheight = 1;
                playerGbc.insets.set(0, 0, 0, 15);
                actionPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setColor(UIHelper.colorPrimary);
                        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    }
                };
                actionPanel.setOpaque(false);
                actionPanel.setLayout(new GridBagLayout());
                playerPanel.add(actionPanel, playerGbc);


                {
                    GridBagConstraints actionGbc = new GridBagConstraints();
                    actionGbc.gridx = 0;
                    actionGbc.gridy = 0;
                    actionGbc.insets.set(10, 10, 0, 10);
                    actionButtonsLabel = new JLabel("Attacks");
                    actionButtonsLabel.setFont(new Font("Mantinia", 1, 20));
                    actionButtonsLabel.setForeground(UIHelper.colorOnPrimary);
                    actionPanel.add(actionButtonsLabel, actionGbc);


                    actionGbc.gridy++;
                    actionButtonPanel = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2d = (Graphics2D) g;
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g2d.setColor(UIHelper.colorOnPrimaryContainer);
                            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                        }
                    };
                    actionButtonPanel.setOpaque(false);
                    actionButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
                    actionButtonPanel.setLayout(new GridBagLayout());
                    actionPanel.add(actionButtonPanel, actionGbc);

                    {
                        GridBagConstraints actionButtonGbc = new GridBagConstraints();
                        actionButtonGbc.gridy = 1;
                        actionButtonGbc.anchor = GridBagConstraints.CENTER;
                        actionButtonGbc.fill = GridBagConstraints.HORIZONTAL;
                        actionButtonGbc.weightx = 1;
                        actionButtonGbc.insets.set(5, 5, 5, 5);
                        actionButtonGbc.gridwidth = 1;
                        strengthButton = new Button("Strength");
                        strengthButton.addActionListener(new Controller(Controller.ActionState.STRENGTH, model, this));
                        actionButtonPanel.add(strengthButton, actionButtonGbc);

                        actionButtonGbc.gridy++;
                        actionButtonGbc.insets.set(5, 5, 5, 5);
                        intelligenceButton = new Button("Intelligence");
                        intelligenceButton
                                .addActionListener(new Controller(Controller.ActionState.INTELLIGENCE, model, this));
                        actionButtonPanel.add(intelligenceButton, actionButtonGbc);

                        actionButtonGbc.gridy++;
                        actionButtonGbc.insets.set(5, 5, 5, 5);
                        faithButton = new Button("Faith");
                        faithButton.addActionListener(new Controller(Controller.ActionState.FAITH, model, this));
                        actionButtonPanel.add(faithButton, actionButtonGbc);
                    }

                    actionGbc.gridy++;
                    actionGbc.anchor = GridBagConstraints.CENTER;
                    actionGbc.fill = GridBagConstraints.HORIZONTAL;
                    actionGbc.insets.set(10, 10, 10, 10);
                    dodgeButton = new Button("Dodge");
                    dodgeButton.addActionListener(new Controller(Controller.ActionState.DODGE, model, this));
                    actionPanel.add(dodgeButton, actionGbc);
                }

                playerGbc.gridy++;
                playerGbc.gridx = 0;
                playerGbc.gridwidth = 2;
                playerGbc.fill = GridBagConstraints.HORIZONTAL;
                playerHealthBar = new ProgressBar();
                playerGbc.insets.set(0, 20, 20, 20);
                playerHealthBar.setPreferredSize(new java.awt.Dimension(250, 40));
                playerPanel.add(playerHealthBar, playerGbc);



            }



            GridBagConstraints enemyGbc = new GridBagConstraints();

            enemyGbc.gridx = 1;
            enemyGbc.gridy = 0;
            enemyGbc.weightx = 1;
            enemyGbc.insets.set(0, 20, 0, 20);
            enemyGbc.fill = GridBagConstraints.HORIZONTAL;
            enemyPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    if (model.isPlayerTurn()) {
                        g2d.setColor(UIHelper.colorSurfaceVariant);
                        enemyInfo.setForeground(UIHelper.colorSurface);
                        enemyInfo.setText("Enemy's next attack: " + model.nextEnemyAttack);
                    } else {
                        g2d.setColor(UIHelper.colorPrimary);
                        enemyInfo.setForeground(UIHelper.colorOnPrimary);
                        enemyInfo.setText("Enemy's Turn");
                    }
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 22, 22);

                    g2d.setColor(UIHelper.colorOnPrimary);
                    g2d.fillRoundRect(2, 45, getWidth() - 4, getHeight() - 52, 20, 20);


                    g2d.setColor(UIHelper.colorPrimaryContainer);
                    g2d.fillRoundRect(2, 90, getWidth() - 4, getHeight() - 92, 20, 20);
                }
            };

            enemyPanel.setOpaque(false);
            enemyPanel.setLayout(new GridBagLayout());
            enemyPanel.setPreferredSize(new java.awt.Dimension(350, 520));
            contentPnl.add(enemyPanel, enemyGbc);
            {
                enemyGbc.gridy++;
                enemyGbc.insets.set(5, 20, 15, 20);
                enemyGbc.fill = GridBagConstraints.NONE;
                enemyGbc.anchor = GridBagConstraints.CENTER;
                enemyInfo = new JLabel("Waiting for Enemy's Turn");
                enemyInfo.setOpaque(false);
                enemyInfo.setFont(new Font("Mantinia", 1, 20));
                enemyPanel.add(enemyInfo, enemyGbc);


                enemyGbc.gridy++;
                enemyGbc.insets.set(0, 20, 5, 20);
                enemyGbc.fill = GridBagConstraints.NONE;
                enemyGbc.anchor = GridBagConstraints.CENTER;
                enemyNameLabel = new JLabel();
                enemyNameLabel.setFont(new Font("Mantinia", 1, 25));
                enemyNameLabel.setForeground(UIHelper.colorPrimary);
                enemyPanel.add(enemyNameLabel, enemyGbc);


                enemyGbc.gridy++;
                enemyGbc.insets.set(0, 2, 0, 2);
                enemyImageLabel = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2d = (Graphics2D) g;
                        Shape mask = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight() , 20, 20);
                        g2d.setClip(mask);
                        super.paintComponent(g2d);
                    }
                };
                enemyImageLabel.setHorizontalAlignment(JLabel.CENTER);
                enemyPanel.add(enemyImageLabel, enemyGbc);

                enemyGbc.gridy++;
                enemyGbc.insets.set(15, 20, 20, 20);
                enemyGbc.fill = GridBagConstraints.HORIZONTAL;
                enemyHealthBar = new ProgressBar();
                enemyHealthBar.setPreferredSize(new java.awt.Dimension(250, 40));
                enemyPanel.add(enemyHealthBar, enemyGbc);

            }







            model.addPropertyChangeListener(
                    evt -> {
                        playerImageLabel.setIcon(
                                new ImageIcon(
                                        new ImageIcon("src/main/resources/Images/"
                                                + model.getPlayerInfo().getJobClass().getName() + ".png")
                                                .getImage()
                                                .getScaledInstance(263, 350, Image.SCALE_SMOOTH)));

                        playerName.setText(model.getPlayerInfo().getName());
                        playerHealthBar.setValue(model.getPlayerInfo().getCurrentHealth());
                        playerHealthBar.setMaximum(model.getPlayerInfo().getMaximumHealth());
                        playerHealthBar.setString(Math.max(model.getPlayerInfo().getCurrentHealth(), 0) + " / "
                                + model.getPlayerInfo().getMaximumHealth());


                        enemyNameLabel.setText(model.getEnemyName());
                        enemyHealthBar.setValue(model.getEnemyHealth());
                        enemyHealthBar.setMaximum(model.getMaximumEnemyHealth());
                        enemyHealthBar.setString(Math.max(model.getEnemyHealth(), 0) + " / " + model.getMaximumEnemyHealth());

                    });

            model.getPlayerInfo().addPropertyChangeListener(
                    evt -> {
                        if (evt.getPropertyName().equals("playerPosition")) {
                            if (((List)evt.getNewValue()).get(1).equals("SE")||
                                ((List) evt.getNewValue()).get(1).equals("BE")){

                                if (((List)evt.getNewValue()).get(1).equals("SE")){
                                    model.randomizeEnemy((int) ((List) evt.getNewValue()).get(3));
                                    enemyImageLabel.setIcon(
                                            new ImageIcon(
                                                    new ImageIcon("src/main/resources/Images/"
                                                            + model.enemyName + ".png")
                                                            .getImage()
                                                            .getScaledInstance(210, 350, Image.SCALE_SMOOTH)));
                                } else if(((List)evt.getNewValue()).get(1).equals("BE")) {
                                    model.setBossEnemy(model.getPlayerInfo().getSelectedAreaFloor());
                                    enemyImageLabel.setIcon(
                                            new ImageIcon(
                                                    new ImageIcon("src/main/resources/Images/"
                                                            + model.enemyName + ".png")
                                                            .getImage()
                                                            .getScaledInstance(346, 355, Image.SCALE_SMOOTH)));
                                }


                                enableButtons(true);

                                playerImageLabel.setIcon(
                                        new ImageIcon(
                                                new ImageIcon("src/main/resources/Images/"
                                                        + model.getPlayerInfo().getJobClass().getName() + ".png")
                                                        .getImage()
                                                        .getScaledInstance(263, 350, Image.SCALE_SMOOTH)));

                                playerName.setText(model.getPlayerInfo().getName());
                                playerHealthBar.setValue(model.getPlayerInfo().getCurrentHealth());
                                playerHealthBar.setMaximum(model.getPlayerInfo().getMaximumHealth());
                                playerHealthBar.setString(Math.max(model.getPlayerInfo().getCurrentHealth(), 0) + " / "
                                        + model.getPlayerInfo().getMaximumHealth());


                                enemyNameLabel.setText(model.getEnemyName());
                                enemyHealthBar.setValue(model.getEnemyHealth());
                                enemyHealthBar.setMaximum(model.getMaximumEnemyHealth());
                                enemyHealthBar.setString(
                                        Math.max(model.getEnemyHealth(), 0) + " / " + model.getMaximumEnemyHealth());

                                getFrame().getPlayerInfo().setRunes(model.getPlayerInfo().getRunes());

                            }
                        } else if (evt.getPropertyName().equals("currentHealth") || evt.getPropertyName()
                                .equals("selectedAreaFloor")) {
                            playerName.setText(model.getPlayerInfo().getName());
                            playerHealthBar.setValue(model.getPlayerInfo().getCurrentHealth());
                            playerHealthBar.setMaximum(model.getPlayerInfo().getMaximumHealth());
                            playerHealthBar.setString(Math.max(model.getPlayerInfo().getCurrentHealth(), 0) + " / "
                                    + model.getPlayerInfo().getMaximumHealth());

                        }

                    }

            );
        }

        public Model getModel() {
            return model;
        }


        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }

        public void enableButtons(boolean enable) {
            strengthButton.setEnabled(enable);
            intelligenceButton.setEnabled(enable);
            faithButton.setEnabled(enable);
            dodgeButton.setEnabled(enable);
        }

    }

    public static class Controller implements ActionListener {
        private ActionState actionState;

        private Model model;
        private View view;
        private int isDodgedSuccess;

        public enum ActionState {
            STRENGTH,
            INTELLIGENCE,
            FAITH,
            DODGE,
            BACK
        }

        public Controller(ActionState actionState, Model model, View view) {
            super();
            this.view = view;
            this.model = model;

            this.actionState = actionState;
            this.isDodgedSuccess = -1;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (actionState == ActionState.STRENGTH || actionState == ActionState.INTELLIGENCE || actionState == ActionState.FAITH || actionState == ActionState.DODGE) {

                if (actionState == ActionState.STRENGTH) {
                    model.playerStrengthAttack();
                } else if (actionState == ActionState.INTELLIGENCE) {
                    model.playerIntelligenceAttack();
                } else if (actionState == ActionState.FAITH) {
                    model.playerFaithAttack();
                } else if (actionState == ActionState.DODGE) {
                    isDodgedSuccess = model.isNextEnemyAttackDodged() ? 1 : 0;
                }
                model.isPlayerTurn = false;
                view.enableButtons(false);
                model.nextEnemyAttack = model.getEnemyAttack();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (isDodgedSuccess == 1) {
                            isDodgedSuccess = -1;
                            JOptionPane.showMessageDialog(view, "Enemy Attack Dodged", "Dodged", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            model.enemyAttack(model.nextEnemyAttack);
                            model.isPlayerTurn = true;
                            isDodgedSuccess = -1;

                        }
                        view.enableButtons(true);

                        view.repaint();

                        if (model.getPlayerInfo().getCurrentHealth() <= 0) {
                            JOptionPane.showMessageDialog(view, "Player Died", "Game Over. You'll lose all of your runes", JOptionPane.INFORMATION_MESSAGE);
                            view.getFrame().getPlayerInfo().firePropertyChange("playerDied", null, null);
                            view.getFrame().getPlayerInfo().setRunes(0);
                            view.getFrame().setView("GameLobby");
                            timer.cancel();

                            return;

                        }

                    }
                }, (int)(300 + Math.random() * 1300));

                view.repaint();
                if (model.getEnemyHealth() <= 0) {
                    int runes = model.getMaximumEnemyHealth() * (model.isBoss ? 5 : 2);
                    timer.cancel();
                    JOptionPane.showMessageDialog(view, "Enemy Died \nYou gained " + runes + " runes", "Victory", JOptionPane.INFORMATION_MESSAGE);
                    view.getFrame().setView("Area");

                    view.getFrame().getPlayerInfo().firePropertyChange("enemyDied", null, null);
                    model.getPlayerInfo().addRunes(runes);
                    return;
                    
                }
                    
            }

        }

        public View getView() {
            return view;
        }
    }
}
