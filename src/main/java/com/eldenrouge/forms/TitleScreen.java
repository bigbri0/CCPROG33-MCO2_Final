package com.eldenrouge.forms;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.eldenrouge.util.custom.Button;
import com.eldenrouge.util.custom.UIHelper;

class TitleScreen {
    public static class View extends JPanel {
        private JLabel titleLbl;
        private Button createButton;
        private Button exitButton;
        
        public View() {
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
            createButton.addActionListener(new Controller(Controller.ActionState.START, this));
            add(createButton, gbc);


            gbc.gridy++;
            exitButton = new Button("Exit");
            exitButton.setPreferredSize(new Dimension( 350, 50 ));
            exitButton.addActionListener(new Controller(Controller.ActionState.BACK, this));
            add(exitButton, gbc);

        }

        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }

    }

    public static class Controller implements ActionListener {
        private ActionState actionState;
        private View view;

        enum ActionState {
            START,
            BACK
        }

        public Controller(ActionState actionState, View view) {
            this.actionState = actionState;
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionState) {
                case START:
                    view.getFrame().setView("CharacterCreation");
                    break;
                case BACK:
                    int confirm = JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    break;
            }
        }

        public View getView() {
            return view;
        }
    }
}

