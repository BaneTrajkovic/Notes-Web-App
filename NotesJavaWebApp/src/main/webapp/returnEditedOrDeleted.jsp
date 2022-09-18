<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>EditedOrDeleted</title>
    <link rel="stylesheet" href="navigationBarStyles.css">
    <link rel="stylesheet" href="indexStyles.css">
    <link rel="stylesheet" href="headerStyles.css">
</head>
<body>
<% String editedOrDeleted = (String) request.getAttribute("editedOrDeleted");

if (editedOrDeleted.equals("EDITED"))
{
    %>
<h2>Note successfully edited</h2>
<jsp:include page="/navigationBarForPages.jsp"/>
<p>You will be able to see the changes in list of notes.</p>
<%
}
else if (editedOrDeleted.equals("DELETED"))
{
%>
<h2>Note successfully deleted</h2>
<jsp:include page="/navigationBarForPages.jsp"/>
<p>You will not be able to see the note in list of notes.</p>
<%}
else
{
%>
<h2>Note cannot be edited</h2>
<jsp:include page="/navigationBarForPages.jsp"/>
<p>Note name already exists.</p>
<%}%>

</body>
</html>
