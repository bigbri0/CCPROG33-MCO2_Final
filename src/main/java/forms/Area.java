package forms;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.List;

import javax.swing.*;

import util.custom.Button;
import util.custom.Tile;
import util.custom.UIHelper;
import util.PlayerInfo;

public class Area {
    public static class Model {
        private PlayerInfo playerInfo;
        private Tile tile;

        public Model() {
            tile = new Tile(UIHelper.mapData, UIHelper.mapDataDimensions);

        }

        public Tile getTile() {
            return tile;
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
        private JPanel playerInfoPnl;
        private Model model;

        private JLabel playerImg;
        private JLabel playerName;
        private JLabel playerInfoLbl;

        private JPanel btnPnl;
        private Tile tile;
        private Button upBtn;
        private Button downBtn;
        private Button leftBtn;
        private Button rightBtn;

        private Button backBtn;

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
            gbc.insets.set(-30, 0, 0, 0);
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

                    g.setColor(UIHelper.colorOnTertiary);
                    g.fillRoundRect(-20, 0, getWidth() + 20, getHeight()-200, 20, 20);

                    g.setColor(UIHelper.colorTertiary);
                    g.fillRoundRect(-20, 45, getWidth() + 20, getHeight() - 55, 20, 20);

                    g.setColor(UIHelper.colorOnTertiaryContainer);
                    g.fillRoundRect(-20, 45, getWidth() + 20, getHeight() - 235, 20, 20);

                }
            };
            playerInfoPnl.setOpaque(false);
            playerInfoPnl.setLayout(new GridBagLayout());
            add(playerInfoPnl, gbc);

