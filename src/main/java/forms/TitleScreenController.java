package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import util.custom.Button;
import util.custom.UIHelper;

public class TitleScreenController implements ActionListener {
    private ActionState actionState;
    private TitleScreen view;

    enum ActionState {
        START,
        BACK
    }

    public TitleScreenController(ActionState actionState, TitleScreen view) {
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

    public TitleScreen getView() {
        return view;
    }
}


