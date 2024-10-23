<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro Hotel Bellmunt</title>
    <!--Estilos-->
    <link rel="stylesheet" href="css/registro.css">
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
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center">
        <div class="d-flex flex-column">
            <h1 class="text-center login-title">BELLMUNT <br>HOTEL</h1>
            <i class="fa-solid fa-user login-icon text-center"></i>
            </div>

        <form action="registro" method="post" class="login-form">
            <div class="registro-container d-flex flex-column">
                <label for="nombre" class="login-label">Nombre</label>
                <input type="text" id="nombre" name="nombre" class="login-input" placeholder="tu nombre...">

                <label for="email" class="login-label">Correo electrónico</label>
                <input type="text" id="email" name="email" class="login-input" placeholder="ejemplo@gmail.com">

                <label for="password" class="login-label">Contraseña</label>
                <input type="password" id="password" name="password" class="login-input" placeholder="ejemplo123">

                <button type="submit" value="enviar" class="login-button">Registrarse</button>
            </div>
        </form>

            <!-- Mostrar mensajes de error -->
        <%
        ArrayList<String> errores = (ArrayList<String>) request.getAttribute("errores");
        if (errores != null && !errores.isEmpty()) {
        %>
            <div class="alert alert-danger">
                <ul>
                    <% for (String error : errores) { %>
                        <li><%= error %></li>
                    <% } %>
                </ul>
            </div>
        <%
        }
        %>

        <a href="login" class="text-center login-info">¿Ya tienes una cuenta? Inicia sesión</a>
    </div>
</body>
</html>