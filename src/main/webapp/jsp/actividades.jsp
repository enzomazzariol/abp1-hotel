<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Actividad" %>
<%@ page import="model.Usuario" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bellmunt Hotel - Actividades</title>
    <!--Estilos-->
    <link rel="stylesheet" href="css/actividades.css">
    <!--Bootstrap 5-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!--Fuente Tigtree-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <!-- SweetAlert CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
 </head>

            <body>
                <% Usuario usuario=(Usuario) session.getAttribute("usuario"); %>
                    <!--Navbar-->
                    <%@include file="/jsp/componentes/navbar.jsp" %>
                <div class="container p-5">
                        <!-- Título principal -->
                        <div class="main-title my-5">Nuestras Actividades</div>

                        <!-- Contenedor de actividades -->
                        <% ArrayList<Actividad> actividades = (ArrayList<Actividad>)
                                request.getAttribute("actividades");
                                for (Actividad actividad : actividades) {
                                %>
                                <div class="activity-container">
                                    <div class="activity-card">
                                        <img src="img/actividades/<%= actividad.getImagen() %>" 
                                            alt="Imagen de la actividades" class="imagen">
                                        <div class="activity-info">
                                            <h2 class="actividad-nombre mb-2">
                                                <%= actividad.getNombre_actividad() %>
                                            </h2>
                                            <div class="d-flex">
                                                <p class="actividad-precio">
                                                    <%= actividad.getPrecio() %>€ 
                                                </p>
                                                <span class="actividad-cupos ms-5 mt-2">Cupos disponibles: <%= actividad.getCupo() %></span>
                                            </div>
                                            <p class="actividad-descripcion">
                                                <%= actividad.getDescripcion() %>
                                            </p>
                                            <p class="actividad-fecha">
                                                Fecha de la actividad: <%= actividad.getFecha_actividad() %>
                                            </p>
                                            <div class="booking-form">
                                                <form action="actividades" method="post" onsubmit="return mostrarAlertaActividad(event, this)">
                                                    <input type="hidden" name="action"
                                                        value="reservar">
                                                    <input type="hidden" name="id_usuario"
                                                        value="<%= usuario.getId() %>">
                                                    <input type="hidden" name="id_actividad"
                                                        value="<%= actividad.getId() %>">
                                                        <input type="hidden" name="estado"
                                                        value="reservado">
                                                    <br>
                                                    <button type="submit" class="mb-2 btn <%= actividad.getCupo() <= 0 ? "disabled" : "" %>">RESERVAR</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <% } %>
                </div>
    <!-- SweetAlert JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>
    <script>
        // Función SweetAlert para confirmar la reserva de actividad
function mostrarAlertaActividad(event, form) {
    event.preventDefault();

    const actividadNombre = form.closest('.activity-card').querySelector('.actividad-nombre').innerText;
    const mensaje = `¿Quieres realizar una reserva para la actividad "${actividadNombre}"?`;

    // Mostrar SweetAlert con el mensaje personalizado
    Swal.fire({
        title: 'Reserva de Actividad',
        text: mensaje,
        icon: 'info',
        showCancelButton: true,
        confirmButtonText: 'Reservar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            form.submit(); 
        }
    });
    return false;
}
    </script>
            </html>