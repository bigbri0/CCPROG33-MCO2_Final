package forms;



import java.beans.*;


import java.util.List;

import util.PlayerInfo;
/**
 * This class represents the Battle screen of the EldenRouge game.
 * It allows the player to engage in battles with enemies.
 * @Authors Roj Friginal, Brian Santamaria

 */
public class Battle {
    /**
     * The Model class holds the data for the Battle screen.
     */
        private PlayerInfo playerInfo;
        public List<List> enemyType;
        public List<List> bossList;

        public String enemyName;
        public int enemyHealth;
        public int maxEnemyHealth;
        public int enemyAttack;
        public double enemyStrengthDefense;
        public double enemyIntelligenceDefense;
        public double enemyFaithDefense;

        public int nextEnemyAttack;
        
        public int playerHealth;
        public int playerAttack;
        public double playerStrengthDefense;
        public double playerIntelligenceDefense;
        public double playerFaithDefense;

        public boolean isPlayerTurn;
        public boolean isBoss;

        public double enemyStrength = 1;


        public PropertyChangeSupport propertyChangeSupport;
        
        public Battle() {
            enemyType = List.of(
                List.of(
                    List.of("Godrick Soldier", "Living Jar"),
                    List.of(20, 30), List.of(70, 80), 0.20, 0.15, 0.10),
                List.of(
                    List.of("Godrick Archer", "Glintstone Sorcerer"),
                    List.of(25, 35), List.of(110, 120), 0.50, 0.15, 0.30),
                List.of(
                    List.of("Godrick Knight", "Battlemage"),
                    List.of(70, 80), List.of(120, 130), 0.25, 0.25, 0.20)
            );
            bossList = List.of(
                List.of(
                    "Godrick the Grafted",
                    200, List.of(100, 300), 0.35, 0.20, 0.15),
                List.of(
                    "Rennala, Queen of the Full Moon",
                    400, List.of(200, 300), 0.15, 0.35, 0.25),
                List.of(
                    "The Elden Beast",
                    800, List.of(250, 500), 0.25, 0.50, 0.40)
            );

            propertyChangeSupport = new PropertyChangeSupport(this);

        }
        /**
         * This method sets the enemy for the battle.
         * @param floor the floor of the area
         */
        public void setBossEnemy(int floor) {
            List enemy = bossList.get((int) (Math.random() * bossList.size()));

            enemyName = (String) enemy.get(0);
            enemyHealth = (int) enemy.get(1);
            maxEnemyHealth = enemyHealth;
            enemyAttack = 0;
            enemyStrengthDefense = (double) enemy.get(3);
            enemyIntelligenceDefense = (double) enemy.get(4);
            enemyFaithDefense = (double) enemy.get(5);

            propertyChangeSupport.firePropertyChange("enemy", null, null);

            isPlayerTurn = true;

        }
        /**
         * This method randomizes the enemy for the battle.
         * @param floor the floor of the area
         */
        public void randomizeEnemy(int floor) {
            List enemy = enemyType.get((int) (Math.random() * enemyType.size()));

            if (floor != 2) {
                enemyName = (String) ((List)enemy.get(0)).get(floor);
                int enemyHealthMin = (int) ((List)enemy.get(1)).get(0);
                int enemyHealthMax = (int) ((List)enemy.get(1)).get(1);
                enemyHealth = (int) (enemyHealthMin + Math.random() * (enemyHealthMax - enemyHealthMin)) * (floor + 1);
                maxEnemyHealth = enemyHealth;

            }
            enemyAttack = 0;
            enemyStrengthDefense = (double) enemy.get(3);
            enemyIntelligenceDefense = (double) enemy.get(4);
            enemyFaithDefense = (double) enemy.get(5);

            propertyChangeSupport.firePropertyChange("enemy", null, null);

            isPlayerTurn = true;


        }

