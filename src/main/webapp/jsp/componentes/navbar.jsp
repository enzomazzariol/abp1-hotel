<!-- Navbar -->
<style>
    /* Eliminación del subrayado en los enlaces y color heredado */
    a {
        text-decoration: none;
        color: inherit;
    }

    /* Navbar */
    .navbar {
        background-color: #FAF3E0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 15px;
    }

    .navbar-title {
        color: #E78F81;
        font-family: 'Figtree', serif;
        font-size: 26px;
        font-weight: 900;
    }

    .navbar-option {
        color: #E78F81;
        font-family: 'Figtree', serif;
        font-size: 20px;
        font-weight: 900;
    }

    .navbar-option-select {
        color: #ED3E22;
        border-bottom: 2px solid #ED3E22;
    }

    .navbar ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        display: flex;
    }

    .navbar ul li {
        margin-left: 30px;
    }
</style>

<div class="navbar container-fluid">
    <div class="navbar-title">
        <a href="">Bellmunt Hotel</a>
    </div>
    <div class="navbar-option">
        <ul>
            <li><a href="habitaciones" class="nav-link">Habitaciones</a></li>
            <li><a href="actividades" class="nav-link">Actividades</a></li>
            <li><a href="misReservas" class="nav-link">Mis Reservas</a></li>
            <li class="me-3"><a href="perfil" class="nav-link">Perfil</a></li>
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
