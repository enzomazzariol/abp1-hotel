// src/dao/ActividadesDAO.js
import { url } from "../utils/Constants";

export const getHabitaciones = async () => {
    try {
        const response = await fetch(`${url}/habitacionJSON`, {
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


export async function sendHabitacion(id : string, habitacion: string, fechaDesde : string, fechaHasta : string) {
    console.log("Habitacion a reservar:", habitacion);
    try {
        const response = await fetch(`${url}/habitacionJSON`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(id + "/" + habitacion + "/" + fechaDesde + "/" + fechaHasta),  
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        console.log("Respuesta del servidor:", data);
    } catch (error) {
        console.error("Error al enviar la solicitud:", error);
    }
}
