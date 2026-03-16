<%@ page import="java.util.*,com.example.*" %>

<h2>User List</h2>

<a href="add-user.jsp">Add User</a>

<table border="1">

<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
</tr>

<%

List<User> list=UserDAO.getAllUsers();

for(User u:list){

%>

<tr>
<td><%=u.getId()%></td>
<td><%=u.getName()%></td>
<td><%=u.getEmail()%></td>
</tr>

<% } %>

</table>
