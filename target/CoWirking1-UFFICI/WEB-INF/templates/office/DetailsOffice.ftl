<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dettagli Ufficio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/html/DettaglioOffice/DettaglioOffice.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>

<!-- NAVBAR -->

<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar1.ftl">
<#else>
    <#include "/navbar/navbar.ftl">
</#if>

<!-- CONTENUTO PRINCIPALE -->
<div class="container my-5 dettagli-container">
    <div class="row g-4">
        <!-- Galleria Immagini -->
        <div class="col-lg-6">
            <div class="galleria-foto d-flex flex-wrap gap-3">
                {% for immagine in ufficio.foto %}

                    <img src="{{ immagine }}" alt="image">

                {% endfor %}
            </div>
        </div>

        <!-- Info Ufficio -->
        <div class="col-lg-6">
            <div class="info-ufficio">
                <h1 class="mb-3">{{ufficio.ufficio.titolo }}</h1>
                <p><strong>Indirizzo:</strong> {{ ufficio.ufficio.indirizzo }}</p>
                <p><strong>Posti disponibili:</strong> {{ ufficio.ufficio.numeroPostazioni }}</p>
                <p><strong>Contatti:</strong> info@affittaufficio.it - +39 0123 456789</p>
                <p><strong>Descrizione:</strong> {{ ufficio.ufficio.descrizione }}</p>
                <p><strong>Superficie:</strong> {{ ufficio.ufficio.superficie }} m^2</p>
                <p><strong>Servizi Aggiuntivi:</strong></p>
                <ul>
                    {% for servizio in ufficio.ufficio.serviziAggiuntivi %}
                    <li>{{ servizio.nomeServizio }}</li>
                    {% endfor %}
                </ul>

                <div class="prezzo h4 text-danger text-end">Prezzo: {{ ufficio.ufficio.prezzo}} euro al giorno</div>
                <a href="/search/showoffice/detailsoffice/confirm/reservated/{{ date }}/{{ ufficio.ufficio.id }}/{{ fascia }}" class="btn btn-danger mt-3"><i class="fa-solid fa-credit-card"></i> Vai al pagamento</a>
            </div>
            <div class="row mt-3">
                <div class="col-6">
                    <a href="/search/showoffice/detailsoffice/review/{{ufficio.ufficio.id}}" class="btn btn-danger w-100">
                        <i class="fa-solid fa-star"></i> Recensioni
                    </a>
                </div>
                <div class="col-6">
                    <a href="/search/showoffice/detailsoffice/Report/{{ ufficio.ufficio.id }}" class="btn btn-danger w-100">
                        <i class="fa-solid fa-flag"></i> Segnala
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/navbar/footer.ftl">

<!-- BOOTSTRAP SCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>

