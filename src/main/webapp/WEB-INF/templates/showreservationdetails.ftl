<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dettagli Prenotazione</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/css/reservationdetails.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<!-- NAVBAR -->

<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl">
<#else>
    <#include "/navbar/navbar1.ftl">
</#if>

<!-- CONTENUTO PRINCIPALE -->
<div class="container my-5 dettagli-container">
    <div class="row g-4">

        <!-- Galleria Immagini -->
       
        <div class="col-lg-6">
            <div class="galleria-foto d-flex flex-wrap gap-3">
                <#list office.foto as photo >
                <img src="${ctx}/photo?id=${photo.id}" class="img-fluid rounded" alt="Foto 1">
                </#list>
            </div>
        </div>

        <!-- Info Ufficio -->
        <div class="col-lg-6">
            <div class="info-ufficio">
                <h1 class="mb-3">${office.titolo }</h1>
                <p><strong>Indirizzo: </strong> ${ office.indirizzo }</p>
                <p><strong>Posti disponibili: </strong>${office.numeroPostazioni}</p>
                <p><strong>Contatti: </strong> ${office.locatore.phone}</p>
                <p><strong>Descrizione: </strong> ${ office.descrizione }</p>

                <hr>
                <p><strong>Prenotato il:</strong> <span class="text-primary">${ reservation.data}</span> per la fascia della <span class="text-primary">${ reservation.fascia }</span> </p>
            </div>
        </div>
        
    </div>
</div>

<!-- FOOTER -->
<#include "/navbar/footer.ftl">

<!-- SCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

