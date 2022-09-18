package uk.ac.ucl.model;


/**
 * Note class used for creating notes, with all possibilities
 * embedded into it (index, name, url, image and text).
 *
 * Collection of Notes are being managed using NotesManager class.
 *
 */

public class Note
{
    private String indexOfNote;
    private String nameOfNote;
    private String urlOfNote;
    private String imageOfNote;
    private String textOfNote;

    public Note(String indexOfNote, String nameOfNote, String urlOfNote,
                String imageOfNote, String textOfNote)
    {
        this.indexOfNote = indexOfNote;
        this.nameOfNote = nameOfNote;
        this.urlOfNote = urlOfNote;
        this.imageOfNote = imageOfNote;
        this.textOfNote = textOfNote;
    }

    public String getNameOfNote()
    {
        return nameOfNote;
    }

    public void setNameOfNote(String nameOfNote)
    {
        this.nameOfNote = nameOfNote;
    }

    public String getTextOfNote()
    {
        return textOfNote;
    }

    public void setTextOfNote(String textOfNote)
    {
        this.textOfNote = textOfNote;
    }

    public String getIndexOfNote()
    {
        return indexOfNote;
    }

    public void setIndexOfNote(String indexOfNote)
    {
        this.indexOfNote = indexOfNote;
    }

    public void setUrlOfNote(String urlOfNote)
    {
        this.urlOfNote = urlOfNote;
    }

    public String getUrlOfNote()
    {
        return urlOfNote;
    }

    public void setImageOfNote(String imageOfNote)
    {
        this.imageOfNote = imageOfNote;
    }

    public String getImageOfNote()
    {
        return imageOfNote;
    }


}
