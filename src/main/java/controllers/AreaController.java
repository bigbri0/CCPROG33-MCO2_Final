package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import forms.Area;

public  class AreaController implements ActionListener {
    private ActionState actionState;

    private Area model;
    private AreaView view;

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

    public AreaController(ActionState actionState, Area model, AreaView view) {
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

    public AreaView getView() {
        return view;
    }
}

