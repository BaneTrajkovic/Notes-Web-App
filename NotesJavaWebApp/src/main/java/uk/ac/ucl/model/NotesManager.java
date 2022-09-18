package uk.ac.ucl.model;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * NotesManager class is used for creating,
 * storing, sorting, as well as file input
 * and output of information contained in
 * Note objects.
 *
 * With every change of Note object managed by NotesManager,
 * corresponding note files are being edited or deleted.
 *
 * Added feature when creating and storing Notes into
 * NotesManager is ability to create new Notes without
 * having any of the fields (index,name,url, image or text)
 * specified. Which means that user can only input
 * fields needed at the time of Note object's creation.
 */

public class NotesManager
{

    /**
     * List for storing all notes which updates accordingly.
     */

    private final ArrayList<Note> listOfNotes;


    public ArrayList<Note> getListOfNotes()
    {
        return new ArrayList<>(listOfNotes);
    }


    public void addNoteToList(Note note)
    {
        listOfNotes.add(note);
    }


    public NotesManager()
    {
        listOfNotes = new ArrayList<Note>();
    }

    /**
     * Method for creating new note file and
     * adding the Note object to listOfNotes.
     */

    public void createNewNoteFile(String indexOfNote, String nameOfNote, String urlOfNote,
                                  String imageOfNote, String textOfNote)
    {

        // Creating a path to data directory where the note is going to be stored.
        String nameOfPath = createNoteNamePath(nameOfNote);
        try
        {
            File obj = new File(nameOfPath);
            if (obj.createNewFile())
            {
                System.out.println("File successfully created: " + obj.getName());
            }
            else
            {
                System.out.println("File already exists.");
            }

            // Writing properties of note to newly created note file.
            writeToNoteFile(indexOfNote,nameOfNote,urlOfNote,imageOfNote,textOfNote,nameOfPath);

            // Adding the newly created note into listOfNotes (always updated).
            listOfNotes.add(new Note(indexOfNote,nameOfNote,urlOfNote,imageOfNote,textOfNote));

        } catch (IOException e) {
            System.out.println("Error while creating a file.");
            e.printStackTrace();
        }
    }


    /**
     * Method which takes all elements of the Note object
     * and writes them to the file line by line.
     */

    private void writeToNoteFile(String indexOfNote, String nameOfNote,
                                 String urlOfNote, String imageOfNote,
                                 String textOfNote, String nameOfPath)
    {
        try
        {
            FileWriter myWriter = new FileWriter(nameOfPath);

            myWriter.write(indexOfNote + "\n" + nameOfNote +
                    "\n" + urlOfNote + "\n" + imageOfNote + "\n" + textOfNote);

            myWriter.close();

        } catch (IOException e) {

            System.out.println("Error while writing to file");
            e.printStackTrace();
        }
    }


    /**
     * Method for deleting note file
     * (inclusive with Note object removal from listOfNotes)
     */

    public void deleteNoteFile(String nameOfNote)
    {
        // Getting the path of the note file for deletion.
        String nameOfPath = createNoteNamePath(nameOfNote);

        Note noteForDeletion = getNoteByName(nameOfNote);
        listOfNotes.remove(noteForDeletion);

        File fileForDeletion = new File(nameOfPath);

        if (fileForDeletion.delete())
        {
            System.out.println("Successful delete for " + nameOfPath);
        }
        else
        {
            System.out.println("Unsuccessful delete");
        }

    }


    /**
     * Method for creating new image file for note.
     * If the user of the note app does not specify any image,
     * defaultImage.png (none) will be set as the image.
     */

