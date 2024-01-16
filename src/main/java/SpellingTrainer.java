import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SpellingTrainer {
    private List<WordImagePair> wordImagePairs;
    private WordImagePair selectedPair;
    private int totalAttempts;
    private int correctAttempts;

    public SpellingTrainer() {
        this.wordImagePairs = new ArrayList<>();
        this.selectedPair = null;
        this.totalAttempts = 0;
        this.correctAttempts = 0;
    }

    public void loadWordPairsFromResources(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            wordImagePairs = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, WordImagePair.class));
        } else {
            wordImagePairs = new ArrayList<>();
        }
    }

    public void startNewSession() {
        selectedPair = null;
        totalAttempts = 0;
        correctAttempts = 0;
    }

    public WordImagePair selectRandomPair() {
        if (wordImagePairs.isEmpty()) {
            return null;
        }

        Random random = new Random();
        selectedPair = wordImagePairs.get(random.nextInt(wordImagePairs.size()));
        return selectedPair;
    }

    public boolean checkAnswer(String userAnswer) {
        if (selectedPair == null) {
            return false;
        }

        totalAttempts++;

        if (userAnswer.equalsIgnoreCase(selectedPair.getWord())) {
            correctAttempts++;
            selectedPair = null; // De-select the pair after a correct answer
            return true;
        }

        return false;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public WordImagePair getSelectedPair() {
        return selectedPair;
    }
}