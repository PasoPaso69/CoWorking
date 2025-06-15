<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Ufficio ${office.titolo!""} - Dettagli</title>
    <link href="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/css/tabler.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .office-main-photo {
            width: 100%;
            max-height: 300px;
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 1rem;
        }

        .services-list {
            display: flex;
            gap: 1.5rem;
            flex-wrap: wrap;
            font-size: 1.2rem;
            margin-top: 1rem;
        }

        .service-item {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .btn-danger-icon {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }
    </style>
</head>

<body>
<#include "/navbar/navbaradmin.ftl">
<div class="page">

    <div class="page-wrapper">
        <header class="navbar navbar-expand-md navbar-light bg-white shadow-sm">
            <div class="container-xl d-flex justify-content-between align-items-center">
                <a href="#" class="navbar-brand">Ufficio ${office.titolo!""}</a>
            </div>
        </header>

        <div class="page-body container py-4">
            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs" role="tablist" id="officeTabs">
                        <li class="nav-item">
                            <a href="#tab-dettagli" class="nav-link active" data-bs-toggle="tab" role="tab">Dettagli</a>
                        </li>
                        <li class="nav-item">
                            <a href="#tab-statistiche" class="nav-link" data-bs-toggle="tab" role="tab">Statistiche</a>
                        </li>
                        <li class="nav-item">
                            <a href="#tab-gestione" class="nav-link" data-bs-toggle="tab" role="tab">Gestione</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body tab-content">

                    <!-- Dettagli -->
                    <div class="tab-pane fade show active" id="tab-dettagli" role="tabpanel">
                        <div class="row">
                            <div class="col-md-6">
                                <div id="carousel-sample" class="carousel slide" data-bs-ride="carousel">
                                    <div class="carousel-indicators">
                                        <#list office.foto![] as foto>
                                            <button type="button" data-bs-target="#carousel-sample" data-bs-slide-to="${foto_index}" class="<#if foto_index == 0>active</#if>"></button>
                                        </#list>
                                    </div>
                                    <div class="carousel-inner">
                                        <#list office.foto![] as foto>
                                            <div class="carousel-item <#if foto_index == 0>active</#if>">
                                                <img src="${ctx}/photo?id=${foto.id!''}" class="d-block w-100 office-main-photo" alt="Foto Ufficio" />
                                            </div>
                                        </#list>
                                    </div>
                                    <a class="carousel-control-prev" data-bs-target="#carousel-sample" role="button" data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Precedente</span>
                                    </a>
                                    <a class="carousel-control-next" data-bs-target="#carousel-sample" role="button" data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Successiva</span>
                                    </a>
                                </div>

                                <h2>${office.titolo!""}</h2>
                                <p class="text-muted mb-2">Data attivazione: ${office.dataApprovazione!""}</p>
                                <p><strong>Indirizzo:</strong> ${office.indirizzo.via!""} ${office.indirizzo.numeroCivico!""}, ${office.indirizzo.citta!""} (${office.indirizzo.provincia!""})</p>
                                <p><strong>Prezzo:</strong> ${office.prezzo!0} euro / postazione</p>
                                <p><strong>Superficie:</strong> ${office.superficie!0} m²</p>
                                <p><strong>Postazioni disponibili:</strong> ${office.numeroPostazioni!0}</p>

                                <h4 class="mt-4">Servizi aggiuntivi</h4>
                                <div class="services-list">
                                    <#list office.serviziAggiuntivi![] as service>
                                        <div class="service-item" title="${service.nomeServizio!''}">${service.nomeServizio!''}</div>
                                    </#list>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Statistiche -->
                    <div class="tab-pane fade" id="tab-statistiche" role="tabpanel">
                        <div class="row">
                            <div class="col-md-6 mb-4">
                                <h4>Andamento Prenotazioni</h4>
                                <canvas id="chartPrenotazioni" height="250"></canvas>
                            </div>
                            <div class="col-md-6 mb-4">
                                <h4>Entrate Mensili (€)</h4>
                                <canvas id="chartEntrate" height="250"></canvas>
                            </div>
                        </div>
                    </div>

                    <!-- Gestione -->
                    <div class="tab-pane fade" id="tab-gestione" role="tabpanel">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">Dettagli Locatore</h3>
                                    </div>
                                    <div class="card-body">
                                        <p><strong>Nome:</strong> ${office.locatore.name!""} ${office.locatore.surname!""}</p>
                                        <p><strong>Email:</strong> ${office.locatore.email!""}</p>
                                        <p><strong>Telefono:</strong> ${office.locatore.phone!""}</p>
                                        <a href="${ctx}/actionOffice?id=${office.id}" class="btn btn-outline-primary mt-3">
                                        Visualizza segnalazioni
                                        </a>

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 d-flex align-items-center justify-content-center">
                                <button class="btn btn-danger btn-lg btn-danger-icon" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                    <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-alert-circle" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
                                        <circle cx="12" cy="12" r="9" />
                                        <line x1="12" y1="8" x2="12" y2="12" />
                                        <line x1="12" y1="16" x2="12.01" y2="16" />
                                    </svg>
                                    Rimuovi
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal" id="exampleModal" tabindex="-1">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    <div class="modal-status bg-danger"></div>
                    <div class="modal-body text-center py-4">
                        <svg xmlns="http://www.w3.org/2000/svg" class="icon mb-2 text-danger icon-lg" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                            <path d="M12 9v2m0 4v.01" />
                            <path d="M5 19h14a2 2 0 0 0 1.84 -2.75l-7.1 -12.25a2 2 0 0 0 -3.5 0l-7.1 12.25a2 2 0 0 0 1.75 2.75" />
                        </svg>
                        <h3>Sei sicuro?</h3>
                        <div class="text-secondary">
                            <#if reservationCount?? && reservationCount > 0>
                                <p>Rimuovendo questo ufficio cancellerai ${reservationCount} prenotazioni</p>
                                <div>
                                    <input type="checkbox" name="shouldRefund" id="shouldRefundCheckbox" />
                                    <label for="shouldRefund">Rimborsare gli utenti?</label>
                                </div>
                            <#else>
                                Nessuna prenotazione attiva presente
                            </#if>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="w-100">
                            <div class="row">
                                <div class="col">
                                    <a href="#" class="btn w-100" data-bs-dismiss="modal">Torna indietro</a>
                                </div>
                                <div class="col">
                                    <form action="${ctx}/admin/office?id=${office.id}" method="POST" id="deleteForm">
                                        <input type="hidden" name="shouldRefund" id="shouldRefundHidden" value="0">
                                    </form>
                                    <a href="#" id="submitAnchor" class="btn btn-danger w-100">
                                        <#if reservationCount?? && reservationCount > 0>
                                            Cancella ${reservationCount} prenotazioni
                                        <#else>
                                            Cancella ufficio
                                        </#if>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@tabler/core@latest/dist/js/tabler.min.js"></script>
<script>
    document.getElementById("submitAnchor").addEventListener('click', function (e) {
        e.preventDefault();
        const form = document.getElementById("deleteForm");
        const checkbox = document.getElementById("shouldRefundCheckbox");
        const hiddenInput = document.getElementById("shouldRefundHidden");
        if (checkbox) hiddenInput.value = checkbox.checked ? "1" : "0";
        form.submit();
    });
</script>
<script>
    const prenotazioniMensili = [${reservationformonth?join(',')}];
    const entrateMensili = [${revenueformonth?join(',')}];
</script>
<script>
    new Chart(document.getElementById('chartPrenotazioni').getContext('2d'), {
        type: 'line',
        data: {
            labels: ['Gen', 'Feb', 'Mar', 'Apr', 'Mag', 'Giu', 'Lug', 'Ago', 'Set', 'Ott', 'Nov', 'Dic'],
            datasets: [{
                label: 'Prenotazioni',
                data: prenotazioniMensili,
                borderColor: '#206bc4',
                backgroundColor: 'rgba(32, 107, 196, 0.1)',
                fill: true,
                tension: 0.3,
                pointRadius: 3,
                borderWidth: 2,
            }]
        },
        options: {
            responsive: true,
            scales: { y: { beginAtZero: true, ticks: { stepSize: 5 } } }
        }
    });

    new Chart(document.getElementById('chartEntrate').getContext('2d'), {
        type: 'bar',
        data: {
            labels: ['Gen', 'Feb', 'Mar', 'Apr', 'Mag', 'Giu', 'Lug', 'Ago', 'Set', 'Ott', 'Nov', 'Dic'],
            datasets: [{
                label: 'Entrate (€)',
                data: entrateMensili,
                backgroundColor: '#e03131',
                borderRadius: 4,
            }]
        },
        options: {
            responsive: true,
            scales: { y: { beginAtZero: true, ticks: { stepSize: 500 } } }
        }
    });
</script>
</body>
</html>