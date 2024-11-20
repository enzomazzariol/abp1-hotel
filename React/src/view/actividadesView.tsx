import { View, ScrollView } from "react-native";
import actividadesStyle from "../styles/actividadesStyle";
import { Text, ActivityIndicator } from "react-native-paper";
import { useEffect, useState } from "react";
import { getActividades} from "../dao/ActividadesDao";
import  ActividadCard from "./components/actividadCard";

export const ActividadesView = () =>{

    const [actividad, setActividades] = useState([]);

    async function cargarActividades() {
        const data = await getActividades();
        setActividades(data);
    }

    useEffect(() => {
        cargarActividades();
    }, []);
      
    return(
        <ScrollView>
            <View style={actividadesStyle.container}>
                <Text style={actividadesStyle.title}>Actividades view</Text>
                {actividad.length === 0 ? (
                    <ActivityIndicator color={"#fff"} size={"large"} />
                ) : (
                    <ActividadCard actividad={actividad} cargarActividades={cargarActividades}/>
                )}
                
            </View>
        </ScrollView>
    )
}

export default ActividadesView;