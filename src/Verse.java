import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verse {
    private String content;
    private String rhyme;

    public Verse(String verseInput) {

        Pattern pattern = Pattern.compile(("/(.*?)/"));
        Matcher matcher = pattern.matcher(verseInput);
        if (matcher.find()) {
            this.rhyme = matcher.group(0);
            this.content = verseInput.replace(" " + this.rhyme, "");
        }
    }

    public String getContent() {
        return this.content;
    }

    public String getRhyme() {
        return this.rhyme;
    }

    public String toString() {
        return this.getContent() + " " + this.getRhyme();
    }
}