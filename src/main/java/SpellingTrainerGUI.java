import javax.swing.*;
import java.io.IOException;

public class SpellingTrainerGUI {
    public static void main(String[] args) throws IOException {

        // Create a SpellingTrainer object
        SpellingTrainer t = new SpellingTrainer();

        try {
            // Load word pairs from a JSON file in the resources folder
            t.loadWordPairsFromResources("wordPairs.json");
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs during loading
            e.printStackTrace();
        }

        String input;
        boolean b;
        WordImagePair pair;

        // Prompt the user for the storage path of an existing word trainer
        input = JOptionPane.showInputDialog(null, "Specify the storage path for an existing word trainer. A new one is started by default.");

        // If the input is not empty, load the state from the specified path
        if (!input.isEmpty()) {
            t.loadState(input);
        }

        // Main application loop
        while (true) {
            // Get the selected word-image pair or select a random one
            pair = t.getSelectedPair();
            if (pair == null) {
                pair = t.selectRandomPair();
            }

            b = false;

            // Inner loop for user input and checking answers
            while (!b) {
                // Show a dialog with the image, total attempts, and correct attempts
                input = JOptionPane.showInputDialog(null,
                        new Object[]{
                                new ImageIcon(pair.getURL()),
                                "Total Inputs: " + t.getTotalAttempts(),
                                "Correct Inputs: " + t.getCorrectAttempts(),
                        });

                // If the user closes the dialog, prompt for saving the state
                if (input == null) {
                    input = JOptionPane.showInputDialog(null, "Do you want to save your current State?");
                    if (input != null && input.equalsIgnoreCase("yes")) {
                        // Prompt for the storage path and save the state
                        input = JOptionPane.showInputDialog(null, "Specify the storage path to save the new word trainer. If not, the default path will be used");
                        if (input == null || input.equals("")) {
                            t.saveState("/save.json");
                        } else {
                            t.saveState(input);
                        }
                    }
                    return; // Exit the application
                }

                // Check the user's answer and set the flag 'b' accordingly
                b = t.checkAnswer(input);
            }
        }
    }
}
