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
                        "Je brandis mon arme comme Tor son marteau /o/",
                        "Je brandis mon arme comme Tor son marteau",
                        "/o/"
                },
                {
                        "Je brandis mon arme comme Tor son marteau",
                        null,
                        null
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
    public void testVerseConstructorAndValidation() {
        Verse v = Verse.getValidInstance(this.input);

        if (v != null) {
            assertEquals(this.expectedRhyme, v.getRhyme());
            assertEquals(this.expectedContent, v.getContent());
        }
    }
}