            {

                GridBagConstraints gbcPlayerInfo = new GridBagConstraints();

                gbcPlayerInfo.gridy = 0;
                gbcPlayerInfo.gridwidth = 2;
                gbcPlayerInfo.anchor = GridBagConstraints.CENTER;
                gbcPlayerInfo.insets.set(2, 10, 1, 10);
                playerName = new JLabel();
                playerName.setFont(new Font("Mantinia", 1, 30));
                playerName.setForeground(UIHelper.colorOnTertiaryContainer);
                playerInfoPnl.add(playerName, gbcPlayerInfo);

                gbcPlayerInfo.gridy = 1;
                gbcPlayerInfo.gridwidth = 1;
                gbcPlayerInfo.insets.set(0, 50, 2, 30);
                gbcPlayerInfo.anchor = GridBagConstraints.WEST;
                playerInfoLbl = new JLabel();
                playerInfoLbl.setFont(new Font("Mantinia", Font.PLAIN, 20));
                playerInfoLbl.setForeground(UIHelper.colorTertiaryContainer);
                playerInfoLbl.setPreferredSize(new Dimension(160, 300));
                playerInfoPnl.add(playerInfoLbl, gbcPlayerInfo);

                gbcPlayerInfo.gridx = 1;
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


                gbcPlayerInfo.gridy = 2;
                gbcPlayerInfo.gridx = 0;
                gbcPlayerInfo.gridwidth = 2;
                gbcPlayerInfo.anchor = GridBagConstraints.CENTER;
                gbcPlayerInfo.insets.set(15, 10, 25, 10);
                btnPnl = new JPanel(new GridLayout(3, 3));
                btnPnl.setOpaque(false);
                btnPnl.setBackground(null);
                btnPnl.setPreferredSize(new Dimension(150, 150));
                playerInfoPnl.add(btnPnl, gbcPlayerInfo);
                {
                    upBtn = new Button() {
                        @Override
                        protected void paintComponent(Graphics grphcs) {
                            super.paintComponent(grphcs);

                            Graphics2D g = (Graphics2D) grphcs;
                            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                            int[] x = { 15, 35, 25 };
                            int[] y = { 35, 35, 15 };
                            g.setColor(UIHelper.colorOnPrimaryContainer);
                            g.fillPolygon(x, y, 3);

                        }
                    };
                    downBtn = new Button() {
                        @Override
                        protected void paintComponent(Graphics grphcs) {
                            super.paintComponent(grphcs);

                            Graphics2D g = (Graphics2D) grphcs;
                            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                            int[] x = { 15, 35, 25 };
                            int[] y = { 15, 15, 35 };
                            g.setColor(UIHelper.colorOnPrimaryContainer);
                            g.fillPolygon(x, y, 3);

                        }
                    };
                    leftBtn = new Button() {
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
                    rightBtn = new Button() {
                        @Override
                        protected void paintComponent(Graphics grphcs) {
                            super.paintComponent(grphcs);

                            Graphics2D g = (Graphics2D) grphcs;
                            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                            int[] x = { 15, 35, 15 };
                            int[] y = { 15, 25, 35 };
                            g.setColor(UIHelper.colorOnPrimaryContainer);
                            g.fillPolygon(x, y, 3);

                        }
                    };

                    upBtn.addActionListener(new Controller(Controller.ActionState.NAVIGATE_UP, model, this));
                    upBtn.setOpaque(false);
                    downBtn.addActionListener(new Controller(Controller.ActionState.NAVIGATE_DOWN, model, this));
                    downBtn.setOpaque(false);
                    leftBtn.addActionListener(new Controller(Controller.ActionState.NAVIGATE_LEFT, model, this));
                    leftBtn.setOpaque(false);
                    rightBtn.addActionListener(new Controller(Controller.ActionState.NAVIGATE_RIGHT, model, this));
                    rightBtn.setOpaque(false);

                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setOpaque(false);
                    emptyPanel.setBackground(null);
                    btnPnl.add(emptyPanel);

                    btnPnl.add(upBtn);
                    emptyPanel = new JPanel();
                    emptyPanel.setOpaque(false);
                    emptyPanel.setBackground(null);
                    btnPnl.add(emptyPanel);

                    btnPnl.add(leftBtn);
                    emptyPanel = new JPanel();
                    emptyPanel.setOpaque(false);
                    emptyPanel.setBackground(null);
                    btnPnl.add(emptyPanel);

                    btnPnl.add(rightBtn);
                    emptyPanel = new JPanel();
                    emptyPanel.setOpaque(false);
                    emptyPanel.setBackground(null);
                    btnPnl.add(emptyPanel);

                    btnPnl.add(downBtn);
                    emptyPanel = new JPanel();
                    emptyPanel.setOpaque(false);
                    emptyPanel.setBackground(null);
                    btnPnl.add(emptyPanel);

                }

            }

            gbc.insets.set(-30, 0, 0, 0);
            gbc.weighty = 1;
            gbc.gridx = 1;
            tile = model.getTile();
            tile.goToFloor(0);
            add(tile, gbc);
          

            tile.addPropertyChangeListener(e -> {
                if (e.getPropertyName().equals("playerPosition")){
                    if (((List)e.getNewValue()).get(1).equals("SE")){
                        new Controller(Controller.ActionState.SPAWN_PASSED, model, this).actionPerformed(null);
                    } else if (((List)e.getNewValue()).get(1).equals("BE")){
                        new Controller(Controller.ActionState.BOSS_PASSED, model, this).actionPerformed(null);
                    } else if (((List)e.getNewValue()).get(1).equals("C")){
                        new Controller(Controller.ActionState.CREDITS, model, this).actionPerformed(null);
                    } 

                    model.getPlayerInfo().firePropertyChange("playerPosition", null, e.getNewValue());
                }
                update();
            });

            model.getPlayerInfo().addPropertyChangeListener(e -> {
                if (e.getPropertyName().equals("selectedAreaFloor")){
                    if ((int)e.getNewValue() == 2){
                        if (model.getPlayerInfo().isFirstBossDefeated() && model.getPlayerInfo().isSecondBossDefeated()){
                            tile.goToFloor((int)e.getNewValue());
                        } else {
                            JOptionPane.showMessageDialog(this,
                                        "You must defeat previous bosses before you can enter The Elden Throne.");
                            new Controller(Controller.ActionState.BACK, model, this).actionPerformed(null);
                        }
                    }
                    tile.goToFloor((int)e.getNewValue());
                } else if (e.getPropertyName().equals("currentHealth") && (int)e.getNewValue() <= 0){
                    model.getTile().resetAllSpawn();
                    model.getPlayerInfo().setRunes(0);
                } 

                update();
            });


        }

