import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verse {
    private String content;
    private String rhyme;

    public Verse(String content, String rhyme) {
        this.rhyme = rhyme;
        this.content = content;
    }

    public static Verse getValidInstance(String verseInput) {
        Pattern pattern = Pattern.compile(("/(.*?)/"));
        Matcher matcher = pattern.matcher(verseInput);
        if (matcher.find()) {
            String rhyme = matcher.group(0);
            String content = verseInput.replace(" " + rhyme, "");
            return new Verse(content, rhyme);
        }
        return null;
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