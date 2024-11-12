import { useState } from "react";
import { View, Text, ScrollView } from "react-native";
import styles from "../../styles/styles";
import { Provider as PaperProvider, TextInput, Card, Button } from "react-native-paper";
import reservaStyle from '../../styles/reservaStyle';
import { DetalleReservaActividad, DetalleReservaHabitacion } from "../../model/Reserva";

const reservasData = {
    detalleReservaActividad: [
        {
            id: 1,
            nombreActividad: "Surf en Cala Blava",
            estadoReservaActividad: "reservado",
            fechaReservaActividad: "2024-11-01",
        },
        {
            id: 2,
            nombreActividad: "Yoga al amanecer",
            estadoReservaActividad: "completado",
            fechaReservaActividad: "2024-10-20"
        }
    ],
    detalleReservaHabitacion: [
        {
            id: 1,
            tipoDeHabitacion: "Suite",
            estadoReserva: "reservado",
            fechaReserva: "2024-11-01",
            fechas: "2024-11-01 a 2024-11-03"
        },
        {
            id: 2,
            tipoDeHabitacion: "Doble",
            estadoReserva: "cancelado",
            fechaReserva: "2024-10-15",
            fechas: "2024-10-15 a 2024-10-18"
        }
    ]
};

export const ReservasView = () => {
    const [detalleReservaActividad, setDetalleReservaActividad] = useState<DetalleReservaActividad[]>(reservasData.detalleReservaActividad);
    const [detalleReservaHabitacion, setDetalleReservaHabitacion] = useState<DetalleReservaHabitacion[]>(reservasData.detalleReservaHabitacion);

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
                            <Text style={reservaStyle.subtitleContentReserva}>Estado: {actividad.estadoReservaActividad}</Text>
                            <Text style={reservaStyle.subtitleContentReserva}>Fecha de la Reserva: {actividad.fechaReservaActividad}</Text>
                        </Card.Content>
                        <View style={reservaStyle.actionReserva}>
                            <Card.Actions>
                                <Button style={reservaStyle.buttonBorder} onPress={() => {/* Lógica para cancelar */ }}><Text style={reservaStyle.buttonColor}>Cancelar</Text></Button>
                                <Button style={reservaStyle.buttonBackground} onPress={() => {/* Lógica para completar */ }}>Completado</Button>
                            </Card.Actions>
                        </View>
                    </Card>
                ))}
            </View>

            {/* Reservas de Habitaciones */}
            <View style={reservaStyle.contentView}>
                <View style={reservaStyle.subtitileView}>
                    <Text style={reservaStyle.subtitile}>Habitaciones</Text>
                </View>
                {detalleReservaHabitacion.map((habitacion) => (
                    <Card key={habitacion.id} style={reservaStyle.reserva}>
                        <Card.Content>
                            <Text style={reservaStyle.titleContentReserva}>{habitacion.tipoDeHabitacion}</Text>
                            <Text style={reservaStyle.subtitleContentReserva}>Estado: {habitacion.estadoReserva}</Text>
                            <Text style={reservaStyle.subtitleContentReserva}>Fecha de la Reserva: {habitacion.fechaReserva}</Text>
                            {habitacion.fechas && <Text style={reservaStyle.subtitleContentReserva}>Fechas: {habitacion.fechas}</Text>}
                        </Card.Content>
                        <View style={reservaStyle.actionReserva}>
                            <Card.Actions>
                                <Button style={reservaStyle.buttonBorder} onPress={() => {/* Lógica para cancelar */ }}><Text style={reservaStyle.buttonColor}>Cancelar</Text></Button>
                                <Button style={reservaStyle.buttonBackground} onPress={() => {/* Lógica para completar */ }}>Completado</Button>
                            </Card.Actions>
                        </View>
                    </Card>
                ))}
            </View>
        </ScrollView>
    );
}

export default ReservasView;