        public void update(){
            
            playerName.setText(model.getPlayerInfo().getName());
            playerImg.setIcon(
                new ImageIcon(
                    new ImageIcon("src/main/resources/Images/"
                            + model.getPlayerInfo().getJobClass().getName() + ".png")
                            .getImage()
                            .getScaledInstance(242, 302, Image.SCALE_SMOOTH)));
            playerInfoLbl.setText(
                String.format(
                    "<html>Position: <b>(%d,%d)</b> <br> Row: <b>%d</b> <br>  Column: <b>%d</b> <br> Door: <b>%d</b>  <br> Floor: <b>%d</b>  <br> Health: <b>%d</b> <br> Level: <b>%d</b> <br> Rune: <b>%d</b>", 
                    tile.getPlayerPosition().x,
                    tile.getPlayerPosition().y,
                    (int) tile.getCurrentTileDimensions().getWidth(),
                    (int) tile.getCurrentTileDimensions().getHeight(),
                    tile.getCurrentDoor(),
                    tile.getCurrentFloor(),
                    model.getPlayerInfo().getCurrentHealth(),
                    model.getPlayerInfo().getLevel(),
                    model.getPlayerInfo().getRunes()));
           
            switch (tile.getCurrentFloor()) {
                case 0:
                    subtitleLbl.setText("storm veil castle");
                    break;

                case 1:
                    subtitleLbl.setText("raya lucaria academy");
                    break;

                case 2:
                    subtitleLbl.setText("the elden throne");
                    break;
             
                default:
                    break;
            }
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
            NAVIGATE_UP,
            NAVIGATE_DOWN,
            NAVIGATE_LEFT,
            NAVIGATE_RIGHT,
            SPAWN_PASSED,
            BOSS_PASSED,
            CREDITS,
            BACK,

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

                case NAVIGATE_UP:
                    model.getTile().moveUp();
                    model.getPlayerInfo().setSelectedAreaFloor(model.getTile().getCurrentFloor());
                    break;
                case NAVIGATE_DOWN:
                    model.getTile().moveDown();
                    model.getPlayerInfo().setSelectedAreaFloor(model.getTile().getCurrentFloor());
                    break;
                case NAVIGATE_LEFT:
                    model.getTile().moveLeft();
                    model.getPlayerInfo().setSelectedAreaFloor(model.getTile().getCurrentFloor());
                    break;
                case NAVIGATE_RIGHT:
                    model.getTile().moveRight();
                    model.getPlayerInfo().setSelectedAreaFloor(model.getTile().getCurrentFloor());
                    break;
                case SPAWN_PASSED:
                    model.getTile().setCurrentDataString("SD");
                    if (Math.random() < 0.75) {
                        JOptionPane.showMessageDialog(view, "Enemy encountered!", "Encounter",
                                JOptionPane.INFORMATION_MESSAGE);
                        view.getFrame().setView("Battle");
                    } else {
                        int runes = ((int) (Math.random() * 100) + 50)
                                * (model.getPlayerInfo().getSelectedAreaFloor() + 1);
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() + runes);
                        JOptionPane.showMessageDialog(view, "Treasure found! You receieved " + runes + " runes",
                                "Treasure",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;
                case BOSS_PASSED:
                    model.getTile().setCurrentDataString("BD");
                    JOptionPane.showMessageDialog(view, "Boss encountered!", "Encounter", JOptionPane.INFORMATION_MESSAGE);
                    
                    view.getFrame().setView("Battle");
                    break;
                case CREDITS:
                    view.getFrame().setView("Credits");
                    break;
                case BACK:
                    view.getFrame().setView("FastTravel");
                    model.getTile().resetAllSpawn();
                    break;


                
            }
           
        }

        public View getView() {
            return view;
        }
    }
}
