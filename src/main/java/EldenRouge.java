

import java.awt.*;
import java.io.*;
import javax.swing.*;

import forms.MainMenu;


/**
 * The main class for the EldenRouge game.
 * This class is responsible for setting up the game environment and starting the game.
 */
public class EldenRouge {
    /**
     * The main method that starts the game.
     * It sets up the font, look and feel of the game, and then starts the game by creating a new MainMenu controller.
     *
     * @authors Roj Friginal, Brian Santamaria
     */
    public static void main(String[] args) {

        // Register the custom font for the game
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/Mantinia Regular.otf")));
        } catch (IOException | FontFormatException e) {
            // Print the stack trace if an exception occurs while registering the font
            e.printStackTrace();
        }

        // Set the look and feel of the game to match the system's look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Print the stack trace if an exception occurs while setting the look and feel
            e.printStackTrace();
        }

        // Start the game by creating a new MainMenu controller
        new MainMenu.Controller();
    }
}