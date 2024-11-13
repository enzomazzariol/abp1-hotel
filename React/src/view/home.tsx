import React from "react";
import { BottomNavigation, Text } from 'react-native-paper';
import ActividadesView from "./components/actividadesView";
import HabitacionesView from "./components/habitacionesView";
import perfil from "./components/perfil";
import reservasView from "./components/reservasView";
import styles from "../styles/styles";

export const Home = () => {

    const [index, setIndex] = React.useState(0);
    const [routes] = React.useState([
      { key: 'misReservas', title: 'Mis reservas', focusedIcon: 'heart', unfocusedIcon: 'heart-outline'},
      { key: 'habitaciones', title: 'Habitaciones', focusedIcon: 'bed', unfocusedIcon: 'bed-outline'},
      { key: 'actividades', title: 'Actividades', focusedIcon: 'swim'},
      { key: 'perfil', title: 'Perfil', focusedIcon: 'account', unfocusedIcon: 'account-outline' },
    ]);
  
    const renderScene = BottomNavigation.SceneMap({
      misReservas: reservasView,
      habitaciones: HabitacionesView,
      actividades: ActividadesView,
      perfil: perfil,
    });
  
    return(
            <BottomNavigation
                style={styles.barraNavegacion}
                navigationState={{ index, routes }}
                onIndexChange={setIndex}
                renderScene={renderScene} />
    )
}

export default Home;