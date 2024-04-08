package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import util.Item;

public class InventoryController implements ActionListener {
    public ActionState actionState;
    private Inventory model;
    private InventoryView view;
    /**
     * Enum representing the different actions that can be performed on the Inventory screen.
     */
    enum ActionState {
        NEXT, PREVIOUS, BACK, EQUIP
    }
    /**
     * Constructor for the Controller class.
     *
     * @param actionState the action to be performed
     * @param model the model of the Inventory screen
     * @param view the view of the Inventory screen
     */
    public InventoryController(ActionState actionState, Inventory model, InventoryView view) {
        super();
        this.actionState = actionState;
        this.view = view;
        this.model = model;

        this.model.getPlayerInfo().addPropertyChangeListener(evt -> {
            view.updateItem();
            model.update();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (actionState) {
            case NEXT:
                Item next = model.nextItem();
                if (next.getName().equals(model.playerInfo.getEquippedWeapon().getName())) {
                    view.setEquipText("Unequip Item");
                } else {
                    view.setEquipText("Equip Item");
                }

                break;
            case PREVIOUS:
                Item previous = model.previousItem();
                if (previous.getName().equals(model.playerInfo.getEquippedWeapon().getName())) {
                    view.setEquipText("Unequip Item");
                } else {
                    view.setEquipText("Equip Item");
                }

                break;
            case BACK:
                view.updateItem();
                view.getFrame().setView("GameLobby");
                break;
            case EQUIP:
                if (view.getEquipText().equals("Unequip Item")) {
                    int result = JOptionPane.showConfirmDialog(view, "Are you sure you want to unequip this item?");
                    if (result == JOptionPane.YES_NO_OPTION) {
                        model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() + model.getItem().getPrice());
                        model.getPlayerInfo().setEquippedWeapon(new Item("None", Item.ItemType.NONE, 0, 0, 0, 0, 0, 0, 0));
                        view.setEquipText("Equip Item");
                        view.updateItem();

                    }
                } else {
                    if (model.getPlayerInfo().getRunes() >= model.getItem().getPrice()) {
                        boolean owned = false;
                        for (Item i : model.getPlayerInfo().getInventory()) {
                            if (model.getItem().getName().equals(i.getName())) {
                                owned = true;
                            }
                        }
                        if (!owned) {
                            model.getPlayerInfo().getInventory().add(model.getItem());
                            model.getPlayerInfo().setRunes(model.getPlayerInfo().getRunes() - model.getItem().getPrice());
                        }
                        model.getPlayerInfo().setEquippedWeapon(model.getItem());


                        view.setEquipText("Unequip Item");
                        view.updateItem();
                        JOptionPane.showMessageDialog(view, "Item Equipped");

                    } else {
                        JOptionPane.showMessageDialog(view, "Not enough runes");
                    }
                }






                break;
            default:
                break;
        }
        view.updateItem();

    }
}

