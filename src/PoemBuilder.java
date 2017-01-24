import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PoemBuilder {

    public Poem buildFromCommandLine() {
        String name = this.askName();
        String author = this.askAuthor();
        ArrayList<Stanza> stanzas;

        try {
            stanzas = this.askStanzasAndVerses();
        } catch (IOException ioe) {
            System.out.println("Error happened during the poem creation. Input is not valid.");
            stanzas = new ArrayList<>();
        }

        return new Poem(name, author, stanzas);
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
        Verse v;
        while(!input.equals(END_OF_INPUT)) {
            input = bufferedReader.readLine();

            if ( !input.equals(CARRIAGE_RETURN) && !input.equals(END_OF_INPUT)) {
                v = Verse.getValidInstance(input);
                if (v != null) {
                    stanza.addVerse(v);
                } else {
                    System.out.println("<--> Invalid verse. Right format: content /phoneme/ <--> ");
                }
            } else if ( !stanza.getVerses().isEmpty() ) {
                // carriage return -> add the stanza to the poem and create a new Stanza
                stanzas.add(stanza);
                stanza = new Stanza(new ArrayList<>());
            }
        }

        return stanzas;
    }
}
