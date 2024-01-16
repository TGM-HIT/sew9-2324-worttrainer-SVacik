import java.net.MalformedURLException;
import java.net.URL;

// Class representing a pair of a word and its corresponding image URL
class WordImagePair {
    private String word; // The word in the pair
    private String url; // The URL of the corresponding image

    // Default constructor
    public WordImagePair() {
    }

    // Parameterized constructor to initialize the WordImagePair with a word and an image URL
    public WordImagePair(String word, String imageUrl) {
        this.word = word;
        this.url = imageUrl;
    }

    // Getter method to retrieve the word from the WordImagePair
    public String getWord() {
        return word;
    }

    // Getter method to retrieve the URL object from the image URL string
    public URL getURL() {
        try {
            return new URL(this.url); // Attempt to create a URL object from the image URL string
        } catch (MalformedURLException e) {
            return null; // Return null if the URL creation fails (malformed URL)
        }
    }

    // Setter method to update the image URL
    public void setImageUrl(String imageUrl) {
        this.url = imageUrl;
    }
}