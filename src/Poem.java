import java.util.ArrayList;

public class Poem {
    private String name;
    private String author;
    private ArrayList<Stanza> stanzas;

    public Poem(String name, String author, ArrayList<Stanza> stanzas) {
        this.name = name;
        this.author = author;
        this.stanzas = stanzas;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public ArrayList<Stanza> getStanzas() {
        return this.stanzas;
    }

    public void addStanza(Stanza stanza) {
        this.stanzas.add(stanza);
    }

    public String toString() {
        String toString = this.getName()+"\n\n";
        String rhymeSchemas = "";

        int i;
        for (i = 0; i < this.getStanzas().size(); i++) {
            toString += this.getStanzas().get(i).toString() + "\n";
            rhymeSchemas += "Stanza " + (i+1) + " " + this.getStanzas().get(i).getRhymesSchema() + "\n";
        }

        toString += this.getAuthor();

        return toString + "\n\nRhyme schemas: \n" + rhymeSchemas;
    }
}
