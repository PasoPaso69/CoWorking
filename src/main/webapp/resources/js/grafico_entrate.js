const ctx = window.location.pathname.split('/')[1]; // estrae 'CoWirking1-UFFICI' da '/CoWirking1-UFFICI/qualcosa'

fetch(`/${ctx}/api/grafici/entrate-mensili`)
  .then(response => {
    const contentType = response.headers.get('content-type');
    if (!contentType || !contentType.includes('application/json')) {
      throw new Error('Risposta non in formato JSON');
    }
    if (!response.ok) {
      throw new Error(`Errore HTTP: ${response.status}`);
    }
    return response.json();
  })
  .then(data => {
    if (!Array.isArray(data)) {
      throw new Error('Dati ricevuti non sono un array');
    }

    const mesi = data.map(e => e.mese);
    const entrate = data.map(e => e.entrate);

    new Chart(document.getElementById('graficoEntrate'), {
      type: 'bar',
      data: {
        labels: mesi,
        datasets: [{
          label: 'Entrate (€)',
          data: entrate,
          backgroundColor: 'rgba(54, 162, 235, 0.7)', // colore barre
          borderColor: 'rgba(54, 162, 235, 1)',       // bordo barre
          borderWidth: 1,
          borderRadius: 5                            // arrotonda gli angoli delle barre
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              callback: value => '€ ' + value.toLocaleString()  // formato valuta asse y
            }
          }
        }
      }
    });
  })
  .catch(err => {
    console.error('Errore nel caricamento o parsing dei dati:', err);
    // Qui puoi gestire meglio l'errore, tipo mostrare un messaggio all’utente
  });
