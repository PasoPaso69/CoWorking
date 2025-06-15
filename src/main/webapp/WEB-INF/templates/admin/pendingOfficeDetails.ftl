<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8" />
    <title>Dettaglio Ufficio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/css/tabler.min.css" rel="stylesheet" />
    <style>
.carousel {
  max-height: 250px;
  overflow-y: auto;
}
        .carousel img {
            max-height: 250px;
            object-fit: cover;
            width: 100%;
            border-radius: 8px;
        }
        .btn-approve { background-color: #2fb344; color: white; }
        .btn-reject { background-color: #d63939; color: white; }
    </style>
</head>
<body>
<div class="page">
<#include "/navbar/navbaradmin.ftl">
    <div class="page-wrapper">
        <div class="container py-4">
            <h2>${office.titolo}</h2>
            <p class="text-muted">Inviato il: ${office.dataCaricamento}</p>

            <div class="row">
                <div class="col-md-6">
                    <h3>Dettagli</h3>
                    <p><strong>Indirizzo:</strong> ${office.indirizzo}</p>
                    <p><strong>Prezzo:</strong>${office.prezzo}  / postazione</p>
                    <p><strong>Superficie:</strong> ${office.superficie} m²</p>
                    <p><strong>Postazioni disponibili:</strong> ${office.numeroPostazioni}</p>
                </div>
                <div class="col-md-6">
                    <h4>Foto</h4>
                    <div class="carousel">
                        <#list office.foto as photo>
                        <img src="${ctx}/photo?id=${photo.id}" alt="Foto 1" />
                       </#list>
                    </div>
                </div>
            </div>

            <h4 class="mt-4">Servizi aggiuntivi</h4>
            <ul>
            <#list office.serviziAggiuntivi as service>
                <li>${service.nomeServizio}</li>
                </#list>
            </ul>

            <div class="mt-4">
                <button class="btn btn-approve me-2" onclick="openApproveModal()">Approva</button>
                <button class="btn btn-reject" onclick="openRejectModal()">Rifiuta</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Approva -->
<form method="post" action="${ctx}/actionOffice">
  <input type="hidden" name="id" value="${office.id}" />
  <input type="hidden" name="action" value="approvato" />
  <div class="modal fade" id="approveModal" tabindex="-1">
    <div class="modal-dialog modal-sm modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Conferma approvazione</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">Sei sicuro di voler approvare questo ufficio?</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-link" data-bs-dismiss="modal">Annulla</button>
          <button type="submit" class="btn btn-success">Conferma</button>
        </div>
      </div>
    </div>
  </div>
</form>


<!-- Modal Rifiuta -->
<form method="post" action="${ctx}/actionOffice" class="modal fade" id="rejectModal" tabindex="-1">
  <div class="modal-dialog modal-sm modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Rifiuta ufficio</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="id" value="${office.id}" />
        <input type="hidden" name="action" value="reject" />
        <label for="rejectReason" class="form-label">Motivazione:</label>
        <textarea id="rejectReason" name="reason" class="form-control" required></textarea>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-link" data-bs-dismiss="modal">Annulla</button>
        <button type="submit" class="btn btn-danger">Invia</button>
      </div>
    </div>
  </div>
</form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const approveModal = new bootstrap.Modal(document.getElementById('approveModal'));
    const rejectModal = new bootstrap.Modal(document.getElementById('rejectModal'));

    function openApproveModal() {
        approveModal.show();
    }

    function openRejectModal() {
        rejectModal.show();
    }

    
</script>
</body>
</html>
