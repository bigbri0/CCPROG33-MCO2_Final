package util.custom;

import javax.swing.border.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.beans.*;

public class Tile extends JPanel {
    private List<List<List<String>>> tileData;
    private List<List<Dimension>> tileDimensions;

    private CardLayout floorPane;
    private Point playerPosition;

    private List<List<Integer>> doorTiles;
    private List<List<Integer>> floorTiles;

    private int doorNow;
    private int floorNow;

    private boolean initialized = false;

    private PropertyChangeSupport propertyChangeSupport;

    public Tile(List<List<List<String>>> tileData, List<List<Dimension>> tileDimensions) {
        this.tileData = new ArrayList<>();
        for (List<List<String>> floor : tileData) {
            List<List<String>> doorData = new ArrayList<>();
            for (List<String> door : floor) {
                List<String> tile = new ArrayList<>();
                for (String t : door) {
                    tile.add(t);
                }
                doorData.add(tile);
            }
            this.tileData.add(doorData);
        }
        this.tileDimensions = tileDimensions;
        this.playerPosition = new Point(0,0);
    
        initComponents();

        propertyChangeSupport = new PropertyChangeSupport(this);


    }


    public void initComponents() {
        initialized = false;
        doorTiles = new ArrayList<>();
        floorTiles = new ArrayList<>();
        floorPane = new CardLayout();
        setOpaque(false);
        setLayout(floorPane);


        for (int floor = 0; floor < tileData.size(); floor++) {
            CardLayout doorCardLayout = new CardLayout();
            JPanel floorPanel = new JPanel(doorCardLayout);
            floorPanel.setBackground(null);
            floorPanel.setOpaque(false);

            for (int door = 0; door < tileData.get(floor).size(); door++) {
                JPanel areaPanel = new JPanel(new GridBagLayout()) {
                    @Override
                    public void paintComponent(Graphics grphcs) {
                        super.paintComponent(grphcs);
                        Graphics2D g = (Graphics2D) grphcs;
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                        g.setColor(UIHelper.colorSurfaceVariant);
                        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                    }
                };
                areaPanel.setOpaque(false);
                areaPanel.setBackground(UIHelper.colorSurfaceVariant);
                areaPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
                floorPanel.add(areaPanel, door + "");

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.anchor = GridBagConstraints.CENTER;
                JPanel areaGridPanel = new JPanel(new GridLayout(tileDimensions.get(floor).get(door).height, tileDimensions.get(floor).get(door).width, 2, 2));
                areaGridPanel.setBackground(UIHelper.colorSurfaceVariant);
                areaGridPanel.setPreferredSize(
                    new Dimension(
                        (40 + 20) * tileDimensions.get(floor).get(door).width, 
                        (40 + 20) * tileDimensions.get(floor).get(door).height));
                areaPanel.add(areaGridPanel, gbc);

                for (int j = 0; j < tileDimensions.get(floor).get(door).height * tileDimensions.get(floor).get(door).width; j++) {
                    String tile = tileData.get(floor).get(door).get(j);
                    if (tile.equals("F0")) {
                        playerPosition = new Point(j % tileDimensions.get(floor).get(door).width, j / tileDimensions.get(floor).get(door).width);
                    }



                    JLabel t = new JLabel() {
                        private boolean visited = false;

                        @Override
                        public boolean getAutoscrolls() {
                            return visited;
                        }

                        @Override
                        public void setAutoscrolls(boolean autoscrolls) {
                            visited = autoscrolls;
                        }

                        @Override
                        public void paintComponent(Graphics grphcs) {
                            Graphics2D g = (Graphics2D) grphcs;
                            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                            if (tile.equals("1")) {
                                g.setColor(UIHelper.colorSecondary);
                                setForeground(UIHelper.colorOnSecondary);
                            } else if (tile.charAt(0) == 'S') {
                                if (!visited){
                                    g.setColor(UIHelper.colorTertiary);
                                    setForeground(UIHelper.colorOnTertiary);
                                } else {
                                    g.setColor(UIHelper.colorSecondary);
                                    setForeground(UIHelper.colorOnSecondary);
                                }
                            } else if (tile.charAt(0) == 'B') {
                                g.setColor(UIHelper.colorPrimary);
                                setForeground(UIHelper.colorOnPrimary);
                            } else if (tile.charAt(0) == 'F') {
                                g.setColor(UIHelper.colorQuaternary);
                                setForeground(UIHelper.colorOnQuaternary);
                            } else if (tile.charAt(0) == 'D') {
                                g.setColor(UIHelper.colorQuinary);
                                setForeground(UIHelper.colorOnQuinary);
                            } else if (tile.equals("C")) {
                                g.setColor(UIHelper.colorSenary);
                                setForeground(UIHelper.colorOnSenary);
                            } else {
                                g.setColor(new Color(0, 0, 0, 0));
                                setForeground(new Color(0, 0, 0, 0));
                            }

                            if (initialized) {
                                if (getCurrentTileString().charAt(0) == 'S') {
                                    if (getCurrentTileString().charAt(1) == 'D') {
                                        visited = true;
                                    }
                                }
                            }

                        
                            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                            
                            super.paintComponent(grphcs);

                        }
                    };

                    if (tile.charAt(0) == 'F') {
                        String[] loc = tile.substring(1).split(",");
                        floorTiles.add(
                            List.of(
                                door,
                                j % tileDimensions.get(floor).get(door).width,
                                j / tileDimensions.get(floor).get(door).width,
                                Integer.parseInt(loc[0]),
                                Integer.parseInt(loc[1]))
                        );
                    } else if (tile.charAt(0) == 'D') {
                        String[] loc = tile.substring(1).split(",");
                        doorTiles.add(
                            List.of(
                                floor,
                                j % tileDimensions.get(floor).get(door).width,
                                j / tileDimensions.get(floor).get(door).width,
                                Integer.parseInt(loc[0]),
                                Integer.parseInt(loc[1]))
                        );

                    }

                    t.setPreferredSize(new Dimension(40, 40));
                    t.setOpaque(false);
                    t.setFont(new Font(null, 1, 40));
                    t.setHorizontalAlignment(JLabel.CENTER);
                    t.setVerticalAlignment(JLabel.CENTER);
                    areaGridPanel.add(t);

                }
            }
            add(floorPanel, floor + "");
        }
        initialized = true;
        
    }


