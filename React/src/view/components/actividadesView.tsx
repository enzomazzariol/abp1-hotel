import { View, Image, ScrollView } from "react-native";
import actividadesStyle from "../../styles/actividadesStyle";
import { Button, Card, Modal, Portal, Text } from "react-native-paper";
import { useState } from "react";


const imagenes = {
    'Actividad_surf.jpg': require('../../assets/Actividad_surf.jpg'),
    'Actividad_snorkel.jpg': require('../../assets/Actividad_snorkel.jpg'),
    'Actividad_moto_agua.jpg': require('../../assets/Actividad_moto_agua.jpg'),
  };

  const actividadData = [
    {
      "nombre_actividad": "Surf en Bellmunt Hotel",
      "descripcion": "Vive la emoción de surfear las olas del Mediterráneo en Cala Blava. Perfecto para todos los niveles, con instructores que te guiarán en una experiencia inolvidable.",
      "imagen": "Actividad_surf.jpg",
      "precio": 50,
      "cupo": 10,
      "fecha_actividad": "2024-11-09"
    },
    {
      "nombre_actividad": "Snorkel en Cala Blava",
      "descripcion": "Explora la vida submarina de Cala Blava con nuestro tour de snorkel. Descubre la increíble biodiversidad del Mediterráneo, desde peces coloridos hasta fascinantes formaciones rocosas bajo el agua.",
      "imagen": "Actividad_snorkel.jpg",
      "precio": 70,
      "cupo": 5,
      "fecha_actividad": "2024-11-03"
    },
    {
      "nombre_actividad": "Motos de agua",
      "descripcion": "Para los amantes de la velocidad y la aventura, las motos de agua son la opcion perfecta. Disfruta de la libertad de recorrer las aguas turquesas de Cala Blava a toda velocidad, sintiendo el viento y el mar a tu alrededor.",
      "imagen": "Actividad_moto_agua.jpg",
      "precio": 45,
      "cupo": 12,
      "fecha_actividad": "2024-11-15"
    }
  ]

export const ActividadesView = () =>{
    const [visible, setVisible] = useState(false);
    const [actividadSeleccionada, setActividadSeleccionada] = useState(null);
    const showModal = (nombre_actividad) => {
        setActividadSeleccionada(nombre_actividad);
        setVisible(true)
    };
    const hideModal = () => setVisible(false);
      
    return(
        <ScrollView>
            <View style={actividadesStyle.container}>
                <Text style={actividadesStyle.title}>Actividades view</Text>
                    {actividadData.map((item, index) => (
                        <Card style={actividadesStyle.actividadContainer} key={index}>
                            <Image source={imagenes[item.imagen]} style={actividadesStyle.actividadImagen} />
                            <Card.Content>
                                <Text style={actividadesStyle.cardTitle}>{item.nombre_actividad}</Text>
                                <Text style={actividadesStyle.cardDescription}>{item.descripcion}</Text>
                                <View style={{flexDirection: "row", justifyContent: "space-between", marginBottom: 14}}>
                                    <Text style={actividadesStyle.cardText}>Precio: {item.precio}$</Text>
                                    <Text style={actividadesStyle.cardText}>Cupos disponibles: {item.cupo}</Text>
                                </View>
                                <Text style={actividadesStyle.cardFecha}>Fecha de la actividad: {item.fecha_actividad}</Text>
                                <Button style={actividadesStyle.cardButton} onPress={() => showModal(item.nombre_actividad)}>
                                    <Text style={actividadesStyle.cardButtonText}>Reservar</Text>
                                </Button>

                                {/*Modal para reservas actividad*/}
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
        </ScrollView>
    )
}

export default ActividadesView;