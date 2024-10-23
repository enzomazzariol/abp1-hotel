<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Usuario" %>
<%@ page import="model.Habitacion" %>
<%@ page import="model.Actividad" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utils.Rol" %>
<%@ page import="utils.Estado" %>
<%@ page import="utils.TipoHabitacion" %>
<%@ page import="model.ReservaGeneral" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Admin Hotel Bellmunt</title>
    <!--Estilos-->
    <link rel="stylesheet" href="css/cssAdmin/admin.css">
    <!--Bootstrap 5-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!--Fuente Tigtree-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <!--Font awesome-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
     <!-- Incluir el CSS y JS de Awesome Alerts (SweetAlert2) -->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
    
</head>
<body>
    <%@include file="/jsp/admin/adminNavbar.jsp"%>

    <%@include file="/jsp/admin/adminReservas.jsp"%>
          
    <!-- Contenido dinámico -->
    <section class="container p-5 listas-container">
        
        <%@include file="/jsp/admin/adminGestionUsuarios.jsp"%>

        <%@include file="/jsp/admin/adminGestionHabitaciones.jsp"%>

        <%@include file="/jsp/admin/adminGestionActividades.jsp"%>

        <%@include file="/jsp/admin/adminOculto.jsp"%>
    </section>

    <!--Bootstrap 5 JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Scripts -->
    <script>
        function showSection(sectionId, navId) {
            // Ocultar todas las secciones
            let sections = document.getElementsByClassName('content-section');
            for (let i = 0; i < sections.length; i++) {
                sections[i].style.display = 'none';
            }

            // Mostrar la sección seleccionada
            document.getElementById(sectionId).style.display = 'block';

             // resetear color a el navbar
            let navLinks = document.getElementsByClassName('admin-navbar-link');
            for (let i = 0; i < navLinks.length; i++) {
                navLinks[i].style.color = "";
            }

            // Añadir el color al elemento del navbar
            document.getElementById(navId).style.color = "#E78F81";
        }

    </script>
</body>
</html>