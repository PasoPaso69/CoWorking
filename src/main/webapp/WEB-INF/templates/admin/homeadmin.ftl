<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Gestione Uffici</title>

  <!-- Tabler CSS -->
  <link href="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/css/tabler.min.css" rel="stylesheet" />

   <link rel="stylesheet" href="${ctx}/resources/css/homeadmin.css" />
</head>

<body>
<div class="page">
  <!-- Sidebar (mock) -->
  <#include "/navbar/navbaradmin.ftl">

  <div class="page-wrapper">
    <div class="page-body container py-4">

      
      <nav class="tabs" role="tablist" aria-label="Seleziona sezione uffici">
        <div class="tab active" data-target="attivi" role="tab" tabindex="0" aria-selected="true" aria-controls="tab-attivi" id="tab-btn-attivi">
          Attivi <span class="badge bg-white">${approvedoffice?size}</span>
        </div>
        <div class="tab" data-target="inattesa" role="tab" tabindex="-1" aria-selected="false" aria-controls="tab-inattesa" id="tab-btn-inattesa">
          In attesa <span class="badge bg-white">${pendingOffice?size}</span>
        </div>
        <div class="tab" data-target="rifiutati" role="tab" tabindex="0" aria-selected="false" aria-controls="tab-rifiutati" id="tab-btn-rifiutati">
          Rifiutati <span class="badge bg-white">${rejectedOffice?size}</span>
        </div>
        <div class="tab" data-target="cancellati" role="tab" tabindex="0" aria-selected="false" aria-controls="tab-cancellati" id="tab-btn-cancellati">
        Cancellati <span class="badge bg-white">${deletedOffice?size}</span>
        </div>
      </nav>

      
      <section id="attivi" class="section-tab active" role="tabpanel" aria-labelledby="tab-btn-attivi">
        <#list approvedoffice as office>
  <div class="card mb-3 office-card">
    <div class="card-body d-flex align-items-start">
      <#if office.foto?has_content>
        <img src="${ctx}/photo?id=${ office.foto[0].id }" alt="Foto ${ office.titolo }" style="max-width: 100px; margin-right: 1rem;" />
      </#if>
      <div>
        <h5 class="mb-1">
          <a href="${ctx}/admin/office?id=${office.id}">${office.titolo}</a>
        </h5>
        <p class="mb-1 text-muted">${office.indirizzo.via} ${office.indirizzo.numeroCivico}, ${office.indirizzo.citta} (${office.indirizzo.provincia})</p>
        <small class="text-muted">Inviato il: ${office.dataApprovazione}</small>
      </div>
    </div>
  </div>
</#list>

      </section>

      <!-- Sezione In attesa -->
       <section id="inattesa" class="section-tab" role="tabpanel" aria-labelledby="tab-btn-inattesa">
      <#list pendingOffice as office>
  <div class="card mb-3 office-card">
    <div class="card-body d-flex align-items-start">
      <#if office.foto?has_content>
        <img src="${ctx}/photo?id=${ office.foto[0].id }" alt="Foto ${ office.titolo }" style="max-width: 100px; margin-right: 1rem;" />
      </#if>
      <div>
        <h5 class="mb-1">
          <a href="${ctx}/admin/office?id=${office.id}">${office.titolo}</a>
        </h5>
        <p class="mb-1 text-muted">${office.indirizzo.via} ${office.indirizzo.numeroCivico}, ${office.indirizzo.citta} (${office.indirizzo.provincia})</p>
        <small class="text-muted">Inviato il: ${office.dataCaricamento}</small>
      </div>
    </div>
  </div>
</#list>
 </section>


      <!-- Sezione Rifiutati -->
      <section id="rifiutati" class="section-tab" role="tabpanel" aria-labelledby="tab-btn-rifiutati">
         <#list rejectedOffice as office>
  <div class="card mb-3 office-card">
    <div class="card-body d-flex align-items-start">
      <#if office.foto?has_content>
        <img src="${ctx}/photo?id=${ office.foto[0].id }" alt="Foto ${ office.titolo }" style="max-width: 100px; margin-right: 1rem;" />
      </#if>
      <div>
        <h5 class="mb-1">
          <a href="${ctx}/admin/office?id=${office.id}">${office.titolo}</a>
        </h5>
        <p class="mb-1 text-muted">${office.indirizzo.via} ${office.indirizzo.numeroCivico}, ${office.indirizzo.citta} (${office.indirizzo.provincia})</p>
        <small class="text-muted">Inviato il: ${office.dataRifiuto}</small>
      </div>
    </div>
  </div>
</#list>
      </section>
<section id="cancellati" class="section-tab" role="tabpanel" aria-labelledby="tab-btn-cancellati">
  <#list deletedOffice as office>
    <div class="card mb-3 office-card">
      <div class="card-body d-flex align-items-start">
        <#if office.foto?has_content>
          <img src="${ctx}/photo?id=${ office.foto[0].id }" alt="Foto ${ office.titolo }" style="max-width: 100px; margin-right: 1rem;" />
        </#if>
        <div>
          <h5 class="mb-1">
            <a href="${ctx}/admin/office?id=${office.id}">${office.titolo}</a>
          </h5>
          <p class="mb-1 text-muted">${office.indirizzo.via} ${office.indirizzo.numeroCivico}, ${office.indirizzo.citta} (${office.indirizzo.provincia})</p>
          <small class="text-muted d-block mb-2">Cancellato il: ${office.dataCancellazione}</small>

<form action="${ctx}/admin/office/restore" method="post" style="display:inline;">
  <input type="hidden" name="id" value="${office.id}" />
  <button type="submit" class="btn btn-sm btn-success">
    Riattiva
  </button>
</form>

        </div>
      </div>
    </div>
  </#list>
</section>

    </div>
  </div>
</div>

<!-- Script tab -->
<script>
  document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.tab').forEach(tab => {
      tab.addEventListener('click', () => {
        const targetId = tab.getAttribute('data-target');

        // Cambia lo stato delle tab
        document.querySelectorAll('.tab').forEach(t => {
          t.classList.toggle('active', t === tab);
          t.setAttribute('aria-selected', t === tab);
          t.setAttribute('tabindex', t === tab ? '0' : '-1');
        });

        // Mostra la sezione corretta
        document.querySelectorAll('.section-tab').forEach(section => {
          section.classList.toggle('active', section.id === targetId);
        });
      });
    });
  });
</script>

<!-- Tabler JS -->
<script src="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/js/tabler.min.js"></script>
</body>
</html>
