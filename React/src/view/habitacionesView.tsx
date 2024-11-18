import { View, Image, ScrollView, ActivityIndicator } from "react-native";
import actividadesStyle from "../styles/actividadesStyle";
import { Text } from "react-native-paper";
import { useEffect, useState } from "react";
import { getHabitaciones } from "../dao/HabitacionesDao";
import { HabitacionCard } from "./components/habitacionCard";

export const HabitacionesView = () =>{

    const[habitacion, setHabitacion] = useState([]);

    useEffect(() => {
        getHabitaciones().then((habitacion) => {
            setHabitacion(habitacion);
        });
    }, []);
      
    return(
        <ScrollView>
            <View style={actividadesStyle.container}>
                <Text style={actividadesStyle.title}>Habitaciones view</Text>
                {habitacion.length === 0? (
                    <ActivityIndicator color={"#fff"} size={"large"} />
                ) : (
                    <HabitacionCard habitacion={habitacion}/>
                )}
            </View>
        </ScrollView>
    )
}

export default HabitacionesView;