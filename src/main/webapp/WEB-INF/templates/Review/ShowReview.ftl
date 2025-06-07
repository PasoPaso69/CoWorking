<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Recensioni Utenti</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/css/showreview.css">
</head>
<body class="text-white">
<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl">
<#else>
    <#include "/navbar/navbar1.ftl">
</#if>


<div class="container mt-5">
    <h2 class="text-center text-light mb-4">Recensioni degli utenti</h2>
    <div class="container mt-5">
        <!-- Intestazione pagina -->
        <div class="form-container mb-4 text-dark text-center">
            <h2>${office.titolo}</h2>
            <p class="mt-2">
                Benvenuto nella sezione dedicata alle opinioni degli utenti. Qui puoi leggere le recensioni lasciate da chi ha usufruito dei nostri servizi.
            </p>
        </div>


        <!-- RECENSIONE FINTA 1 -->
        <#list reviews as review>
        <div class="form-container mb-4">
            <div class="d-flex align-items-center mb-2">
                <div class="text-warning me-2">
                    <#assign valutazione = (review.valutazione!0)?int >
                    <#assign stelle_piene= valutazione >
                    <#assign stelle_vuote= 5-stelle_piene >

                    <#list  1..stelle_piene as stella >
                    <i class="fa fa-star"></i>
                    </#list>
                   

                </div>
                <span class="text-dark">${ review.valutazione}</span>
            </div>
            <p class="text-dark">${review.commento}</p>
        </div>
        </#list>
    </div>
</div>

<#include "/navbar/footer.ftl">

<!-- Scripts per navbar e footer -->


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
