<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>Сотрудники · Управление персоналом</title>
</head>
<body>

<jsp:include page="/views/header.jsp" />

<main class="container">

    <section class="hero">
        <h2><i class="bi bi-people-fill text-primary"></i> Сотрудники</h2>
        <p>Реестр работников предприятия с привязкой к должностям.</p>
    </section>

    <div class="row g-4">
        <div class="col-xl-8">
            <div class="card border-0 shadow-sm">
                <div class="card-header bg-white border-0 pt-3">
                    <h3 class="h5 mb-0"><i class="bi bi-list-ul me-2"></i>Список сотрудников</h3>
                </div>
                <div class="card-body p-0 table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                            <tr>
                                <th scope="col" style="width: 60px;">Код</th>
                                <th scope="col">Фамилия</th>
                                <th scope="col">Имя</th>
                                <th scope="col">Должность</th>
                                <th scope="col">Телефон</th>
                                <th scope="col">Эл. почта</th>
                                <th scope="col" class="text-center" style="width: 100px;">Действия</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="person" items="${persons}">
                                <tr>
                                    <td>${person.id}</td>
                                    <td>${person.lastName}</td>
                                    <td>${person.firstName}</td>
                                    <td><span class="badge bg-secondary">${person.role}</span></td>
                                    <td>${person.phone}</td>
                                    <td><a href="mailto:${person.email}">${person.email}</a></td>
                                    <td class="text-center">
                                        <a href="#" class="btn btn-sm btn-outline-primary me-1" title="Редактировать">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                        <a href="#" class="btn btn-sm btn-outline-danger" title="Удалить">
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty persons}">
                                <tr><td colspan="7" class="text-center text-muted py-4">Сотрудники не найдены</td></tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-xl-4">
            <div class="card border-0 shadow-sm">
                <div class="card-header bg-white border-0 pt-3">
                    <h3 class="h5 mb-0"><i class="bi bi-plus-circle me-2"></i>Новый сотрудник</h3>
                </div>
                <div class="card-body">
                    <form method="POST" action="${pageContext.request.contextPath}/person">
                        <div class="mb-3">
                            <label for="lastname" class="form-label">Фамилия</label>
                            <input type="text" name="lastname" id="lastname" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="firstname" class="form-label">Имя</label>
                            <input type="text" name="firstname" id="firstname" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="role" class="form-label">Должность</label>
                            <select name="idRole" id="role" class="form-select" required>
                                <option value="">— выберите —</option>
                                <c:forEach var="role" items="${roles}">
                                    <option value="${role.id}">${role.namerole}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Телефон</label>
                            <input type="tel" name="phone" id="phone" class="form-control" placeholder="+7 (___) ___-__-__">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Эл. почта</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="user@example.com">
                        </div>
                        <button type="submit" class="btn btn-primary w-100">
                            <i class="bi bi-plus-lg me-1"></i> Добавить
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">
            <i class="bi bi-arrow-left"></i> На главную
        </a>
    </div>

</main>

<jsp:include page="/views/footer.jsp" />

<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.js"></script>
<script defer src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

</body>
</html>
