<%@ page import="com.example.TeamProject.controller.ChatController" %>
<%
<%--    Забираем с запроса параметр ?--%>
    String param1 = request.getParameter("param1");
    String strResponse;
<%--Вызывается метод класса--%>
    strResponse = SomeClass.doSomeActionToGetResponse(param1);
<%-- Выгружается ответ --%>
    out.println(strResponse);
    out.flush();
%>