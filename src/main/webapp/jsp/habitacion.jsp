<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Habitacion" %>
<%@ page import="model.Usuario" %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Bellmunt Hotel - Actividades</title>
                <link href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap" rel="stylesheet">
                <!--Estilos-->
                <link rel="stylesheet" href="css/actividades.css">
                <link rel="stylesheet" href="css/habitaciones.css">
                <!--Bootstrap 5-->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap"
                    rel="stylesheet">
            </head>

            <body>
                <% Usuario usuario=(Usuario) session.getAttribute("usuario"); %>
                    <!--Navbar-->
                    <%@include file="/jsp/componentes/navbar.jsp" %>
                    <div class="container p-5">
                        <!-- Título principal -->
                        <div class="main-title my-5">Nuestras Habitaciones</div>

                        <!-- Contenedor de habitaciones -->
                        <div class="container">
                            <% ArrayList<Habitacion> habitaciones = (ArrayList<Habitacion>)
                                    request.getAttribute("habitaciones");
                                    for (Habitacion habitacion : habitaciones) {
                                    %>
                                    <div class="activity-container">
                                        <div class="activity-card">
                                            <img src="img/habitaciones/<%= habitacion.getImagen() %>"
                                                alt="Imagen de la habitación" class="imagen">
                                            <div class="activity-info">
                                                <h2 class="habitacion-tipo">
                                                    <%= habitacion.getTipoHabitacion() %>
                                                </h2>
                                                <span class="actividad-precio">
                                                    <%= habitacion.getPrecio() %>€
                                                </span>
                                                <p class="habitacion-estado">
                                                    Estado: <%= habitacion.getEstado() %>
                                                </p>
                                                <div class="booking-form">
                                                    <form action="habitacion" method="post" onsubmit="return mostrarAlertaHabitacion(event, this)">
                                                        <input type="hidden" name="action" value="reservar">
                                                        <input type="hidden" name="idHabitacion"
                                                            value="<%= habitacion.getId() %>">
                                                        <input type="hidden" name="idUsuario"
                                                            value="<%= usuario.getId() %>">
                                                        <input type="hidden" name="estado" value="reservado">
                                                        <div class="form-row d-flex">
                                                            <div class="d-flex flex-column w-100 me-2">
                                                                <label class="habitacion-label mb-1">Desde:</label>
                                                                <input type="date" class="form-control habitacion-input"
                                                                name="fechaEntrada" placeholder="Desde" id="fechaEntrada<%= habitacion.getId() %>" required>    
                                                            </div>
                                                            <div class="d-flex flex-column w-100">  
                                                                <label class="habitacion-label mb-1">Hasta:</label>                                                                                          
                                                                <input type="date" class="form-control habitacion-input"
                                                                name="fechaSalida" placeholder="Hasta" id="fechaSalida<%= habitacion.getId() %>" required>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <button type="submit">RESERVAR</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <% } %>
                        </div>
                    </div>
     <!-- SweetAlert JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>
        // Configura las restricciones de fecha
        function setDateRestrictions(id) {
            const fechaEntrada = document.getElementById("fechaEntrada" + id);
            const fechaSalida = document.getElementById("fechaSalida" + id);
            const today = new Date().toISOString().split("T")[0];
            
            fechaEntrada.min = today;

            fechaEntrada.addEventListener("change", function() {
                fechaSalida.min = fechaEntrada.value;
            });
        }

        // Inicializa restricciones en cada habitación
        window.onload = function() {
            <% for (Habitacion habitacion : habitaciones) { %>
                setDateRestrictions(<%= habitacion.getId() %>);
            <% } %>
        };

        // Función SweetAlert para confirmar la reserva
function mostrarAlertaHabitacion(event, form) {
    event.preventDefault();

    // Obtener los valores de fecha de entrada y salida
    const fechaEntrada = form.querySelector('[name="fechaEntrada"]').value;
    const fechaSalida = form.querySelector('[name="fechaSalida"]').value;

    // Verifica que ambos campos de fecha tengan valor antes de mostrar la alerta
    if (!fechaEntrada || !fechaSalida) {
        Swal.fire({
            title: 'Error',
            text: 'Por favor selecciona ambas fechas: entrada y salida.',
            icon: 'warning',
            confirmButtonText: 'OK'
        });
        return false;
    }

    // Mostrar SweetAlert con las fechas seleccionadas
    Swal.fire({
        title: 'Confirmar Reserva',
        text: `¿Quieres realizar una reserva para esta habitación desde el ${fechaEntrada} hasta el ${fechaSalida}?`,
        icon: 'info',
        showCancelButton: true,
        confirmButtonText: 'Reservar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            form.submit(); // Envía el formulario solo si se confirma
        }
    });
    return false;
}
    </script>
            </html>