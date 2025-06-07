<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Errore Prenotazione</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="stylesheet" href="${ctx}/resources/css/conferma.css"> 
</head>
<body class="bg-light text-dark">

<#include "/navbar/navbar.ftl">


<div id="content-wrapper" class="container py-5 text-center">
    <i class="fa-solid fa-circle-exclamation fa-4x text-danger mb-3"></i>
    <h1 class="text-danger">Errore nella Prenotazione</h1>
    <p class="lead mt-3">Purtroppo non ci sono posti disponibili per la data e fascia oraria selezionata.</p>
    <a href="${ctx}/home-utente" class="btn btn-primary mt-4">
        Torna alla Ricerca
    </a>
</div>

<#include "/navbar/footer.ftl">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

