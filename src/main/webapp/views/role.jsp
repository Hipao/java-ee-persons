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
<title>Должности · Управление персоналом</title>
</head>
<body>

<jsp:include page="/views/header.jsp" />

<main class="container">

    <section class="hero">
        <h2><i class="bi bi-briefcase-fill text-primary"></i> Должности</h2>
        <p>Справочник должностей предприятия с возможностью добавления, редактирования и удаления.</p>
    </section>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="row g-4">
        <div class="col-lg-8">
            <div class="card border-0 shadow-sm">
                <div class="card-header bg-white border-0 pt-3">
                    <h3 class="h5 mb-0"><i class="bi bi-list-ul me-2"></i>Список должностей</h3>
                </div>
                <div class="card-body p-0">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                            <tr>
                                <th scope="col" style="width: 60px;">Код</th>
                                <th scope="col">Наименование</th>
                                <th scope="col" class="text-center" style="width: 110px;">Действия</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="role" items="${roles}">
                                <tr>
                                    <td>${role.id}</td>
                                    <td>${role.namerole}</td>
                                    <td class="text-center">
                                        <a href="${pageContext.request.contextPath}/role?action=edit&id=${role.id}"
                                           class="btn btn-sm btn-outline-primary me-1" title="Редактировать">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/role?action=delete&id=${role.id}"
                                           class="btn btn-sm btn-outline-danger"
                                           title="Удалить"
                                           onclick="return confirm('Удалить должность «${role.namerole}»?');">
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty roles}">
                                <tr><td colspan="3" class="text-center text-muted py-4">Должности не найдены</td></tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="card border-0 shadow-sm">
                <div class="card-header bg-white border-0 pt-3">
                    <h3 class="h5 mb-0">
                        <c:choose>
                            <c:when test="${not empty editRole}">
                                <i class="bi bi-pencil-square me-2"></i>Редактирование должности
                            </c:when>
                            <c:otherwise>
                                <i class="bi bi-plus-circle me-2"></i>Новая должность
                            </c:otherwise>
                        </c:choose>
                    </h3>
                </div>
                <div class="card-body">
                    <form method="POST" action="${pageContext.request.contextPath}/role">
                        <c:if test="${not empty editRole}">
                            <input type="hidden" name="id" value="${editRole.id}">
                        </c:if>
                        <div class="mb-3">
                            <label for="inputRole" class="form-label">Наименование</label>
                            <input type="text" name="namerole" class="form-control"
                                   id="inputRole" required
                                   value="${not empty editRole ? editRole.namerole : ''}">
                        </div>
                        <button type="submit" class="btn btn-primary w-100">
                            <c:choose>
                                <c:when test="${not empty editRole}">
                                    <i class="bi bi-save me-1"></i> Сохранить изменения
                                </c:when>
                                <c:otherwise>
                                    <i class="bi bi-plus-lg me-1"></i> Добавить
                                </c:otherwise>
                            </c:choose>
                        </button>
                        <c:if test="${not empty editRole}">
                            <a href="${pageContext.request.contextPath}/role" class="btn btn-link w-100 mt-2">Отмена</a>
                        </c:if>
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
