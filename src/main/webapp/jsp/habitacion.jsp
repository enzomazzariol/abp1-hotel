<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Habitaciones</title>
</head>
<body>

<h2>Lista de Habitaciones</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tipo</th>
        <th>Precio</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%-- Iteramos sobre la lista de habitaciones --%>
    <c:forEach var="habitacion" items="${habitaciones}">
        <tr>
            <td>${habitacion.id}</td>
            <td>${habitacion.tipoHabitacion}</td>
            <td>${habitacion.precio}</td>
            <td>${habitacion.estado}</td>
            <td>
                    <%-- Formulario para actualizar habitación --%>
                <form action="habitacion" method="POST">
                    <input type="hidden" name="id" value="${habitacion.id}">
                    <input type="hidden" name="action" value="actualizar">
                    Tipo: <input type="text" name="tipoHabitacion" value="${habitacion.tipoHabitacion}">
                    Precio: <input type="text" name="precio" value="${habitacion.precio}">
                    Estado: <input type="text" name="estado" value="${habitacion.estado}">
                    <button type="submit">Actualizar</button>
                </form>
            </td>
            <td>
                    <%-- Formulario para marcar habitación como eliminada --%>
                <form action="habitacion" method="POST">
                    <input type="hidden" name="id" value="${habitacion.id}">
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
