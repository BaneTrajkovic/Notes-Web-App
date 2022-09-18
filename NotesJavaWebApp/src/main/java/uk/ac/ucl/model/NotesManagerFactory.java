package uk.ac.ucl.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * NotesManagerFactory is a class used for
 * implementing Singleton Pattern.
 *
 * Since singleton pattern is implemented,
 * only one instance object of the class
 * can be created when running the program.
 */

public class NotesManagerFactory
{
    private static NotesManager storedNotes;

    private NotesManagerFactory()
    {
    }

    /**
     * Static method which returns instance
     * of NoteManager class for its use in
     * the note app.
     *
     * When running the app again, stored
     * note files will be read line by line,
     * creating Note objects and adding them
     * to NotesManager's listOfNotes.
     */
    public static NotesManager getNotesManager()
    {
        if (storedNotes==null)
        {
            storedNotes = new NotesManager();

            // Creating list of all files in the specified directory.
            File directoryData = new File("./data");
            File[] notesTextFilesList = directoryData.listFiles();

            Scanner myReader;
            String line;

            String indexOfNoteFromFile = "";
            String nameOfNoteFromFile = "";
            String urlOfNoteFromFile = "";
            String textOfNoteFromFile = "";
            String imageOfNoteFromFile = "";

            // Declaring StringBuffer for storing main text of note.
            StringBuffer sb;

            int i;

            // Adding each note into NoteManager's listOfNotes.
            if (notesTextFilesList != null)
            {
                for (File noteTextFile: notesTextFilesList)
                {

                    i = 0;
                    try
                    {
                        myReader = new Scanner(noteTextFile);
                        sb = new StringBuffer();

                        while (myReader.hasNextLine())
                        {
                            line = myReader.nextLine();

                            switch (i)
                            {
                                case 0 -> indexOfNoteFromFile = line;
                                case 1 -> nameOfNoteFromFile = line;
                                case 2 -> urlOfNoteFromFile = line;
                                case 3 -> imageOfNoteFromFile = line;
                                default ->
                                        {
                                            sb.append(line);
                                            sb.append("\n");
                                        }
                            }
                            i+=1;
                        }

                        textOfNoteFromFile = sb.toString();

                        storedNotes.addNoteToList(new Note(indexOfNoteFromFile,nameOfNoteFromFile,
                                urlOfNoteFromFile,imageOfNoteFromFile,textOfNoteFromFile));

                        myReader.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("Error: File has not been found");
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                System.out.println("Abstract pathname does not denote a directory.");
            }
        }
        return storedNotes;
    }
}
