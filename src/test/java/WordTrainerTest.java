import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// JUnit test class for WordTrainer
class WordTrainerTest {

    private static final String TEST_FILE_PATH = "testState.json"; // File path for testing state persistence
    private SpellingTrainer spellingTrainer; // Instance of SpellingTrainer for testing

    // Method to set up the test environment before each test method is executed
    @BeforeEach
    void setUp() {
        spellingTrainer = new SpellingTrainer(); // Initialize a new SpellingTrainer instance
        List<WordImagePair> testPairs = new ArrayList<>(); // Create a list of test WordImagePairs
        testPairs.add(new WordImagePair("cat", "cat.jpg"));
        testPairs.add(new WordImagePair("dog", "dog.jpg"));
        spellingTrainer.setCorrectAttempts(5); // Set correct attempts for testing
        spellingTrainer.setTotalAttempts(10); // Set total attempts for testing
        spellingTrainer.setWordImagePairs(testPairs); // Set WordImagePairs for testing
    }

    // Test method to check the save and load state functionality
    @Test
    void testSaveAndLoadState() throws IOException {
        spellingTrainer.saveState(TEST_FILE_PATH); // Save the state to a test file
        SpellingTrainer loadedTrainer = new SpellingTrainer(); // Initialize a new SpellingTrainer instance
        loadedTrainer.loadState(TEST_FILE_PATH); // Load the state from the test file

        // Assertions to check if the loaded state matches the expected state
        assertEquals(spellingTrainer.getCorrectAttempts(), loadedTrainer.getCorrectAttempts());
        assertEquals(spellingTrainer.getTotalAttempts(), loadedTrainer.getTotalAttempts());
        assertEquals(spellingTrainer.checkAnswer("cat"), loadedTrainer.checkAnswer("cat"));
        assertEquals(spellingTrainer.checkAnswer("dog"), loadedTrainer.checkAnswer("dog"));
    }

    // Test method to check the selection of a random WordImagePair
    @Test
    void testSelectRandomPair() {
        WordImagePair selectedPair = spellingTrainer.selectRandomPair(); // Select a random pair

        // Assertions to check if the selected pair is not null and is contained in the WordImagePairs list
        assertNotNull(selectedPair);
        assertTrue(spellingTrainer.getWordImagePairs().contains(selectedPair));
        assertEquals(selectedPair, spellingTrainer.getSelectedPair());
    }

    // Test method to check if the checkAnswer method handles correct answers
    @Test
    void testCheckAnswerCorrect() {
        SpellingTrainer trainer = new SpellingTrainer(); // Initialize a new SpellingTrainer instance
        trainer.setWordImagePairs(Arrays.asList(new WordImagePair("cat", "cat.jpg"))); // Set a test WordImagePair

        // Assume the selected pair is set to "cat" and the user answers "cat" (correct)
        trainer.setSelectedPair(new WordImagePair("cat", "cat.jpg"));
        boolean result = trainer.checkAnswer("cat");

        assertTrue(result); // This should be true
    }

    // Test method to check if the checkAnswer method handles incorrect answers
    @Test
    void testCheckAnswerIncorrect() {
        SpellingTrainer trainer = new SpellingTrainer(); // Initialize a new SpellingTrainer instance
        trainer.setWordImagePairs(Arrays.asList(new WordImagePair("cat", "cat.jpg"))); // Set a test WordImagePair

        // Assume the selected pair is set to "cat" and the user answers "dog" (incorrect)
        trainer.setSelectedPair(new WordImagePair("cat", "cat.jpg"));
        boolean result = trainer.checkAnswer("dog");

        assertFalse(result); // This should be false
    }

}