<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
            <h3>顧客　新規登録ページ</h3>

        <form method="post" action="<c:url value='/clients/create'/>">
            <c:import url="_form.jsp"></c:import>
        </form>
        <p><a href="<c:url value='/clients/index'/>">一覧に戻る</a></p>
    </c:param>
</c:import>