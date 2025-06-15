<!-- Questo modal serve per mostrare i dettagli completi di un ufficio -->

<!-- Modale: Dettagli Ufficio -->
<div class="modal modal-blur fade" id="modal-dettagli" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title text-primary" id="modalTitoloHeader">🏢 Dettagli Ufficio</h3>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="card border-0 shadow-sm p-4">
          <p><strong>Nome Ufficio:</strong> <span id="modalNomeUfficio"></span></p>
          <p><strong>Descrizione:</strong> <span id="modalDescrizione"></span></p>
          <p><strong>Prezzo:</strong> <span id="modalPrezzo"></span> €</p>
          <p><strong>Superficie:</strong> <span id="modalSuperficie"></span> m²</p>
         
          <p><strong>Indirizzo:</strong> <span id="modalIndirizzo"></span></p>
          <p><strong>Postazioni:</strong> <span id="modalPostazioni"></span></p>
          <p><strong>Servizi:</strong> <span id="modalServizi"></span></p>
        </div>
      </div>
    </div>
  </div>
</div>
