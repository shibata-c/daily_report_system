<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${client != null}">
                <h2>id: ${client.id}　の顧客情報　編集ページ</h2>
                <form method="post" action="<c:url value='/clients/update'/>">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="#" onclick="confirmDestroy();">この顧客情報を削除する。</a></p>
                <form method="POST"action="<c:url value='/clients/destroy' />">
                    <input type="hidden"name="_token"value="${_token}">
                </form>
                <script>
                    function confirmDestroy(){
                        if(confirm("本当に削除してよろしいですか?")){
                            document.forms[0].submit();
                        }
                    }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/clients/index' />">一覧に戻る</a>
    </c:param>
</c:import>