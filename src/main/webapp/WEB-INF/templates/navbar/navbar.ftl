<link rel="stylesheet" href="${ctx}/resources/css/navbar.css" />
<nav class="navbar navbar-expand-lg navbar-dark bg-black py-4 px-3">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="${ctx}/resources/images/ufficiologo2-removebg-preview%20-%20Copia.png" alt="Logo" >
<#if nome?? && cognome??>
            <span class="text-white small me-6"><strong>  Ciao ${nome} ${cognome}!</strong></span>
<#else>
  <span class="text-white small me-6"><strong>Ciao utente, buon pomeriggio</strong></span>
</#if>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navLinks">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navLinks">
            <ul class="navbar-nav ms-auto gap-3">

                <li class="nav-item"><a class="nav-link" href="${ctx}/register"><i class="fas fa-user"></i> Profilo</a></li>
                <li class="nav-item"><a class="nav-link" href="${ctx}/home-utente"><i class="fas fa-home"></i> Home</a></li>
                <li class="nav-item"><a class="nav-link" href="${ctx}/home-utente/search/showoffice/Detailsoffice/confirm"><i class="fas fa-bookmark"></i> Prenotazioni</a></li>

            </ul>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</nav>