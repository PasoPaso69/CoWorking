
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Segnalazione Inviata</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    
    <link rel="stylesheet" href="${ctx}/resources/css/conferma.css">
</head>
<body class="bg-light text-dark">

<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl">
<#else>
    <#include "/navbar/navbar1.ftl">
</#if>


<div id="content-wrapper" class="container py-5 text-center">
    <i class="fa-solid fa-flag fa-4x text-warning mb-3"></i>
    <h1 class="text-warning">Segnalazione Ricevuta</h1>
    <p class="lead mt-3">Grazie per averci contattato. Il nostro team analizzerà la tua segnalazione il prima possibile.</p>
</div>
<#include "/navbar/footer.ftl">



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
