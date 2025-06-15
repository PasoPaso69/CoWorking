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

             <!-- Foto Ufficio (prima foto se esiste) -->
            <#if ufficio.foto?has_content>
              <img 
                src="${ctx}/photo?id=${ufficio.foto?first.id}" 
                alt="Foto di ${ufficio.titolo}" 
                class="card-img-top"
                style="object-fit: cover; height: 200px; width: 100%;"
              />
            <#else>
              <img 
                src="/CoWirking1-UFFICI/resources/img/default-office.jpg" 
                alt="Immagine predefinita ufficio" 
                class="card-img-top" 
                style="object-fit: cover; height: 200px; width: 100%;"
              />
            </#if>
           
            <div class="card-body">
              <h4 class="card-title">${ufficio.titolo}</h4>
              <p class="mb-1"><strong>Prezzo:</strong> ${ufficio.prezzo} €</p>
              <p class="mb-3"><strong>Indirizzo:</strong> ${ufficio.indirizzo}</p>
              <div class="d-flex justify-content-between">

                <button
                  class="btn btn-primary btn-sm btn-dettagli"
                  data-bs-toggle="modal"
                  data-bs-target="#modal-dettagli"
                  data-titolo="${ufficio.titolo}"
                  data-descrizione="${ufficio.descrizione}"
                  data-prezzo="${ufficio.prezzo}"
                  data-superficie="${ufficio.superficie}"
                 
                  data-indirizzo="${ufficio.indirizzo}"
                  data-postazioni="${ufficio.numeroPostazioni}"
                  data-servizi="<#list ufficio.serviziAggiuntivi as s>${s.nomeServizio}<#if s_has_next>, </#if></#list>">
                  <i class="fa fa-eye me-1"></i> Dettagli
                </button>

                <button class="btn btn-danger btn-sm btn-cancella"
                        data-bs-toggle="modal"
                        data-bs-target="#modal-conferma-cancella"
                        data-office-id="${ufficio.id}">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


<!-- JS per il modale -->
<script src="/CoWirking1-UFFICI/resources/js/gestione_uffici.js"></script>

<script>
 document.addEventListener("DOMContentLoaded", function () {
  let officeIdToDelete = null;

  document.querySelectorAll('.btn-cancella').forEach(button => {
    button.addEventListener('click', function () {
      officeIdToDelete = this.dataset.officeId;
      console.log('Office da cancellare:', officeIdToDelete);
    });
  });

  document.getElementById('conferma-cancella-btn').addEventListener('click', function () {
    console.log('Cliccato il bottone conferma cancellazione');

    if (!officeIdToDelete) {
      console.error("Nessun ID ufficio selezionato");
      return;
    }

    fetch(`${ctx}/gestione`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams({ idUfficio: officeIdToDelete })
    })
    .then(response => {
      if (response.ok) {
        window.location.reload();
      } else {
        alert("Errore durante la cancellazione");
      }
    })
    .catch(error => {
      console.error('Errore fetch:', error);
    });
  });
});

</script>

</body>
</html>