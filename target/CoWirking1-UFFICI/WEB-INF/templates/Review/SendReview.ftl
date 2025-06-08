
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Recensione</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/css/showreview.css">

</head>
<body class=" text-white">
<!--NAVBAR-->


     <#include "/navbar/navbar.ftl">




<div class="container form-container">
    <h2 class="mb-4 text-dark">Lascia una Recensione</h2>
    <form action="${ctx}/home-utente/reservation/Review?idreservation=${idreservation }" method="POST">

    <!-- Valutazione -->
    <div class="mb-3">
        <label class="form-label text-dark">Valutazione:</label>
        <div id="stelle" class="star-rating">
            <i class="fa fa-star" data-val="1"></i>
            <i class="fa fa-star" data-val="2"></i>
            <i class="fa fa-star" data-val="3"></i>
            <i class="fa fa-star" data-val="4"></i>
            <i class="fa fa-star" data-val="5"></i>
        </div>
        <input type="hidden" id="voto" name="voto" value="0">
    </div>

    <!-- Testo recensione -->
    <div class="mb-3">
        <label for="recensione" class="form-label text-dark">Scrivi la tua recensione:</label>
        <textarea class="form-control" id="review" name="review" rows="5" placeholder="Scrivi qui..." required></textarea>
    </div>

    <!-- Bottone invio -->
    <button type="submit" class="btn btn-danger">Conferma</button>
    </form>
</div>
 <#include "/navbar/footer.ftl">
<script>
    // Gestione stelle cliccabili
    const stars = document.querySelectorAll('.fa-star');
    const votoInput = document.getElementById('voto');

    stars.forEach(star => {
        star.addEventListener('click', () => {
            const value = star.getAttribute('data-val');
            votoInput.value = value;

            // Aggiungi classe "checked" fino alla stella selezionata
            stars.forEach(s => {
                if (s.getAttribute('data-val') <= value) {
                    s.classList.add('checked');
                } else {
                    s.classList.remove('checked');
                }
            });
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
