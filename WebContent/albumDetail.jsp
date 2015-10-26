<%@ page import="unl.cse.albums.Album" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Album Details</title>
</head>
<body>

<% 
  int albumId = -1;
  try {
	  albumId = Integer.parseInt(request.getParameter("albumId"));
  } catch(Exception e) {
	  albumId = -1;
  }
  Album a = Album.getDetailedAlbum(albumId); %>
<h1><%=a.getTitle()%></h1>

<p>By <i><%=a.getBand().getName()%></i>, (<%=a.getYear()%>)</p>

<ol>
  <%for(String title : a.getSongTitles()) {%>
    <li><%=title%></li>
  <%}%>
</ol>

</body>
</html>