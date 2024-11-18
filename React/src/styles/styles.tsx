import { StyleSheet } from "react-native";

const styles = StyleSheet.create({

    //Login
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

    loginContainer: {
        display: "flex",
        flexDirection: "column",
        width: 300
    },

   input: {
    marginBottom: 20
   },

    barraNavegacion: {
     backgroundColor: "#E78F81",
    },

    safeArea: {
      flex: 1,
      backgroundColor: '#fff',
    },
  });

  export default styles;