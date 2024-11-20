// src/dao/ReservasDAO.js
import { url } from "../utils/Constants";

export const getReservas = async () => {
    try {
        const response = await fetch(`${url}/misReservasJSON`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.statusText}`);
        }

        console.log("Datos obtenidos con exito");
        const data = await response.json();
        // Retornar las dos listas separadas
        return {
            
            reservasActividad: data.reservasActividad || [],
            reservasHabitacion: data.reservasHabitacion || [],
        };
    } catch (error) {
        console.log('Error al obtener los datos:', error);
        return { reservasActividad: [], reservasHabitacion: [] };
    }
};

export async function sendReservas(action: string, id: number, setVisible: (value: boolean) => void, cargarReservas: Function) {
    console.log("Acción a actualizar:", action, "con id:", id);

    try {
        const response = await fetch(`${url}/misReservasJSON`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(action + '-' + id), 
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        // volver a cargar las reservas
        cargarReservas();
        setVisible(false); // Cierra el modal correspondiente
        console.log("Respuesta del servidor:", data);
        console.log("Acción realizada con éxito:", action);
    } catch (error) {
        console.error("Error al enviar la solicitud:", error);
    }
}


