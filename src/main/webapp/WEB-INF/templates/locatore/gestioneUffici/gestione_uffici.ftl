<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Gestione Uffici - Coworking</title>

  <!-- Tabler Core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/css/tabler.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

  <!-- CSS personalizzato -->
  <link href="/CoWirking1-UFFICI/resources/css/locatore/gestione_uffici.css" rel="stylesheet">
</head>
<body>

<!-- Creo la navbar -->
<#include "/locatore/navbar/navbarLocatore.ftl" />

<!-- Main Content -->
<div class="page-wrapper main-content">
  <div class="container-xl mt-5">
    <h1 class="mb-3">Gestione Uffici</h1>
    <p class="text-muted mb-4">Visualizza, modifica o rimuovi gli uffici attualmente disponibili nel sistema.</p>

    <!-- Elenco Uffici -->
    <div class="row g-4">
      <#list uffici as ufficio>
        <div class="col-md-6 col-lg-4">
          <div class="card">
            <img src="${ufficio.foto}" class="card-img-top" alt="Ufficio">
            <div class="card-body">
              <h4 class="card-title">${ufficio.ufficio.titolo}</h4>
              <p class="mb-1"><strong>Prezzo:</strong> ${ufficio.ufficio.prezzo} €</p>
              <p class="mb-3"><strong>Indirizzo:</strong> ${ufficio.ufficio.indirizzo}</p>
              <div class="d-flex justify-content-between">
                <button
                  class="btn btn-primary btn-sm btn-dettagli"
                  data-bs-toggle="modal"
                  data-bs-target="#modal-dettagli"
                  data-titolo="${ufficio.ufficio.titolo}"
                  data-descrizione="${ufficio.ufficio.descrizione}"
                  data-prezzo="${ufficio.ufficio.prezzo}"
                  data-superficie="${ufficio.ufficio.superficie}"
                  data-fascia="${ufficio.intervallo.fascia.value}"
                  data-indirizzo="${ufficio.ufficio.indirizzo}"
                  data-postazioni="${ufficio.ufficio.numeroPostazioni}"
                  data-servizi="<#list ufficio.servizi as s>${s.nomeServizio}<#if s_has_next>, </#if></#list>">
                  <i class="fa fa-eye me-1"></i> Dettagli
                </button>

                <button class="btn btn-danger btn-sm btn-cancella"
                        data-bs-toggle="modal"
                        data-bs-target="#modal-conferma-cancella"
                        data-office-id="${ufficio.ufficio.id}">
                  <i class="fa fa-trash me-1"></i> Cancella
                </button>
              </div>
            </div>
          </div>
        </div>
      </#list>
    </div>
  </div>
</div>

<!-- Tabler Core JS -->
<script src="https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js"></script>

<!-- Modali -->
<#include "/locatore/gestioneUffici/modal_dettagli_ufficio.ftl">
<#include "/locatore/gestioneUffici/modal_conferma_cancella.ftl">

<!-- JS per il modale -->
<script src="/js/gestione_uffici.js"></script>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    let officeIdToDelete = null;

    document.querySelectorAll('.btn-cancella').forEach(button => {
      button.addEventListener('click', function () {
        officeIdToDelete = this.dataset.officeId;
      });
    });

    document.getElementById('conferma-cancella-btn').addEventListener('click', function () {
      console.log('Cliccato il bottone cancella');
      if (!officeIdToDelete) return;

      fetch(`/offices/${officeIdToDelete}/delete`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'shouldRefund=1'
      })
      .then(response => response.text())
      .then(text => {
        console.log('Risposta fetch (testo grezzo):', text);
      })
      .catch(error => {
        console.error('Errore fetch:', error);
      });
    });
  });
</script>

</body>
</html>

