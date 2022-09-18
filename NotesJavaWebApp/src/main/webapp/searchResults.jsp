<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SearchResults</title>
    <link rel="stylesheet" href="navigationBarStyles.css">
    <link rel="stylesheet" href="headerStyles.css">
    <link rel="stylesheet" href="indexStyles.css">
    <link rel="stylesheet" href="showSortedIndexOnlyNamesStyles.css">
</head>
<body>
<h2>Search Results</h2>
<jsp:include page="/navigationBarForPages.jsp"/>
<div>

                    <h2>Search By Index:</h2>
    <ul style="height: fit-content;
               font-size: 25px;">
            <%
        ArrayList<Note> listOfNotes = (ArrayList<Note>) request.getAttribute("listOfNotes");
        ArrayList<Note> notesSearchIndex = (ArrayList<Note>) request.getAttribute("notesSearchIndex");
        ArrayList<Note> notesSearchName = (ArrayList<Note>) request.getAttribute("notesSearchName");


        String currentIndexOfNote = "";
        for (Note note: notesSearchIndex)
        {

            if (currentIndexOfNote.equals(""))
                {
                    currentIndexOfNote = note.getIndexOfNote();

                    %>
        <li><%=currentIndexOfNote%></li>
        <ul style="background-color: white;
                   font-size: 20px;
                   border-radius: 25px;
                   border: 1px solid dodgerblue;
                   display: inline-block;
                   padding-right: 25px;">

            <%}
            else if (!note.getIndexOfNote().equals(currentIndexOfNote))
            {
                currentIndexOfNote = note.getIndexOfNote();
            %>
              </ul>
        <li><%=currentIndexOfNote%></li>
        <ul style="background-color: white;
                   font-size: 20px;
                   border-radius: 25px;
                   border: 1px solid dodgerblue;
                   display: inline-block;
                   padding-right: 25px;">
            <%}%>
            <%--        removing spaces for url    --%>
            <% String href = "/getNoteForEditOrDelete.html?nameOfNote=" + note.getNameOfNote().replace(" ","_");
            %>

            <li><a href=<%=href%>><%=note.getNameOfNote()%></a></li>

            <%}%>
        </ul>
    </ul>
</div>

    <h2>Search By Name:</h2>
    <ul style="background-color: white;
               font-size: 20px;
               border-radius: 25px;
               border: 1px solid dodgerblue;
               display: inline-block;
               padding-right: 25px;">
        <%
            for (Note note: notesSearchName)
            {

                String href = "/getNoteForEditOrDelete.html?nameOfNote=" + note.getNameOfNote().replace(" ","_");
        %>
        <li><a href=<%=href%>><%=note.getNameOfNote()%></a></li>
        <%}%>





</body>
</html>
