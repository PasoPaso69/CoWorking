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

            <!-- SEZIONE SEGNALAZIONI -->
            <h5>Segnalazioni ricevute</h5>
            <#if reports?size == 0>
              <p class="text-muted">Nessuna segnalazione presente.</p>
            <#else>
              <ul class="list-group">
                <#list reports as report>
                  <#if report.commento?has_content>
                    <li class="list-group-item">
                      <strong>Commento:</strong> ${report.commento}
                    </li>
                  </#if>
                </#list>
              </ul>
            </#if>
          </div>
        </div>

      </div>
    </div>
  </div>

  <!-- Tabler JS -->
  <script src="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/js/tabler.min.js"></script>
</body>
</html>

