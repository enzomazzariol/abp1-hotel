import { StyleSheet } from "react-native";
import { Figtree_400Regular, Figtree_700Bold } from '@expo-google-fonts/figtree'; // Importa las variantes de la fuente
import { transparent } from "react-native-paper/lib/typescript/styles/themes/v2/colors";


const actividadesStyle = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        padding: 24,
        backgroundColor: "#1E212D",
      },

      title: {
        fontFamily: "Copperplate",
        fontWeight: 500,
        fontSize: 40,
        marginBottom: 30,
        marginTop: 30,
        color: "#E78F81"
      },

    actividadContainer: {
        width: 360,
        borderWidth: 0,
        borderColor: '#ccc',
        borderRadius: 5,
        backgroundColor: "#EAD7BB",
        color: "#1E212D",
        marginBottom: 40,
        borderStartStartRadius: 10,
        borderStartEndRadius: 10
      },

      actividadImagen: {
        width: '100%',
        height: 220,
        resizeMode: 'cover', 
        marginBottom: 10,
        borderStartStartRadius: 10,
        borderStartEndRadius: 10
      },

      // Card
      cardContainer: {
        backgroundColor: "#1E212D",
      },

      cardTitle: {
        fontSize: 28,
        fontWeight: 300,
        textAlign: "center",
        marginBottom: 10,
        fontFamily: 'KohinoorTelugu-Medium',
      },

      cardDescription: {
        fontSize: 18,
        marginBottom: 10,
        fontFamily: 'KohinoorTelugu-Regular',
      },

      cardText: {
        fontFamily: 'KohinoorTelugu-Medium',
        fontWeight: 900,
        fontSize: 22,
      },

      cardFecha: {
        fontFamily: 'KohinoorTelugu-Medium',
        fontSize: 18,
        fontWeight: 900,
        marginBottom: 14
      },

      cardButton: {
        padding: 2,
        backgroundColor: "#E78F81",
        color: "#1E1E1E"
      },

      cardButtonText: {
        color: "#1E1E1E",
        fontWeight: 700,
        fontFamily: "KohinoorTelugu-Medium"
      },

      //Habitaciones
      habitacionTitle: {
        fontSize: 28,
        fontWeight: 300,
        marginBottom: 15,
        fontFamily: 'KohinoorTelugu-Medium',
      },

      habitacionInput: {
        width: 150,
        height: 40,
        backgroundColor: "#FFF5E4",
      },

      // Modal para reservar actividad
      modal: {
        backgroundColor: "#FAF3E0",
        padding: 25,
        height: 300,
        width: 340,
        borderRadius: 12,
        marginHorizontal: 40,
        marginTop: 280, 
       },

       modalTitle:{
        fontSize: 24,
        fontWeight: 900,
        fontFamily: "Copperplate",
        textAlign: "center",
        marginBottom: 20
       },

       modalDescription: {
        fontSize: 20,
        fontFamily: "KohinoorTelugu-Medium",
        textAlign: "center",
        marginBottom: 20
       },

       modalButton: {
        padding: 2,
        marginTop: 10,
        backgroundColor: "transparent",
        color: "#1E1E1E",
        borderColor: "#E78F81",
        borderWidth: 2
       },

})

export default actividadesStyle;