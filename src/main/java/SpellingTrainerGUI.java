import javax.swing.*;
import java.io.IOException;

public class SpellingTrainerGUI {
    public static void main(String[] args) {

        SpellingTrainer t = new SpellingTrainer();

        try {
            t.loadWordPairsFromResources("wordPairs.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String input = "";
        Boolean b;
        WordImagePair pair;

        while (true){
            pair = t.selectRandomPair();
            b = false;
            while (!b) {
                input = JOptionPane.showInputDialog(null,
                        new Object[]{
                                new ImageIcon(pair.getURL()),
                                "Total Inputs: " + t.getTotalAttempts(),
                                "Correct Inputs: " + t.getCorrectAttempts(),
                        });

                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Program aborted. Goodbye!");
                    return;
                }
                b = t.checkAnswer(input);
            }
        }
    }
}

