<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DetalleReservaHabitacion" %>
<%@ page import="model.DetalleReservaActividad" %>
<%@ page import="model.Usuario" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Mis Reservas - Bellmunt Hotel</title>

                    <!--Animaciones-->
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
                    <!--Fuente Tigtree-->
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
                </head>
                <style>
                    body {
                        margin: 0;
                        background-color: #1E212D;
                    }

                    /* Contenido */

                    .title {
                        color: #E78F81;
                        font-family: 'Merriweather', serif;
                        font-size: 30px;
                        font-weight: 900;
                        text-align: center;
                    }

                    .div-title2 {
                        padding-left: 100px;
                        text-align: left;
                    }

                    .title2 {
                        color: #E78F81;
                        font-family: 'Merriweather', serif;
                        font-size: 20px;
                        font-weight: 900;
                        margin-bottom: 5px;
                    }

                    .reservas {
                        display: flex;
                        justify-content: center;
                        padding: 10px;
                    }

                    .subtitle {
                        color: #E78F81;
                        font-family: 'Merriweather', serif;
                        font-size: 25px;
                        font-weight: 900;
                        text-align: center;
                    }

                    /* Estructura general de la tabla convertida en divs */
                    .table {
                        width: 100%;
                        font-family: 'Merriweather', serif;
                        font-weight: 900;
                        border-collapse: separate;
                        border-spacing: 0 10px;
                    }

                    .table-thead,
                    .table-body {
                        display: flex;
                        flex-direction: column;
                    }

                    .table-row {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        background-color: #D9D9D9;
                        border-radius: 20px;
                        margin-bottom: 10px;
                        padding: 5px;
                        height: 50px;
                        width: 900px;
                    }

                    .table-cell-head {
                        padding: 5px;
                        flex: 1;
                        text-align: center;
                        color: #E78F81;
                        font-size: 20px;
                    }

                    .table-cell {
                        padding: 5px;
                        flex: 1;
                        text-align: center;
                        color: #1E212D;
                        font-size: 15px;
                    }

                    .buttons .empty-actions {
                        min-height: 40px;
                        /* Ajusta la altura al tamaño esperado de los botones */
                        visibility: hidden;
                        /* Oculta pero mantiene el espacio */
                    }

                    /* Estilos específicos para la cabecera */
                    .table-thead .table-row {
                        color: #E78F81;
                        font-size: 20px;
                        background-color: transparent;
                        margin-bottom: 0px;
                        padding: 0px;
                    }

                    .table-body .table-row {
                        background-color: #FAF3E0;
                    }

                    .buttons {
                        display: flex;
                        justify-content: center;
                        gap: 10px;
                    }

                    .button-update {
                        border: none;
                        border-radius: 10px;
                        background-color: transparent;
                        font-family: 'Merriweather', serif;
                        font-weight: 900;
                        font-size: 15px;
                        padding: 7px;
                        cursor: pointer;
                        border: solid 2px #e27c6c;
                        color: #e27c6c;
                    }

                    .button-delete {
                        border: none;
                        border-radius: 10px;
                        background-color: transparent;
                        font-family: 'Merriweather', serif;
                        font-weight: 900;
                        font-size: 15px;
                        padding: 7px;
                        cursor: pointer;
                        border: solid 3px #ED3E22;
                        color: #ED3E22;
                    }

                    .button-delete:hover {
                        background-color: #ED3E22;
                        color: white;
                    }

                    .button-update:hover {
                        background-color: #E78F81;
                        color: white;
                    }

                    .mensaje-error {
                        color: #FAF3E0;
                        font-size: 20px;
                        text-align: center;
                        font-family: "Figtree";
                    }


                    /* Responsive */
                    @media (max-width: 800px) {
                        .table-row {
                            flex-direction: column;
                            height: auto;
                            width: auto;
                        }

                        .table-cell,
                        .table-cell-head {
                            text-align: left;
                            font-size: 18px;
                            padding: 10px 0;
                        }
                    }

                    @media (max-width: 400px) {

                        .table-cell,
                        .table-cell-head {
                            font-size: 16px;
                        }

                        .navbar {
                            flex-direction: column;
                            text-align: center;
                        }

                        .navbar ul {
                            flex-direction: column;
                            margin-top: 10px;
                        }

                        .div-title2 {
                            padding-left: 20px;
                        }
                    }
                </style>

                <body>
                    <% Usuario usuario=(Usuario) session.getAttribute("usuario"); %>
                        <!--Navbar-->
                        <%@include file="/jsp/componentes/navbar.jsp" %>
                        <!--Contenido-->
                        <div class="row">
                            <div class="title">
                                <p>BELLMUNT<br>HOTEL</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="div-title2">
                                <p class="title2">Mis Reservas</p>
                            </div>

                            <div class="reservas">
                                <div class="row">
                                    <div class="reserva">
                                        <div class="subtitle">
                                            <p>Actividades</p>
                                        </div>

                                        <% ArrayList<DetalleReservaActividad> reservas = (ArrayList
                                            <DetalleReservaActividad>
                                                ) request.getAttribute("reservaActividadById");
                                                if (reservas != null  && !reservas.isEmpty()) { %>
                                                <div class="table">
                                                    <div class="table-thead">
                                                        <div class="table-row">
                                                            <div class="table-cell-head">Nombre</div>
                                                            <div class="table-cell-head">Estado</div>
                                                            <div class="table-cell-head">Fecha Reserva</div>
                                                            <div class="table-cell-head">Reserva</div>
                                                            <div class="table-cell-head">Acciones</div>
                                                        </div>
                                                    </div>

                                                    <div class="table-body animate__animated animate__fadeInLeft">
                                                        <% for (DetalleReservaActividad reserva: reservas) { %>
                                                            <div class="table-row">
                                                                <div class="table-cell">
                                                                    <%= reserva.getNombreActividad() %>
                                                                </div>
                                                                <div class="table-cell">
                                                                    <%= reserva.getEstadoReservaActividad() %>
                                                                </div>
                                                                <div class="table-cell">
                                                                    <%= reserva.getFechaReservaActividad() %>
                                                                </div>
                                                                <div class="table-cell">
                                                                    <%= reserva.getFechasReservaHabitacion() %>
                                                                </div>
                                                                <div class="table-cell buttons">
                                                                    <% if
                                                                        (reserva.getEstadoReservaActividad().equals("reservado"))
                                                                        { %>
                                                                        <form action="misReservas" method="post">
                                                                            <input type="hidden" name="action"
                                                                                value="actualizarActividades">
                                                                            <input type="hidden" name="id"
                                                                                value="<%= reserva.getId() %>">
                                                                            <button type="submit"
                                                                                class="button-update">Completar</button>
                                                                        </form>
                                                                        <form action="misReservas" method="post">
                                                                            <input type="hidden" name="action"
                                                                                value="eliminarActividades">
                                                                            <input type="hidden" name="id"
                                                                                value="<%= reserva.getId() %>">
                                                                            <button type="submit"
                                                                                class="button-delete">Cancelar</button>
                                                                        </form>
                                                                        <% } else { %>
                                                                            <div class="empty-actions"></div>
                                                                            <% } %>
                                                                </div>
                                                            </div>
                                                            <% } %>
                                                    </div>
                                                    <% } else { %>
                                                        <div class="col-12">
                                                            <p class="mensaje-error">No tienes ninguna actividad reservada.</p>
                                                        </div>
                                                        <% } %>
                                                </div>
                                    </div>
                                </div>
                            </div>

                            <div class="reservas">
                                <div class="row">
                                    <div class="reserva">
                                        <div class="subtitle">
                                            <p>Habitaciones</p>
                                        </div>
                                        <% ArrayList<DetalleReservaHabitacion> reservahabitaciones = (ArrayList
                                            <DetalleReservaHabitacion>)
                                                request.getAttribute("reservaHabitacionById");
                                                if (reservahabitaciones != null && !reservahabitaciones.isEmpty()) { %>

                                                <div class="table">
                                                    <div class="table-thead">
                                                        <div class="table-row">
                                                            <div class="table-cell-head">Tipo</div>
                                                            <div class="table-cell-head">Estado</div>
                                                            <div class="table-cell-head">Fecha Reserva</div>
                                                            <div class="table-cell-head">Reserva</div>
                                                            <div class="table-cell-head">Acciones</div>
                                                        </div>
                                                    </div>

                                                    <div class="table-body animate__animated animate__fadeInRightBig">
                                                        <% for (DetalleReservaHabitacion reservahabitacion :
                                                            reservahabitaciones) { %>
                                                            <div class="table-row">
                                                                <div class="table-cell">
                                                                    <%= reservahabitacion.getTipoDeHabitacion() %>
                                                                </div>
                                                                <div class="table-cell">
                                                                    <%= reservahabitacion.getEstadoReserva() %>
                                                                </div>
                                                                <div class="table-cell">
                                                                    <%= reservahabitacion.getFechaReserva() %>
                                                                </div>
                                                                <div class="table-cell">
                                                                    <%= reservahabitacion.getFechas() %>
                                                                </div>
                                                                <div class="table-cell buttons">
                                                                    <% if
                                                                        (reservahabitacion.getEstadoReserva().equals("reservado"))
                                                                        { %>
                                                                        <form action="misReservas" method="post">
                                                                            <input type="hidden" name="action"
                                                                                value="actualizarHabitaciones">
                                                                            <input type="hidden" name="id"
                                                                                value="<%= reservahabitacion.getId() %>">
                                                                            <button type="submit"
                                                                                class="button-update">Completar</button>
                                                                        </form>
                                                                        <form action="misReservas" method="post">
                                                                            <input type="hidden" name="action"
                                                                                value="eliminarHabitaciones">
                                                                            <input type="hidden" name="id"
                                                                                value="<%= reservahabitacion.getId() %>">
                                                                            <button type="submit"
                                                                                class="button-delete">Cancelar</button>
                                                                        </form>

                                                                        <% } else { %>
                                                                            <div class="empty-actions"></div>
                                                                            <% } %>
                                                                </div>
                                                            </div>
                                                            <% } %>
                                                    </div>
                                                    <% } else { %>
                                                        <div class="col-12">
                                                            <p class="mensaje-error">No tienes ninguna habitacion reservada.</p>
                                                        </div>
                                                        <% } %>
                                                </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                </body>

                </html>