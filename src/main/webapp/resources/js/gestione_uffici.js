document.addEventListener('DOMContentLoaded', () => {
    // Gestione modale dettagli
    const modalDettagli = document.getElementById('modal-dettagli');

    if (modalDettagli) {
        modalDettagli.addEventListener('show.bs.modal', (event) => {
            const button = event.relatedTarget;

            if (button) {
                const titolo = button.getAttribute('data-titolo') || 'huftvh';
                const descrizione = button.getAttribute('data-descrizione') || '';
                const prezzo = button.getAttribute('data-prezzo') || '';
                const superficie = button.getAttribute('data-superficie') || '';
                //const fascia = button.getAttribute('data-fascia') || '';
                const indirizzo = button.getAttribute('data-indirizzo') || '';
                const postazioni = button.getAttribute('data-postazioni') || '';
                const servizi = button.getAttribute('data-servizi') || '';
                

                document.getElementById('modalTitoloHeader').textContent = `🏢 ${titolo}`;
                document.getElementById('modalNomeUfficio').textContent = titolo;
                document.getElementById('modalDescrizione').textContent = descrizione;
                document.getElementById('modalPrezzo').textContent = prezzo;
                document.getElementById('modalSuperficie').textContent = superficie;
               // document.getElementById('modalFascia').textContent = fascia;
                document.getElementById('modalIndirizzo').textContent = indirizzo;
                document.getElementById('modalPostazioni').textContent = postazioni;
                document.getElementById('modalServizi').textContent = servizi;
                
            }
        });
    }

    // Gestione cancellazione
    let cardToDelete = null;

    document.querySelectorAll('.btn-cancella').forEach(btn => {
        btn.addEventListener('click', () => {
            cardToDelete = btn.closest('.col-md-6') || btn.closest('.col-lg-4');
        });
    });

    const confermaBtn = document.getElementById('conferma-cancella-btn');
    if (confermaBtn) {
        confermaBtn.addEventListener('click', () => {
            if (cardToDelete) {
                cardToDelete.remove();
                cardToDelete = null;

                const modalEl = document.getElementById('modal-conferma-cancella');
                let instance = bootstrap.Modal.getInstance(modalEl);
                if (!instance) {
                    instance = new bootstrap.Modal(modalEl);
                }
                instance.hide();
            }
        });
    }
});


