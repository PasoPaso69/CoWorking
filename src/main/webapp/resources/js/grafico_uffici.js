const appContext = window.location.pathname.split('/')[1];

fetch(`/${appContext}/api/grafici/utilizzoUffici`)
  .then(response => {
    if (!response.ok) {
      // Se status HTTP non OK, leggi il testo e lancia errore con contenuto
      return response.text().then(text => {
        throw new Error(`Errore HTTP ${response.status}:\n${text}`);
      });
    }

    const contentType = response.headers.get('content-type');
    if (!contentType || !contentType.includes('application/json')) {
      // Se non è JSON, leggi il testo e lancia errore con contenuto
      return response.text().then(text => {
        throw new Error(`Risposta non JSON:\n${text}`);
      });
    }

    // Tutto ok, restituisci json
    return response.json();
  })
  .then(data => {
    console.log("Dati ricevuti:", data);

    const nomiUffici = data.map(e => e.nome);
    const utilizzi = data.map(e => e.numeroPrenotazioni);

    new Chart(document.getElementById('graficoUtilizzo'), {
      type: 'pie',
      data: {
        labels: nomiUffici,
        datasets: [{
          label: 'Prenotazioni',
          data: utilizzi,
          backgroundColor: [
            'rgba(255, 99, 132, 0.7)',
            'rgba(54, 162, 235, 0.7)',
            'rgba(255, 206, 86, 0.7)',
            'rgba(75, 192, 192, 0.7)',
            'rgba(153, 102, 255, 0.7)',
            'rgba(255, 159, 64, 0.7)'
          ],
          borderColor: 'white',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { position: 'bottom' },
          tooltip: {
            callbacks: {
              label: context => {
                const label = context.label || '';
                const value = context.parsed;
                return `${label}: ${value} prenotazioni`;
              }
            }
          }
        }
      }
    });
  })
  .catch(err => {
    console.error("Errore nel fetch del grafico:", err.message);
    // Qui puoi eventualmente mostrare un messaggio d'errore all'utente
  });
