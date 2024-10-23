<%@ page import="java.util.ArrayList" %>
    <%@ page import="model.Habitacion" %>
        <%@ page import="model.Actividad" %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>Bellmunt Hotel</title>

                <!-- Bootstrap CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Bootstrap JS (opcional para componentes interactivos como modales) -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

                <!-- Iconos Awesome -->
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
                    rel="stylesheet">


                <!--Fuentes para las letras-->
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Seymour+One&display=swap" rel="stylesheet">
                <link href="https://fonts.googleapis.com/css2?family=Sigmar+One&display=swap" rel="stylesheet">
                <link
                    href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Ribeye+Marrow&family=Seymour+One&display=swap"
                    rel="stylesheet">

                <style>
                    /* Eliminación del subrayado en los enlaces y color heredado */
                    a {
                        text-decoration: none;
                        color: inherit;
                    }

                    /* Fondo para la sección del navbar del home */
                    .navbar-background {
                        position: relative;
                        background-image: url('img/Imagen_portada_home.jpg');
                        background-size: cover;
                        background-repeat: no-repeat;
                        background-position: center;
                        color: #E78F81;
                        font-family: 'Merriweather', serif;
                        font-weight: 900;
                        padding: 20px;
                        height: 350px;

                    }

                    /* Capa oscura sobre el fondo de la imagen del navbar */
                    .navbar-background::before {
                        content: "";
                        position: absolute;
                        top: 0;
                        left: 0;
                        right: 0;
                        bottom: 0;
                        background-color: rgba(0, 0, 0, 0.5);
                        z-index: 1;
                    }

                    /* Estilo para los títulos y enlaces del navbar */
                    .navbar-title,
                    .navbar-link {
                        position: relative;
                        z-index: 2;
                    }

                    .navbar-title {
                        font-size: 50px;
                    }

                    .navbar-link {
                        font-size: 30px;
                    }

                    /* Estilo para los iconos en el navbar */
                    .navbar-icon {
                        font-size: 40px;
                        color: #E78F81;
                        margin-top: 20px;
                        position: relative;
                        z-index: 2;
                    }

                    /* Fondo claro para la sección de información */
                    .info-background {
                        background-color: #FAF3E0;
                        padding: 50px;
                    }

                    /* Lista horizontal con iconos */
                    .icon-list {
                        list-style-type: none;
                        padding: 0;
                        margin-top: 20px;
                        display: flex;
                        justify-content: center;
                    }

                    .icon-list-item {
                        text-align: center;
                        margin: 0 30px;
                        align-items: center;
                        margin-left: 60px;
                    }

                    .icon-list-item i {
                        font-size: 30px;
                        display: block;
                    }

                    /* Estilo para los títulos y enlaces del navbar */

                    .info-contenido {
                        padding-top: 50px;
                    }

                    .info-title {
                        font-family: 'Sigmar One', regular;
                        font-size: 30px;
                    }

                    .info-datos {
                        margin-right: 20px;
                        font-family: 'Merriweather', serif;
                        font-size: 15px;
                        font-weight: 900;
                    }

                    .info-color {
                        color: #E78F81;
                    }

                    /* Estilo para las imágenes de la sección de información */
                    .info-image {
                        height: 20vw;
                    }

                    /* Fondo para el apartado ubicación */
                    .ubi-background {
                        background-color: #1E212D;
                        padding: 50px
                    }

                    .map-container {
                        max-width: 950px;
                        /* Establece un ancho máximo para el contenedor */
                        margin: 0 auto;
                        /* Centra el contenedor horizontalmente */
                        height: 300px;
                        /* Altura del mapa */
                        overflow: hidden;
                        /* Asegura que no se muestre contenido fuera del contenedor */
                        position: relative;
                        /* Necesario para el posicionamiento absoluto del iframe */
                        padding: 50px;
                    }

                    iframe {
                        position: absolute;
                        /* Posiciona el iframe dentro del contenedor */
                        top: 0;
                        /* Ajusta la posición vertical al inicio */
                        left: 0;
                        /* Ajusta la posición horizontal al inicio */
                        width: 100%;
                        /* Ajusta el ancho al 100% del contenedor */
                        height: 100%;
                        /* Ajusta la altura al 100% del contenedor */
                        border: 0;
                        /* Elimina el borde del iframe */
                        border-radius: 20px;
                        /* Bordes redondeados */
                        /* Elimina el margen superior para que el mapa se ajuste completamente */
                    }

                    .ubi-title {
                        font-family: "Seymour One", sans-serif;
                        font-size: 30px;
                        font-weight: 900;
                        color: white;

                    }

                    .ubi-datos {
                        font-family: "Montserrat Alternates", sans-serif;
                        font-size: 20px;
                        font-weight: 900;
                        color: #E78F81;
                        text-align: center;
                        padding: 30px;
                    }

                    /*  Fondo Cards */
                    .card-background {
                        background-color: #EAD7BB;
                        padding: 50px;
                    }

                    /* Crad */
                    .card {
                        background-color: #FFFBF5;
                        border-radius: 5px;
                        margin: 30px;
                        padding: 20px;
                        width: 50vb;
                    }

                    .card-img {
                        position: relative;
                        width: 100%;
                        height: 200px;
                        /* Puedes ajustar la altura según tus necesidades */
                        overflow: hidden;
                    }

                    .card-img img {
                        width: 100%;
                        height: 100%;
                        object-fit: cover;
                        /* Asegura que la imagen cubra todo el contenedor sin distorsionarse */
                    }

                    .card-body {
                        justify-content: center;
                    }

                    .card-title-first {
                        font-family: "Ribeye Marrow", serif;
                        font-size: 30px;
                        font-weight: 900;
                        color: black;
                    }

                    .card-title {
                        font-family: "Montserrat Alternates", sans-serif;
                        font-size: 17px;
                        font-weight: 900;
                        color:#E78F81;
                    }

                    .card-price {
                        font-family: "Montserrat Alternates", sans-serif;
                        font-size: 15px;
                        font-weight: 600;
                        color:#E78F81;
                    }

                    .card-button {
                        background-color: transparent;
                        color: #E78F81;
                        border-radius: 10px;
                        padding: 5px;
                        width: 100px;
                        border: solid 3px #E78F81;
                    }

                    .card-button:hover {
                        background-color: #E78F81;
                        color: white;
                    }

                    /* Media Queries para ajustar tamaños en pantallas pequeñas */
                    @media (max-width: 1200px) {
                        .navbar-title {
                            font-size: 40px;
                        }

                        .navbar-link {
                            font-size: 25px;
                        }

                        .navbar-icon {
                            font-size: 35px;
                        }

                        .icon-list-item i {
                            font-size: 28px;
                        }
                    }

                    @media (max-width: 992px) {
                        .navbar-title {
                            font-size: 35px;
                        }

                        .navbar-link {
                            font-size: 25px;
                        }

                        .navbar-icon {
                            font-size: 35px;
                        }

                        .icon-list-item i {
                            font-size: 20px;
                        }

                        .icon-list-item span {
                            font-size: 20px;
                        }
                    }

                    @media (max-width: 768px) {
                        .navbar-title {
                            font-size: 30px;
                        }

                        .navbar-link {
                            font-size: 20px;
                        }

                        .navbar-icon {
                            font-size: 30px;
                        }

                        .icon-list-item i {
                            font-size: 15px;
                        }

                        .icon-list-item span {
                            font-size: 15px;
                        }
                    }

                    @media (max-width: 500px) {
                        .navbar-title {
                            font-size: 25px;
                        }

                        .navbar-link {
                            font-size: 15px;
                        }

                        .navbar-icon {
                            font-size: 25px;
                        }

                        .icon-list-item i {
                            font-size: 10px;
                        }

                        .icon-list-item span {
                            font-size: 10px;
                        }
                    }
                </style>
            </head>

            <body>
                <!--Navbar del home-->
                <div class="container-fluid navbar-background text-center align-content-center">
                    <div class="row">
                        <p class="navbar-title animate__animated animate__zoomInDown animate__slow">BELLMUNT</p>
                    </div>
                    <div class="row">
                        <div class="col-3">
                            <a href="#informacion" class="navbar-link">Informacion</a>
                        </div>
                        <div class="col-3">
                            <a href="#habitaciones" class="navbar-link">Habitaciones</a>
                        </div>
                        <div class="col-3">
                            <a href="#actividades" class="navbar-link">Actividades</a>
                        </div>
                        <div class="col-3">
                            <a href="jsp/login.jsp" class="navbar-link">Login</a>
                        </div>
                    </div>
                    <div class="row">
                        <a href="jsp/login.jsp"><i class="fa-regular fa-user navbar-icon"></i></a>
                    </div>
                </div>

                <!--Sección de información-->
                <div id="informacion" class="container-fluid info-background">
                    <div class="row">
                        <ul class="icon-list">
                            <li class="icon-list-item">
                                <i class="fas fa-plane"></i>
                                <span>Traslado al <br>Aeropuerto</span>
                            </li>
                            <li class="icon-list-item">
                                <i class="fa-solid fa-wifi"></i>
                                <span>WiFi Gratis</span>
                            </li>
                            <li class="icon-list-item">
                                <i class="fa-solid fa-utensils"></i>
                                <span>Restaurantes</span>
                            </li>
                            <li class="icon-list-item">
                                <i class="fa-solid fa-car"></i>
                                <span>Parking Gratis</span>
                            </li>
                            <li class="icon-list-item">
                                <i class="fas fa-broom"></i>
                                <span>Servicio de <br>Limpieza</span>
                            </li>
                        </ul>
                    </div>
                    <div class="row info-contenido">
                        <div class="col-12 col-md-6 d-flex justify-content-center align-items-center">
                            <img class="info-image" src="img/habitaciones/Habitacion_sencilla.jpg" alt="Habitacion_sencilla">
                        </div>
                        <div class="col-12 col-md-6 info-color mt-3">
                            <div class="">
                                <a class="info-title">Sobre Nosotros</a>
                            </div>
                            <div class="info-datos mt-2">
                                <p>Bienvenido a Bellmunt Hotel, el nuevo destino moderno en Cala Blava, Mallorca.
                                    <br><br>Disfruta de un entorno exclusivo, a solo pasos de las aguas cristalinas del
                                    Mediterráneo. Nuestro hotel combina diseño contemporáneo con la serenidad de la
                                    costa,
                                    ofreciendo habitaciones elegantes y comodidades de lujo.
                                    <br><br>En Bellmunt, cada rincón está pensado para brindarte una experiencia
                                    inolvidable.
                                    Relájate en nuestras instalaciones y déjate cautivar por el encanto de Mallorca.
                                    <br><br>Disfrute de una estancia inolvidable, donde cada momento está pensado para
                                    que
                                    se sienta
                                    como en casa, pero con un toque de sofisticación y elegancia.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Ubicación-->
                <div class="container-fluid ubi-background">
                    <div class="row mb-5">
                        <div class="text-center">
                            <a class="ubi-title">¿Donde estamos?</a>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="map-container">
                            <iframe
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1411.3789786250604!2d2.7345917767809995!3d39.48350675310495!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x129797083ac9efdd%3A0xd7b9e396528f818c!2s07609%20Cala%20Blava%2C%20Illes%20Balears!5e1!3m2!1ses!2ses!4v1729175299361!5m2!1ses!2ses"
                                allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12 info-color mt-3">
                            <div class="ubi-datos">
                                <p>Cala Blava, situada en la costa sur de Mallorca, es un rincón tranquilo con aguas
                                    turquesas y
                                    acantilados bajos. Ideal para escapar del bullicio, disfrutar del sol y practicar
                                    snorkeling en
                                    un entorno natural. A pocos minutos de Palma, es el destino perfecto para relajarse
                                    y
                                    admirar la
                                    belleza del Mediterráneo.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Cards-->
                <div class="container-fluid card-background">

                    <!--Cards Habitaciones-->
                    <div class="row">
                        <div id="habitaciones" class="row justify-content-center card-title-first">
                            Habitaciones
                        </div>
                        <div class="row justify-content-center">
                            <% ArrayList<Habitacion> habitaciones = (ArrayList<Habitacion>)
                                    request.getAttribute("habitaciones");
                                    if(habitaciones != null) {
                                    for (Habitacion habitacion: habitaciones) {
                                    %>
                                    <div class="card col-4">
                                        <div class="card-img">
                                            <img src="img/habitaciones/<%= habitacion.getImagen() %>"
                                                alt="Imagen de la habitacion" class="">
                                        </div>
                                        <div class="card-body row">
                                            <div class="col-7">
                                                <div class="card-title">
                                                    <%= habitacion.getTipoHabitacion() %>
                                                </div>
                                                <div class="card-price">
                                                    <%= habitacion.getPrecio() %> €
                                                </div>
                                            </div>
                                            <div class="col-5 d-flex justify-content-end align-items-center">
                                                <button class="card-button"
                                                    onclick="alert('¡Botón presionado!')">Reservar</button>
                                            </div>
                                        </div>
                                    </div>
                                    <% } } else { %>
                                        <div class="col-12">
                                            <p>No hay habitaciones disponibles.</p>
                                        </div>
                                        <% } %>
                        </div>
                    </div>

                    <hr>

                    <!--Cards Actividades-->
                    <div class="row mt-2">
                        <div id="actividades" class="row justify-content-center card-title-first">
                            Actividades
                        </div>
                        <div class="row justify-content-center">
                            <% ArrayList<Actividad> actividades = (ArrayList<Actividad>)
                                    request.getAttribute("actividades");
                                    if(actividades != null) {
                                    for (Actividad actividad: actividades) {
                                    %>
                                    <div class="card col-4">
                                        <div class="card-img">
                                            <img src="img/actividades/<%= actividad.getImagen() %>" alt="Imagen de la actividades"
                                                class="imagen">
                                        </div>
                                        <div class="card-body row">
                                            <div class="col-7">
                                                <div class="card-title">
                                                    <%= actividad.getNombre_actividad() %>
                                                </div>
                                                <div class="card-price">
                                                    <%= actividad.getPrecio() %> €
                                                </div>
                                            </div>
                                            <div class="col-5 d-flex justify-content-end align-items-center">
                                                <button class="card-button"
                                                    onclick="alert('¡Botón presionado!')">Reservar</button>
                                            </div>
                                        </div>
                                    </div>
                                    <% } } else { %>
                                        <div class="col-12">
                                            <p>No hay actividades disponibles.</p>
                                        </div>
                                        <% } %>
                        </div>
                    </div>
                </div>

                <!--Footer-->
                <%@include file="/jsp/componentes/footer.jsp" %>

            </body>

            </html>