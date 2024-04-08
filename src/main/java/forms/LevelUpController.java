package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JOptionPane;

public class LevelUpController implements ActionListener {
    private ActionState actionState;

    private LevelUp model;
    private LevelUpView view;

    public enum ActionState {
        BACK,
        HEALTH,
        ENDURANCE,
        DEXTERITY,
        STRENGTH,
        INTELLIGENCE,
        FAITH
    }

    public LevelUpController(ActionState actionState, LevelUp model, LevelUpView view) {
        super();
        this.actionState = actionState;
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int levelUpCost = model.getPlayerInfo().getLevel() * 100 / 2;
        int dialogResult;
        switch (actionState) {
            case HEALTH:
                dialogResult = JOptionPane.showConfirmDialog(null, "Level Up Health for " + levelUpCost + " Runes?",
                        "Level Up", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                        model.getPlayerInfo().getJobClass()
                                .setHealth(model.getPlayerInfo().getJobClass().getHealth() + 1);
                        model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                        model.getPlayerInfo().firePropertyChange("levelUpHealth",
                                model.getPlayerInfo().getJobClass().getHealth() - 1,
                                model.getPlayerInfo().getJobClass().getHealth());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case ENDURANCE:
                dialogResult = JOptionPane.showConfirmDialog(null,
                        "Level Up Endurance for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                        model.getPlayerInfo().getJobClass()
                                .setEndurance(model.getPlayerInfo().getJobClass().getEndurance() + 1);
                        model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                        model.getPlayerInfo().firePropertyChange("levelUpEndurance",
                                model.getPlayerInfo().getJobClass().getEndurance() - 1,
                                model.getPlayerInfo().getJobClass().getEndurance());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case DEXTERITY:
                dialogResult = JOptionPane.showConfirmDialog(null,
                        "Level Up Dexterity for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                        model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                        model.getPlayerInfo().getJobClass()
                                .setDexterity(model.getPlayerInfo().getJobClass().getDexterity() + 1);
                        model.getPlayerInfo().firePropertyChange("levelUpDexterity",
                                model.getPlayerInfo().getJobClass().getDexterity() - 1,
                                model.getPlayerInfo().getJobClass().getDexterity());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case STRENGTH:
                dialogResult = JOptionPane.showConfirmDialog(null,
                        "Level Up Strength for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                        model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                        model.getPlayerInfo().getJobClass()
                                .setStrength(model.getPlayerInfo().getJobClass().getStrength() + 1);
                        model.getPlayerInfo().firePropertyChange("levelUpStrength",
                                model.getPlayerInfo().getJobClass().getStrength() - 1,
                                model.getPlayerInfo().getJobClass().getStrength());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case INTELLIGENCE:
                dialogResult = JOptionPane.showConfirmDialog(null,
                        "Level Up Intelligence for " + levelUpCost + " Runes?", "Level Up",
                        JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                        model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                        model.getPlayerInfo().getJobClass()
                                .setIntelligence(model.getPlayerInfo().getJobClass().getIntelligence() + 1);
                        model.getPlayerInfo().firePropertyChange("levelUpIntelligence",
                                model.getPlayerInfo().getJobClass().getIntelligence() - 1,
                                model.getPlayerInfo().getJobClass().getIntelligence());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case FAITH:
                dialogResult = JOptionPane.showConfirmDialog(null,
                        "Level Up Faith for " + levelUpCost + " Runes?", "Level Up", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (model.getPlayerInfo().getRunes() >= levelUpCost) {
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - levelUpCost);
                        model.getPlayerInfo().setLevel(model.getPlayerInfo().getLevel() + 1);
                        model.getPlayerInfo().getJobClass()
                                .setFaith(model.getPlayerInfo().getJobClass().getFaith() + 1);
                        model.getPlayerInfo().firePropertyChange("levelUpFaith",
                                model.getPlayerInfo().getJobClass().getFaith() - 1,
                                model.getPlayerInfo().getJobClass().getFaith());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Runes", "Level Up",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            
                break;
            case BACK:
                view.getFrame().setView("GameLobby");
                break;
        }

    }

    public LevelUpView getView() {
        return view;
    }
}


