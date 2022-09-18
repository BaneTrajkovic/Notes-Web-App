<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoteEditorPage</title>
    <link rel="stylesheet" href="existingNoteEditorStyles.css">
    <link rel="stylesheet" href="tableForInputStyles.css">
    <link rel="stylesheet" href="navigationBarStyles.css">
    <link rel="stylesheet" href="headerStyles.css">
</head>
<body>
<h2>View/Edit/Delete Note</h2>

<jsp:include page="/navigationBarForPages.jsp"/>

<div class="bothColumnContainer">
    <div class="columnContainer">
    <form method="POST" action="/editOrDeleteNote.html" enctype="multipart/form-data">
            <table class="container">
                <tr>
                    <th>Index of the note:</th>
                </tr>
                <tr>
                    <td><input type="text" name="indexOfNote" value="<%=request.getAttribute("indexOfNote")%>"/></td>
                </tr>
                <tr>
                    <th>Name of the note:</th>
                </tr>
                <tr>
                    <td><input type="text" name="nameOfNote" value="<%=request.getAttribute("nameOfNote")%>"/></td>
                    <td><input type="hidden" name="nameOfNoteBefore" value="<%=request.getAttribute("nameOfNote")%>"/></td>
                </tr>
                <tr>
                    <th>URL of note: (first enter: https://)</th>
                </tr>
                <tr>
                    <td><input type="text" name="urlOfNote" value="<%=request.getAttribute("urlOfNote")%>" /> </td>
                </tr>
                <tr>
                    <th>Image of the note: (jpeg, png or gif)</th>
                </tr>
                <tr>
                    <th><input type="file" name="imageOfNote" accept="image/jpeg, image/png, image/gif"></th>
                </tr>
                <tr>
                    <td><a href="<%=request.getAttribute("urlOfNote")%> " target="_blank"><%=request.getAttribute("urlOfNote")%></a></td>
                </tr>
                <tr>
                    <th>Text of the note:</th>
                </tr>
                <tr>
                    <td><textarea name="textOfNote"><%=request.getAttribute("textOfNote")%></textarea></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update note" name="saveEditButton"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Delete note" name="deleteButton"></td>
                </tr>
            </table>
    </form>
    </div>

    <div class="columnContainer">
        <img src="<%=request.getAttribute("pathForImage")%>" alt="imageForNote" width="500px" height="500px">
    </div>
</div>
</body>
</html>
