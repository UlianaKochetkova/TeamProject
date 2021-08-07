<%@ page import="com.example.TeamProject.controller.ChatController"%>
<%
<%--    Тот параметр, который прописали в send--%>
    String tagid = request.getParameter("tagid");

    String strResponse;
<%--Вызывается метод класса--%>
<%--Здесь создаем объект класса, вызываем новую функцию с параметрами, получаем ответ (лучше сразу в JSON)--%>
    ChatController cc=new ChatController();
    strResponse = cc.getTag1(tagid);

    out.println(strResponse);
    out.flush();
%>