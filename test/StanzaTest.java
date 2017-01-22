import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StanzaTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Verse> v = new ArrayList<>();
        v.add(new Verse("Deux fois je regarde ma montre, /tre/"));
        v.add(new Verse("Et deux fois à mes yeux distraits /ai/"));
        v.add(new Verse("L’aiguille au même endroit se montre :, /tre/"));
        v.add(new Verse("Il est une heure... Une heure après. /ai/"));

        ArrayList<Verse> v2 = new ArrayList<>();
        v2.add(new Verse("Tu as besoin d'azur /ur/"));
        v2.add(new Verse("De grand large et d'air pur /ur/"));
        v2.add(new Verse("Alors, fuis la menace /as/"));
        v2.add(new Verse("Des phénomènes de masse /as/"));
        v2.add(new Verse("Les cris de populace /as/"));

        ArrayList<Verse> v3 = new ArrayList<>();
        v3.add(new Verse("Mon enfant, ma soeur, /eur/"));
        v3.add(new Verse("Songe à la douceur /eur/"));
        v3.add(new Verse("D'aller là-bas vivre ensemble ! /ble/"));
        v3.add(new Verse("Aimer à loisir, /ir/"));
        v3.add(new Verse("Aimer et mourir /ir/"));
        v3.add(new Verse("Au pays qui te ressemble ! /ble/"));

        ArrayList<Verse> v4 = new ArrayList<>();
        v4.add(new Verse("Retrouve dans ta tête /ète/"));
        v4.add(new Verse("Ton âme de poète /ète/"));
        v4.add(new Verse("Souviens-toi comme c'est chouette /ète/"));
        v4.add(new Verse("Le parfum des violettes /ète/"));
        v4.add(new Verse("Aimer et mourir /ète/"));
        v4.add(new Verse("Un soir de pâquerettes /ète/"));

        ArrayList<Verse> v5 = new ArrayList<>();

        ArrayList<Verse> v6 = new ArrayList<>();
        v6.add(new Verse("dozo /o/"));
        v6.add(new Verse("dozo /o/"));

        return Arrays.asList(new Object[][] {
                {v, "ABAB"},
                {v2, "AABBB"},
                {v3, "AABCCB"},
                {v4, "AAAAAA"},
                {v5, ""},
                {v6, "AA"}
        });
    }

    private ArrayList<Verse> verses;
    private String expectedRhymeSchema;

    public StanzaTest(ArrayList<Verse> verses, String rhymeSchema){
        this.verses = verses;
        this.expectedRhymeSchema = rhymeSchema;
    }

    @Test
    public void testStanzaConstructor() {
        Stanza stanza = new Stanza(this.verses);

        assertEquals(this.verses, stanza.getVerses());
        assertEquals(this.expectedRhymeSchema, stanza.getRhymesSchema());
    }

    @Test
    public void testStanzaHasThisRhymeSchema() {
        Stanza stanza = new Stanza(this.verses);

        if (this.expectedRhymeSchema.equals("ABAB")) {
            assertFalse(stanza.hasThisRhymesSchema("DDDD"));
            assertTrue(stanza.hasThisRhymesSchema("ABAB"));
        }

        if (this.expectedRhymeSchema.equals("AABBB")) {
            assertFalse(stanza.hasThisRhymesSchema("ABAB"));
            assertTrue(stanza.hasThisRhymesSchema("AABBB"));
        }
    }

    @Test
    public void testStanzaAddVerse() {
        Stanza stanza = new Stanza(this.verses);
        Verse verse = new Verse("Un soir de pâquerettes /ète/");
        stanza.addVerse(verse);

        assertEquals(this.verses, stanza.getVerses());
    }

    @Test
    public void testStanzaAddVerseUpdateRhymeSchema() {
        Stanza stanza = new Stanza(this.verses);
        Verse verse = new Verse("Un soir de pâquerettes /ète/");
        stanza.addVerse(verse);

        if (this.expectedRhymeSchema.equals("ABAB")) {
            assertFalse(stanza.hasThisRhymesSchema("DDDD"));
            assertTrue(stanza.hasThisRhymesSchema("ABABC"));
        }

        if (this.expectedRhymeSchema.equals("AABBB")) {
            assertFalse(stanza.hasThisRhymesSchema("ABAB"));
            assertTrue(stanza.hasThisRhymesSchema("AABBBC"));
        }
    }
}