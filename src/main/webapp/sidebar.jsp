<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope['superAdmin'] != null && sessionScope['superAdmin'] == 0}">
    <jsp:include page="sidebarUser.jsp"/>
</c:if>
<c:if test="${sessionScope['superAdmin'] != null && sessionScope['superAdmin'] == 1}">
    <jsp:include page="sidebarAdmin.jsp"/>
</c:if>