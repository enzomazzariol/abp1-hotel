import { StyleSheet } from "react-native";

const loginStyle = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: "#1E212D",
    },
    imageContainer: {
        position: 'relative',
        width: "100%",
        aspectRatio: 16 / 9, // Ajusta según la relación de aspecto de la imagen
    },
    img: {
        width: "100%",
        height: "100%",
        resizeMode: "cover",
    },
    overlay: {
        ...StyleSheet.absoluteFillObject, // Ocupa todo el espacio de la imagen
        backgroundColor: "rgba(0, 0, 0, 0.6)", // Color negro con opacidad
        justifyContent: "center",
        alignItems: "center",
    },
    overlayText: {
        color: "#E78F81",
        fontSize: 24,
        fontWeight: "bold",
        fontFamily: "Copperplate"
    },
    loginContainer: {
        display: "flex",
        flexDirection: "column",
        width: 300,
    },
    viewTitle: {
        alignItems: "center",
        margin: 30,
    },
    title: {
        color: "#E78F81",
        fontSize: 40,
        fontWeight: "bold",
        fontFamily: "Copperplate"
    },
    button: {
        backgroundColor: "#E78F81",
    },
    inputBackground: {
        backgroundColor: "#EAD7BB",
        marginBottom: 20,
    }
});

export default loginStyle;
