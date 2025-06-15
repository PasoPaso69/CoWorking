<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8" />
    <title>Segnala Annuncio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet" />
    <link href="${ctx}/resources/css/report.css" rel="stylesheet" />
</head>
<body class="text-white">

<#if isloggedin == "isLoggedIn">
    <#include "/navbar/navbar.ftl">
<#else>
    <#include "/navbar/navbar1.ftl">
</#if>


<div class="container form-container text-dark">
    <h2 class="mb-4 text-center">Segnala questo annuncio</h2>

    <form id="segnalazioneForm" action="${ctx}/home-utente/search/showoffice/detailsoffice/Report?idufficio=${idufficio }" method="POST" novalidate>
        <div class="mb-3 fs-5">
            <label class="form-label">Perché segnali questo annuncio?</label>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="motivo" id="nonUfficio" value="Non è un ufficio" required />
                <label class="form-check-label" for="nonUfficio">Non è un ufficio</label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="motivo" id="truffa" value="È una truffa" />
                <label class="form-check-label" for="truffa">È una truffa</label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="motivo" id="contenutiInutili" value="Contenuti inutili" />
                <label class="form-check-label" for="contenutiInutili">Contenuti inutili</label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="motivo" id="altro" value="Altro" />
                <label class="form-check-label" for="altro">Altro</label>
            </div>
        </div>

        <div class="mb-3" id="altroTestoContainer" style="display:none;">
            <label for="altroTesto" class="form-label fs-5">Specifica il motivo</label>
            <textarea class="form-control" id="altroTesto" name="altroTesto" rows="3" placeholder="Scrivi qui..."></textarea>
        </div>

        <button type="submit" class="btn btn-danger w-100">Conferma Segnalazione</button>
    </form>
</div>

<#include "/navbar/footer.ftl">

<script>

    // Gestione mostra/nascondi textarea e required su "Altro"
    const radios = document.querySelectorAll('input[name="motivo"]');
    const altroContainer = document.getElementById("altroTestoContainer");
    const altroTextarea = document.getElementById("altroTesto");
    const form = document.getElementById("segnalazioneForm");

    radios.forEach(radio => {
        radio.addEventListener("change", () => {
            if (document.getElementById("altro").checked) {
                altroContainer.style.display = "block";
                altroTextarea.setAttribute("required", "required");
            } else {
                altroContainer.style.display = "none";
                altroTextarea.removeAttribute("required");
                altroTextarea.value = "";
            }
        });
    });

    // Facoltativo: validazione personalizzata se vuoi
    form.addEventListener("submit", (e) => {
        if (!form.checkValidity()) {
            e.preventDefault();
            e.stopPropagation();
            form.classList.add('was-validated');
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

