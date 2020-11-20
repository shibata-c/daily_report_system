<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${client != null}">
                <h2>id: ${client.id} の顧客詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>顧客番号</th>
                            <td><c:out value="${client.code}"></c:out></td>
                        </tr>

                        <tr>
                            <th>顧客氏名</th>
                            <td><c:out value="${client.name}"></c:out></td>
                        </tr>

                        <tr>
                            <th>年齢</th>
                            <td><c:out value="${client.age}"></c:out></td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/clients/edit?id=${client.id}' />">顧客情報を編集する</a></p>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/clients/index'/>">一覧に戻る</a></p>
    </c:param>
</c:import>