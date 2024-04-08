package forms;

import java.awt.*;

import javax.swing.*;

import util.custom.Button;
import util.custom.UIHelper;

class TitleScreen extends JPanel{
        private JLabel titleLbl;
        private Button createButton;
        private Button exitButton;
        
        public TitleScreen() {
            initComponents();

        }

        private void initComponents() {
            setBackground(UIHelper.colorBackground);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets.set(10, 10, 10, 10);
            
            gbc.gridy = 0;
            titleLbl = new JLabel();
            titleLbl.setText("Elden rougE");
            titleLbl.setFont(new Font("Mantinia", Font.BOLD, 75));
            titleLbl.setForeground(UIHelper.colorTertiary);
            add(titleLbl, gbc);
            

            gbc.gridy++;
            createButton = new Button("Create Character");
            createButton.setPreferredSize(new Dimension( 350, 50 ));
            createButton.addActionListener(new TitleScreenController(TitleScreenController.ActionState.START, this));
            add(createButton, gbc);


            gbc.gridy++;
            exitButton = new Button("Exit");
            exitButton.setPreferredSize(new Dimension( 350, 50 ));
            exitButton.addActionListener(new TitleScreenController(TitleScreenController.ActionState.BACK, this));
            add(exitButton, gbc);

        }

        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }

    }
   