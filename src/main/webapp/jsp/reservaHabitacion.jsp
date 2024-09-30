<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reservas de Habitaciones</title>
</head>
<body>

<h2>Lista de Reservas de Habitaciones</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>ID Habitacion</th>
        <th>Fecha Entrada</th>
        <th>Fecha Salida</th>
        <th>Estado</th>
        <th>Fecha Reserva</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reserva" items="${reservasHabitaciones}">
        <tr>
            <td>${reserva.id}</td>
            <td>${reserva.idHabitacion}</td>
            <td>${reserva.fechaEntrada}</td>
            <td>${reserva.fechaSalida}</td>
            <td>${reserva.estado}</td>
            <td>${reserva.fechaReserva}</td>
            <td>
                <form action="reservaHabitacion" method="POST">
                    <input type="hidden" name="id" value="${reserva.id}">
                    <input type="hidden" name="action" value="actualizar">
                    <button type="submit">Actualizar</button>
                </form>
                <form action="reservaHabitacion" method="POST">
                    <input type="hidden" name="id" value="${reserva.id}">
                    <input type="hidden" name="action" value="eliminar">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
