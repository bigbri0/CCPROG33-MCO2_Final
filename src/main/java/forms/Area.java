package forms;

import util.custom.Tile;
import util.custom.UIHelper;
import util.PlayerInfo;

public class Area {
        private PlayerInfo playerInfo;
        private Tile tile;

        public Area() {
            tile = new Tile(UIHelper.mapData, UIHelper.mapDataDimensions);

        }

        public Tile getTile() {
            return tile;
        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
            

        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }
    }

 