<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Le Tue Prenotazioni</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/css/showReservation.css" />
</head>
<body>
<!--NAVBAR-->

<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl">
<#else>
    <#include "/navbar/navbar1.ftl">
</#if>


<main class="flex-grow-1">
    <div class="container py-5">
        <h2 class="text-center mb-4">Le tue prenotazioni</h2>

        <!-- Toggle -->
        <div class="d-flex justify-content-center gap-3 mb-5">
            <button class="btn btn-toggle active" id="btn-attive">Attive</button>
            <button class="btn btn-toggle" id="btn-passate">Passate</button>
        </div>

        <!-- reservation -->
        <div id="lista-prenotazioni">
            <#list  activereservation as reservation >
            <!-- Prenotazione Attiva -->
            <div class="card card-custom mb-4 d-flex flex-row p-3 align-items-center prenotazione attiva">
<img src="${ctx}/photo?id=${reservation.foto.id}" class="card-img me-3" alt="Ufficio">
                <div class="flex-grow-1">
                    <h5 class="mb-1 text-danger">${ reservation.ufficio.titolo }</h5>
                    <p class="mb-2"><Strong>Descrizione : </Strong>${ reservation.ufficio.descrizione }</p>
                    <button class="btn btn-outline-secondary" onclick="window.location.href='${ctx}/home-utente/reservation/Review?idreservation=${ reservation.reservation.id }'">Lascia una recensione</button>

                </div>
                <button class="btn-visualizza" onclick="window.location.href='${ctx}/home-utente/search/showoffice/detailsreservation?idreservation=${ reservation.reservation.id }'">Visualizza</button>
            </div>
            </#list>
            <#list oldreservation as reservation1  >
            <!-- old reservation -->
            <div class="card card-custom mb-4 d-flex flex-row p-3 align-items-center prenotazione passata d-none">
<img src="${ctx}/photo?id=${reservation1.foto.id}" class="card-img me-3" alt="Ufficio">
                <div class="flex-grow-1">
                    <h5 class="mb-1 text-danger">${ reservation1.ufficio.titolo }</h5>
                    <p class="mb-2"><Strong>Descrizione : </Strong>${ reservation1.ufficio.descrizione }</p>
                    <button class="btn btn-outline-secondary" onclick="window.location.href='${ctx}/home-utente/Reservation/Review?idreservation=${ reservation1.reservation.id }'">Lascia una recensione</button>
                </div>
                <button class="btn-visualizza" onclick="window.location.href='${ctx}/home-utente/search/showoffice/Detailsreservation?idreservation=${ reservation1.reservation.id }'">Visualizza</button>
            </div>

            </#list>
        </div>
    </div>
</main>
<!--FOOTER-->
<#include "/navbar/footer.ftl">


<script>
    const btnAttive = document.getElementById("btn-attive");
    const btnPassate = document.getElementById("btn-passate");
    const prenotazioni = document.querySelectorAll(".prenotazione");

    btnAttive.addEventListener("click", () => {
        btnAttive.classList.add("active");
        btnPassate.classList.remove("active");
        prenotazioni.forEach(card => {
            card.classList.toggle("d-none", card.classList.contains("passata"));
        });
    });

    btnPassate.addEventListener("click", () => {
        btnPassate.classList.add("active");
        btnAttive.classList.remove("active");
        prenotazioni.forEach(card => {
            card.classList.toggle("d-none", card.classList.contains("attiva"));
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
