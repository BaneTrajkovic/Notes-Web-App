<%@ page import="java.util.ArrayList" %>
<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListOfNotes</title>
    <link rel="stylesheet" href="navigationBarStyles.css">
    <link rel="stylesheet" href="showSortedIndexOnlyNamesStyles.css">
    <link rel="stylesheet" href="headerStyles.css">
</head>
<body>
<h2>List Of Notes Sorted By Index</h2>
<jsp:include page="/navigationBarForPages.jsp"/>
<div class="listContainer">
    <ul style="height: fit-content;
               font-size: 25px;">
            <%
        ArrayList<Note> listOfNotes = (ArrayList<Note>) request.getAttribute("listOfNotes");
        String currentIndexOfNote = "";
        for (Note note: listOfNotes)
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

            <ul>
                <li id="notesText"><%=note.getTextOfNote()%></li>
            </ul>
            <%}%>
        </ul>
</div>
</body>
</html>
