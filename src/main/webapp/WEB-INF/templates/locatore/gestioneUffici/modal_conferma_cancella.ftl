<!-- MODALE CONFERMA CANCELLA -->
<div class="modal fade" id="modal-conferma-cancella" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title">
          <i class="fa fa-trash me-2"></i> Conferma eliminazione
        </h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Chiudi"></button>
      </div>
      <div class="modal-body">
        Sei sicuro di voler eliminare questo ufficio?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="conferma-cancella-btn">
          <i class="fa fa-check me-1"></i> Conferma
        </button>
      </div>
    </div>
  </div>
</div>

<!-- FORM NASCOSTO PER INVIO DATI -->
<form id="form-cancella-ufficio" method="POST" action="${ctx}/gestione">
  <input type="hidden" name="idUfficio" id="id-ufficio-cancella" value="">
</form>

<!-- SCRIPT -->
<script>
  let ufficioSelezionato = null;

  // Funzione per aprire il modale e settare l'ID dell'ufficio
  function apriModaleCancella(idUfficio) {
    ufficioSelezionato = idUfficio;
    const modal = new bootstrap.Modal(document.getElementById('modal-conferma-cancella'));
    modal.show();
  }

  // Listener sul pulsante "Conferma"
  document.getElementById('conferma-cancella-btn').addEventListener('click', function() {
    if (ufficioSelezionato) {
      document.getElementById('id-ufficio-cancella').value = ufficioSelezionato;
      document.getElementById('form-cancella-ufficio').submit();
    }
  });
</script>
