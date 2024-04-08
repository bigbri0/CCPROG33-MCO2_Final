
package forms;


import util.GameStats;
import util.JobClass;
import util.PlayerInfo;

/**
 * This class represents the Character Creation screen of the EldenRouge game.
 * It allows the player to choose a character JobClass and enter a name for their character.
 * @Authors Roj Friginal, Brian Santamaria
 */
public class CharacterCreation {
    /**
     * The Model class is responsible for the data and logic of the Character Creation screen.
     */
        private JobClass jobClass;
        private int jobClassIndex;
        private String name;
        private PlayerInfo playerInfo;
        /**
         * Constructor for the Model class.
         * It initializes the jobClassIndex and jobClass attributes.
         */

        public CharacterCreation() {
            jobClassIndex = 0;
            jobClass = GameStats.jobClasses[jobClassIndex];

        }

        public void setJobClass(JobClass jobClass) {
            this.jobClass = jobClass;
        }

        public JobClass getJobClass() {
            return jobClass;
        }

        public JobClass nextJobClass() {
            jobClassIndex++;
            if (jobClassIndex >= GameStats.jobClasses.length) {
                jobClassIndex = 0;
            }
            jobClass = GameStats.jobClasses[jobClassIndex];
            return jobClass;
        }

        public JobClass previousJobClass() {
            jobClassIndex--;
            if (jobClassIndex < 0) {
                jobClassIndex = GameStats.jobClasses.length - 1;
            }
            jobClass = GameStats.jobClasses[jobClassIndex];
            return jobClass;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPlayerInfo(PlayerInfo playerInfo) {
            this.playerInfo = playerInfo;
        }

        public PlayerInfo getPlayerInfo() {
            return playerInfo;
        }
    }
 