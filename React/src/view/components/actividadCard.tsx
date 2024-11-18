import { Card, Text, Button, Portal, Modal} from "react-native-paper";
import { View, Image } from "react-native";
import actividadesStyle from "../../styles/actividadesStyle";
import { useState } from "react";

const imagenes = {
    'Actividad_surf.jpg': require('../../assets/Actividad_surf.jpg'),
    'Actividad_snorkel.jpg': require('../../assets/Actividad_snorkel.jpg'),
    'Actividad_moto_agua.jpg': require('../../assets/Actividad_moto_agua.jpg'),
};

export function ActividadCard({ actividad }) {
    const [visible, setVisible] = useState(false);
    const [actividadSeleccionada, setActividadSeleccionada] = useState(null);

    const showModal = (nombre_actividad) => {
        setActividadSeleccionada(nombre_actividad);
        setVisible(true);
    };

    const hideModal = () => setVisible(false);

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
                                <Button style={actividadesStyle.cardButton}>
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
