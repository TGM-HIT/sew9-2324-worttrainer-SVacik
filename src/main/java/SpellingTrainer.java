import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Class representing a Spelling Trainer
class SpellingTrainer {
    private List<WordImagePair> wordImagePairs; // List to store WordImagePair objects
    private WordImagePair selectedPair; // Currently selected WordImagePair
    private int totalAttempts; // Total attempts made by the user
    private int correctAttempts; // Total correct attempts made by the user

    // Constructor to initialize instance variables
    public SpellingTrainer() {
        this.wordImagePairs = new ArrayList<>();
        this.selectedPair = null;
        this.totalAttempts = 0;
        this.correctAttempts = 0;
    }

    // Method to save the state of the SpellingTrainer to a file
    public void saveState(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), this);
    }

    // Method to load the state of the SpellingTrainer from a file
    public void loadState(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        if (file.exists()) {
            SpellingTrainer loadedTrainer = objectMapper.readValue(file, SpellingTrainer.class);
            this.wordImagePairs = loadedTrainer.wordImagePairs;
            this.selectedPair = loadedTrainer.selectedPair;
            this.totalAttempts = loadedTrainer.totalAttempts;
            this.correctAttempts = loadedTrainer.correctAttempts;
        }
    }

    // Method to load WordImagePair objects from a JSON file in the resources folder
    public void loadWordPairsFromResources(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Get an InputStream for the specified resource file
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        // Check if the InputStream is not null
        if (inputStream != null) {
            // Read and deserialize WordImagePair objects from the InputStream
            wordImagePairs = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, WordImagePair.class));
        } else {
            wordImagePairs = new ArrayList<>(); // If InputStream is null, initialize an empty list
        }
    }

    // Method to select a random WordImagePair from the list
    public WordImagePair selectRandomPair() {
        if (wordImagePairs.isEmpty()) {
            return null; // Return null if the list is empty
        }

        Random random = new Random();
        selectedPair = wordImagePairs.get(random.nextInt(wordImagePairs.size()));
        return selectedPair;
    }

    // Method to check the user's answer against the selected WordImagePair
    public boolean checkAnswer(String userAnswer) {
        if (selectedPair == null) {
            return false; // Return false if no WordImagePair is selected
        }

        totalAttempts++; // Increment total attempts

        // Check if the user's answer is equal to the word in the selected WordImagePair (case-insensitive)
        if (userAnswer.equalsIgnoreCase(selectedPair.getWord())) {
            correctAttempts++; // Increment correct attempts
            selectedPair = null; // De-select the pair after a correct answer
            return true; // Return true for a correct answer
        }

        return false; // Return false for an incorrect answer
    }

    // Getter method for wordImagePairs
    public List<WordImagePair> getWordImagePairs() {
        return wordImagePairs;
    }

    // Setter method for wordImagePairs
    public void setWordImagePairs(List<WordImagePair> wordImagePairs) {
        this.wordImagePairs = wordImagePairs;
    }

    // Getter method for selectedPair
    public WordImagePair getSelectedPair() {
        return selectedPair;
    }

    // Setter method for selectedPair
    public void setSelectedPair(WordImagePair selectedPair) {
        this.selectedPair = selectedPair;
    }

    // Getter method for totalAttempts
    public int getTotalAttempts() {
        return totalAttempts;
    }

    // Setter method for totalAttempts
    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    // Getter method for correctAttempts
    public int getCorrectAttempts() {
        return correctAttempts;
    }

    // Setter method for correctAttempts
    public void setCorrectAttempts(int correctAttempts) {
        this.correctAttempts = correctAttempts;
    }
}
