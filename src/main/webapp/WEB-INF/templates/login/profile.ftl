<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Profilo Utente</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- link to bootstrap-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- link to awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- link to css for the style of profile -->
    <link rel="stylesheet" href="${ctx}/resources/css/login.css">

</head>
<body>

<!-- include navbar -->
<#include "/navbar/navbar.ftl">

<!-- main content profile -->
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow p-4 bg-light text-dark">
                <h3 class="mb-4 text-danger text-center">
                    <i class="fas fa-user"></i> Profilo Utente
                </h3>
                <dl class="row">
                    <dt class="col-sm-4">Nome</dt>
                    <dd class="col-sm-8">${user.name }</dd>

                    <dt class="col-sm-4">Cognome</dt>
                    <dd class="col-sm-8">${ user.surname }</dd>

                    <dt class="col-sm-4">Email</dt>
                    <dd class="col-sm-8">${ user.email }</dd>


                    <dt class="col-sm-4">Data di nascita</dt>
                    <dd class="col-sm-8">${ user.dob}</dd>

                    <dt class="col-sm-4">Telefono</dt>
                    <dd class="col-sm-8">${ user.phone }</dd>
                </dl>
                <div class="mt-4">
                    <a href="${ctx}/logout" class="btn btn-danger w-100">
                        <i class="fas fa-flag"></i> Logout
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- include footer-->
<#include "/navbar/footer.ftl">


</body>
</html>

