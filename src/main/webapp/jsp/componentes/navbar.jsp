<head>
    <!--Estilos-->
    <link rel="stylesheet" href="css/navbar.css">
 </head>
<!-- Navbar -->
<div class="navbar">
    <div class="navbar-title">
        <a href="" class="nav-title">Bellmunt Hotel</a>
    </div>
    <div class="navbar-option">
        <ul>
            <li class="me-3"><a href="habitacion" class="nav-link">Habitaciones</a></li>
            <li class="me-3"><a href="actividades" class="nav-link">Actividades</a></li>
            <li class="me-3"><a href="misReservas" class="nav-link">Mis Reservas</a></li>
            <li class="me-5"><a href="perfil" class="nav-link">Perfil</a></li>
        </ul>
    </div>
</div>

<script>
    // JavaScript para subrayar el enlace activo
    document.querySelectorAll('.nav-link').forEach(link => {
        if (link.href === window.location.href) {
            link.classList.add('navbar-option-select');
        }
    });
</script>
