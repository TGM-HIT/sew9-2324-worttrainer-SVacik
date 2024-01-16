import java.net.MalformedURLException;
import java.net.URL;

class WordImagePair {
    private String word;
    private String imageUrl;

    public WordImagePair() {
    }

    public WordImagePair(String word, String imageUrl) {
        this.word = word;
        this.imageUrl = imageUrl;
    }

    public String getWord() {
        return word;
    }


    public URL getURL() {
        try {
            return new URL(this.imageUrl);
        }catch (MalformedURLException e){
            return null;
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}