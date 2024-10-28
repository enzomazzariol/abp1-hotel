<div id="actividades" class="content-section lista-actividades-container" style="display:none;">
    <h2 class="text-center mb-5">Gestion de Actividades</h2>
        <% 
                    ArrayList<Actividad> actividades = (ArrayList<Actividad>) request.getAttribute("actividades");
                    if (actividades != null && !actividades.isEmpty()) {
                        for (Actividad actividad : actividades) {
        %>
    <div class="user-container d-flex justify-content-between p-2 mb-4">
        <div class="d-flex align-items-center p-1">
            <i class="fa-solid fa-person-swimming user-list-icon me-3"></i>
            <p class="m-0"><%= actividad.getNombre_actividad()%></p>
        </div>
        <div class="p-1">
            <button class="list-user-button btn btn-outline-light disabled me-3">
                <%= actividad.getPrecio()%>$
            </button>

            <button class="list-user-button btn btn-outline-light disabled me-3">
                <%= actividad.getCupo()%>
            </button>

            <button class="list-user-button btn btn-outline-light me-3">
                <%= actividad.getFecha_actividad()%>
            </button>

            <button class="list-user-button btn btn-warning me-3" data-bs-toggle="modal" data-bs-target="#modalActualizarActividad"
                onclick="abrirModalActualizarActividad(<%= actividad.getId() %>, '<%= actividad.getNombre_actividad() %>', '<%= actividad.getDescripcion() %>', '<%= actividad.getPrecio() %>', '<%= actividad.getCupo() %>', '<%= actividad.getFecha_actividad() %>')">
                Actualizar
            </button>

            <button class="list-user-button eliminar-button btn btn-danger" onclick="abrirModalEliminarActividad(<%= actividad.getId() %>)" data-bs-toggle="modal" data-bs-target="#modalEliminarActividad">
                Eliminar
            </button>
        </div>
        </div>
        <% 
                }
            } else { 
        %>
            <p>No hay actividades disponibles</p>
        <% 
            } 
        %>
        <div class="container new-user-button mt-5 d-flex justify-content-center align-items-center">
        <button class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#modalCrearActividad">Nueva actividad</button>
    </div>
</div>

<!--Modal para crear a una nueva actividad -->
<div class="modal fade" id="modalCrearActividad" tabindex="-1" aria-labelledby="modalCrearActividadLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalCrearActividadLabel">Creando nueva actividad</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="admin" method="post" class="login-form">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre de la actividad</label>
                        <input type="text" class="form-control" id="nombre" name="nombre_actividad" required>
                    </div>
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripcion de la actividad</label>
                        <input type="text" class="form-control" id="descripcion" name="descripcion" required>
                    </div>
                    <div class="mb-3">
                        <label for="imagen" class="form-label">Imagen de la actividad</label>
                        <input type="file" class="form-control" id="imagen" name="imagenes">
                    </div>
                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="precio" name="precio" required>
                    </div>
                    <div class="mb-3">
                        <label for="cupo" class="form-label">Cupos disponibles</label>
                        <input type="number" class="form-control" id="cupo" name="cupo" required>
                    </div>
                    <div class="mb-3">
                        <label for="fecha" class="form-label">Fecha de la actividad</label>
                        <input type="date" class="form-control" id="fecha_actividad" name="fecha_actividad" required>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <input type="hidden" name="action" value="insertarActividadAdmin">
                <button type="submit" value="enviar" class="btn btn-outline-primary">Crear actividad</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal para actualizar una actividad -->
<div class="modal fade" id="modalActualizarActividad" tabindex="-1" aria-labelledby="modalActualizarActividadLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalActualizarActividadLabel">Actualizando la actividad</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="admin" method="post" class="login-form">
                    <div class="mb-3">
                        <label for="nombreActualizar" class="form-label">Nombre de la actividad</label>
                        <input type="text" class="form-control" id="nombreActualizar" name="nombre_actividad" required>
                    </div>
                    <div class="mb-3">
                        <label for="descripcionActualizar" class="form-label">Descripcion de la actividad</label>
                        <input type="text" class="form-control" id="descripcionActualizar" name="descripcion" required>
                    </div>
                    <div class="mb-3">
                        <label for="imagenActualizar" class="form-label">Imagen de la actividad</label>
                        <input type="file" class="form-control" id="imagenActualizar" name="imagenes">
                    </div>
                    <div class="mb-3">
                        <label for="precioActualizar" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="precioActualizar" name="precio" required>
                    </div>
                    <div class="mb-3">
                        <label for="cupoActualizar" class="form-label">Cupos disponibles</label>
                        <input type="number" class="form-control" id="cupoActualizar" name="cupo" required>
                    </div>
                    <div class="mb-3">
                        <label for="fechaActualizar" class="form-label">Fecha de la actividad</label>
                        <input type="date" class="form-control" id="fechaActualizar" name="fecha_actividad" required>
                    </div>
                    <input type="hidden" name="id" id="idActividadActualizar">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <input type="hidden" name="action" value="actualizarActividadAdmin">
                <button type="submit" value="enviar" class="btn btn-outline-primary">Actualizar actividad</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal para eliminar una actividad -->
<div class="modal fade" id="modalEliminarActividad" tabindex="-1" aria-labelledby="modalEliminarActividadLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalEliminarActividadLabel">Confirmar accion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Quieres eliminar la actividad con ID <span id="idActividadEliminar"></span> definitivamente?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <form id="eliminarActividadForm" action="admin" method="post">
                    <input type="hidden" name="action" value="eliminarActividadAdmin">
                    <input type="hidden" name="id" id="id_actividad_eliminar">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
      function abrirModalActualizarActividad(idActividad, nombre, descripcion, precio, cupo, fecha_actividad) {
    // Pasar el ID de la actividad al campo oculto en el formulario
    document.getElementById('idActividadActualizar').value = idActividad;
    
    // Rellenar los campos del formulario de actualización con los valores actuales
    document.getElementById('nombreActualizar').value = nombre;
    document.getElementById('descripcionActualizar').value = descripcion;
    document.getElementById('precioActualizar').value = precio;
    document.getElementById('cupoActualizar').value = cupo;
    document.getElementById('fechaActualizar').value = fecha_actividad;
}

function abrirModalEliminarActividad(idActividad) {
    // Mostrar el ID de la actividad en el cuerpo del modal
    document.getElementById('idActividadEliminar').textContent = idActividad;

    // Pasar el ID de la actividad al campo oculto en el formulario
    document.getElementById('id_actividad_eliminar').value = idActividad;
}

document.addEventListener('DOMContentLoaded', function() {
    const modalEliminarActividad = document.getElementById('modalEliminarActividad');
    
    modalEliminarActividad.addEventListener('hidden.bs.modal', function() {
        // Limpia los campos al cerrar el modal
        document.getElementById('idActividadEliminar').textContent = '';
        document.getElementById('id_actividad_eliminar').value = '';
    });
});

    // Obtener la fecha actual
    const today = new Date().toISOString().split('T')[0];
    
    // Asignar la fecha mínima al input
    document.getElementById('fecha_actividad').setAttribute('min', today);
</script>