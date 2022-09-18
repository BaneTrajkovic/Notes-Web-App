package uk.ac.ucl.servlets;

import uk.ac.ucl.model.NotesManager;
import uk.ac.ucl.model.NotesManagerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Random;

// The servlet invoked to try to create new note (if one already does not exist)
// The url http://localhost:8080/savenote.html is mapped to calling doPost on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/createNewNote.html")
@MultipartConfig
public class CreateNewNoteServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Using static method to get reference to the NotesManager object (Singleton pattern)
        NotesManager manager = NotesManagerFactory.getNotesManager();

        /* Getting (index and name) parameters set by the user */
        String indexOfNote = request.getParameter("indexOfNote");
        String nameOfNote = request.getParameter("nameOfNote");
        String urlOfNote = request.getParameter("urlOfNote");


        /*
         In case of user not setting index of note.
         Note will be stored in the defaultIndex (index).
        */
        if (indexOfNote.equals(""))
        {
            indexOfNote = "defaultIndex";
        }


        /*
         In case of user not setting name of note.
         Note will be stored as defaultNote (name),
         followed by random integer.
        */
        if (nameOfNote.equals(""))
        {
            Random random = new Random();
            nameOfNote = String.format("defaultNote%d", Math.abs(random.nextInt()));
        }

        /*
         In case url is not specified.
         Note will contain default url
         index.html (url).
        */
        if (urlOfNote.equals(""))
        {
            urlOfNote = "index.html";
        }


        String textOfNote = request.getParameter("textOfNote");


        // Setting necessary attributes for JSP
        request.setAttribute("indexOfNote", indexOfNote);
        request.setAttribute("nameOfNote", nameOfNote);
        request.setAttribute("textOfNote", textOfNote);
        request.setAttribute("urlOfNote", urlOfNote);


        /*
         Checking whether note file with
         the same name already exists.
        */
        String doesAlreadyExist;
        if (manager.noteAlreadyExists(nameOfNote))
        {
            doesAlreadyExist = "YES";
        }
        else
        {
            doesAlreadyExist = "NO";

            Part filePart = request.getPart("imageOfNote");

            // Creates image file and returns filename
            String imageOfNote = manager.createImageFile(filePart);

            // Creating new note with elements from the user's input
            manager.createNewNoteFile(indexOfNote,nameOfNote,urlOfNote,imageOfNote,textOfNote);
        }


        // Setting the attribute whether note already exists or not ("YES"/"NO")
        request.setAttribute("doesAlreadyExist", doesAlreadyExist);


        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/returnNewCreatedOrNot.jsp");
        dispatch.forward(request, response);
    }
}

