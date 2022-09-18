package uk.ac.ucl.servlets;

import uk.ac.ucl.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// The servlet invoked to display a sorted list of notes by index (only names).
// The url http://localhost:8080/listOfNotesByIndexOnlyNames.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/listOfNotesByIndexOnlyNames.html")
public class SortedIndexOnlyNamesServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Get the data from the model.
        NotesManager manager = NotesManagerFactory.getNotesManager();


        // Sorting listOfNotes
        manager.sortByIndexAndName();


        // Set attribute listOfNotes (now sorted) for further use in JSP.
        request.setAttribute("listOfNotes", manager.getListOfNotes());


        // Invoke the JSP.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/showSortedIndexOnlyNames.jsp");
        dispatch.forward(request, response);
    }
}
