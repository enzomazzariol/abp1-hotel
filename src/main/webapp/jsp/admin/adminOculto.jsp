<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login Secreto</title>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div id="oculto" class="content-section" style="display:none;">
    <div class="d-flex flex-column justify-content-center align-items-center">
        <h2 class="text-center mb-5">Sección oculta</h2>
        <button type="button" class="btn btn-outline-danger p-3" data-bs-toggle="modal" data-bs-target="#loginSecreto">Haz login primero</button>
    </div>
</div>

<!-- Modal de login secreto -->
<div class="modal fade" id="loginSecreto" tabindex="-1" aria-labelledby="loginSecretoLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="loginSecretoLabel">Bienvenido a la sección oculta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="loginForm" name="form" action="">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre de super admin</label>
                        <input type="text" class="form-control" id="nombre" placeholder="nombre del usuario" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Contraseña secreta</label>
                        <input type="password" class="form-control" id="password" placeholder="tu contraseña secreta" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-outline-primary" onclick="verificarLogin()">Entrar</button> <!-- Cambiado a type="submit" -->
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<section class="seccion-oculta-container container" id="seccion-oculta" style="display:none;">
    <h2>Aquí va el contenido de la sección</h2>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.getElementById('loginForm').addEventListener('submit', verificarLogin); // Mover el evento aquí

function verificarLogin() {
    event.preventDefault(); // Evita el envío del formulario y la recarga de la página
    console.log("Función verificarLogin llamada"); // Verificar si la función se llama
    
    // Obtener valores de los campos de texto
    var nombre = document.getElementById('nombre').value; // Sin trim()
    var password = document.getElementById('password').value; // Sin trim()

    // Imprimir valores
    console.log("Nombre:", nombre, "Contraseña:", password); 
    
    // Verificar las credenciales
    if ((nombre === 'enzo' || nombre === 'santi' || nombre === 'luis') && password === 'marketing') {
        // Alerta de éxito
        Swal.fire({
            title: 'Bienvenido de vuelta super admin',
            text: '',
            icon: 'success',
            confirmButtonText: 'Continuar'
        }).then((result) => {
            if (result.isConfirmed) {
                const seccionOculta = document.getElementById('seccion-oculta');
                seccionOculta.style.display = "block";  // Mostrar la sección oculta
                const modal = bootstrap.Modal.getInstance(document.getElementById('loginSecreto')); // Cerrar modal
                modal.hide();
            }
        });
    } else {
        // Alerta de error
        Swal.fire({
            title: 'Error',
            text: 'No eres el super admin. Vuelve más tarde',
            icon: 'error',
            confirmButtonText: 'Cerrar'
        });
    }
}
</script>

</body>
</html>