        /**
         * This method Gets the enemy attack for the battle.
         */
        public int getEnemyAttack() {
            List enemy = enemyType.get((int) (Math.random() * enemyType.size()));

            if (playerInfo.getSelectedAreaFloor() == 2) {
                enemy = bossList.get((int) (Math.random() * bossList.size()));
            }

            int enemyAttackMin = (int) ((List) enemy.get(2)).get(0);
            int enemyAttackMax = (int) ((List) enemy.get(2)).get(1);

            int attack = (int) (enemyAttackMin
                    + Math.random() * (enemyAttackMax - enemyAttackMin) * (playerInfo.getSelectedAreaFloor() + 1));

            attack *= enemyStrength;

            return attack;
        }
        /**
         * This method sets the enemy attack for the battle.
         */
        public int enemyAttack() {
            List enemy = enemyType.get((int) (Math.random() * enemyType.size()));

            if (playerInfo.getSelectedAreaFloor() == 2){
                enemy = bossList.get((int) (Math.random() * bossList.size()));
            }

            int enemyAttackMin = (int) ((List) enemy.get(2)).get(0);
            int enemyAttackMax = (int) ((List) enemy.get(2)).get(1);

            int attack = (int) (enemyAttackMin + Math.random() * (enemyAttackMax - enemyAttackMin) * (playerInfo.getSelectedAreaFloor()+1));

            attack *= enemyStrength;
            playerInfo.setCurrentHealth(playerInfo.getCurrentHealth() - attack);

            return attack;
        }
        /**
         * This method sets the enemy attack for the battle.
         * */
        public int enemyAttack(int attack) {
            playerInfo.setCurrentHealth(playerInfo.getCurrentHealth() - attack);

            return attack;
        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
            propertyChangeSupport.firePropertyChange("playerInfo", null, null);
        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }

        public boolean isPlayerTurn() {
            return isPlayerTurn;
        }


        public String getEnemyName() {
            return enemyName;
        }

        public int getEnemyHealth() {
            return enemyHealth;
        }

        public int getMaximumEnemyHealth() {
            return maxEnemyHealth;
        }

        public double getEnemyStrengthDefense() {
            return enemyStrengthDefense;
        }

        public double getEnemyIntelligenceDefense() {
            return enemyIntelligenceDefense;
        }

        public double getEnemyFaithDefense() {
            return enemyFaithDefense;
        }

        public String getPlayerName() {
            return playerInfo.getName();
        }

        public int getPlayerHealth() {
            return ((playerInfo.getJobClass().getHealth()
                    + playerInfo.getEquippedWeapon().getHealth()) * 100);

        }

        public int getPlayerStrengthDamage() {
            return (int)((playerInfo.getJobClass().getStrength() + playerInfo.getEquippedWeapon().getStrength()) * (1 - getEnemyStrengthDefense()));
        }

        public int getPlayerIntelligenceDamage() {
            return (int)((playerInfo.getJobClass().getIntelligence() + playerInfo.getEquippedWeapon().getIntelligence()) * (1 - getEnemyIntelligenceDefense()));
        }

        public int getPlayerFaithDamage() {
            return (int)((playerInfo.getJobClass().getFaith() + playerInfo.getEquippedWeapon().getDexterity()) * (1 - getEnemyFaithDefense()));
        }


        public boolean isNextEnemyAttackDodged() {
            int dodgeRateCheck = (int) (Math.random() * 100);
            int dodgeRate = (20 + playerInfo.getJobClass().getEndurance() + playerInfo.getEquippedWeapon().getEndurance()) / 2;
            return dodgeRateCheck <= dodgeRate;
        }

        public int playerStrengthAttack() {
            enemyHealth -= getPlayerStrengthDamage();
            propertyChangeSupport.firePropertyChange("playerStrengthAttack", null, enemyHealth);

            return getPlayerStrengthDamage();
        }
        public int playerIntelligenceAttack() {
            enemyHealth -= getPlayerIntelligenceDamage();
            propertyChangeSupport.firePropertyChange("playerIntelligenceAttack", null, enemyHealth);

            return getPlayerIntelligenceDamage();
        }

        public int playerFaithAttack() {
            enemyHealth -= getPlayerFaithDamage();
            propertyChangeSupport.firePropertyChange("playerFaithAttack", null, enemyHealth);

            return getPlayerFaithDamage();
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.removePropertyChangeListener(listener);
        }


    }

