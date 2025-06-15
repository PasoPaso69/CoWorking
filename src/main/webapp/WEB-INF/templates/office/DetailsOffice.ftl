<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Dettagli Ufficio</title>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
  />
  <link
    rel="stylesheet"
    href="${ctx}/resources/css/DetailsOffice.css"
  />
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
  />
  <style>
    /* RESET */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: Arial, sans-serif;
      background-color: #222;
      color: #333;
    }

    /* NAVBAR */
    .navbar {
      padding: 30px 40px;
      height: 120px;
      background-color: #000;
    }

    .navbar-brand img {
      width: 50px;
    }

    .navbar-nav .nav-link {
      color: white;
      font-size: 1.1rem;
      transition: color 0.3s;
    }

    .navbar-nav .nav-link:hover {
      color: #e74c3c;
    }

    .navbar-nav .nav-link i {
      margin-right: 6px;
    }

    /* CONTENITORE DETTAGLI */
    .dettagli-container {
      max-width: 900px;
      margin: 60px auto;
      padding: 20px;
      background: #fff;
      border-radius: 16px;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
    }

    /* GALLERIA IMMAGINI */
    .galleria-foto {
      display: flex;
      gap: 15px;
      overflow-x: auto;
      padding-bottom: 10px;
      padding-left:10px;
      scrollbar-width: thin;
    }

    .galleria-foto::-webkit-scrollbar {
      height: 8px;
    }

    .galleria-foto::-webkit-scrollbar-thumb {
      background-color: rgba(0, 0, 0, 0.2);
      border-radius: 4px;
    }

    .galleria-foto img {
      width: 180px;
      height: 120px;
      object-fit: cover;
      border-radius: 12px;
      flex-shrink: 0;
    }

    /* INFO UFFICIO */
    .info-ufficio {
      display: flex;
      flex-direction: column;
      gap: 15px;
    }

    .info-ufficio h1 {
      font-size: 2rem;
      color: #222;
    }

    .info-ufficio p {
      font-size: 1.1rem;
      color: #444;
    }

    .info-ufficio .prezzo {
      font-size: 1.5rem;
      color: #e74c3c;
      font-weight: bold;
      text-align: end;
    }

    .btn {
      display: inline-flex;
      align-items: center;
      gap: 8px;
      background: linear-gradient(135deg, #e74c3c, #c0392b);
      color: white;
      padding: 12px 25px;
      border-radius: 30px;
      text-decoration: none;
      font-weight: 600;
      border: none;
      transition: background 0.3s ease;
    }

    .btn:hover {
      background: linear-gradient(135deg, #ff6f61, #e74c3c);
    }

    /* FOOTER (caricato dinamicamente) */
    .site-footer {
      background-color: #000;
      color: #fff;
      padding: 60px 20px 40px;
      font-size: 1rem;
      border-top: 1px solid rgba(255, 255, 255, 0.1);
    }
  </style>
</head>
<body>
  <!-- NAVBAR -->
  <#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl" />
  <#else>
    <#include "/navbar/navbar1.ftl" />
  </#if>

  <!-- CONTENUTO PRINCIPALE -->
  <div class="dettagli-container my-5">
    <div class="row g-4">
      <!-- Galleria Immagini -->
      <div class="col-lg-6">
        <div class="galleria-foto">
          <#list foto as photo>
            <img src="${photo}" alt="image" class="img-fluid mb-2" />
          </#list>
        </div>
      </div>

      <!-- Info Ufficio -->
      <div class="col-lg-6">
        <div class="info-ufficio">
          <h1 class="mb-3">${ufficio.titolo}</h1>
          <p><strong>Indirizzo:</strong> ${ufficio.indirizzo}</p>
          <p><strong>Posti disponibili:</strong> ${ufficio.numeroPostazioni}</p>
          <p><strong>Contatti:</strong> ${ufficio.locatore.phone}</p>
          <p><strong>Descrizione:</strong> ${ufficio.descrizione}</p>
          <p><strong>Superficie:</strong> ${ufficio.superficie} m<sup>2</sup></p>
          <p><strong>Servizi Aggiuntivi:</strong></p>
          <ul>
            <#list ufficio.serviziAggiuntivi as servizio>
              <li>${servizio.nomeServizio}</li>
            </#list>
          </ul>

          <div class="prezzo h4 text-danger text-end">
            Prezzo: ${ufficio.prezzo} euro al giorno
          </div>
          <form action="${ctx}/home-utente/search/showoffice/detailsoffice/confirm" method="post">
  <input type="hidden" name="idufficio" value="${ufficio.id}" />
  <input type="hidden" name="date" value="${date}" />
  <input type="hidden" name="slot" value="${slot}" />
  <button type="submit" class="btn btn-danger mt-3 w-100">
    <i class="fa-solid fa-credit-card"></i> Vai al pagamento
  </button>
</form>

        </div>

        <div class="row mt-3">
          <div class="col-6">
            <a href="${ctx}/home-utente/search/showoffice/detailsoffice/review?idufficio=${ufficio.id}" class="btn btn-danger w-100"
              ><i class="fa-solid fa-star"></i> Recensioni</a
            >
          </div>
          <div class="col-6">
            <a
              href="${ctx}/home-utente/search/showoffice/detailsoffice/Report?idufficio=${ufficio.id}"
              class="btn btn-danger w-100"
              ><i class="fa-solid fa-flag"></i> Segnala</a
            >
          </div>
        </div>
      </div>
    </div>
  </div>

  <#include "/navbar/footer.ftl" />

  <!-- BOOTSTRAP SCRIPT -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
