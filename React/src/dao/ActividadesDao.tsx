// src/dao/ActividadesDAO.js

export const getActividades = async () => {
    try {
        const response = await fetch(`http://10.4.6.142:8080/untitled/actividadesJSON`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log(response)
        const data = await response.json();
        console.log("Datos obtenidos:", data);
        return data;
    } catch (error) {
        console.log('Error al obtener los datos:', error);
        return [];
    }
};
