<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <jsp:include page="sidebar.jsp"/>
        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">

                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">${plan.name}</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="day" items="${days}">
                        <table class="table">

                            <thead>

                            <tr class="d-flex">
                                <th class="col-2">${day.name}</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">

                            <c:forEach var="recipePlan" items="${recipePlans}">
                                <c:choose>
                                    <c:when test="${recipePlan.dayNameId==day.id}">


                                        <tr class="d-flex">
                                        <td class="col-2">${recipePlan.mealName}</td>
                                        <c:forEach var="recipe" items="${recipes}">
                                            <c:choose>
                                                <c:when test="${recipe.id==recipePlan.recipeId}">


                                                    <td class="col-7">${recipe.name}</td>

                                                    <td class="col-1 center">
                                                        <a href="/app/recipe/plan/delete?id=${recipePlan.id}"
                                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                                    </td>
                                                    <td class="col-2 center">
                                                        <a href="/app/recipe/details?id=${recipe.id}"
                                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                                    </td>
                                                    </tr>
                                                </c:when>

                                            </c:choose>
                                        </c:forEach>
                                    </c:when>

                                </c:choose>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:forEach>


                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>