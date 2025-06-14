<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Prenotazione Confermata</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="stylesheet" href="${ctx}/resources/css/conferma.css">
</head>
<body class="bg-light text-dark">


    <#include "/navbar/navbar.ftl">



<div id="content-wrapper" class="container py-5 text-center">
    <i class="fa-solid fa-calendar-check fa-4x text-success mb-3"></i>
    <h1 class="text-success">Prenotazione Confermata</h1>
    <p class="lead mt-3">La tua prenotazione è stata registrata con successo. Riceverai una conferma via email.</p>
</div>

<#include "/navbar/footer.ftl">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

