import { View, Image, ScrollView } from "react-native";
import actividadesStyle from "../../styles/actividadesStyle";
import { Button, Card, Modal, Portal, Text, TextInput } from "react-native-paper";
import { useState } from "react";
import { lightBlue100 } from "react-native-paper/lib/typescript/styles/themes/v2/colors";

const imagenes = {
    'Habitacion_sencilla.jpg': require('../../assets/Habitacion_sencilla.jpg'),
    'Habitacion_doble.jpg': require('../../assets/Habitacion_doble.jpg'),
    'Habitacion_suite.jpg': require('../../assets/Habitacion_suite.jpg'),
  };

const habitacionData = [
    {
        "tipoHabitacion": "sencilla", 
        "imagen": "Habitacion_sencilla.jpg",
        "precio": 50.00,
        "estado": "disponible"
    },
    {
        "tipoHabitacion": "doble",
        "imagen": "Habitacion_doble.jpg",
        "precio": 80.00,
        "estado": "disponible"
    },
    {
        "tipoHabitacion": "suite",
        "imagen": "Habitacion_suite.jpg",
        "precio": 150.00,
        "estado": "disponible"
    }
]

export const HabitacionesView = () =>{
    const [visible, setVisible] = useState(false);
    const showModal = () => setVisible(true);
    const hideModal = () => setVisible(false);
      
    return(
        <ScrollView>
            <View style={actividadesStyle.container}>
                <Text style={actividadesStyle.title}>Habitaciones view</Text>
                    {habitacionData.map((item, index) => (
                        <Card style={actividadesStyle.actividadContainer} key={index}>
                            <Image source={imagenes[item.imagen]} style={actividadesStyle.actividadImagen} />
                            <Card.Content>
                                <Text style={actividadesStyle.habitacionTitle}>Habitación {item.tipoHabitacion}</Text>
                                <View style={{flexDirection: "row", justifyContent: "space-between", marginBottom: 14}}>
                                    <Text style={actividadesStyle.cardText}>Precio: {item.precio}$</Text>
                                    <Text style={actividadesStyle.cardText}>Estado: {item.estado}</Text>
                                </View>
                                <View style={{flexDirection: "row", justifyContent: "space-between", marginBottom: 14, gap: 10}}>
                                    <TextInput 
                                        style={actividadesStyle.habitacionInput}
                                        label="Desde"
                                        />
                                    <TextInput 
                                        style={actividadesStyle.habitacionInput}
                                        label="Hasta"
                                        />
                                </View>
                                <Button style={actividadesStyle.cardButton} onPress={showModal}>
                                    <Text style={actividadesStyle.cardButtonText} >Reservar</Text>
                                </Button>

                                {/*Modal para reservas habitaciones*/}
                                <Portal>
                                    <Modal visible={visible} onDismiss={hideModal} style={actividadesStyle.modal}>
                                        <Text style={actividadesStyle.modalTitle}>Reserva de Habitacion</Text>
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

export default HabitacionesView;