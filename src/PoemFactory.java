import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PoemFactory {

    public Poem create(String name, String author, ArrayList<Stanza> stanzas) {
        return new Poem(name, author, stanzas);
    }

    public Poem createFromCommandLine() {
        String name = this.askName();
        String author = this.askAuthor();
        ArrayList<Stanza> stanzas;

        try {
            stanzas = this.askStanzasAndVerses();
        } catch (IOException ioe) {
            System.out.println("Error happened during the poem creation. Input is not valid.");
            stanzas = new ArrayList<>();
        }

        return this.create(name, author, stanzas);
    }

    private String askName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What's the name of your poem ?");
        return scanner.nextLine();
    }

    private String askAuthor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Who's the author ?");
        return scanner.nextLine();
    }

    private ArrayList<Stanza> askStanzasAndVerses() throws IOException {
        final String END_OF_INPUT = "done";
        final String CARRIAGE_RETURN = "";

        System.out.println("Let's write some verses. Type "+END_OF_INPUT+" when your poem is written.");

        ArrayList<Stanza> stanzas = new ArrayList<>();

        String input = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(isr);

        Stanza stanza = new Stanza(new ArrayList<>());
        while(!input.equals(END_OF_INPUT)) {
            input = bufferedReader.readLine();

            if ( !input.equals(CARRIAGE_RETURN) && !input.equals(END_OF_INPUT)) {
                stanza.addVerse(new Verse(input));
            } else if ( !stanza.getVerses().isEmpty() ) {
                // carriage return -> add the stanza to the poem and create a new Stanza
                stanzas.add(stanza);
                stanza = new Stanza(new ArrayList<>());
            }
        }

        return stanzas;
    }
}
