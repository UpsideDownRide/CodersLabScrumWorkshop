<%@ page contentType="text/html;charset=UTF-8" %>

<ul class="nav flex-column long-bg">
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">
      <span>Pulpit</span>
      <i class="fas fa-angle-right"></i>
    </a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/app/recipe/list">
      <span>Przepisy</span>
      <i class="fas fa-angle-right"></i>
    </a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/app/plan/list">
      <span>Plany</span>
      <i class="fas fa-angle-right"></i>
    </a>
  </li>
  <li class="nav-item">
    <a  class="nav-link" href="${pageContext.request.contextPath}/app/user/edit">
      <span>Edytuj dane</span>
      <i class="fas fa-angle-right"></i>
    </a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="${pageContext.request.contextPath}/app/user/edit/password">
      <span>Zmień hasło</span>
      <i class="fas fa-angle-right"></i>
    </a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/app/user/superAdmin">
      <span>Użytkownicy</span>
      <i class="fas fa-angle-right"></i>
    </a>
  </li>
</ul>
