
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Actividades</title>
</head>
<body>

<h2>Lista de Actividades</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Precio</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="actividad" items="${actividades}">
        <tr>
            <td>${actividad.id}</td>
            <td>${actividad.nombre}</td>
            <td>${actividad.descripcion}</td>
            <td>${actividad.precio}</td>
            <td>
                    <%-- Formulario para actualizar actividad --%>
                <form action="actividades" method="POST">
                    <input type="hidden" name="id" value="${actividad.id}">
                    <input type="hidden" name="action" value="actualizar">
                    Nombre: <input type="text" name="nombre" value="${actividad.nombre}">
                    Descripción: <input type="text" name="descripcion" value="${actividad.descripcion}">
                    Precio: <input type="text" name="precio" value="${actividad.precio}">
                    <button type="submit">Actualizar</button>
                </form>
            </td>
            <td>
                    <%-- Formulario para marcar actividad como eliminada --%>
                <form action="actividades" method="POST">
                    <input type="hidden" name="id" value="${actividad.id}">
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
