

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Affitta Ufficio</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet" />

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${ctx}/resources/css/homeutent.css" />
</head>
<body>


<!-- NAVBAR -->

<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl">
<#else>
    <#include "/navbar/navbar1.ftl">
</#if>



<!-- HERO + DESCRIZIONE + BARRA DI RICERCA -->
<section class="hero-background py-5">
    <div class="container">
        <div class="text-center mb-4">
            <h2>Il tuo ufficio ovunque</h2>
            <p class="lead">Hai bisogno di uno spazio dove lavorare mentre sei in viaggio? La nostra piattaforma ti permette di cercare e prenotare un ufficio disponibile per mezza giornata o per l’intera giornata, ovunque ti trovi.</p>
            <p>Seleziona la data, il luogo e la fascia oraria preferita e trova l’ambiente perfetto per lavorare in serenità.</p>
        </div>

        <form action="${ctx}/home-utente/search" method="GET" class="row g-3 align-items-end">
            <div class="col-md-4">
                <label class="form-label">Nome ufficio o Luogo</label>
                <div class="input-group">
                    <input type="text" name="query" class="form-control" placeholder="Nome ufficio o luogo" required>
                    <span class="input-group-text"><i class="fas fa-location-arrow"></i></span>
                </div>
            </div>

            <div class="col-md-3">
                <label class="form-label">Data</label>
                <input type="date" name="date" class="form-control" required>
            </div>

            <div class="col-md-3">
                <label class="form-label">Fascia Oraria</label>
                <div class="input-group">
                    <select name="slot" class="form-select" required>
                        <option value="Mattina">Mattina</option>
                        <option value="Pomeriggio">Pomeriggio</option>
                    </select>
                    <span class="input-group-text"><i class="fas fa-clock"></i></span>
                </div>
            </div>

            <div class="col-md-2">
                <button type="submit" class="btn btn-primary w-100">
                    <i class="fas fa-search"></i> Cerca
                </button>
            </div>
        </form>
    </div>
</section>

<!-- SEZIONE SERVIZI -->
<section class="py-5 nero">
    <div class="container">
        <h2 class="text-center mb-4">Servizi Aggiuntivi</h2>
        <p class="text-center mb-5">Per offrirti un'esperienza lavorativa completa, mettiamo a disposizione una serie di servizi aggiuntivi pensati per il tuo comfort e la tua produttività.</p>
        <div class="row g-4">
            <div class="col-md-6 col-lg-3">
                <div class="card h-100 text-center">
                    <img src="${ctx}/resources/images/wifi.webp" class="card-img-top" alt="Wi-Fi Ultraveloce">
                    <div class="card-body">
                        <h5 class="card-title">Wi-Fi Ultraveloce</h5>
                        <p class="card-text">Connessione stabile e ad alta velocità per videoconferenze e lavoro online senza interruzioni.</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-3">
                <div class="card h-100 text-center">
                    <img src="${ctx}/resources/images/stampante.png" class="card-img-top" alt="Stampante & Scanner">
                    <div class="card-body">
                        <h5 class="card-title">Stampante & Scanner</h5>
                        <p class="card-text">Accesso rapido a dispositivi per la stampa e la scansione di documenti professionali.</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-3">
                <div class="card h-100 text-center">
                    <img src="${ctx}/resources/images/areacoffeebreak.jpg" class="card-img-top" alt="Area Coffee Break">
                    <div class="card-body">
                        <h5 class="card-title">Area Coffee Break</h5>
                        <p class="card-text">Un angolo di relax con caffè, bevande calde e snack per le tue pause rigeneranti.</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-3">
                <div class="card h-100 text-center">
                    <img src="${ctx}/resources/images/sala-riunioni.avif" class="card-img-top" alt="Sala Riunioni">
                    <div class="card-body">
                        <h5 class="card-title">Sala Riunioni</h5>
                        <p class="card-text">Spazi riservati per meeting, brainstorming e videoconferenze professionali.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<!-- FOOTER -->
<#include "/navbar/footer.ftl">
<!-- Bootstrap JS -->

</body>
</html>
