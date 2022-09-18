package uk.ac.ucl.servlets;

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

// The servlet invoked to display a list of notes.
// The url http://localhost:8080/listOfNotesByIndexWithText.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/listOfNotesByIndexWithText.html")
public class SortedIndexWithTextServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Get the data from the model.
        NotesManager manager = NotesManagerFactory.getNotesManager();


        // Sorting listOfNotes.
        manager.sortByIndexAndName();


        // Set attribute listOfNotes (now sorted) for further use in JSP.
        request.setAttribute("listOfNotes", manager.getListOfNotes());


        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/showSortedIndexWithText.jsp");
        dispatch.forward(request, response);
    }
}
