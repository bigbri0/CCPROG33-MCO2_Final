package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.CharacterCreationController;
import controllers.CharacterCreationController.ActionState;
import forms.CharacterCreation;
import forms.MainMenu;
import forms.MainMenu.View;
import util.custom.Button;
import util.custom.TextField;
import util.custom.UIHelper;
import util.GameStats;
import util.JobClass;
import util.PlayerInfo;
/**
     * The View class is responsible for the visual representation of the Character Creation screen.
     */
    public class CharacterCreationView extends JPanel {
        private JLabel titleLbl, subtitleLbl, nameLabel, jobClassName, jobClassInfo, jobClassImage;
        private JPanel headerPnl;
        private JPanel contentPnl;
        public TextField nameField;
        private JPanel jobClassPanel;
        private Button jobClassNextButton, jobClassPreviousButton, backBtn;
        private Button continueButton;
        private CharacterCreation model;

        /**
         * Constructor for the View class.
         * It initializes the components of the Character Creation screen.
         *
         * @param playerInfo the player's information
         */
        public CharacterCreationView(PlayerInfo playerInfo) {
            model = new CharacterCreation();
            model.setPlayerInfo(playerInfo);

            initComponents();

        }
        /**
         * This method initializes the components of the Character Creation screen.
         */
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
                backBtn.addActionListener(new CharacterCreationController(CharacterCreationController.ActionState.BACK, model, this));
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
                subtitleLbl.setText("choose character JobClass");
                subtitleLbl.setFont(new Font("Mantinia", Font.BOLD, 28));
                subtitleLbl.setForeground(UIHelper.colorPrimary);
                headerPnl.add(subtitleLbl, gbcHeader);

                gbcHeader.gridx++;
                gbcHeader.gridy = 0;
                gbcHeader.weightx = 0;
                gbcHeader.gridheight = 2;
                gbcHeader.insets.set(10, 40, 10, 40);
                continueButton = new Button("START") {
                    @Override
                    protected void paintComponent(Graphics grphcs) {
                        super.paintComponent(grphcs);

                        Graphics2D g = (Graphics2D) grphcs;
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                        int[] x = { 80, 100, 80 };
                        int[] y = { 15, 25, 35 };
                        g.setColor(UIHelper.colorOnPrimaryContainer);
                        g.fillPolygon(x, y, 3);

                    }
                };
                continueButton.setFont(new Font("Mantinia", Font.BOLD, 14));
                continueButton.addActionListener(new CharacterCreationController(CharacterCreationController.ActionState.CONTINUE, model, this));
                continueButton.setPreferredSize(new Dimension(115, 50));
                continueButton.setHorizontalAlignment(JLabel.LEFT);
                headerPnl.add(continueButton, gbcHeader);

            }

            gbc.insets.set(-30, 0, 0, 0);
            gbc.weighty = 1;
            gbc.gridy++;
            contentPnl = new JPanel() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    g.setColor(UIHelper.colorOnSecondaryContainer);
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
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
            jobClassName = new JLabel();
            jobClassName.setFont(new Font("Mantinia", 1, 25));
            jobClassName.setForeground(UIHelper.colorOnPrimary);
            contentPnl.add(jobClassName, gbcContainer);

            gbcContainer.insets.set(0, 10, 20, 10);
            gbcContainer.gridy++;
            gbcContainer.gridx = 1;
            gbcContainer.weighty = 1;
            gbcContainer.gridwidth = 1;
            jobClassPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    super.paintComponent(grphcs);

                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    g.setColor(UIHelper.colorOnSecondary);
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                }
            };
            jobClassPanel.setOpaque(false);
            jobClassPanel.setPreferredSize(new Dimension(510, 335));
            jobClassPanel.setLayout(new GridBagLayout());
            contentPnl.add(jobClassPanel, gbcContainer);

            gbcContainer.gridx = 0;
            gbcContainer.weightx = 0;
            gbcContainer.weighty = 0;
            gbcContainer.insets.set(30, 30, 15, 5);
            jobClassPreviousButton = new Button() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    super.paintComponent(grphcs);

                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    int[] x = { 35, 15, 35 };
                    int[] y = { 15, 25, 35 };
                    g.setColor(UIHelper.colorOnPrimaryContainer);
                    g.fillPolygon(x, y, 3);
                }
            };
            jobClassPreviousButton.setPreferredSize(new Dimension(50, 50));
            jobClassPreviousButton.addActionListener(new CharacterCreationController(CharacterCreationController.ActionState.PREVIOUS, model, this));
            contentPnl.add(jobClassPreviousButton, gbcContainer);

            gbcContainer.gridx = 2;
            gbcContainer.anchor = GridBagConstraints.EAST;
            gbcContainer.insets.set(30, 5, 15, 30);
            jobClassNextButton = new Button() {
                @Override
                protected void paintComponent(Graphics grphcs) {
                    super.paintComponent(grphcs);

                    Graphics2D g = (Graphics2D) grphcs;
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    int[] x = { 15, 35, 15 };
                    int[] y = { 15, 25, 35 };
                    g.setColor(UIHelper.colorOnPrimaryContainer);
                    g.fillPolygon(x, y, 3);
                }
            };
            jobClassNextButton.setPreferredSize(new Dimension(50, 50));
            jobClassNextButton.addActionListener(new CharacterCreationController(CharacterCreationController.ActionState.NEXT, model, this));
            contentPnl.add(jobClassNextButton, gbcContainer);

            GridBagConstraints gbcJobClassPanel = new GridBagConstraints();
            gbcJobClassPanel.gridy = 0;
            gbcJobClassPanel.gridx = 0;
            gbcJobClassPanel.gridwidth = 1;
            gbcJobClassPanel.weightx = 0;
            gbcJobClassPanel.weighty = 1;
            gbcJobClassPanel.insets.set(0, 140, 0, 0);
            jobClassImage = new JLabel();
            jobClassPanel.add(jobClassImage, gbcJobClassPanel);

            gbcJobClassPanel.insets.set(0, 0, 0, 0);
            gbcJobClassPanel.fill = GridBagConstraints.HORIZONTAL;
            gbcJobClassPanel.anchor = GridBagConstraints.EAST;
            jobClassInfo = new JLabel();
            jobClassInfo.setFont(new Font("Mantinia", Font.PLAIN, 20));
            jobClassInfo.setForeground(UIHelper.colorOnPrimaryContainer);
            jobClassPanel.add(jobClassInfo, gbcJobClassPanel);

            gbcContainer.gridy++;
            gbcContainer.gridx = 0;
            gbcContainer.anchor = GridBagConstraints.CENTER;
            gbcContainer.insets.set(0, 10, 5, 10);
            gbcContainer.gridwidth = 3;
            nameLabel = new JLabel("Player Name");
            nameLabel.setFont(new Font("Mantinia", Font.BOLD, 20));
            nameLabel.setForeground(UIHelper.colorOnPrimary);
            contentPnl.add(nameLabel, gbcContainer);

            gbcContainer.insets.set(0, 10, 30, 10);
            gbcContainer.gridy++;
            nameField = new TextField();
            nameField.setHorizontalAlignment(JLabel.CENTER);
            nameField.setPreferredSize(new Dimension(500, 50));
            contentPnl.add(nameField, gbcContainer);

            updateJobClass();

        }
        /**
         * This method updates the job class displayed on the screen.
         */
        public void updateJobClass() {
            JobClass jobClass = model.getJobClass();
            ImageIcon icon = new ImageIcon("src/main/resources/Images/" + jobClass.getName() + ".png");
            jobClassImage.setIcon(icon);
            jobClassName.setText(jobClass.getName());
            jobClassInfo.setText(
                "<html> Health: <b>%d</b><br> Dexterity: <b>%d</b><br> Intelligence: <b>%d</b><br> Endurance: <b>%d</b><br> Strength: <b>%d</b><br> Faith: <b>%d</b>".formatted(
                    jobClass.getHealth(), 
                    jobClass.getDexterity(), 
                    jobClass.getIntelligence(), 
                    jobClass.getEndurance(), 
                    jobClass.getStrength(), 
                    jobClass.getFaith()));
        }

        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }

        public CharacterCreation getModel() {
            return model;
        }

    }

