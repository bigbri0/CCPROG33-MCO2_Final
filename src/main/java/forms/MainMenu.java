package forms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import View.AreaView;
import View.BattleView;
import View.CharacterCreationView;
import View.Credits;
import View.FastTravelView;
import util.custom.UIHelper;
import util.PlayerInfo;

public class MainMenu {
    public static class Model {
        private PlayerInfo playerInfo;

        public Model() {
            playerInfo = new PlayerInfo();
        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }
        
    }

    public static class View extends JFrame {
        public static final int WINDOW_WIDTH = 1000;
        public static final int WINDOW_HEIGHT = 800;

        private JPanel cardPanel;
        private CardLayout cardLayout;

        private Model model;

        public View() {
            initComponents();

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });
        }
        

 private void initComponents() {
            model = new Model();

            setTitle("Elden Rouge");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBackground(UIHelper.colorBackground);
            setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            setLocationRelativeTo(null);

            cardPanel = new JPanel();
            cardLayout = new CardLayout();
            cardPanel.setLayout(cardLayout);

            getContentPane().add(cardPanel);
        }
        
        public void addView(JPanel view, String name) {
            cardPanel.add(view, name);
        }

        public void setView(String view) {
            cardLayout.show(cardPanel, view);
        }

        public PlayerInfo getPlayerInfo() {
            return model.getPlayerInfo();
        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            model.setPlayerInfo(playerInfo);
        }

    }

    public static class Controller {
        private Model model;
        private View view;


        public Controller() {
            model = new Model();
            view = new View();
            view.setVisible(true);

            view.addView(new AreaView(model.getPlayerInfo()), "Area");
            view.addView(new BattleView(model.getPlayerInfo()), "Battle");
            view.addView(new TitleScreen(), "TitleScreen");
            view.addView(new CharacterCreationView(model.getPlayerInfo()), "CharacterCreation");
            view.addView(new GameLobbyView(model.getPlayerInfo()), "GameLobby");
            view.addView(new FastTravelView(model.getPlayerInfo()), "FastTravel");
            view.addView(new LevelUpView(model.getPlayerInfo()), "LevelUp");
            view.addView(new InventoryView(model.getPlayerInfo()), "Inventory");
            view.addView(new Credits(), "Credits");
            
            view.setView("TitleScreen");
        }



        
    }
}

