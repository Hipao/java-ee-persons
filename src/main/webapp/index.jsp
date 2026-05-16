<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-icons.min.css">
<link rel="stylesheet" href="css/style.css">

<title>Управление персоналом</title>
</head>
<body>

    <jsp:include page="/views/header.jsp" />

    <main class="container">
        <section class="hero">
            <h2>Управление персоналом</h2>
            <p>Информационная система учёта сотрудников и должностей предприятия</p>
        </section>

        <section class="feature-grid">
            <a href="#" class="feature-card persons">
                <div class="icon-wrap"><i class="bi bi-people-fill"></i></div>
                <h3>Сотрудники</h3>
                <p>Реестр работников предприятия: ФИО, контактные данные, должность, дата приёма.</p>
                <span class="cta">Перейти к разделу <i class="bi bi-arrow-right"></i></span>
            </a>

            <a href="#" class="feature-card roles">
                <div class="icon-wrap"><i class="bi bi-briefcase-fill"></i></div>
                <h3>Должности</h3>
                <p>Справочник должностей с указанием оклада и подчинённости.</p>
                <span class="cta">Перейти к разделу <i class="bi bi-arrow-right"></i></span>
            </a>
        </section>
    </main>

    <jsp:include page="/views/footer.jsp" />

    <script src="js/jquery-3.6.4.js"></script>
    <script defer src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
