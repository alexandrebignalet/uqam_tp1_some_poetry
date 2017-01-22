import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PoemFactoryTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Verse> v2 = new ArrayList<>();
        v2.add(new Verse("Tu as besoin d'azur /ur/"));
        v2.add(new Verse("De grand large et d'air pur /ur/"));
        v2.add(new Verse("Alors, fuis la menace /as/"));
        v2.add(new Verse("Des phénomènes de masse /as/"));
        v2.add(new Verse("Les cris de populace /as/"));

        ArrayList<Stanza> stanzaArrayList = new ArrayList<>();
        stanzaArrayList.add(new Stanza(v2));

        String[] stanzaInput1 = {
                "Tu as besoin d'azur /ur/",
                "De grand large et d'air pur /ur/",
                "Alors, fuis la menace /as/",
                "Des phénomènes de masse /as/",
                "Les cris de populace /as/",
                "done"
        };

        ArrayList<Verse> v = new ArrayList<>();
        v.add(new Verse("Tu as besoin d'azur /ur/"));

        ArrayList<Stanza> stanzaArrayList2 = new ArrayList<>();
        stanzaArrayList2.add(new Stanza(v));

        String[] stanzaInput2 = {
                "Tu as besoin d'azur /ur/",
                "done"
        };

        return Arrays.asList(new Object[][] {
                {"poem1", "booba", stanzaInput1, stanzaArrayList},
                {"poem2", "kaaris", stanzaInput2, stanzaArrayList2}
        });
    }

    private String expectedName;
    private String expectedAuthor;
    private String[] expectedStanzasInput;
    private ArrayList<Stanza> expectedStanzas;

    public PoemFactoryTest(String name, String author, String[] stanzasInputs, ArrayList<Stanza> stanzas){
        this.expectedName = name;
        this.expectedAuthor = author;
        this.expectedStanzasInput = stanzasInputs;
        this.expectedStanzas = stanzas;
    }

    @Test
    public void testCreate() {
        Poem poemFromFactory = new PoemFactory().create(this.expectedName, this.expectedAuthor, this.expectedStanzas);

        assertEquals(this.expectedName, poemFromFactory.getAuthor());
        assertEquals(this.expectedAuthor, poemFromFactory.getName());
        assertEquals(this.expectedStanzas, poemFromFactory.getStanzas());
    }

    @Test
    public void testAskName() {
        ByteArrayInputStream in = new ByteArrayInputStream(this.expectedName.getBytes());
        System.setIn(in);


        PoemFactory poemFactory = new PoemFactory();
        try {
            Class<?> poemCommandLineCreator = poemFactory.getClass();
            Method privateAskName = poemCommandLineCreator.getDeclaredMethod("askName");
            privateAskName.setAccessible(true);

            assertEquals(this.expectedName, privateAskName.invoke(poemFactory));
        } catch (Exception e) {
            System.out.println("Error in test ask name: "+e);
        }

        System.setIn(System.in);
    }

    @Test
    public void testAskAuthor() {
        ByteArrayInputStream in = new ByteArrayInputStream(this.expectedAuthor.getBytes());
        System.setIn(in);

        PoemFactory poemFactory = new PoemFactory();
        try {
            Class<?> poemCommandLineCreator = poemFactory.getClass();
            Method privateAskAuthor = poemCommandLineCreator.getDeclaredMethod("askAuthor");
            privateAskAuthor.setAccessible(true);

            assertEquals(this.expectedAuthor, privateAskAuthor.invoke(poemFactory));
        } catch (Exception e) {
            System.out.println("Error in test ask author: "+e);
        }

        System.setIn(System.in);
    }

    @Test
    public void testAskStanzasAndVerses() {
        String mockedUserInput = "";

        int i; // we doesn't want to put a line sepator after the "done"
        for (i = 0; i < this.expectedStanzasInput.length - 1; i++) {
            mockedUserInput += this.expectedStanzasInput[i] + System.getProperty("line.separator");
        }

        System.setIn(new ByteArrayInputStream(mockedUserInput.getBytes()));

        PoemFactory poemFactory = new PoemFactory();
        try {
            Class<?> poemCommandLineCreator = poemFactory.getClass();
            Method privateAskStanzasAndVerses = poemCommandLineCreator.getDeclaredMethod("askStanzasAndVerses");
            privateAskStanzasAndVerses.setAccessible(true);

            assertEquals(this.expectedStanzas, privateAskStanzasAndVerses.invoke(poemFactory));
        } catch (Exception e) {
            System.out.println("Error in test ask author: "+e);
        }

        System.setIn(System.in);
    }
}