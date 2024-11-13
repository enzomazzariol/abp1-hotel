import { StyleSheet } from "react-native";

const perfilStyle = StyleSheet.create({
    // Estilos específicos para la pantalla de perfil
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
      },
  
      title: {
          fontSize: 40,
          fontWeight: 900,
          fontFamily: 'Cochin',
          marginBottom: 30
      },
    profileContent: {
      marginBottom: 20,
      flexDirection: 'row',
      alignItems: 'center',
      justifyContent: 'space-between',
    },
    profileText: {
      fontSize: 18,
      color: '#E78F81',
      fontFamily: 'Figtree',
      fontWeight: '400',
    },
    profileValue: {
      fontSize: 16,
      color: '#000',
      backgroundColor: '#FAF3E0',
      borderRadius: 5,
      padding: 5,
      width: '60%',
    },
    profileImage: {
      alignSelf: 'center',
      marginBottom: 20,
    },
    imgWrapper: {
      width: 250,
      height: 250,
      borderRadius: 125,
      backgroundColor: '#f0f0f0',
      justifyContent: 'center',
      alignItems: 'center',
      overflow: 'hidden',
    },
    img: {
      width: '100%',
      height: '100%',
      resizeMode: 'cover',
      borderRadius: 125,
    },
    changeImageButton: {
      backgroundColor: '#007bff',
      color: '#fff',
      paddingVertical: 8,
      paddingHorizontal: 15,
      borderRadius: 5,
      marginTop: 10,
    },
    changeImageButtonHover: {
      backgroundColor: '#0056b3',
    },
    
  });
  
  export default perfilStyle;