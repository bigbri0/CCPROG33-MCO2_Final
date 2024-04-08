package View;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.border.*;

import controllers.BattleController;
import forms.Battle;
import forms.MainMenu;

import java.util.List;

import util.custom.Button;
import util.custom.ProgressBar;
import util.custom.UIHelper;
import util.PlayerInfo;

public class BattleView extends JPanel {
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

    private Battle model;

    public BattleView(PlayerInfo playerInfo) {
        model = new Battle();
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
                    strengthButton.addActionListener(new BattleController(BattleController.ActionState.STRENGTH, model, this));
                    actionButtonPanel.add(strengthButton, actionButtonGbc);

                    actionButtonGbc.gridy++;
                    actionButtonGbc.insets.set(5, 5, 5, 5);
                    intelligenceButton = new Button("Intelligence");
                    intelligenceButton
                            .addActionListener(new BattleController(BattleController.ActionState.INTELLIGENCE, model, this));
                    actionButtonPanel.add(intelligenceButton, actionButtonGbc);

                    actionButtonGbc.gridy++;
                    actionButtonGbc.insets.set(5, 5, 5, 5);
                    faithButton = new Button("Faith");
                    faithButton.addActionListener(new BattleController(BattleController.ActionState.FAITH, model, this));
                    actionButtonPanel.add(faithButton, actionButtonGbc);
                }

                actionGbc.gridy++;
                actionGbc.anchor = GridBagConstraints.CENTER;
                actionGbc.fill = GridBagConstraints.HORIZONTAL;
                actionGbc.insets.set(10, 10, 10, 10);
                dodgeButton = new Button("Dodge");
                dodgeButton.addActionListener(new BattleController(BattleController.ActionState.DODGE, model, this));
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

    public Battle getModel() {
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
