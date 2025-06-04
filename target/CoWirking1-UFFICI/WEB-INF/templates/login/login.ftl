<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Accedi o Registrati</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/login.css">
</head>
<body class="text-dark">

<!-- NAVBAR -->
 <#include "/navbar/navbar1.ftl">

<!-- CONTENUTO -->
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow p-4">
                <h3 id="form-title" class="mb-4 text-danger text-center">
                    <i class="fas fa-sign-in-alt"></i> Accedi
                </h3>

                <!-- FORM LOGIN -->
                <form id="login-form" action="${ctx}/login" method="post">
                    <div class="mb-3">
                        <label for="login-email" class="form-label">Email</label>
                        <input name="email" type="email" class="form-control" id="login-email" required>
                    </div>
                    <div class="mb-3">
                        <label for="login-password" class="form-label">Password</label>
                        <input name="password" type="password" class="form-control" id="login-password" required>
                    </div>
                    <button type="submit" class="btn btn-danger w-100">Accedi</button>
                </form>

                <!-- FORM REGISTRAZIONE (nascosto inizialmente) -->
                <form id="register-form" style="display: none;" action="${ctx}/register" method="post">
                    <div class="mb-3">
                        <label for="reg-nome" class="form-label">Nome</label>
                        <input name="name" type="text" class="form-control" id="reg-nome" required>
                    </div>
                    <div class="mb-3">
                        <label for="reg-cognome" class="form-label">Cognome</label>
                        <input name="surname" type="text" class="form-control" id="reg-cognome" required>
                    </div>
                    <div class="mb-3">
                        <label for="reg-data-nascita" class="form-label">Data di Nascita</label>
                        <input name="date" type="date" class="form-control" id="reg-data-nascita" required>
                    </div>
                    <div class="mb-3">
                        <label for="reg-telefono" class="form-label">Telefono</label>
                        <input name="phone" type="tel" class="form-control" id="reg-telefono" required>
                    </div>
                    <div class="mb-3">
                        <label for="reg-email" class="form-label">Email</label>
                        <input name="email" type="email" class="form-control" id="reg-email" required>
                    </div>
                    <div class="mb-3">
                        <label for="reg-password" class="form-label">Password</label>
                        <input name="password" type="password" class="form-control" id="reg-password" required>
                    </div>

                    <!-- Switch locatore -->
                    <div class="form-check form-switch mb-3">
                        <input class="form-check-input"  type="checkbox" id="isLocatoreSwitch">
                        <label class="form-check-label" for="isLocatoreSwitch">Sei un locatore?</label>
                    </div>
                    <!-- Hidden input per user type -->
                    <input type="hidden" name="userType" id="userTypeInput" value="UTENTE">
                    <!-- Campo Partita IVA -->
                    <div class="mb-3" id="partitaIvaField" style="display: none;">
                        <label for="reg-partita-iva" class="form-label">Partita IVA</label>
                        <input name="piva" type="text" class="form-control" id="reg-partita-iva">
                    </div>

                    <button type="submit" class="btn btn-danger w-100">Registrati</button>
                </form>


                <!-- Toggle tra Login/Registrazione -->
                <div class="text-center mt-4">
                    <p id="toggle-text">
                        Non hai un account?
                        <a href="#" id="toggle-link">Registrati</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<#-- FOOTER -->
<#include "/navbar/footer.ftl">

<!-- SCRIPT -->
<script>

    // Switch login <-> registrazione
    document.addEventListener("DOMContentLoaded", () => {
        const loginForm = document.getElementById("login-form");
        const registerForm = document.getElementById("register-form");
        const formTitle = document.getElementById("form-title");
        let isLogin = true;

        const handleToggle = (e) => {
            e.preventDefault();

            isLogin = !isLogin;

            if (isLogin) {
                loginForm.style.display = "block";
                registerForm.style.display = "none";
                formTitle.innerHTML = '<i class="fas fa-sign-in-alt"></i> Accedi';
                toggleText.innerHTML = 'Non hai un account? <a href="#" id="toggle-link">Registrati</a>';
            } else {
                loginForm.style.display = "none";
                registerForm.style.display = "block";
                formTitle.innerHTML = '<i class="fas fa-user-plus"></i> Registrati';
                toggleText.innerHTML = 'Hai già un account? <a href="#" id="toggle-link">Accedi</a>';
            }

            // Riattacca il listener al nuovo link appena creato
            document.getElementById("toggle-link").addEventListener("click", handleToggle);
        };

        // Attiva primo listener
        document.getElementById("toggle-link").addEventListener("click", handleToggle);
    });
</script>
<script>
    // Mostra/nascondi campo Partita IVA
    document.addEventListener("DOMContentLoaded", () => {
        const locatoreSwitch = document.getElementById("isLocatoreSwitch");
        const partitaIvaField = document.getElementById("partitaIvaField");

        locatoreSwitch.addEventListener("change", () => {
            const isLocatore = locatoreSwitch.checked;
            partitaIvaField.style.display = locatoreSwitch.checked ? "block" : "none";
            userTypeInput.value = isLocatore ? "LOCATORE" : "UTENTE";
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

