<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Ufficio ${office.titolo} - Dettagli</title>
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
        <!-- Navbar top -->
        <header class="navbar navbar-expand-md navbar-light bg-white shadow-sm">
            <div class="container-xl d-flex justify-content-between align-items-center">
                <a href="#" class="navbar-brand">Ufficio ${office.titolo}</a>
            </div>
        </header>

        <!-- Page body -->
        <div class="page-body container py-4">
            <!-- Tabs -->
            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs" role="tablist" id="officeTabs">
                        <li class="nav-item">
                            <a href="#tab-dettagli" class="nav-link active" data-bs-toggle="tab" role="tab"
                               aria-selected="true">Dettagli</a>
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
                                        <button
                                            type="button"
                                            data-bs-target="#carousel-sample"
                                            data-bs-slide-to="0"
                                            class="active"
                                        ></button>
                                        <#list office.foto as photo>
                                            <button type="button" data-bs-target="#carousel-sample" data-bs-slide-to="${photo_index + 1}"></button>
                                        </#list>
                                    </div>
                                    <div class="carousel-inner">
                                        <#list office.foto as photo>
                                            <div class="carousel-item <#if photo_index == 0>active</#if>">
                                                <img
                                                    class="d-block w-100"
                                                    alt="Ufficio ${office.titolo} foto ${photo_index + 1}"
                                                    src="${ctx}/photo?id=${photo.id}"
                                                />
                                            </div>
                                        </#list>
                                    </div>
                                    <a
                                            class="carousel-control-prev"
                                            data-bs-target="#carousel-sample"
                                            role="button"
                                            data-bs-slide="prev"
                                    >
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Precedente</span>
                                    </a>
                                    <a
                                            class="carousel-control-next"
                                            data-bs-target="#carousel-sample"
                                            role="button"
                                            data-bs-slide="next"
                                    >
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Successiva</span>
                                    </a>
                                </div>
                                <h2>${office.titolo}</h2>
                                <p class="text-muted mb-2">Data rifiuto: ${office.dataRifiuto}</p>
                                <p class="text-muted mb-2">Motivazione: ${office.motivoRifiuto}</p>
                                <p><strong>Indirizzo:</strong> ${office.indirizzo.via} ${office.indirizzo.numeroCivico}, ${office.indirizzo.citta} (${office.indirizzo.provincia})</p>
                                <p><strong>Prezzo:</strong> ${office.prezzo} € / postazione</p>
                                <p><strong>Superficie:</strong> ${office.superficie} m^2</p>
                                <p><strong>Postazioni disponibili:</strong> ${office.numeroPostazioni}</p>

                                <h4 class="mt-4">Servizi aggiuntivi</h4>
                                <div class="services-list">
                                    <#list office.serviziAggiuntivi as service>
                                        <div class="service-item" title="${service.nomeServizio}">${service.nomeServizio}</div>
                                    </#list>
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
</body>
</html>
