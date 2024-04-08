package forms;

import util.PlayerInfo;

/**
 * This class represents the Fast Travel screen of the EldenRouge game.
 * It allows the player to fast travel to different areas in the game.
 * @Authors Roj Friginal, Brian Santamaria
 */


public class FastTravel {

    /**
     * The Model class is responsible for the data of the Fast Travel screen.
     */
        private PlayerInfo playerInfo;

        public FastTravel() {

        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }
    }

   