    public Point getPlayerPosition() {
        return playerPosition;
    }

    public JLabel getTile(int floor, int door, int x, int y) {
        return (JLabel) ((JPanel) ((JPanel) ((JPanel) getComponent(floor)).getComponent(door)).getComponent(0)).getComponent(y * tileDimensions.get(floor).get(door).width + x);
    }

    public String getTileString(int floor, int door, int x, int y) {
        return tileData.get(floor).get(door).get(y * tileDimensions.get(floor).get(door).width + x);
    }

    public String getCurrentTileString() {
        return getTileString(floorNow, doorNow, playerPosition.x, playerPosition.y);
    }

    public void setTileString(int floor, int door, int x, int y, String tile) {
        tileData.get(floor).get(door).set(y * tileDimensions.get(floor).get(door).width + x, tile);
    }

    public void setDataString(int floor, int door, int x, int y, String data) {
        tileData.get(floor).get(door).set(y * tileDimensions.get(floor).get(door).width + x, data);
    }

    public void setCurrentDataString(String data) {
        setDataString(floorNow, doorNow, playerPosition.x, playerPosition.y, data);
    }

    public JLabel getCurrentTileLabel(){
        return getTile(floorNow, doorNow, playerPosition.x, playerPosition.y);
    }

    public void setCurrentTileLabel(String tile) {
        setTileString(floorNow, doorNow, playerPosition.x, playerPosition.y, tile);
        getCurrentTileLabel().setText(tile);
    }

    public void resetAllSpawn(){
        for (int floor = 0; floor < tileData.size(); floor++) {
            for (int door = 0; door < tileData.get(floor).size(); door++) {
                for (int j = 0; j < tileDimensions.get(floor).get(door).height * tileDimensions.get(floor).get(door).width; j++) {
                    if (tileData.get(floor).get(door).get(j).charAt(0) == 'S') {
                        getTile(
                            floor, 
                            door, 
                            j % tileDimensions.get(floor).get(door).width, 
                            j / tileDimensions.get(floor).get(door).width)
                            .setAutoscrolls(false);
                        setTileString(
                            floor, 
                            door, 
                            j % tileDimensions.get(floor).get(door).width, 
                            j / tileDimensions.get(floor).get(door).width, 
                            "SE");
                    }
                }
            }
        }
        getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("");
        
        setCurrentFloor(0);
        setCurrentDoor(0);
        setPlayerPosition(new Point(0, 0));
           
    }




