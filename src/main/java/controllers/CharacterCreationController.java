package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.CharacterCreationView;
import forms.CharacterCreation;
import util.PlayerInfo;

public class CharacterCreationController implements ActionListener {
    public ActionState actionState;
    private CharacterCreation model;
    private CharacterCreationView view;

    public enum ActionState {
        NEXT, PREVIOUS, BACK, CONTINUE
    }

    public CharacterCreationController(ActionState actionState, CharacterCreation model, CharacterCreationView view) {
        super();
        this.actionState = actionState;
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (actionState) {
            case NEXT:
                model.nextJobClass();
                break;
            case PREVIOUS:
                model.previousJobClass();
                break;
            case BACK:
                view.getFrame().setView("TitleScreen");
                break;
            case CONTINUE:
                
                if (view.nameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (view.nameField.getText().length() > 25) {
                    JOptionPane.showMessageDialog(view, "Name must be less than 25 characters", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    model.setName(view.nameField.getText());
                    view.getFrame().setView("GameLobby");
                    PlayerInfo playerInfo = new PlayerInfo(model.getName(), model.getJobClass());
                    model.getPlayerInfo().replacePlayerInfo(playerInfo);
                }

                break;
            default:
                break;
        }
        view.updateJobClass();
        
    }
}
