<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Admin Hotel Bellmunt</title>
    <!--Estilos-->
    <link rel="stylesheet" href="css\cssAdmin\adminNavbar.css">
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
    <!--Navbar-->
    <nav class="admin-navbar d-flex justify-content-between p-4">
        <div class="admin-logo-container">
            <a href="#" class="admin-logo">Panel Admin</a>
        </div>
        <ul class="admin-navbar-container d-flex">
            <li class="me-5"><a href="#" class="admin-navbar-link usuarios" id="nav-usuarios" onclick="showSection('usuarios', 'nav-usuarios')">Usuarios</a></li>
            <li class="me-5"><a href="#" class="admin-navbar-link habitaciones" id="nav-habitaciones" onclick="showSection('habitaciones', 'nav-habitaciones')">Habitaciones</a></li>
            <li class="me-5"><a href="#" class="admin-navbar-link actividades" id="nav-actividades" onclick="showSection('actividades', 'nav-actividades')">Actividades</a></li>
        </ul>
    </nav>

    <!--Hero-->
    <section class="hero p-5">
        <div class="row d-flex justify-content-center align-items-center m-1">
            <div class="col-md-1 col-sm-12 col-12 col-1">
                <i class="fa-solid fa-user-tie hero-icon"></i>
            </div>

            <div class="col-md-9 col-sm-12 col-12 col-9">
                <h3 class="hero-title">Admin</h3>
                <p class="hero-description">Administrador de Bellmunt Hotel</p>
                <p class="hero-insignia">
                    <a href="#" class="hero-insignia-link" id="nav-superAdmin" onclick="showSection('oculto', 'nav-superAdmin')">Super admin</a>
                </p>
            </div>

            <div class="col-md-2 col-sm-12 col-12 col-2 d-flex flex-column">
                <a href="home" class="hero-button cerrar-sesion mb-4">Cerrar sesion</a>
                <a href="perfil" class="hero-button volver">Volver</a>
            </div>
        </div>
    </section>
</body>
</html>