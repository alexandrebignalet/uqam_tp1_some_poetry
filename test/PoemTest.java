import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PoemTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Verse> v = new ArrayList<>();
        v.add(new Verse("Deux fois je regarde ma montre,", "/tre/"));
        v.add(new Verse("Et deux fois à mes yeux distraits", "/ai/"));
        v.add(new Verse("L’aiguille au même endroit se montre :,", "/tre/"));
        v.add(new Verse("Il est une heure... Une heure après.", "/ai/"));

        ArrayList<Verse> v2 = new ArrayList<>();
        v2.add(new Verse("Tu as besoin d'azur", "/ur/"));
        v2.add(new Verse("De grand large et d'air pur", "/ur/"));
        v2.add(new Verse("Alors, fuis la menace", "/as/"));
        v2.add(new Verse("Des phénomènes de masse", "/as/"));
        v2.add(new Verse("Les cris de populace", "/as/"));

        ArrayList<Verse> v3 = new ArrayList<>();
        v3.add(new Verse("Mon enfant, ma soeur,", "/eur/"));
        v3.add(new Verse("Songe à la douceur", "/eur/"));
        v3.add(new Verse("D'aller là-bas vivre ensemble !", "/ble/"));
        v3.add(new Verse("Aimer à loisir,", "/ir/"));
        v3.add(new Verse("Aimer et mourir", "/ir/"));
        v3.add(new Verse("Au pays qui te ressemble !","/ble/"));

        ArrayList<Verse> v4 = new ArrayList<>();
        v4.add(new Verse("Retrouve dans ta tête", "/ète/"));
        v4.add(new Verse("Ton âme de poète", "/ète/"));
        v4.add(new Verse("Souviens-toi comme c'est chouette", "/ète/"));
        v4.add(new Verse("Le parfum des violettes", "/ète/"));
        v4.add(new Verse("Aimer et mourir", "/ète/"));
        v4.add(new Verse("Un soir de pâquerettes", "/ète/"));

        Stanza s = new Stanza(v);
        Stanza s2 = new Stanza(v2);
        Stanza s3 = new Stanza(v3);
        Stanza s4 = new Stanza(v4);

        ArrayList<Stanza> stanzaArrayList = new ArrayList<>();
        stanzaArrayList.add(s);
        stanzaArrayList.add(s2);

        ArrayList<Stanza> stanzaArrayList2 = new ArrayList<>();
        stanzaArrayList2.add(s3);
        stanzaArrayList2.add(s4);

        ArrayList<Stanza> stanzaArrayList3 = new ArrayList<>();
        stanzaArrayList3.add(s);
        stanzaArrayList3.add(s3);

        return Arrays.asList(new Object[][] {
                {"poem1", "booba", stanzaArrayList},
                {"poem2", "kaaris", stanzaArrayList2},
                {"poem3", "PNL", stanzaArrayList3}
        });
    }

    private String expectedName;
    private String expectedAuthor;
    private ArrayList<Stanza> expectedStanzas;

    public PoemTest(String name, String author, ArrayList<Stanza> stanzas){
        this.expectedName = name;
        this.expectedAuthor = author;
        this.expectedStanzas = stanzas;
    }

    @Test
    public void testPoemConstructor() {
        Poem poem = new Poem(this.expectedName, this.expectedAuthor, this.expectedStanzas);

        assertEquals(this.expectedName, poem.getName());
        assertEquals(this.expectedAuthor, poem.getAuthor());
        assertEquals(this.expectedStanzas, poem.getStanzas());
    }

    @Test
    public void testPoemAddStanza() {
        Poem poem = new Poem(this.expectedName, this.expectedAuthor, this.expectedStanzas);

        ArrayList<Verse> verses = new ArrayList<>();
        verses.add(new Verse("Tu as besoin d'azur", "/ur/"));
        verses.add(new Verse("De grand large et d'air pur", "/ur/"));
        verses.add(new Verse("Alors, fuis la menace", "/as/"));
        verses.add(new Verse("Des phénomènes de masse", "/as/"));
        verses.add(new Verse("Les cris de populace", "/as/"));

        Stanza stanza = new Stanza(verses);
        poem.addStanza(stanza);

        assertEquals(stanza, poem.getStanzas().get(poem.getStanzas().size()-1));
        assertEquals(this.expectedStanzas, poem.getStanzas());
    }
}