// src/dao/ActividadesDAO.js
import { url } from "../utils/Constants";

export const getActividades = async () => {
    try {
        const response = await fetch(`${url}/actividadesJSON`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const data = await response.json();
        console.log("Datos obtenidos con exito");
        return data;
    } catch (error) {
        console.log('Error al obtener los datos:', error);
        return [];
    }
};

export async function sendActividad(actividad: string, id: string, setVisible, cargarActividades: Function) {
    console.log("Actividad a reservar:", actividad, "con id:", id);
    try {
        const response = await fetch(`${url}/actividadesJSON`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(id + '-' + actividad), 
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        //ocultar el modal
        setVisible(false);
        // Volver a cargar las actividades
        cargarActividades();
        console.log("Respuesta del servidor:", data);
        console.log("Reserva realizada con éxito para la actividad:", actividad);
    } catch (error) {
        console.error("Error al enviar la solicitud:", error);
    }
}
