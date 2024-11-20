import { url } from "../utils/Constants";

export const getUsuario = async () => {
    try {
        const response = await fetch(`${url}/loginJSON`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log('Error al obtener los datos:', error);
        return [];
    }
};