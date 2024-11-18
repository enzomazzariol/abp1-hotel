    <div class="p-5">
        <h1 class="admin-title text-center mb-5">Vista de reservas:</h1>
        <!--Vista de reservas hechas por usuarios-->
        <div class="table-container container">
            <table class="table table-striped table-bordered admin-reservas-table">
                <thead class="user-thead">
                <tr class="user-trow">
                    <th class="user-th bg-light">Usuario</th>
                    <th class="user-th bg-light">Tipo de Reserva</th>
                    <th class="user-th bg-light">Detalle de la Reserva</th>
                    <th class="user-th bg-light">Estado</th>
                    <th class="user-th bg-light">Fecha de Entrada</th>
                    <th class="user-th bg-light">Fecha de Salida</th>
                    <th class="user-th bg-light">Fecha de Reserva</th>
                </tr>
                </thead>
                <tbody>
                    <% 
                        ArrayList<ReservaGeneral> reservas = (ArrayList<ReservaGeneral>) request.getAttribute("reservas");
                        if (reservas != null && !reservas.isEmpty()) {
                            for (ReservaGeneral reserva : reservas) {
                    %>
                    <tr class="user-tr-result">
                        <td class="user-td"><%= reserva.getUsuario() %></td>
                        <td class="user-td"><%= reserva.getTipoReserva() %></td>
                        <td class="user-td"><%= reserva.getDetalleReserva() %></td>
                        <td class="user-td"><%= reserva.getEstado() %></td>
                        <td class="user-td">
                            <%= (reserva.getFechaEntrada() == null || reserva.getFechaEntrada().isEmpty()) ? "No disponible" : reserva.getFechaEntrada()%>
                        </td>
                        <td class="user-td">
                            <%= (reserva.getFechaSalida() == null || reserva.getFechaSalida().isEmpty()) ? "No disponible" : reserva.getFechaSalida() %>
                        </td>
                        <td class="user-td"><%= reserva.getFechaReserva() %></td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                        <tr>
                            <td colspan="7" class="text-center">No hay reservas disponibles.</td>
                        </tr>
                    <% 
                        } 
                    %>
                </tbody>
            </table>
        </div>
    </div>    
