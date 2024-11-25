import React from "react";
import { BottomNavigation, Text } from 'react-native-paper';
import ActividadesView from "./actividadesView";
import HabitacionesView from "./habitacionesView";
import perfil from "./perfil";
import reservasView from "./components/reservasView";

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
                navigationState={{ index, routes }}
                onIndexChange={setIndex}
                renderScene={renderScene} 
                barStyle={{ backgroundColor: '#1E212D'}} 
                activeColor="#E78F81" 
                inactiveColor="#FAF3E0" 
                />
    )
}

export default Home;