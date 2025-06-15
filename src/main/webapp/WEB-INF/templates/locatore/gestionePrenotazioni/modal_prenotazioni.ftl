
<div class="modal modal-blur fade" id="modal-dettagliP" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title text-primary">📋 Dettagli Prenotazione</h3>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="d-flex justify-content-center mb-3">
          <button class="btn btn-outline-primary me-2 tab-btn active" data-tab="tab-cliente">👤 Cliente</button>
          <button class="btn btn-outline-primary tab-btn" data-tab="tab-ufficio">🏢 Ufficio</button>
        </div>

        <div id="tab-cliente" class="tab-content-section active">
          <p><strong>Nome:</strong> <span id="modalNome"></span></p>
          <p><strong>Cognome:</strong> <span id="modalCognome"></span></p>
          <p><strong>Data Prenotazione:</strong> <span id="modalData"></span></p>
          <p><strong>Telefono:</strong> <span id="modalTelefono"></span></p>
        </div>

        <div id="tab-ufficio" class="tab-content-section" style="display:none;">
          <p><strong>Nome Ufficio:</strong> <span id="modalNomeUfficio"></span></p>
          <p><strong>Descrizione:</strong> <span id="modalDescrizione"></span></p>
          <p><strong>Prezzo:</strong> <span id="modalPrezzo"></span></p>
          <p><strong>Superficie:</strong> <span id="modalSuperficie"></span></p>
          <p><strong>Indirizzo:</strong> <span id="modalIndirizzo"></span></p>
          <p><strong>Postazioni:</strong> <span id="modalPostazioni"></span></p>
          <p><strong>Servizi:</strong> <span id="modalServizi"></span></p>
        </div>
      </div>
    </div>
  </div>
</div>
