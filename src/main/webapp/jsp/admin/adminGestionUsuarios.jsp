<div id="usuarios" class="content-section lista-usuarios-container">
    <h2 class="text-center mb-5">Gestion de Usuarios</h2>
        <% 
                    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
                    if (usuarios != null && !usuarios.isEmpty()) {
                        for (Usuario usuario : usuarios) {
        %>
    <div class="user-container d-flex justify-content-between p-2 mb-4">
        <div class="d-flex align-items-center p-1">
            <i class="fa-solid fa-user-large user-list-icon me-3"></i>
            <p class="m-0"><%= usuario.getNombre()%></p>
        </div>
        <div class="p-1">
            <button href="" class="list-user-button 
                <%= usuario.getRol() == Rol.ADMIN ? "admin-button btn btn-success" : "admin-button btn btn-outline-success" %> me-3" id="admin-button"
                <% if(usuario.getRol() != Rol.ADMIN) { %>
                data-bs-toggle="modal" data-bs-target="#confirmAdminModal" onclick="prepareAdminModal('<%=usuario.getId()%>', '<%=usuario.getNombre()%>')"
                <% } %>>
                <%= usuario.getRol() == Rol.ADMIN ? "admin" : "Hacer admin" %>
            </button>

            <form action="jsp/perfil.jsp" method="get" class="d-inline">
                <input type="hidden" name="id" value="<%= usuario.getId() %>">
                <button type="submit" class="list-user-button mostrar-button btn btn-light me-3" id="mostrar-button">
                    Mostrar
                </button>
            </form>

            <button href="" class="list-user-button eliminar-button btn btn-danger" id="eliminar-button"
                data-bs-toggle="modal" data-bs-target="#modalEliminarUsuario"
                onclick="eliminarUsuarioModal('<%=usuario.getId()%>', '<%=usuario.getNombre()%>')">
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
        <button class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#modalCrearUsuario">Nuevo Usuario</button>
</div>
</div>

<!-- Modal para confirmar si quieres hacer admin al usuario -->
<div class="modal fade" id="confirmAdminModal" tabindex="-1" aria-labelledby="confirmAdminModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmAdminModalLabel">Confirmar accion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Quieres promover a <span id="nombreUsuario"></span> a administrador?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <form id="makeAdminForm" action="admin" method="post">
                    <input type="hidden" name="action" value="actualizarUsuarioRol">
                    <input type="hidden" name="id" id="id_usuario" value="">
                    <button type="submit" class="btn btn-success">Hacer admin</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!--Modal para eliminar a un usuario -->
<div class="modal fade" id="modalEliminarUsuario" tabindex="-1" aria-labelledby="modalEliminarUsuarioLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalEliminarUsuarioLabel">Confirmar accion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Quieres eliminar a <span id="nombreUsuario_eliminar"></span> definitivamente?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <form id="eliminarUsuarioForm" action="perfil" method="post">
                    <input type="hidden" name="action" value="eliminar">
                    <input type="hidden" name="id" id="id_usuario_eliminar">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!--Modal para crear a un nuevo usuario -->
<div class="modal fade" id="modalCrearUsuario" tabindex="-1" aria-labelledby="modalCrearUsuarioLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content"> 
            <div class="modal-header">  
                <h5 class="modal-title" id="modalCrearUsuarioLabel">Creando nuevo usuario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="admin" method="post" class="login-form" onsubmit="return validarFormulario()">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="nombre del usuario">
                         <div class="invalid-feedback" id="nombre-error">Por favor ingresa un nombre.</div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Correo electronico</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="nombre@ejemplo.com">
                        <div class="invalid-feedback" id="email-error">Por favor ingresa un correo electronico valido.</div>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Contrasena</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="tu contrasena secreta"></input>
                        <div class="invalid-feedback" id="password-error">Por favor ingresa una contrasena.</div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <input type="hidden" name="action" value="insertarUsuarioAdmin">
                <button type="submit" value="enviar" class="btn btn-outline-primary">Crear usuario</button>
            </div>
            </form>
        </div>
    </div>
</div>

<script>

let selectedUserId = null;

        function prepareAdminModal(id_usuario, nombre) {
        // Actualizar el nombre del usuario en el modal
        document.getElementById('nombreUsuario').innerText = nombre;
        // Asignar el ID del usuario al campo oculto del formulario 
        document.getElementById('id_usuario').value = id_usuario;
        }

        function eliminarUsuarioModal(id_usuario, nombre){
            document.getElementById('nombreUsuario_eliminar').innerText = nombre;
            document.getElementById('id_usuario_eliminar').value = id_usuario;
        }

    document.addEventListener('DOMContentLoaded', function() {
            // Esto asegura que el modal no se muestre hasta que el DOM esté completamente cargado
            const modalHacerAdmin = document.getElementById('confirmAdminModal');
            const modalEliminarUsuario = document.getElementById('modalEliminarUsuario');
            const modalCrearUsuario = document.getElementById('modalCrearUsuario');
            
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

    function validarFormulario() {
        // Obtener los valores de los campos
        const nombre = document.getElementById('nombre').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();

        let esValido = true;

        // Validar el campo de nombre
        if (nombre === "") {
            document.getElementById('nombre-error').style.display = 'block';
            document.getElementById('nombre').classList.add('is-invalid');
            esValido = false;
        } else {
            document.getElementById('nombre-error').style.display = 'none';
            document.getElementById('nombre').classList.remove('is-invalid');
        }

        // Validar el campo de correo electrónico
        if (email === "" || !validarEmail(email)) {
            document.getElementById('email-error').style.display = 'block';
            document.getElementById('email').classList.add('is-invalid');
            esValido = false;
        } else {
            document.getElementById('email-error').style.display = 'none';
            document.getElementById('email').classList.remove('is-invalid');
        }

        // Validar el campo de contraseña
        if (password === "") {
            document.getElementById('password-error').style.display = 'block';
            document.getElementById('password').classList.add('is-invalid');
            esValido = false;
        } else {
            document.getElementById('password-error').style.display = 'none';
            document.getElementById('password').classList.remove('is-invalid');
        }

        // Si algún campo no es válido, evitar el envío del formulario
        return esValido;
    }
</script>