<!DOCTYPE html>
<html lang="it" data-bs-theme="light">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Aggiungi Ufficio - Cooworking</title>

  <!-- Tabler Core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/css/tabler.min.css" rel="stylesheet" />

  <!-- FontAwesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

  <!-- CSS personalizzato -->
  <link rel="stylesheet" href="/CoWirking1-UFFICI/resources/css/locatore/aggiungi_ufficio.css" />
</head>
<body>

  <!-- Navbar -->
  <#include "/locatore/navbar/navbarLocatore.ftl" />

  <div class="page-wrapper main-content">
    <div class="container-xl mt-5">
      <h1 class="mb-3">Aggiungi un nuovo ufficio</h1>
      <p class="text-muted mb-4">Compila il modulo qui sotto per aggiungere un nuovo spazio di coworking alla piattaforma.</p>

<form id="form-ufficio" class="card card-body shadow-sm" action="${ctx}/aggiunta" method="POST" enctype="multipart/form-data">
        <div class="mb-3">
          <label class="form-label">Nome Ufficio</label>
          <input type="text" class="form-control" id="nome-ufficio" name="nome-ufficio" placeholder="Es. Ufficio moderno Milano Centrale" required>
        </div>

        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="provincia" class="form-label">Provincia</label>
            <input type="text" class="form-control" id="provincia" name="provincia" placeholder="Es. Milano" required>
          </div>
          <div class="col-md-6 mb-3">
            <label for="comune" class="form-label">Comune</label>
            <input type="text" class="form-control" id="comune" name="comune" placeholder="Es. Montesilvano" required>
          </div>
          <div class="col-md-4 mb-3">
            <label for="indirizzo" class="form-label">Indirizzo</label>
            <input type="text" class="form-control" id="indirizzo" name="indirizzo" placeholder="Via Roma" required>
          </div>
          <div class="col-md-4 mb-3">
            <label for="civico" class="form-label">Numero Civico</label>
            <input type="text" class="form-control" id="civico" name="civico" placeholder="6" required>
          </div>
          <div class="col-md-4 mb-3">
            <label for="cap" class="form-label">CAP</label>
            <input type="text" class="form-control" id="cap" name="cap" placeholder="65015" required>
          </div>
        </div>

        <div class="row">
          <div class="col-md-4 mb-3">
            <label for="prezzo" class="form-label">Prezzo (€)</label>
            <input type="number" class="form-control" id="prezzo" name="prezzo" min="0" required>
          </div>
          <div class="col-md-4 mb-3">
            <label for="superficie" class="form-label">Superficie (m²)</label>
            <input type="number" class="form-control" id="superficie" name="superficie" min="0" required>
          </div>
          <div class="col-md-4 mb-3">
            <label for="postazioni" class="form-label">Numero di postazioni</label>
            <input type="number" class="form-control" id="postazioni" name="postazioni" min="0" required>
          </div>
        </div>

        <div class="mb-3">
          <label for="descrizione" class="form-label">Descrizione dell'ufficio</label>
          <textarea class="form-control" id="descrizione" name="descrizione" rows="4" placeholder="Descrivi le caratteristiche, arredi, posizione..."></textarea>
        </div>

        <div class="row">
          <div class="col-md-4 mb-3">
            <label for="data-inizio" class="form-label">Inizio disponibilità</label>
            <input type="date" class="form-control" id="data-inizio" name="data_inizio" required>
          </div>
          <div class="col-md-4 mb-3">
            <label for="data-fine" class="form-label">Fine disponibilità</label>
            <input type="date" class="form-control" id="data-fine" name="data_fine" required>
          </div>
          <div class="col-md-4 mb-3">
            <label for="fascia" class="form-label">Fascia di disponibilità</label>
            <select class="form-select" id="fascia" name="fascia" required>
              <option value="">-- Seleziona una fascia --</option>
              <option value="Mattina">Mattina</option>
              <option value="Pomeriggio">Pomeriggio</option>
            </select>
          </div>
        </div>

        <div class="mb-3">
          <label class="form-label">Carica fino a 3 foto</label>
          <div class="custom-file-upload">
            <button type="button" class="btn btn-outline-primary" id="seleziona-foto">
              <i class="fa fa-upload me-2"></i> Seleziona foto
            </button>
            <span id="conteggio-foto" class="ms-2 text-muted">0 / 3 immagini caricate</span>
          </div>
          <input type="file" id="foto" name="foto" accept="image/*" multiple hidden>
          <div id="anteprima-foto" class="image-preview-container mt-3"></div>
        </div>

        <div class="mb-3">
          <label class="form-label">Servizi disponibili</label>
          <div class="form-check"><input class="form-check-input" type="checkbox" id="wifi" name="servizi" value="Wi-Fi"> <label class="form-check-label" for="wifi">Wi-Fi</label></div>
          <div class="form-check"><input class="form-check-input" type="checkbox" id="aria-condizionata" name="servizi" value="Aria condizionata"> <label class="form-check-label" for="aria-condizionata">Aria condizionata</label></div>
          <div class="form-check"><input class="form-check-input" type="checkbox" id="parcheggio" name="servizi" value="Parcheggio"> <label class="form-check-label" for="parcheggio">Parcheggio</label></div>
          <div class="form-check"><input class="form-check-input" type="checkbox" id="stampante" name="servizi" value="Stampante"> <label class="form-check-label" for="stampante">Stampante</label></div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="altro-servizio" />
            <label class="form-check-label" for="altro-servizio">Altro</label>
          </div>
          <input type="text" class="form-control mt-2 d-none" id="campo-altro" placeholder="Specifica altri servizi..." name="altro-servizio" />
        </div>

        <button type="submit" class="btn btn-primary mt-2">
          Aggiungi ufficio
        </button>
      </form>
    </div>
  </div>

  <!-- Modale conferma -->
  <div class="modal fade" id="confermaModale" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
      <div class="modal-content">
        <div class="modal-header bg-warning text-white">
          <h5 class="modal-title"><i class="fa-solid fa-circle-exclamation me-2"></i> Conferma aggiunta</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Chiudi"></button>
        </div>
        <div class="modal-body">
          <p>Stai per aggiungere un nuovo ufficio alla piattaforma.</p>
          <p class="mb-0">Sei sicuro di voler procedere?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annulla</button>
          <button type="button" class="btn btn-warning" id="confermaSubmit">
            <i class="fa-solid fa-check me-1"></i> Conferma
          </button>
        </div>
      </div>
    </div>
  </div>

  <!-- Modale successo -->
  <div class="modal fade" id="successoModale" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
      <div class="modal-content">
        <div class="modal-header bg-success text-white">
          <h5 class="modal-title"><i class="fa-solid fa-check-circle me-2"></i> Successo</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Chiudi"></button>
        </div>
        <div class="modal-body">
          <p>Ufficio aggiunto con successo!</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" data-bs-dismiss="modal">Chiudi</button>
        </div>
      </div>
    </div>
  </div>

  <!-- JS -->
  <script src="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/js/tabler.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script src="/CoWirking1-UFFICI/resources/js/aggiungi_ufficio.js"></script>

</body>
</html>

