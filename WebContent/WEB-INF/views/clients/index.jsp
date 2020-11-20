<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>顧客　一覧</h2>
        <table id="client_list">
            <tbody>
                <tr>
                    <th>顧客番号</th>
                    <th>氏名</th>
                    <th>年齢</th>
                </tr>
                <c:forEach var="client" items="${clients}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${client.code}" /></td>
                        <td><c:out value="${client.name}" /></td>
                        <td><c:out value="${client.age}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${clients_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((clients_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/clients/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/clients/new' />">新規顧客の登録</a></p>
        <p><a href="<c:url value='/reports/index'/>">日報の編集に戻る</a></p>

    </c:param>
</c:import>