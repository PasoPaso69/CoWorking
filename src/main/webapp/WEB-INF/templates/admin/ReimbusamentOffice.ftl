<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Dettaglio Ufficio</title>

  <!-- Tabler CSS -->
  <link href="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/css/tabler.min.css" rel="stylesheet" />

  <style>
    .card-title {
      font-size: 1.5rem;
    }
  </style>
</head>

<body>
  <div class="page">
    <!-- Navbar admin -->
    <#include "/navbar/navbaradmin.ftl">

    <div class="page-wrapper">
      <div class="page-body container py-4">

        <!-- CARD DETTAGLIO UFFICIO -->
        <div class="card shadow mb-4">
          <div class="card-header">
            <h3 class="card-title mb-0">${office.titolo}</h3>
          </div>
          <div class="card-body">

            <div class="row mb-3">
              <div class="col-md-6">
                <p class="mb-1"><strong>Locatore:</strong> ${office.locatore.name} ${office.locatore.surname}</p>
                <p class="mb-1"><strong>Email:</strong> ${office.locatore.email}</p>
                <p class="mb-1"><strong>Telefono:</strong> ${office.locatore.phone}</p>
              </div>
              <div class="col-md-6">
                <p class="mb-1"><strong>Prezzo giornaliero:</strong> € ${office.prezzo}</p>
                <p class="mb-1"><strong>Posti disponibili:</strong> ${office.numeroPostazioni} </p>
              </div>
            </div>

            <hr>

            <div class="card shadow">
          <div class="card-header bg-secondary text-white">
            <h4 class="card-title mb-0">Rimborsi Effettuati</h4>
          </div>
          <div class="card-body">
            <#if reimbursements?size == 0>
              <p class="text-muted">Nessun rimborso registrato per questo ufficio.</p>
            <#else>
              <div class="table-responsive">
                <table class="table table-bordered table-striped table-hover">
                  <thead class="table-light">
                    <tr>
                      <th>Utente</th>
                      <th>Email</th>
                      <th>Importo</th>
                      <th>Commento</th>
                    </tr>
                  </thead>
                  <tbody>
                    <#list reimbursements as r>
                      <tr>
                        <td>${r.segnalazione.utente.name} ${r.segnalazione.utente.surname}</td>
                        <td>${r.segnalazione.utente.email}</td>
                        <td>${r.importo}</td>
                        <td>
                          <#if r.segnalazione?? && r.segnalazione.commento?has_content>
                            ${r.segnalazione.commento}
                          <#else>
                            -
                          </#if>
                        </td>
                      </tr>
                    </#list>
                  </tbody>
                </table>
              </div>
            </#if>
          </div>
        </div>
          </div>
        </div>

      </div>
    </div>
  </div>

  <!-- Tabler JS -->
  <script src="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/js/tabler.min.js"></script>
</body>
</html>


