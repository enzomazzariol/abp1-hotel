<%@ page import="model.Usuario" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mi Perfil - Bellmunt Hotel</title>


        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap JS (opcional para componentes interactivos como modales) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <style>
        body {
            margin: 0;
            background-color: #1E212D;
        }

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
            font-family: 'Merriweather', serif;
            font-size: 30px;
            font-weight: 900;
        }

        .navbar-option {
            color: #E78F81;
            font-family: 'Merriweather', serif;
            font-size: 25px;
            font-weight: 900;
        }

        .navbar-option-select {
            color: #ED3E22;
        }

        .navbar ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
        }

        .navbar ul li {
            margin-left: 20px;
        }

        .title {
            color: #E78F81;
            font-family: 'Merriweather', serif;
            font-size: 40px;
            font-weight: 900;
            text-align: center;
            margin-top: 20px;
        }

        /* Contenedor general del perfil */
        .profile-all-content {
            padding-right: 20px;
        }

        /* Estilo para cada sección de contenido */
        .profile-content {
            margin-bottom: 20px;
            display: flex;
            align-items: flex-start;
            flex-wrap: wrap;
            /* Permite que los valores se coloquen debajo */
        }

        /* Texto del perfil (Nombre, Correo Electrónico, Contraseña) */
        .profile-content-text {
            font-weight: bold;
            font-size: 1.2em;
            color: #E78F81;
            width: 100%;
            /* Hace que el texto ocupe todo el ancho disponible */
        }

        /* Valor del campo */
        .profile-content-value {
            font-size: 1em;
            color: #000000;
            width: 60%;
            /* Ajusta el ancho del valor */
            margin-top: 5px;
            margin-right: 20px;
            /* Espacio entre texto y valor */
            padding: 5px;
            background-color: #FAF3E0;
            border-radius: 5px;
        }

        /* Botón para editar cada campo */
        .btn-edit-field {
            background-color: transparent;
            border: 3px solid #28a745;
            color: #28a745;
            padding: 5px 10px;
            font-size: 0.9em;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            align-self: flex-end;
            /* Alinea el botón a la derecha */
            height: 34px;
        }

        .btn-edit-field:hover {
            background-color: #28a745;
            color: white;
        }

        /* Contenedor de la imagen de perfil */
        .profile-img {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .img {
            width: 250px;
            height: 250px;
            border-radius: 50%;
            overflow: hidden;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .profile-picture {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
        }

        /* Botón para cambiar la imagen de perfil */
        .btn-change-picture {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 15px;
            font-size: 0.9em;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 10px;
        }

        .btn-change-picture:hover {
            background-color: #0056b3;
        }

        .button-login-out {
            background-color: transparent;
            border: 3px solid #ED3E22;
            color: #ED3E22;
            padding: 5px 10px;
            font-size: 0.9em;
            border-radius: 5px;
            margin-right: 20px;
            cursor: pointer;
            transition: background-color 0.3s;
            align-self: flex-end;
            /* Alinea el botón a la derecha */
            height: 34px;
            width: 130px;
        }

        .button-login-out:hover {
            background-color: #ED3E22;
            color: white;
        }

        .button-admin {
            background-color: transparent;
            border: 3px solid #E78F81;
            color: #E78F81;
            padding: 5px 10px;
            font-size: 0.9em;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            align-self: flex-end;
            /* Alinea el botón a la derecha */
            height: 34px;
            width: 130px;
        }

        .button-admin:hover {
            background-color: #E78F81;
            color: white;
        }

        .button-change-img {
            background-color: transparent;
            border: 3px solid #0056b3;
            color: #0056b3;
            padding: 5px 10px;
            font-size: 0.9em;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            height: 34px;
            width: 130px;
        }

        .button-change-img:hover {
            background-color: #0056b3;
            color: white;
        }
        
    </style>

    <body>
        <!--Navbar-->
        <div class="navbar container-fluid">
            <div class="navbar-title">
                <a href="">Bellmunt Hotel</a>
            </div>
            <div class="navbar-option">
                <ul>
                    <li><a href="">Habitaciones</a></li>
                    <li><a href="">Actividades</a></li>
                    <li><a href="misReservas">Mis Reservas</a></li>
                    <li><a href="" class="navbar-option-select">Perfil</a></li>
                </ul>
            </div>
        </div>

        <!--Contenido-->
        <div class="row container-fluid ">
            <div class="title">BELLMUNT<br>HOTEL</div>
        </div>
        <div class="row container-fluid mt-5 mb-5">
            <div class="col-5 profile-img d-flex flex-column align-items-center">
                <% Usuario usuario=(Usuario) request.getAttribute("usuario"); %>
                    <div class="img">
                        <img src="<%= usuario.getImagen() %>" alt="Imagen de perfil"
                            class="profile-picture">
                    </div>
                    <button class="btn-change-picture mt-2 button-change-img" data-bs-toggle="modal" data-bs-target="#changeProfilePictureModal">Cambiar foto</button>
                    <input type="file" id="file-upload" accept="image/*" style="display:none;">
            </div>
            <div class="col-7 profile-all-content">
                <div class="profile-content">
                    <div class="profile-content-text">Nombre</div>
                    <div class="profile-content-value">
                        <span>
                            <%= usuario.getNombre() %>
                        </span>
                    </div>
                    <button class="btn-edit-field" data-bs-toggle="modal"
                        data-bs-target="#editNombreModal">Editar</button>
                </div>
                <div class="profile-content">
                    <div class="profile-content-text">Correo Electrónico</div>
                    <div class="profile-content-value">
                        <span>
                            <%= usuario.getEmail() %>
                        </span>
                    </div>
                    <button class="btn-edit-field" data-bs-toggle="modal"
                        data-bs-target="#editEmailModal">Editar</button>
                </div>
                <div class="profile-content">
                    <div class="profile-content-text">Contraseña</div>
                    <div class="profile-content-value">
                        <span>
                            <%= usuario.getPassword() %>

                        </span>
                    </div>
                    <button class="btn-edit-field" data-bs-toggle="modal"
                        data-bs-target="#editPasswordModal">Editar</button>
                </div>
                <div class="profile-content">
                    <a href=""><button class="button-login-out">Cerra sesion</button></a>
                    <% if (usuario.getRol().getNombre().equals("admin")) { %>
                        <a href=""><button class="button-admin">Admin</button></a>
                    <% } %>
                    
                </div>
            </div>
        </div>

        <!-- Modals -->
        <!-- Modal para Editar Nombre -->
        <div class="modal fade" id="editNombreModal" tabindex="-1" aria-labelledby="editNombreModalLabel"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="perfil" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editNombreModalLabel">Editar Nombre</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="action" value="actualizar">
                            <input type="hidden" name="id" value="<%= usuario.getId() %>">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nuevo Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre"
                                    placeholder="<%= usuario.getNombre() %>">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal para Editar Correo Electrónico -->
        <div class="modal fade" id="editEmailModal" tabindex="-1" aria-labelledby="editEmailModalLabel"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="perfil" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editEmailModalLabel">Editar Correo Electrónico</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="id" value="<%= usuario.getId() %>">
                            <div class="mb-3">
                                <input type="hidden" name="action" value="actualizar">
                                <label for="email" class="form-label">Nuevo Correo Electrónico</label>
                                <input type="email" class="form-control" id="email" name="email"
                                    placeholder="<%= usuario.getEmail() %>">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal para Editar Contraseña -->
        <div class="modal fade" id="editPasswordModal" tabindex="-1" aria-labelledby="editPasswordModalLabel"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="perfil" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editPasswordModalLabel">Editar Contraseña</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="id" value="<%= usuario.getId() %>">
                            <div class="mb-3">
                                <input type="hidden" name="action" value="actualizar">
                                <label for="password" class="form-label">Nueva Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password"
                                    placeholder="Nueva Contraseña">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal para Cambiar Imagen de Perfil -->
        <div class="modal fade" id="changeProfilePictureModal" tabindex="-1"
            aria-labelledby="changeProfilePictureModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="perfil" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="changeProfilePictureModalLabel">Cambiar Imagen de Perfil</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="id" value="<%= usuario.getId() %>">
                            <div class="mb-3">
                                <input type="hidden" name="action" value="actualizar">
                                <label for="imagen" class="form-label">URL de la Imagen</label>
                                <input type="url" class="form-control" id="imagen" name="imagen"
                                    placeholder="Ingresa la URL de la imagen" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </body>

    </html>