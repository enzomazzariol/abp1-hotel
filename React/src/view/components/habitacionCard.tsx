import { useState, useEffect } from "react";
import { View, Image } from "react-native";
import { Button, Card, Modal, Portal, Text } from "react-native-paper";
import actividadesStyle from "../../styles/actividadesStyle";
import { DatePickerInput } from 'react-native-paper-dates'; 
import { sendHabitacion } from "../../dao/HabitacionesDao";
import { getUsuario } from "../../dao/perfilDao";

const imagenes = {
  'Habitacion_sencilla.jpg': require('../../assets/Habitacion_sencilla.jpg'),
  'Habitacion_doble.jpg': require('../../assets/Habitacion_doble.jpg'),
  'Habitacion_suite.jpg': require('../../assets/Habitacion_suite.jpg'),
};

export function HabitacionCard({ habitacion }) {
  const [modalVisible, setModalVisible] = useState(null);
  const [usuario, setUsuario] = useState({
    id: null,
});

  // Variables para el manejo de fechas de reserva
  const initialFechas = habitacion.map(() => ({ desde: null, hasta: null }));
  const [fechas, setFechas] = useState(initialFechas);

  // Mostrar modal para reservar la habitación
  const showModal = (index) => {
    setModalVisible(index);
  };

  // Ocultar modal para reservar la habitación
  const hideModal = () => {
    setModalVisible(null);
  };

// Restablecer las fechas al estado inicial para la habitación específica
const resetFechas = (index) => {
  setFechas((prev) => {
    const updatedFechas = [...prev];
    updatedFechas[index] = { desde: null, hasta: null };
    return updatedFechas;
  });
};

  useEffect(() => {
    cargarUsuario();
}, []);

// Recogerar datos del usuario
async function cargarUsuario() {
    const usuarioData = await getUsuario();
    console.log("Datos del usuario:", usuarioData);  
    setUsuario(usuarioData);
}

// Manejar fechas seleccionadas
  const handleFechaChange = (index, tipo, valor) => {
    setFechas((prev) => {
      const updatedFechas = [...prev];
      updatedFechas[index][tipo] = valor;
      return updatedFechas;
    });
  };

  return (
    <View style={actividadesStyle.cardContainer}>
      {habitacion.map((item, index) => (
        <Card style={actividadesStyle.actividadContainer} key={index}>
          <Image source={imagenes[item.imagen]} style={actividadesStyle.actividadImagen} />
          <Card.Content>
            <Text style={actividadesStyle.habitacionTitle}>Habitación {item.tipoHabitacion}</Text>
            <View style={{ flexDirection: "row", justifyContent: "space-between", marginBottom: 14 }}>
              <Text style={actividadesStyle.cardText}>Precio: {item.precio}$</Text>
              <Text style={actividadesStyle.cardText}>Estado: {item.estado}</Text>
            </View>
            <View style={{ flexDirection: "row", justifyContent: "space-between", marginBottom: 14, gap: 10 }}>
              <DatePickerInput
                locale="es-ES"
                label="Desde"
                value={fechas[index].desde}
                onChange={(d) => handleFechaChange(index, 'desde', d)}
                inputMode="start"
                validRange={{
                  startDate: new Date(),
                }}
                style={actividadesStyle.habitacionInput}
              />
              <DatePickerInput
                locale="es-ES"
                label="Hasta"
                value={fechas[index].hasta}
                onChange={(d) => handleFechaChange(index, 'hasta', d)}
                inputMode="start"
                validRange={{
                  startDate: new Date(),
                }}
                style={actividadesStyle.habitacionInput}
              />
            </View>
            <Button style={actividadesStyle.cardButton} 
              onPress={() => {
                if(!fechas[index].desde ||!fechas[index].hasta) {
                  alert('Debes seleccionar una fecha de inicio y fin.');
                  return;
                } else if (new Date(fechas[index].desde) >= new Date(fechas[index].hasta)) {
                  alert("La fecha de inicio debe ser anterior a la fecha de fin.");
                }  else {
                  showModal(index);
                }
              }}
            >
              <Text style={actividadesStyle.cardButtonText}>Reservar</Text>
            </Button>

            {/* Modal para reservas habitaciones */}
            <Portal>
              <Modal visible={modalVisible === index} onDismiss={hideModal} style={actividadesStyle.modal}>
                <Text style={actividadesStyle.modalTitle}>Reserva de Habitación</Text>
                <Text style={actividadesStyle.modalDescription}>
                  ¿Quieres reservar desde el {fechas[index].desde?.toLocaleDateString()} hasta el {fechas[index].hasta?.toLocaleDateString()} esta habitación?
                </Text>
                <Button
                  style={actividadesStyle.cardButton}
                  onPress={() => {
                    const formattedDesde = fechas[index].desde.toISOString().split('T')[0];
                    const formattedHasta = fechas[index].hasta.toISOString().split('T')[0];
                    sendHabitacion(usuario.id, item.id, formattedDesde, formattedHasta);
                    resetFechas(index);
                    alert('Reserva realizada con éxito!');
                    hideModal();
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