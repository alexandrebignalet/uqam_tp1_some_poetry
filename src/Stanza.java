import java.util.ArrayList;

public class Stanza {
    private ArrayList<Verse> verses;
    private String rhymesSchema;

    public Stanza(ArrayList<Verse> verses) {
        this.verses = verses;
        this.rhymesSchema = findRhymesSchema();
    }

    public ArrayList<Verse> getVerses() {
        return verses;
    }

    public String getRhymesSchema() {
        return rhymesSchema;
    }

    public void addVerse(Verse verse) {
        this.verses.add(verse);
        this.rhymesSchema = findRhymesSchema();
    }

    public boolean hasThisRhymesSchema(String rhymesSchema) {
        return this.rhymesSchema.equals(rhymesSchema);
    }

    private String findRhymesSchema() {
        if (this.getVerses().isEmpty()) {
            return "";
        }

        String rhymesSchema = this.verses.get(0).getRhyme();

        // phonems will contain all the rhymes' phonemes
        ArrayList<String> phonemes = new ArrayList<>();
        phonemes.add(this.verses.get(0).getRhyme());

        int i;
        /*
         * Loop against stanza's verses to get all the phonemes
         * Start at 1 because we have already set the first phoneme
         * Concatenate all the rhymes in a String
         * All of this to be sure that phonemes are unique
         */
        for ( i = 1; i < this.verses.size(); i++) {

            rhymesSchema += this.verses.get(i).getRhyme();

            Integer index = phonemes.indexOf(this.verses.get(i).getRhyme());
            if ( index == -1 ) {
                phonemes.add(this.verses.get(i).getRhyme());
            }
        }

        /*
         * Now it iterates through the phonemes to build the rhyme schema
         * replacing the rhymes by a letter
         */
        char[] rhymesSchemaLetters = "ABCDEF".toCharArray();

        for ( i = 0; i < phonemes.size(); i++ ) {
            rhymesSchema = rhymesSchema.replace(phonemes.get(i), ""+rhymesSchemaLetters[i]);
        }

        return rhymesSchema;
    }

    public String toString() {
        String toString = "";

        for (Verse verse: this.verses) {
            toString += verse.toString() + "\n";
        }

        return toString;
    }
}
