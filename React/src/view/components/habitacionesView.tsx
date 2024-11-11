import { useState } from "react"
import { View, Text } from "react-native";
import styles from "../../styles/styles";
import { Provider as PaperProvider, TextInput } from "react-native-paper";
import { Button } from 'react-native-paper';

export const HabitacionesView = () =>{

    return(
        <PaperProvider>
            <View style={styles.container}>
                <Text style={styles.title}>Habitaciones view</Text>
            </View>
        </PaperProvider>
    )
}

export default HabitacionesView;