<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful</title>
    <link rel="stylesheet" href="navigationBarStyles.css">
    <link rel="stylesheet" href="returnNewCreatedOrNotStyles.css">
    <link rel="stylesheet" href="headerStyles.css">
    <link rel="stylesheet" href="indexStyles.css">
</head>
<body>
<% String doesAlreadyExist = (String) request.getAttribute("doesAlreadyExist");
if (doesAlreadyExist.equals("YES"))
{%>
<h2>Note Already Exists</h2>
<jsp:include page="/navigationBarForPages.jsp"/>
<p>In order to edit/change note with this name:</p>
<ol>
    <li>
        Go to Home tab
    </li>
    <li>Find existing note with:
        <ul>
            <li>Search</li>
            <li>Sorted notes</li>
        </ul>
    </li>
</ol>
<%}
else
{%>
<h2>Successful Creation Of Note</h2>
<jsp:include page="/navigationBarForPages.jsp"/>
<p>Index will store a collection of notes.</p>
<h3>This is your note:</h3>
    <table class="container">
        <tr>
            <th>Index of the note:</th>
        </tr>
        <tr>
            <td><%=request.getAttribute("indexOfNote")%></td>
        </tr>
        <tr>
            <th>Name of the note:</th>
        </tr>
        <tr>
            <td><%=request.getAttribute("nameOfNote")%></td>
        </tr>
        <tr>
            <th>Url of the note:</th>
        </tr>
        <tr>
            <td><%=request.getAttribute("urlOfNote")%></td>
        </tr>
        <tr>
            <th>Image of the note (to be seen in sorted list of notes)</th>
        </tr>
        <tr>
            <th>Text of the note:</th>
        </tr>
        <tr>
            <td id="notesText"><%=request.getAttribute("textOfNote")%></td>
        </tr>
    </table>
<%}%>
<a href="index.html"><button>Go to Home</button></a>
</body>
</html>
