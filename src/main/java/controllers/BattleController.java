package controllers;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.beans.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import View.BattleView;
import forms.Battle;

public  class BattleController implements ActionListener {
    private ActionState actionState;

    private Battle model;
    private BattleView view;
    private int isDodgedSuccess;

    public enum ActionState {
        STRENGTH,
        INTELLIGENCE,
        FAITH,
        DODGE,
        BACK
    }

    public BattleController(ActionState actionState, Battle model, BattleView view) {
        super();
        this.view = view;
        this.model = model;

        this.actionState = actionState;
        this.isDodgedSuccess = -1;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (actionState == ActionState.STRENGTH || actionState == ActionState.INTELLIGENCE || actionState == ActionState.FAITH || actionState == ActionState.DODGE) {

            if (actionState == ActionState.STRENGTH) {
                model.playerStrengthAttack();
            } else if (actionState == ActionState.INTELLIGENCE) {
                model.playerIntelligenceAttack();
            } else if (actionState == ActionState.FAITH) {
                model.playerFaithAttack();
            } else if (actionState == ActionState.DODGE) {
                isDodgedSuccess = model.isNextEnemyAttackDodged() ? 1 : 0;
            }
            model.isPlayerTurn = false;
            view.enableButtons(false);
            model.nextEnemyAttack = model.getEnemyAttack();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (isDodgedSuccess == 1) {
                        isDodgedSuccess = -1;
                        JOptionPane.showMessageDialog(view, "Enemy Attack Dodged", "Dodged", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        model.enemyAttack(model.nextEnemyAttack);
                        model.isPlayerTurn = true;
                        isDodgedSuccess = -1;

                    }
                    view.enableButtons(true);

                    view.repaint();

                    if (model.getPlayerInfo().getCurrentHealth() <= 0) {
                        JOptionPane.showMessageDialog(view, "Player Died", "Game Over. You'll lose all of your runes", JOptionPane.INFORMATION_MESSAGE);
                        view.getFrame().getPlayerInfo().firePropertyChange("playerDied", null, null);
                        view.getFrame().getPlayerInfo().setRunes(0);
                        view.getFrame().setView("GameLobby");
                        timer.cancel();

                        return;

                    }

                }
            }, (int)(300 + Math.random() * 1300));

            view.repaint();
            if (model.getEnemyHealth() <= 0) {
                int runes = model.getMaximumEnemyHealth() * (model.isBoss ? 5 : 2);
                timer.cancel();
                JOptionPane.showMessageDialog(view, "Enemy Died \nYou gained " + runes + " runes", "Victory", JOptionPane.INFORMATION_MESSAGE);
                view.getFrame().setView("Area");

                view.getFrame().getPlayerInfo().firePropertyChange("enemyDied", null, null);
                model.getPlayerInfo().addRunes(runes);
                return;
                
            }
                
        }

    }

    public BattleView getView() {
        return view;
    }
}


