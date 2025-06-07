<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dettagli Ufficio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/css/DetailsOffice.css">
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
<div class="container my-5  dettagli-container style="max-width: 1200px; margin: auto;">
    <div class="row g-4">
        <!-- COLONNA SINISTRA - Galleria immagini -->
        <div class="col-lg-6">
            <div class="galleria-foto d-flex flex-wrap gap-3">
                <#list foto as photo>
                    <img src="${photo}" alt="image">
                </#list>
            </div>
        </div>

        <!-- COLONNA DESTRA - Info + pulsanti -->
        <div class="col-lg-6 flex-column justify-content-between">
            <!-- Info ufficio -->
            <div class="info-ufficio mb-4">
                <h1 class="mb-3">${ufficio.titolo}</h1>
                <p><strong>Indirizzo:</strong> ${ufficio.indirizzo}</p>
                <p><strong>Posti disponibili:</strong> ${ufficio.numeroPostazioni}</p>
                <p><strong>Contatti:</strong> info@affittaufficio.it - +39 0123 456789</p>
                <p><strong>Descrizione:</strong> ${ufficio.descrizione}</p>
                <p><strong>Superficie:</strong> ${ufficio.superficie} m²</p>
                <p><strong>Servizi Aggiuntivi:</strong></p>
                <ul>
                    <#list ufficio.serviziAggiuntivi as servizio>
                        <li>${servizio.nomeServizio}</li>
                    </#list>
                </ul>
                <div class="prezzo h4 text-danger text-end">Prezzo: ${ufficio.prezzo} euro al giorno</div>
            </div>

            <!-- BOTTONI -->
            <div class="bottone-container">
                <!-- Bottone "Vai al pagamento" -->
                <form method="post" action="${ctx}/home-utente/search/showoffice/detailsoffice/confirm">
                    <input type="hidden" name="date" value="${date}">
                    <input type="hidden" name="idufficio" value="${ufficio.id}">
                    <input type="hidden" name="slot" value="${slot}">
                    <button type="submit" class="btn btn-danger w-100 py-3 fs-5 mb-3">
                        <i class="fa-solid fa-credit-card"></i> Vai al pagamento
                    </button>
                </form>

                <!-- Due bottoni affiancati -->
                <div class="row">
                    <div class="col-6 pe-1">
                        <a href="${ctx}/home-utente/search/showoffice/detailsoffice/review?idufficio=${ufficio.id}" class="btn btn-danger w-100">
                            <i class="fa-solid fa-star"></i> Recensioni
                        </a>
                    </div>
                    <div class="col-6 ps-1">
                        <a href="${ctx}/home-utente/search/showoffice/detailsoffice/Report?idufficio=${ufficio.id}" class="btn btn-danger w-100">
                            <i class="fa-solid fa-flag"></i> Segnala
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "/navbar/footer.ftl">

<!-- BOOTSTRAP SCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>

