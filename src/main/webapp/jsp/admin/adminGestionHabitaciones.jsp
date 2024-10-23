<div id="habitaciones" class="content-section lista-habitaciones-container" style="display:none;">
    <h2 class="text-center mb-5">Gestion de Habitaciones</h2>
        <% 
                    ArrayList<Habitacion> habitaciones = (ArrayList<Habitacion>) request.getAttribute("habitaciones");
                    if (habitaciones != null && !habitaciones.isEmpty()) {
                        for (Habitacion habitacion : habitaciones) {
        %>
    <div class="user-container d-flex justify-content-between p-2 mb-4">
        <div class="d-flex align-items-center p-1">
            <i class="fa-solid fa-bed user-list-icon me-3"></i>
            <p class="m-0"><%= habitacion.getTipoHabitacion()%></p>
        </div>
        <div class="p-1">
            <button class="list-user-button btn btn-outline-light disabled me-3">
                <%= habitacion.getPrecio()%>$
            </button>

            <button class="list-user-button btn btn-outline-light me-3">
                <small><%= habitacion.getEstado()%></small>
            </button>

            <button class="list-user-button btn btn-warning me-3" data-id="<%= habitacion.getId() %>" data-bs-toggle="modal" data-bs-target="#modalActualizarHabitacion" 
                onclick="abrirModalActualizarHabitacion(<%= habitacion.getId() %>, <%= habitacion.getPrecio() %>,)">
                Actualizar
            </button>

            <button class="list-user-button eliminar-button btn btn-danger" data-id="<%= habitacion.getId() %>" data-bs-toggle="modal" data-bs-target="#modalEliminarHabitacion" onclick="abrirModalEliminarHabitacion(<%= habitacion.getId() %>)">
                Eliminar
            </button>
        </div>
        </div>
        <% 
                }
            } else { 
        %>
            <p>No hay usuarios disponibles</p>
        <% 
            } 
        %>
        <div class="container new-user-button mt-5 d-flex justify-content-center align-items-center">
        <button class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#modalCrearHabitacion">Nueva habitacion</button>
    </div>
</div>

<!--Modal para crear a una nueva habitacion -->
<div class="modal fade" id="modalCrearHabitacion" tabindex="-1" aria-labelledby="modalCrearHabitacionLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalCrearHabitacionLabel">Creando nueva habitacion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="admin" method="post" class="login-form">
                    <select class="form-select mb-3" name="tipoHabitacion" aria-label="Default select example" required>
                        <option selected>Selecciona el tipo</option>
                        <option value="sencilla">Sencilla</option>
                        <option value="doble">Doble</option>
                        <option value="suite">Suite</option>
                    </select>
                    <div class="mb-3">
                        <label for="imagen" class="form-label">Imagen de la habitacion</label>
                        <input type="file" class="form-control" id="imagen" name="imagen" required>
                    </div>
                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="precio" name="precio" required>
                    </div>
                    <select class="form-select" name="estado" aria-label="Default select example" required>
                        <option selected>Estado de la habitacion</option>
                        <option value="disponible">Disponible</option>
                        <option value="ocupada">Ocupada</option>
                        <option value="mantenimiento">Mantenimiento</option>
                    </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <input type="hidden" name="action" value="insertarHabitacionAdmin">
                <button type="submit" value="enviar" class="btn btn-outline-primary">Crear habitacion</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!--Modal para actualizar una habitacion -->
<div class="modal fade" id="modalActualizarHabitacion" tabindex="-1" aria-labelledby="modalActualizarHabitacionLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalActualizarHabitacionLabel">Actualizando la habitacion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="admin" method="post" class="login-form">
                    <select class="form-select mb-3" name="estado" id="estadoActualizar" aria-label="Default select example" required>
                        <option selected>Disponible</option>
                        <option value="ocupada">Ocupada</option>
                        <option value="mantenimiento">Mantenimiento</option>
                    </select>
                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="precioActualizar" name="precio" required>
                    </div>
                    <div class="mb-3">
                        <label for="imagen" class="form-label">Imagen de la habitacion</label>
                        <input type="file" class="form-control" id="imagen" name="imagen" required>
                    </div>
                    <input type="hidden" name="id" id="idHabitacionActualizar">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <input type="hidden" name="action" value="actualizarHabitacionAdmin">
                <button type="submit" value="enviar" class="btn btn-outline-primary">Actualizar habitacion</button>
            </div>
            </form>
        </div>
    </div>
</div>


<!--Modal para eliminar una habitacion -->
<div class="modal fade" id="modalEliminarHabitacion" tabindex="-1" aria-labelledby="modalEliminarHabitacionLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalEliminarHabitacionLabel">Confirmar accion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Quieres eliminar la habitacion con ID <span id="idHabitacionEliminar"></span> definitivamente?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <form id="eliminarHabitacionForm" action="habitacion" method="post">
                    <input type="hidden" name="action" value="eliminar">
                    <input type="hidden" name="id" id="id_habitacion_eliminar">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
        function abrirModalActualizarHabitacion(idHabitacion, precio) {
            // Pasar el ID de la habitación al campo oculto en el formulario
            document.getElementById('idHabitacionActualizar').value = idHabitacion;
            document.getElementById('precioActualizar').value = precio;
        }

        function abrirModalEliminarHabitacion(idHabitacion) {
            // Mostrar el ID de la habitación en el cuerpo del modal
            document.getElementById('idHabitacionEliminar').textContent = idHabitacion;

            // Pasar el ID de la habitación al campo oculto en el formulario
            document.getElementById('id_habitacion_eliminar').value = idHabitacion;
        }

    
    document.addEventListener('DOMContentLoaded', function() {
            // Esto asegura que el modal no se muestre hasta que el DOM esté completamente cargado
            const modalHacerAdmin = document.getElementById('confirmAdminModal');
            const modalEliminarUsuario = document.getElementById('modalEliminarUsuario');
            const modalCrearUsuario = document.getElementById('modalCrearUsuario');
            const modalCrearHabitacion = document.getElementById('modalCrearHabitacion');
            
            modalEliminarUsuario.addEventListener('hidden.bs.modal', function(){
                // Limpia los campos al cerrar el modal, si es necesario
                document.getElementById('nombreUsuario_eliminar').innerText = '';
                document.getElementById('id_usuario_eliminar').value = '';
            });

            modalHacerAdmin.addEventListener('hidden.bs.modal', function () {
                // Limpia los campos al cerrar el modal, si es necesario
                document.getElementById('nombreUsuario').innerText = '';
                document.getElementById('id_usuario').value = '';
        });
    });
</script>