<!DOCTYPE html>
<html lang="it" data-bs-theme="light">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home - Cooworking</title>
  <!-- Tabler CSS -->
  <link href="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/css/tabler.min.css" rel="stylesheet">
  <!-- CSS Personalizzato -->
  <link href="${ctx}/resources/css/locatore/homeLocatore.css" rel="stylesheet">


  <!-- FontAwesome for icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
</head>
<body>
  
  <!-- Creo la navbar -->
  <#-- Include FreeMarker per navbar -->
  <#include "/locatore/navbar/navbarLocatore.ftl" />

  <!-- Contenuto principale -->
  <div class="page-wrapper main-content">

    <!-- Header con titolo e profilo -->
    <header class="page-header d-flex flex-column flex-md-row justify-content-between align-items-center p-4">
      <!-- Titolo -->
      <div class="flex-grow-1 text-center text-md-start mb-3 mb-md-0">
        <h1 class="welcome-title">Bentornato in Cooworking</h1>
      </div>
      <!-- Profilo utente -->
      <div class="dropdown">
        <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
          <div class="avatar rounded-circle bg-primary text-white d-flex align-items-center justify-content-center shadow" style="width: 3rem; height: 3rem;">
            <i class="fas fa-user fs-4"></i>
          </div>
        </a>
        <ul class="dropdown-menu dropdown-menu-end shadow rounded-3 mt-2">
          <li><a class="dropdown-item d-flex align-items-center" href="${ctx}/profilo"><i class="fas fa-user me-2 text-primary"></i> Profilo</a></li>
          <li><a class="dropdown-item d-flex align-items-center" href="#"><i class="fas fa-cog me-2 text-secondary"></i> Impostazioni</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item d-flex align-items-center text-danger" href="${ctx}/logout"><i class="fas fa-sign-out-alt me-2"></i> Logout</a></li>
        </ul>
      </div>
    </header>

    <!-- Contenuto della pagina -->
    <div class="page-body">
      <div class="container bg-light rounded p-4 my-4">
        <p class="lead text-center">Questa è la tua area riservata per gestire gli spazi di coworking.</p>
        <p class="lead text-center">Qui potrai aggiungere nuovi uffici, gestire al meglio le prenotazioni ricevute e rimuovere uffici vecchi che non servono più.</p>
        <p class="lead text-center">Infine puoi controllare quando e come vuoi le tue statistiche in quanto locatore, così da essere sempre aggiornato sull'andamento.</p>
      </div>
    </div>

    <!-- Sezione Grafici con immagini fasulle -->
    <div class="container my-5">

      <!-- Entrate Mensili -->
      <div class="card mb-5 shadow border-0 rounded-4 bg-light-subtle">
        <div class="card-body p-5">
          <h2 class="text-center fw-bold mb-3 text-primary fs-1">📊 Entrate Mensili</h2>
          <p class="text-center text-muted fs-5 mb-4">
            Monitora l'andamento delle entrate generate dai tuoi uffici nel tempo per avere una visione chiara della tua crescita economica.
          </p>
          <div class="text-center">
           <div style="max-width: 600px; height: 400px; margin: auto;">
               <canvas id="graficoEntrate" style="width: 100%; height: 100%;"></canvas>
            </div>
         </div>
        </div>
      </div>

      <!-- Utilizzo Uffici -->
      <div class="card mb-5 shadow border-0 rounded-4 bg-light-subtle">
        <div class="card-body p-5">
          <h2 class="text-center fw-bold mb-3 text-success fs-1">🏢 Utilizzo degli Uffici</h2>
          <p class="text-center text-muted fs-5 mb-4">
            Visualizza quanto frequentemente vengono prenotati i tuoi uffici, per ottimizzare la gestione degli spazi disponibili.
          </p>
          <div class="d-flex justify-content-center">
            <div style="max-width: 500px; width: 100%;">
            <canvas id="graficoUtilizzo"></canvas>
          </div>
          </div>
        </div>
      </div>

      

    </div>

  </div>

  <!-- Tabler JS -->
  <script src="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/js/tabler.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script src="${ctx}/resources/js/grafico_entrate.js"></script>
  <script src="${ctx}/resources/js/grafico_uffici.js"></script>

</body>
</html>

