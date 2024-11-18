// src/dao/ActividadesDAO.js

export const getHabitaciones = async () => {
    try {
        const response = await fetch(`http://10.4.14.248:8080/untitled/habitacionJSON`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const data = await response.json();
        console.log("Datos obtenidos:", data);
        return data;
    } catch (error) {
        console.log('Error al obtener los datos:', error);
        return [];
    }
};
