
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Reservas de Actividades</title>
</head>
<body>

<h2>Lista de Reservas de Actividades</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID Usuario</th>
        <th>ID Actividad</th>
        <th>Estado</th>
        <th>Fecha Reserva</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reserva" items="${reservasActividades}">
        <tr>
            <td>${reserva.idUsuario}</td>
            <td>${reserva.idActividad}</td>
            <td>${reserva.estado}</td>
            <td>${reserva.fechaReserva}</td>
            <td>
                <form action="reservaActividad" method="POST">
                    <input type="hidden" name="index" value="${loop.index}">
                    <input type="hidden" name="action" value="actualizar">
                    <input type="hidden" name="idUsuario" value="${reserva.idUsuario}">
                    <input type="hidden" name="estado" value="${reserva.estado}">
                    <input type="hidden" name="fechaReserva" value="${reserva.fechaReserva}">
                    <input type="hidden" name="idActividad" value="${reserva.idActividad}">
                    <button type="submit">Actualizar</button>
                </form>
                <form action="reservaActividad" method="POST">
                    <input type="hidden" name="index" value="${loop.index}">
                    <input type="hidden" name="action" value="eliminar">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Agregar Nueva Reserva de Actividad</h3>
<form action="reservaActividad" method="POST">
    <input type="hidden" name="action" value="crear">
    ID Usuario: <input type="text" name="idUsuario" required>
    ID Actividad: <input type="text" name="idActividad" required>
    Estado: <input type="text" name="estado" required>
    Fecha Reserva: <input type="date" name="fechaReserva" required>
    <button type="submit">Agregar Reserva</button>
</form>

</body>
</html>
