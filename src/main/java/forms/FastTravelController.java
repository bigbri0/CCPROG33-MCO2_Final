package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.FastTravelView;

public  class FastTravelController implements ActionListener {
    private ActionState actionState;
    
    private FastTravel model;
    private FastTravelView view;

    public enum ActionState {
        STORM_VEIL_CASTLE,
        RAYA_LUCARIA_ACADEMY,
        THE_ELDEN_THRONE,
        BACK
    }

    public FastTravelController(ActionState actionState, FastTravel model, FastTravelView view) {
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

    public FastTravelView getView() {
        return view;
    }
}


