import java.util.ArrayList;
import java.util.Scanner;

public class SomePoetry {

    public static void main(String [] args) {

        final String EXIT_INPUT = "exit";

        ArrayList<Poem> poems = new ArrayList<>();

        PoemBuilder poemBuilder = new PoemBuilder();
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------------");
        System.out.println("-- Winter is here. Let's write some poetry. --");
        System.out.println("----------------------------------------------");

        String input = "";
        while (!input.equals(EXIT_INPUT)) {
            Poem poem = poemBuilder.buildFromCommandLine();
            System.out.println(poem.toString());
            poems.add(poem);

            System.out.println("\n----------------------------------------------------------------------------");
            System.out.println("-- Type \"exit\" to stop expressing yourself. Or any key to go on writing --");
            System.out.println("----------------------------------------------------------------------------");
            input = scanner.nextLine();
        }

        System.out.println("\n----------------------------------------------------------------------------");
        System.out.println("\n--     Select one of your Poem(s) if you want to check rhymes schema      --");
        System.out.println("\n----------------------------------------------------------------------------");

        int i;
        for (i = 0; i < poems.size() ; i++) {
            System.out.println(i + ": " + poems.get(i).getName() + " written by " + poems.get(i).getAuthor());
        }

        int poemChoice;
        final int MAX_VALID_POEM_CHOICE = poems.size();

        System.out.print("Choice: ");
        poemChoice = scanner.nextInt();
        while (poemChoice >= MAX_VALID_POEM_CHOICE) {
            System.out.println("Wrong value ...");
            System.out.print("Choice: ");
            poemChoice = scanner.nextInt();
        }

        System.out.println("\nChoose a stanza");

        int y;
        Poem poem = poems.get(poemChoice);
        for (y = 0; y < poem.getStanzas().size() ; y++) {
            System.out.println(y + ": \n" + poem.getStanzas().get(y).toString());
        }

        int stanzaChoice;
        final int MAX_VALID_STANZA_CHOICE = poem.getStanzas().size();

        System.out.print("Choice: ");
        stanzaChoice = scanner.nextInt();
        while (stanzaChoice >= MAX_VALID_STANZA_CHOICE) {
            System.out.println("Wrong value ...");
            System.out.print("Choice: ");
            stanzaChoice = scanner.nextInt();
        }

        String rhymesSchemaInput;
        Stanza stanzaChoosen = poem.getStanzas().get(stanzaChoice);
        System.out.print("\nEnter a rhyme schema in CAPS like ABAB: ");
        rhymesSchemaInput = scanner.nextLine();
        if (!stanzaChoosen.hasThisRhymesSchema(rhymesSchemaInput) || rhymesSchemaInput.equals("")) {
            System.out.println("Wrong ! This stanza hasn't this rhyme schema: " + rhymesSchemaInput
                    + " but this one: " + stanzaChoosen.getRhymesSchema());
        }
        System.out.println("Right ! This stanza has this rhyme schema: " + rhymesSchemaInput);
    }
}