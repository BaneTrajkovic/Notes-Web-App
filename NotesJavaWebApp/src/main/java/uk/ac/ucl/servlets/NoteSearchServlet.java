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
import java.util.ArrayList;

// The servlet invoked to perform a search.
// The url http://localhost:8080/runsearch.html is mapped to calling doPost on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/searchForNotes.html")
public class NoteSearchServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Use the model to do the search and put the results into the request object sent to the
        // Java Server Page used to display the results.
        NotesManager manager = NotesManagerFactory.getNotesManager();

        String searchKeyword = request.getParameter("searchKeyword");

        ArrayList<Note> notesSearchIndex = manager.searchForIndex(searchKeyword);
        ArrayList<Note> notesSearchName = manager.searchForName(searchKeyword);

        manager.sortByIndexAndName();

        request.setAttribute("listOfNotes", manager.getListOfNotes());
        request.setAttribute("notesSearchIndex", notesSearchIndex);
        request.setAttribute("notesSearchName", notesSearchName);

        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/searchResults.jsp");
        dispatch.forward(request, response);
    }
}
