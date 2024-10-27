<%@ page import="java.util.ArrayList" %>
    <%@ page import="model.Actividad" %>
        <%@ page import="model.Usuario" %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Bellmunt Hotel - Actividades</title>
                <link href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap" rel="stylesheet">

                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap"
                    rel="stylesheet">
            </head>

            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #1E212D !important;
                }

                /* Título principal */
                .main-title {
                    color: #E78F81;
                    font-family: 'Figtree', serif;
                    font-size: 40px;
                    font-weight: 900;
                    text-align: center;
                    margin-top: 20px;
                    line-height: 40px;
                    margin-bottom: 20px;
                }

                /* Estilo de las actividades */
                .activity-container {
                    display: flex;
                    justify-content: center;
                    margin-bottom: 40px;
                }

                .activity-card {
                    background-color: #E78F81;
                    padding: 30px;
                    border-radius: 15px;
                    width: 800px;
                    display: flex;
                    gap: 20px;
                }

                .activity-card img {
                    border-radius: 15px;
                    width: 300px;
                    height: auto;
                }

                .activity-info {
                    width: 100%;
                    display: flex;
                    flex-direction: column;
                    justify-content: space-between;
                }

                .activity-info h2 {
                    color: #1E212D;
                    font-family: 'Figtree', serif;
                    font-weight: 900;
                    font-size: 24px;
                    margin: 0;
                }

                .activity-info p {
                    color: #1E212D;
                    font-family: 'Figtree', serif;
                    font-weight: 900;
                    font-size: 16px;
                    margin: 10px 0;
                }

                .price {
                    color: #1E212D;
                    font-family: 'Figtree', serif;
                    font-weight: 900;
                    font-size: 24px;
                    font-weight: bold;
                }

                /* Formulario */
                .booking-form {
                    display: flex;
                    flex-direction: column;
                    gap: 10px;
                    /* Espacio entre los elementos */
                    margin-top: 10px;
                }

                /* Inputs en una fila */
                .date-inputs {
                    display: flex;
                    gap: 10px;
                    /* Espacio entre los inputs */
                }

                .date-inputs input {
                    padding: 8px;
                    border: none;
                    border-radius: 5px;
                    font-size: 14px;
                    width: 100%;
                    /* Se extiende al 100% del contenedor */
                }

                .booking-form button {
                    background-color: transparent;
                    color: #FAF3E0;
                    border-radius: 10px;
                    padding: 5px;
                    width: 100%;
                    border: solid 3px #FAF3E0;
                }

                .booking-form button:hover {
                    background-color: #FAF3E0;
                    color: #E78F81;
                }
            </style>

            <body>
                <% Usuario usuario=(Usuario) session.getAttribute("usuario"); %>
                    <!--Navbar-->
                    <%@include file="/jsp/componentes/navbar.jsp" %>

                        <!-- Título principal -->
                        <div class="main-title">Nuestras Actividades</div>

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
                                            <h2>
                                                <%= actividad.getNombre_actividad() %>
                                            </h2>
                                            <span class="price">
                                                <%= actividad.getPrecio() %>€
                                            </span>
                                            <p>
                                                <%= actividad.getDescripcion() %>
                                            </p>
                                            <p>
                                                Fecha de la actividad: <%= actividad.getFecha_actividad() %>
                                            </p>
                                            <div class="booking-form">
                                                <form action="actividades" method="post">
                                                    <input type="hidden" name="action"
                                                        value="reservar">
                                                    <input type="hidden" name="id_usuario"
                                                        value="<%= usuario.getId() %>">
                                                    <input type="hidden" name="id_actividad"
                                                        value="<%= actividad.getId() %>">
                                                        <input type="hidden" name="estado"
                                                        value="reservado">
                                                    <br>
                                                    <button type="submit">RESERVAR</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <% } %>

            </body>

            </html>