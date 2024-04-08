package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class GameLobbyController implements ActionListener {
    public ActionState actionState;
    public GameLobbyView view;

    enum ActionState {
        FAST_TRAVEL,
        LEVEL_UP,
        INVENTORY,
        QUIT
    }

    public GameLobbyController(ActionState actionState, GameLobbyView view) {
        super();
        this.actionState = actionState;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (actionState) {
            case FAST_TRAVEL:
                view.getFrame().setView("FastTravel");
                break;
            case LEVEL_UP:
                view.getFrame().setView("LevelUp");
                break;
            case INVENTORY:
                view.getFrame().setView("Inventory");
                break;
            case QUIT:
                int confirm = JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure you want to quit the game?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
        }
    }
}
