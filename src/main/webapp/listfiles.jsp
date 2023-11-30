<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.File" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>List of Uploaded Files</title>
</head>
<body>
    <h1>List of Uploaded Files</h1>
     <% 
        String uploadPath = "D:/sp/ISTE/Upload"; // Specify the directory where files are uploaded

        File uploadDir = new File(uploadPath);
        File[] files = uploadDir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
    %>
                <p><a href="<%= request.getContextPath() %>/DownloadServlet?fileName=<%= file.getName() %>"><%= file.getName() %></a></p>
    <%
            }
        } else {
    %>
            <p>No files uploaded yet.</p>
    <% 
        }
    %>


</body>
</html>