    public void setPlayerPosition(Point playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Dimension getTileDimensions(int floor, int door) {
        return tileDimensions.get(floor).get(door);
    }

    public Dimension getCurrentTileDimensions() {
        return tileDimensions.get(floorNow).get(doorNow);
    }

    private void checkObject() {

        if (getTileString(floorNow, doorNow, playerPosition.x, playerPosition.y).charAt(0) == 'D')
            for (List<Integer> door : doorTiles) {
                if (door.get(0) == floorNow && 
                    door.get(1) == playerPosition.x && 
                    door.get(2) == playerPosition.y && 
                    door.get(3) == doorNow) {
                    for (List<Integer> doorTo : doorTiles) {
                        if (doorTo.get(3) == door.get(4) && 
                            doorTo.get(4) == door.get(3) && 
                            doorTo.get(0) == floorNow && 
                            doorTo != door) {

                            getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("");
                            setCurrentDoor(door.get(4));
                            setPlayerPosition(new Point(doorTo.get(1), doorTo.get(2)));

                            propertyChangeSupport.firePropertyChange("passedDoor", null, true);
                            getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("◉");

                            
                            return;
                        }
                    }
                }
            }

        if (getTileString(floorNow, doorNow, playerPosition.x, playerPosition.y).charAt(0) == 'F')
            for (List<Integer> floor : floorTiles) {
                if (floor.get(0) == doorNow &&
                    floor.get(1) == playerPosition.x && 
                    floor.get(2) == playerPosition.y && 
                    floor.get(3) == floorNow) {
                    for (List<Integer> floorTo : floorTiles) {
                        if (floorTo.get(4) == floor.get(3) && 
                            floorTo.get(3) == floor.get(4) && 
                            floorTo != floor) {

                            getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("");
                            setCurrentFloor(floor.get(4));
                            setCurrentDoor(floorTo.get(0));
                            setPlayerPosition(new Point(floorTo.get(1), floorTo.get(2)));

                            getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("◉");
                            propertyChangeSupport.firePropertyChange("passedFloor", null, true);


                            return;
                        }
                    }
                }
            }

        

       
    }

    public void moveLeft() {
        if (playerPosition.x > 0) {
            if (!getTile(floorNow, doorNow, playerPosition.x - 1, playerPosition.y).getText().equals("0")) {
                if (!getTileString(floorNow, doorNow, playerPosition.x - 1, playerPosition.y).equals("0")) {
                    getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("");
                    playerPosition.x--;
                    getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("◉");

                    checkObject();
                }
            }
        } 
        propertyChangeSupport
                .firePropertyChange("playerPosition", null, List.of(playerPosition, getCurrentTileString(),
                        doorNow, floorNow));
    }

    public void moveRight() {
        if (playerPosition.x < tileDimensions.get(floorNow).get(doorNow).width - 1 ) {
            if (!getTile(floorNow, doorNow, playerPosition.x + 1, playerPosition.y).getText().equals("0")) {
                if (!getTileString(floorNow, doorNow, playerPosition.x + 1, playerPosition.y).equals("0")) {
                    getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("");
                    playerPosition.x++;
                    getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("◉");

                  
                    checkObject();
                }
            }
        }
        propertyChangeSupport
                .firePropertyChange("playerPosition", null, List.of(playerPosition, getCurrentTileString(),
                        doorNow, floorNow));
    }

    public void moveUp() {
        if (playerPosition.y > 0) {
            if (!getTileString(floorNow, doorNow, playerPosition.x, playerPosition.y - 1).equals("0")) {
                getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("");
                playerPosition.y--;
                getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("◉");

           
                checkObject();
            }
        }
        propertyChangeSupport
                .firePropertyChange("playerPosition", null, List.of(playerPosition, getCurrentTileString(),
                        doorNow, floorNow));
    }

    public void moveDown() {
        if (playerPosition.y < tileDimensions.get(floorNow).get(doorNow).height - 1) {
            if (!getTileString(floorNow, doorNow, playerPosition.x, playerPosition.y + 1).equals("0")) {
                getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("");
                playerPosition.y++;
                getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("◉");

                checkObject();
            }
        }
        propertyChangeSupport
                .firePropertyChange("playerPosition", null, List.of(playerPosition, getCurrentTileString(),
                        doorNow, floorNow));
    }

    public void setCurrentDoor(int door) {
        this.doorNow = door;
        ((CardLayout)((JPanel) getComponent(floorNow)).getLayout()).show((JPanel)getComponent(floorNow), door + "");
    }

    public int getCurrentDoor() {
        return doorNow;
    }

    public void setCurrentFloor(int floor) {
        this.floorNow = floor;
        this.floorPane.show(this, floor + "");
    }

    public int getCurrentFloor() {
        return floorNow;
    }

    public void goToFloor(int floor) {
        setCurrentFloor(floor);
        setCurrentDoor(0);
        for (List<Integer> floorTo : floorTiles) {
            if (floorTo.get(3) == floor) {
                setPlayerPosition(new Point(floorTo.get(1), floorTo.get(2)));
                getTile(floorNow, doorNow, playerPosition.x, playerPosition.y).setText("◉");
                return;
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

}
