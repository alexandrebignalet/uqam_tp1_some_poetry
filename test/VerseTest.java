import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class VerseTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {
                        "Coucou bibi, ici Paris /i/",
                        "Coucou bibi, ici Paris",
                        "/i/"
                },
                {
                        "Je sors ma bite comme le marteau de Tor /or/",
                        "Je sors ma bite comme le marteau de Tor",
                        "/or/"
                }
        });
    }

    private String input;
    private String expectedContent;
    private String expectedRhyme;

    public VerseTest(String input, String content, String rhyme){
        this.input = input;
        this.expectedContent = content;
        this.expectedRhyme = rhyme;
    }

    @Test
    public void testVerseConstructor() {
        assertEquals(this.expectedRhyme, new Verse(this.input).getRhyme());
        assertEquals(this.expectedContent, new Verse(this.input).getContent());
    }

    @Test
    public void testVerseConstructorFail() {
        String verseInput = "A 25 ballets, je pensais être millionaire";

        String expectedContent = "A 25 ballets, je pensais être millionaire";
        String expectedRhyme = "/ère/";

        Verse v = new Verse(verseInput);

        assertNotEquals(expectedRhyme, v.getRhyme());
        assertNotEquals(expectedContent, v.getContent());
    }
}