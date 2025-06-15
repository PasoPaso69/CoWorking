<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Gestione Prenotazioni - Cooworking</title>
  
  <!-- Tabler Core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/css/tabler.min.css" rel="stylesheet">
  <!-- FontAwesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <!-- CSS personalizzato -->
  <link href="${ctx}/resources/css/locatore/gestione_prenotazioni.css" rel="stylesheet">

</head>

<body>
<!-- Creo la navbar -->
<#include "/locatore/navbar/navbarLocatore.ftl" />

<!-- Main Content -->
<div class="page-wrapper main-content">
  <!-- Header -->
  <div class="container-xl mt-5">
    <h1 class="mb-3">Gestione Prenotazioni</h1>
    <p class="text-muted mb-4">Visualizza e gestisci tutte le prenotazioni attive e passate del tuo spazio coworking.</p>

    <!-- Tabs -->
    <div class="tabs">
      <div class="tab active" data-tab="attive">Prenotazioni Attive</div>
      <div class="tab" data-tab="passate">Prenotazioni Passate</div>
    </div>

    <!-- Prenotazioni Attive -->
    <div id="attive" class="tab-content">
      <#list activeReservations as item>
      <div class="prenotazione">
        <img src="${ctx}/photo?id=${item.ufficio.foto?first.id}" alt="Ufficio 1" />
        <div class="prenotazione-info">
          <h5>${item.ufficio.titolo}</h5>
          <p><strong>Cliente:</strong> ${item.utente.name} ${item.utente.surname}</p>
          <p><strong>Telefono:</strong> ${item.utente.phone}</p>
        </div>
        <a href="#" class="btn-dettagli" data-bs-toggle="modal" data-bs-target="#modal-dettagliP"
           data-nome="${item.utente.name}"
           data-cognome="${item.utente.surname}"
           data-data="${item.data}"
           data-telefono="${item.utente.phone}"

           data-nome-ufficio="${item.ufficio.titolo}"
           data-descrizione="${item.ufficio.descrizione}"
           data-prezzo="${item.ufficio.prezzo} €"
           data-superficie="${item.ufficio.superficie} m²"
           data-indirizzo="${item.ufficio.indirizzo}"
           data-postazioni="${item.ufficio.numeroPostazioni}"
           data-servizi="<#list item.ufficio.serviziAggiuntivi as s>${s.nomeServizio}<#if s_has_next>, </#if></#list>">
          Dettagli
        </a>
      </div>
      </#list>
    </div>

    <!-- Prenotazioni Passate -->
    <div id="passate" class="tab-content" style="display:none;">
      <#list pastReservations as item>
      <div class="prenotazione">
        <img src="${ctx}/photo?id=${item.ufficio.foto?first.id}" alt="Ufficio 1" />
        <div class="prenotazione-info">
          <h5>${item.ufficio.titolo}</h5>
          <p><strong>Cliente:</strong> ${item.utente.name} ${item.utente.surname}</p>
          <p><strong>Telefono:</strong> ${item.utente.phone}</p>
        </div>
        <a href="#" class="btn-dettagliP" data-bs-toggle="modal" data-bs-target="#modal-dettagliP"
           data-nome="${item.utente.name}"
           data-cognome="${item.utente.surname}"
           data-data="${item.data}"
           data-telefono="${item.utente.phone}"

           data-nome-ufficio="${item.ufficio.titolo}"
           data-descrizione="${item.ufficio.descrizione}"
           data-prezzo="${item.ufficio.prezzo} €"
           data-superficie="${item.ufficio.superficie} m²"
           data-indirizzo="${item.ufficio.indirizzo}"
           data-postazioni="${item.ufficio.numeroPostazioni}"
           data-servizi="<#list item.ufficio.serviziAggiuntivi as s>${s.nomeServizio}<#if s_has_next>, </#if></#list>">
          Dettagli
        </a>
      </div>
      </#list>
    </div>
  </div>
</div>

<!-- creo il modal che viene chiamato dallo script js -->
<#include "/locatore/gestionePrenotazioni/modal_prenotazioni.ftl">

<!-- Tabler Core JS -->
<script src="https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js"></script>

<!-- Chiamo lo script per il modal e le tabs-->
<script src="${ctx}/resources/js/gestione_prenotazioni.js"></script>

</body>
</html>

