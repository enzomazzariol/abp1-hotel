import React from "react";
import { BottomNavigation, Text } from 'react-native-paper';
import styles from "../styles/styles";
import actividadesView from "./components/actividadesView";
import habitacionesView from "./components/habitacionesView";
import perfil from "./components/perfil";
import reservasView from "./components/reservasView";

export const Home = () => {

    const [index, setIndex] = React.useState(0);
    const [routes] = React.useState([
      { key: 'recents', title: 'Mis reservas', focusedIcon: 'heart', unfocusedIcon: 'heart-outline'},
      { key: 'albums', title: 'Habitaciones', focusedIcon: 'bed', unfocusedIcon: 'bed-outline'},
      { key: 'music', title: 'Actividades', focusedIcon: 'swim'},
      { key: 'notifications', title: 'Perfil', focusedIcon: 'account', unfocusedIcon: 'account-outline' },
    ]);
  
    const renderScene = BottomNavigation.SceneMap({
      recents: reservasView,
      music: actividadesView,
      albums: habitacionesView,
      notifications: perfil,
    });
  
    return(
            <BottomNavigation
                navigationState={{ index, routes }}
                onIndexChange={setIndex}
                renderScene={renderScene} />
    )
}

export default Home;