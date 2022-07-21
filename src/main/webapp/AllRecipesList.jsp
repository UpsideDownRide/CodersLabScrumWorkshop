<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <div class="m-4 p-3 width-medium">
            <form method="POST" class="padding-small text-center">
                <div class="form-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="podaj tytul przepisu">
                </div>
                <button class="btn btn-color rounded-0" type="submit">wyszukaj</button>
            </form>
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding"><h3 class="color-header text-uppercase">Lista Przepisów</h3></div>
                </div>
                <table class="table border-bottom schedules-content">
                    <thead>
                    <tr class="d-flex text-color-darker">
                        <th scope="col" class="col-1">ID</th>
                        <th scope="col" class="col-2">NAZWA</th>
                        <th scope="col" class="col-3">OPIS</th>
                        <th scope="col" class="col-4">DODANO</th>
                        <th scope="col" class="col-1">AKCJE</th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${recipes}" var="recipe">
                        <jsp:useBean id="recipe" scope="request" class="pl.coderslab.model.Recipe"/>
                        <tr class="d-flex">
                            <th scope="row" class="col-1">${recipe.id}</th>
                            <td class="col-2">${recipe.name}</td>
                            <td class="col-3">${recipe.description}</td>
                            <td class="col-4">${recipe.created}</td>
                            <td class="col-1"><a href="/allRecipesDetails?id=${recipe.id}" class="btn btn-info rounded-0 text-light">Szczegóły</a></td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<section class="last-info-section padding-small" id="contact">
    <div class="container">
        <div class="row">
            <div class="col">
                <h3 class="mb-4">Witaj</h3>
                <p> ${contact.description}</p>
            </div>
            <div class="col pl-4 ml-4">
                <h3 class="mb-4">${contact.name}</h3>
                <ul class="container">
                    <li>${contact.email}</li>
                    <li>${contact.phoneNumber}</li>
                    <li>${contact.facebook}</li>

                </ul>
            </div>
            <div class="col">
                <h3 class="mb-4">Znajdź nas online</h3>
                <div class="input-group mb-3">
                    <input type="text" class="form-control border-0 rounded-0" placeholder="Szukaj"
                           aria-label="Recipient's username" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2"><a
                                href="https://www.google.com/search?q=${contact.name}">Szukaj</a></button>
                    </div>
                </div>
                <div class="container d-flex-row">
                    <a href="https://pl-pl.facebook.com/">
                        <i class="fab fa-facebook-square mr-4 icon-social"></i>
                    </a>
                    <a href="https://twitter.com/?lang=pl">
                        <i class="fab fa-twitter-square mr-4 icon-social"></i>

                    </a>
                    <a href="https://www.instagram.com/">
                        <i class="fab fa-instagram icon-social"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
