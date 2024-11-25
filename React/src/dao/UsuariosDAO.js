// src/dao/UsuariosDAO.js

// Función para obtener todos los usuarios 
export const getUsuarios = async () => {
    try {
        const response = await fetch(`http://10.4.14.248:8080/untitled/usuariosJSON`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const data = await response.json();
        console.log("Datos obtenidos:", data);
        return data;
    } catch (error) {
        console.error('Error al obtener los datos:', error);
        return [];
    }
};

// Función para obtener un usuario específico por ID
export const getUsuarioById = async (id) => {
    try {
        const response = await fetch(`http://10.4.14.248:8080/untitled/usuarioJSON?id=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) throw new Error('Error al obtener el usuario');
        
        const data = await response.json();
        console.log("Usuario obtenido:", data);
        return data;
    } catch (error) {
        console.error('Error al obtener el usuario:', error);
        return null;
    }
};

// Función para actualizar un usuario específico
export const updateUsuario = async (usuario) => {
    try {
        const response = await fetch(`http://10.4.14.248:8080/untitled/usuarioJSON?id=${usuario.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        });
        if (!response.ok) throw new Error('Error al actualizar el usuario');

        const updatedData = await response.json();
        console.log("Usuario actualizado:", updatedData);
        return updatedData;
    } catch (error) {
        console.error('Error al actualizar el usuario:', error);
        return null;
    }
};