    public String createImageFile(Part filePart)
    {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        if (!fileName.equals(""))
        {
            try (InputStream fileContent = filePart.getInputStream())
            {
                File imageInFolder = new File(createFromUserToServerImagePath(fileName));
                Files.copy(fileContent,imageInFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {

                System.out.println("Error while trying to save image: " + fileName);
            }
        }
        else
        {
            fileName = "defaultImage.png";
        }

        return fileName;

    }


    /**
     * Method for deleting image file.
     * Used upon deleting its corresponding note.
     */

    public void deleteImageFile(String nameOfNoteBefore)
    {
        Note note = getNoteByName(nameOfNoteBefore);

        String pathToImage = createFromUserToServerImagePath(note.getImageOfNote());
        System.out.println("Image of note:" + pathToImage);

        File imageFile = new File(pathToImage);
        if (!imageFile.getName().equals("defaultImage.png"))
        {
            if (imageFile.delete())
            {
                System.out.println("Successfully deleted image: " + pathToImage);
            }
            else
            {
                System.out.println("Unsuccessful deletion of image");
            }
        }

    }


    /**
     * Method for creating name of path for
     * chosen note name (notes are stored in directory data).
     */

    private String createNoteNamePath(String nameOfNote)
    {
        return "./data/" + nameOfNote + ".txt";
    }


    /**
     * Method for getting pathname for storing
     * image files created in the app.
     */

    public String createFromUserToServerImagePath(String fileName)
    {
        return "./src/main/webapp/imageData/" + fileName;
    }


    /**
     * Methods for getting pathname for storing
     * image files created in the app.
     *
     */

    public String createFromServerToUserImagePath(String imageOfNote)
    {
        return "./imageData/" + imageOfNote;
    }


    /**
     * Class implementing Comparator interface
     * for the purpose of sorting the list of notes
     * (alphabetically) by index and by the name
     * in the same index.
     */

    private static class SortByIndexAndNameComparator implements Comparator<Note>
    {
        @Override
        public int compare(Note o1, Note o2)
        {
            if (o1.getIndexOfNote().equals(o2.getIndexOfNote()))
            {
                return o1.getNameOfNote().compareTo(o2.getNameOfNote());
            }
            else
            {
                return o1.getIndexOfNote().compareTo(o2.getIndexOfNote());
            }
        }
    }


    /**
     * Method for sorting list of notes
     * (alphabetically) using created Comparator.
     */

    public void sortByIndexAndName()
    {
        listOfNotes.sort(new SortByIndexAndNameComparator());
    }


    /**
     *  Getting Note object, based on name of the Note.
     *  Useful when editing or deleting previously created note.
     */

    public Note getNoteByName(String nameOfNote)
    {
        for (Note note: listOfNotes)
        {
            if (note.getNameOfNote().equals(nameOfNote))
            {
                return note;
            }
        }
        System.out.println("Error in gettingNoteName");
        return null;
    }


    /**
     * Method for searching for the index of Note,
     * by its name. Returns the list of Notes
     * with indexes matching search keyword.
     */

    public ArrayList<Note> searchForIndex(String inputFromSearch)
    {
        sortByIndexAndName();
        ArrayList<Note> notesSearchIndex = new ArrayList<>();
        for(Note note: listOfNotes)
        {
            if (note.getIndexOfNote().startsWith(inputFromSearch)
                    || note.getIndexOfNote().endsWith(inputFromSearch))
            {
                notesSearchIndex.add(note);
            }
        }
        return notesSearchIndex;
    }


    /**
     * Method for searching for the name of Note,
     * Returns the list of notes with names matching
     * the search keyword.
     */

    public ArrayList<Note> searchForName(String inputFromSearch)
    {
        ArrayList<Note> notesSearchName = new ArrayList<>();
        for(Note note: listOfNotes)
        {
            if (note.getNameOfNote().startsWith(inputFromSearch)
                    || note.getNameOfNote().endsWith(inputFromSearch))
            {
                notesSearchName.add(note);
            }
        }
        notesSearchName.sort(Comparator.comparing(Note::getNameOfNote));
        return notesSearchName;

    }


    /**
     * Checking if note with same name already exists
     * Function is used for determining whether new
     * name for note can be used (no duplicates)
     */

    public boolean noteAlreadyExists(String nameOfNote)
    {
        String nameOfPath = createNoteNamePath(nameOfNote);
        File note = new File(nameOfPath);
        return note.exists();
    }


}
