import { useState, useEffect } from "react";
import { View, Text, ScrollView } from "react-native";
import { Provider as PaperProvider, Card, Button, Portal, Modal } from "react-native-paper";
import reservaStyle from "../../styles/reservaStyle";
import { DetalleReservaActividad, DetalleReservaHabitacion } from "../../model/Reserva";
import { getReservas, sendReservas } from "../../dao/ReservasDao";
import { getUsuario } from "../../dao/perfilDao";

export const ReservasView = () => {
    const [detalleReservaActividad, setDetalleReservaActividad] = useState<DetalleReservaActividad[]>([]);
    const [detalleReservaHabitacion, setDetalleReservaHabitacion] = useState<DetalleReservaHabitacion[]>([]);
    const [visibleActividad, setVisibleActividad] = useState(false);
    const [visibleHabitacion, setVisibleHabitacion] = useState(false);
    const [reservaSeleccionada, setReservaSeleccionada] = useState<any>(null);
    const [usuario, setUsuario] = useState({
        id: null,
    });

    useEffect(() => {
        cargarReservas();
    }, []);


    // Función para cargar datos
    const cargarReservas = async () => {
        try {
            const { reservasActividad, reservasHabitacion } = await getReservas();
            setDetalleReservaActividad(reservasActividad);
            setDetalleReservaHabitacion(reservasHabitacion);
        } catch (error) {
            console.error("Error al cargar las reservas:", error);
        }
    };

    // Abrir modal para actividades
    const showModalActividad = (actividad: DetalleReservaActividad) => {
        setReservaSeleccionada(actividad);
        setVisibleActividad(true);
    };

    // Abrir modal para habitaciones
    const showModalHabitacion = (habitacion: DetalleReservaHabitacion) => {
        setReservaSeleccionada(habitacion);
        setVisibleHabitacion(true);
    };

    // Cerrar modal
    const hideModal = () => {
        setReservaSeleccionada(null);
        setVisibleActividad(false);
        setVisibleHabitacion(false);
    };

    return (
        <ScrollView style={reservaStyle.content}>
            <View style={reservaStyle.titleView}>
                <Text style={reservaStyle.title}>Mis Reservas</Text>
            </View>

            {/* Reservas de Actividades */}
            <View style={reservaStyle.contentView}>
                <View style={reservaStyle.subtitileView}>
                    <Text style={reservaStyle.subtitile}>Actividades</Text>
                </View>
                {detalleReservaActividad.map((actividad) => (
                    <Card key={actividad.id} style={reservaStyle.reserva}>
                        <Card.Content>
                            <Text style={reservaStyle.titleContentReserva}>{actividad.nombreActividad}</Text>
                            <Text style={reservaStyle.subtitleContentReserva}>Estado: <Text style={reservaStyle.underling}>{actividad.estadoReservaActividad}</Text></Text>
                            <Text style={reservaStyle.subtitleContentReserva}>Fecha de la Reserva: {actividad.fechaReservaActividad}</Text>
                        </Card.Content>

                        <View style={reservaStyle.actionReserva}>
                            {actividad.estadoReservaActividad == "reservado" && (
                                <Card.Actions>
                                    <Button
                                        style={reservaStyle.buttonBorder}
                                        onPress={() => showModalActividad(actividad)}
                                    >
                                        <Text style={reservaStyle.buttonColor}>Ver Detalles</Text>
                                    </Button>
                                </Card.Actions>
                            )}
                        </View>

                    </Card>
                ))}
            </View>

            {/* Modal para actividades */}
            <Portal>
                <Modal visible={visibleActividad} onDismiss={hideModal} style={reservaStyle.modal}>
                    <Text style={reservaStyle.modalTitle}>{reservaSeleccionada?.nombreActividad}</Text>
                    <Text style={reservaStyle.modalDescription}>Estado: {reservaSeleccionada?.estadoReservaActividad}</Text>
                    <Text style={reservaStyle.modalDescription}>Fecha: {reservaSeleccionada?.fechaReservaActividad}</Text>

                    <View style={reservaStyle.buttonRow}>
                        <Button
                            style={[reservaStyle.buttonBackground, reservaStyle.buttonSpacing]}
                            onPress={() => {
                                sendReservas("actualizarActividades", reservaSeleccionada.id, setVisibleActividad, cargarReservas);
                                console.log("Acción completada sobre la reserva:", reservaSeleccionada);
                                hideModal();
                            }}
                        >
                            <Text style={reservaStyle.cardButtonText}>Completado</Text>
                        </Button>
                        <Button
                            style={[reservaStyle.buttonBackground, reservaStyle.buttonSpacing]}
                            onPress={() => {
                                sendReservas("eliminarActividades", reservaSeleccionada.id, setVisibleActividad, cargarReservas);
                                console.log("Acción cancelada sobre la reserva:", reservaSeleccionada);
                                hideModal();
                            }}
                        >
                            <Text style={reservaStyle.cardButtonText}>Cancelar</Text>
                        </Button>
                    </View>
                    <Button style={reservaStyle.modalButton} onPress={hideModal}>
                        <Text style={reservaStyle.cardButtonText}>Volver</Text>
                    </Button>
                </Modal>
            </Portal>

            {/* Reservas de Habitaciones */}
            <View style={reservaStyle.contentView}>
                <View style={reservaStyle.subtitileView}>
                    <Text style={reservaStyle.subtitile}>Habitaciones</Text>
                </View>
                {detalleReservaHabitacion.map((habitacion) => (
                    <Card key={habitacion.id} style={reservaStyle.reserva}>
                        <Card.Content>
                            <Text style={reservaStyle.titleContentReserva}>{habitacion.tipoDeHabitacion}</Text>
                            <Text style={reservaStyle.subtitleContentReserva}>Estado: <Text style={reservaStyle.underling}>{habitacion.estadoReserva}</Text></Text>
                            {habitacion.fechas && <Text style={reservaStyle.subtitleContentReserva}>Fechas: {habitacion.fechas}</Text>}
                        </Card.Content>

                        <View style={reservaStyle.actionReserva}>
                            {habitacion.estadoReserva == "reservado" && (
                                <Card.Actions>
                                    <Button
                                        style={reservaStyle.buttonBorder}
                                        onPress={() => showModalHabitacion(habitacion)}
                                    >
                                        <Text style={reservaStyle.buttonColor}>Ver Detalles</Text>
                                    </Button>
                                </Card.Actions>
                            )}
                        </View>

                    </Card>
                ))}
            </View>

            {/* Modal para habitaciones */}
            <Portal>
                <Modal visible={visibleHabitacion} onDismiss={hideModal} style={reservaStyle.modal}>
                    <Text style={reservaStyle.modalTitle}>{reservaSeleccionada?.tipoDeHabitacion}</Text>
                    <Text style={reservaStyle.modalDescription}>Estado: {reservaSeleccionada?.estadoReserva}</Text>
                    <Text style={reservaStyle.modalDescription}>Fechas: {reservaSeleccionada?.fechas}</Text>

                    <View style={reservaStyle.buttonRow}>
                        <Button
                            style={[reservaStyle.buttonBackground, reservaStyle.buttonSpacing]}
                            onPress={() => {
                                sendReservas("actualizarHabitaciones", reservaSeleccionada.id, setVisibleHabitacion, cargarReservas);
                                console.log("Acción completada sobre la reserva:", reservaSeleccionada);
                                hideModal();
                            }}
                        >
                            <Text style={reservaStyle.cardButtonText}>Completado</Text>
                        </Button>
                        <Button
                            style={[reservaStyle.buttonBackground, reservaStyle.buttonSpacing]}
                            onPress={() => {
                                sendReservas("eliminarHabitaciones", reservaSeleccionada.id, setVisibleHabitacion, cargarReservas);
                                console.log("Acción cancelada sobre la reserva:", reservaSeleccionada);
                                hideModal();
                            }}
                        >
                            <Text style={reservaStyle.cardButtonText}>Cancelar</Text>
                        </Button>
                    </View>
                    <Button style={reservaStyle.modalButton} onPress={hideModal}>
                        <Text style={reservaStyle.cardButtonText}>Volver</Text>
                    </Button>
                </Modal>
            </Portal>
        </ScrollView>
    );
};

export default ReservasView;
