package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Note;
import uk.ac.ucl.model.NotesManager;
import uk.ac.ucl.model.NotesManagerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// The servlet invoked to get the editor for chosen note
// The url http://localhost:8080/getNoteForEditOrDelete.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/getNoteForEditOrDelete.html")
public class GetNoteForEditOrDeleteServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /*
         Getting NoteManager object from the factory
         in order to obtain notes data.
        */

        NotesManager manager = NotesManagerFactory.getNotesManager();


        /*
         Getting the name of note (replacing underscores with spaces),
         since underscores replaced spaces when the name was
         passed to url.
        */

        String nameOfNoteWithUnderscores = request.getParameter("nameOfNote");
        String nameOfNote = nameOfNoteWithUnderscores.replace("_"," ");


        /*
         Get Note object by using only name of the Note,
         in order to get all the other elements.
        */

        Note note = manager.getNoteByName(nameOfNote);


        String pathForImage = manager.createFromServerToUserImagePath(note.getImageOfNote());


        // Setting all Note attributes for JSP.
        request.setAttribute("indexOfNote", note.getIndexOfNote());
        request.setAttribute("nameOfNote", nameOfNote);
        request.setAttribute("urlOfNote", note.getUrlOfNote());
        request.setAttribute("pathForImage", pathForImage);
        request.setAttribute("textOfNote", note.getTextOfNote());


        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/existingNoteEditor.jsp");
        dispatch.forward(request, response);
    }
}


