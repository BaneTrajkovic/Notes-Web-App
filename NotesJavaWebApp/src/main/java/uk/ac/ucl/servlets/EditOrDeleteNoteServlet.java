package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Note;
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
import java.nio.file.Paths;

// The servlet invoked to process edit or delete of note
// The url http://localhost:8080/editOrDeleteNote.html is mapped to calling doPost on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/editOrDeleteNote.html")
@MultipartConfig
public class EditOrDeleteNoteServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /*
         Use the model to do the search and send the results to the
         Java Server Page used to display them.
        */
        NotesManager manager = NotesManagerFactory.getNotesManager();


        /*
         Requesting parameters from both submit options to see
         which option was clicked.
        */
        String saveEditButton = request.getParameter("saveEditButton");
        String deleteButton = request.getParameter("deleteButton");


        // Requesting hidden input text (name of Note before the change by user).
        String nameOfNoteBefore = request.getParameter("nameOfNoteBefore");


        String nameOfNoteEdited = request.getParameter("nameOfNote");

        /*
         Checking whether saveEditButton was clicked
         for saving the newly edited file.
        */
        String editedOrDeleted = "";

        if (saveEditButton!=null)
        {
            editedOrDeleted = "EDITED";

            if (!nameOfNoteBefore.equals(nameOfNoteEdited) && manager.noteAlreadyExists(nameOfNoteEdited))
            {
                editedOrDeleted = "ALREADY EXISTS";
            }
            else
            {
                /*
                 Getting Note object before edit in order
                 to reuse the image of note before the edit
                 (if no new image has been specified).
                */
                Note noteBeforeEdit = manager.getNoteByName(nameOfNoteBefore);

                manager.deleteNoteFile(nameOfNoteBefore);

                // Getting properties of the newly edited note.
                String indexOfNote = request.getParameter("indexOfNote");
                String urlOfNote = request.getParameter("urlOfNote");
                String textOfNote = request.getParameter("textOfNote");
                Part filePart = request.getPart("imageOfNote");

                // Creates image file and returns filename
                String imageOfNote = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                if (imageOfNote.equals(""))
                {
                    imageOfNote = noteBeforeEdit.getImageOfNote();
                }

                manager.createImageFile(filePart);

                manager.createNewNoteFile(indexOfNote,nameOfNoteEdited,urlOfNote,imageOfNote,textOfNote);
            }
        }

        /*
         Checking whether deleteButton was clicked
         for deleting the viewed file.
        */
        else if (deleteButton!=null)
        {
            editedOrDeleted = "DELETED";

            manager.deleteImageFile(nameOfNoteBefore);
            manager.deleteNoteFile(nameOfNoteBefore);

        }


        request.setAttribute("editedOrDeleted", editedOrDeleted);

        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/returnEditedOrDeleted.jsp");
        dispatch.forward(request, response);
    }
}

