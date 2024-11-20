import { Card, Text, Button, Portal, Modal} from "react-native-paper";
import { View, Image } from "react-native";
import actividadesStyle from "../../styles/actividadesStyle";
import { useEffect, useState } from "react";
import { sendActividad } from "../../dao/ActividadesDao";
import { getUsuario } from "../../dao/perfilDao";

const imagenes = {
    'Actividad_surf.jpg': require('../../assets/Actividad_surf.jpg'),
    'Actividad_snorkel.jpg': require('../../assets/Actividad_snorkel.jpg'),
    'Actividad_moto_agua.jpg': require('../../assets/Actividad_moto_agua.jpg'),
};

export function ActividadCard({ actividad, cargarActividades }) {
    const [visible, setVisible] = useState(false);
    const [actividadSeleccionada, setActividadSeleccionada] = useState(null);
    const [usuario, setUsuario] = useState({
        id: null,
    });

    // Abrir modal para reservas actividad
    const showModal = (nombre_actividad) => {
        setActividadSeleccionada(nombre_actividad);
        setVisible(true);
    };

    // Cerrar modal para reservas actividad
    const hideModal = () => setVisible(false);

    useEffect(() => {
        cargarUsuario();
    }, []);

    async function cargarUsuario() {
        const usuarioData = await getUsuario();
        console.log("Datos del usuario:", usuarioData);  
        setUsuario(usuarioData);
    }

    return (
        <View style={actividadesStyle.cardContainer}>
            {actividad.map((item, index) => (
                <Card style={actividadesStyle.actividadContainer} key={index}>
                    <Image source={imagenes[item.imagen]} style={actividadesStyle.actividadImagen} />
                    <Card.Content>
                        <Text style={actividadesStyle.cardTitle}>{item.nombre_actividad}</Text>
                        <Text style={actividadesStyle.cardDescription}>{item.descripcion}</Text>
                        <View style={{ flexDirection: "row", justifyContent: "space-between", marginBottom: 14 }}>
                            <Text style={actividadesStyle.cardText}>Precio: {item.precio}$</Text>
                            <Text style={actividadesStyle.cardText}>Cupos disponibles: {item.cupo}</Text>
                        </View>
                        <Text style={actividadesStyle.cardFecha}>Fecha de la actividad: {item.fecha_actividad}</Text>
                        <Button style={actividadesStyle.cardButton} onPress={() => showModal(item.nombre_actividad)}>
                            <Text style={actividadesStyle.cardButtonText}>Reservar</Text>
                        </Button>

                        {/* Modal para reservas actividad */}
                        <Portal>
                            <Modal visible={visible && actividadSeleccionada === item.nombre_actividad} onDismiss={hideModal} style={actividadesStyle.modal}>
                                <Text style={actividadesStyle.modalTitle}>Reserva de Actividad</Text>
                                <Text style={actividadesStyle.modalDescription}>¿Quieres hacer una reserva para la actividad {item.nombre_actividad}?</Text>
                                <Button style={actividadesStyle.cardButton} onPress={() => {
                                    sendActividad(item.id, usuario.id, setVisible, cargarActividades)
                                    alert('Reserva realizada con éxito!')
                                    }}
                                    >
                                    <Text style={actividadesStyle.cardButtonText}>Reservar</Text>
                                </Button>
                                <Button style={actividadesStyle.modalButton} onPress={hideModal}>
                                    <Text style={actividadesStyle.cardButtonText}>Cerrar</Text>
                                </Button>
                            </Modal>
                        </Portal>
                    </Card.Content>
                </Card>
            ))}
        </View>
    );
}

export default ActividadCard;
