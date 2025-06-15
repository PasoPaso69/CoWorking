<!DOCTYPE html>
<html lang="it" data-bs-theme="light">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Profilo Utente - Cooworking</title>

  <!-- Tabler Core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/css/tabler.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

  <style>
    body {
      background: #f8f9fa;
    }
    .page-wrapper {
      padding: 40px 0;
    }
    .card {
      max-width: 700px;
      margin: auto;
    }
    .form-label {
      font-weight: 600;
    }
    .input-group-text {
      background-color: #e9ecef;
      border: none;
    }
  </style>
</head>
<body>

  <!-- Navbar -->
  <#include "/locatore/navbar/navbarLocatore.ftl" />

  <div class="page-wrapper main-content">
    <div class="container-xl">
      <h1 class="mb-3 text-center">Profilo Utente</h1>
      <p class="text-center text-muted mb-5">Visualizza le informazioni del tuo profilo personale.</p>

      <div class="card shadow-sm p-4">
        <div class="row g-4">
          <div class="col-md-6">
            <label for="nome" class="form-label">Nome</label>
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                id="nome"
                name="nome"
                value="${nome}"
                placeholder="Inserisci il tuo nome"
              />
              <span class="input-group-text"><i class="fa-solid fa-pencil-alt"></i></span>
            </div>
          </div>

          <div class="col-md-6">
            <label for="cognome" class="form-label">Cognome</label>
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                id="cognome"
                name="cognome"
                value="${cognome}"
                placeholder="Inserisci il tuo cognome"
              />
              <span class="input-group-text"><i class="fa-solid fa-pencil-alt"></i></span>
            </div>
          </div>

          <div class="col-md-6">
            <label for="partita-iva" class="form-label">Partita IVA</label>
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                id="partita-iva"
                name="partita_iva"
                value="${partitaIva}"
                placeholder="Inserisci la tua Partita IVA"
              />
              <span class="input-group-text"><i class="fa-solid fa-pencil-alt"></i></span>
            </div>
          </div>

          <div class="col-md-6">
            <label for="telefono" class="form-label">Telefono</label>
            <div class="input-group">
              <input
                type="tel"
                class="form-control"
                id="telefono"
                name="telefono"
                value="${telefono}"
                placeholder="Inserisci il tuo numero di telefono"
              />
              <span class="input-group-text"><i class="fa-solid fa-phone"></i></span>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>

  <!-- JS -->
  <script src="https://cdn.jsdelivr.net/npm/@tabler/core@1.0.0/dist/js/tabler.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

  <script>
    document.getElementById('saveBtn').addEventListener('click', () => {
      const nome = document.getElementById('nome').value;
      const cognome = document.getElementById('cognome').value;
      const partitaIva = document.getElementById('partita-iva').value;
      const telefono = document.getElementById('telefono').value;

      alert(`Profilo salvato:\nNome: ${nome}\nCognome: ${cognome}\nPartita IVA: ${partitaIva}\nTelefono: ${telefono}`);
    });
  </script>
</body>
</html>
