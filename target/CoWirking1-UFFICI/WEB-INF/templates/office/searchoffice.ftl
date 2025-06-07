<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Risultati ricerca uffici</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${ctx}/resources/css/searchoffice.css">
</head>

<body>

<!-- NAVBAR -->

<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl">
<#else>
    <#include "/navbar/navbar1.ftl">
</#if>

<!-- RISULTATI -->
<div class="container-fluid text-center py-5 bg-black text-white">
    <h1>Risultati della ricerca</h1>
</div>
<#if results?size == 0>
<div class="container my-5">
    <div class="alert alert-warning text-center py-5">
        <h2 class="mb-3"><i class="fa-solid fa-triangle-exclamation text-warning me-2"></i> Nessun ufficio trovato</h2>
        <p>Prova a cambiare i parametri di ricerca .</p>
        <a href="/home" class="btn btn-primary mt-3"><i class="fa-solid fa-house"></i> Torna alla Home</a>
    </div>
</div>
<#else>
<div class="container py-5 ufficio-container">
<div class="container py-5 ufficio-container">
    <div class="row g-4">
        <!-- Card 1 -->
        <#list results as result>
        <div class="col-12">
            <div class="ufficio-card d-flex flex-column flex-md-row mx-auto">
                <img src="${result.foto}" alt="Foto Ufficio" class="ufficio-img">
                <div class="ufficio-info">
                    <a href="${ctx}/home-utente/search/detailsoffice?idufficio=${result.ufficio.id}&date=${date}&slot=${slot}" class="btn-prenota"><i class="fa-solid fa-calendar-check"></i> Prenota</a>
                    <h2>${result.ufficio.titolo}</h2>
                    <p><strong>Indirizzo: </strong>${result.ufficio.indirizzo}</p>
                    <p><strong>Posti disponibili: </strong>${result.ufficio.numeroPostazioni} </p>
                    <div class="prezzo"> Prezzo : ${result.ufficio.prezzo} euro al giorno</div>
                </div>
            </div>
        </div>
</#list>
    </div>
</div>
    </#if>
</div>

<!-- FOOTER -->
       <#include "/navbar/footer.ftl">

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
