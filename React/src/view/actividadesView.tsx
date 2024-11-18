import { View, ScrollView } from "react-native";
import actividadesStyle from "../styles/actividadesStyle";
import { Text, ActivityIndicator } from "react-native-paper";
import { useEffect, useState } from "react";
import { getActividades} from "../dao/ActividadesDao";
import  ActividadCard from "./components/actividadCard";

export const ActividadesView = () =>{

    const [actividad, setActividades] = useState([]);

    useEffect(() => {
        checkData();
    }, []);
      
    async function checkData() {
        setActividades(await getActividades());
    }

    return(
        <ScrollView>
            <View style={actividadesStyle.container}>
                <Text style={actividadesStyle.title}>Actividades view</Text>
                {actividad.length === 0 ? (
                    <ActivityIndicator color={"#fff"} size={"large"} />
                ) : (
                    <ActividadCard actividad={actividad}/>
                )}
                
            </View>
        </ScrollView>
    )
}

export default ActividadesView;