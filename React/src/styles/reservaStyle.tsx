import { StyleSheet } from "react-native";

const reservaStyle = StyleSheet.create({
    content: {
        backgroundColor: "#1E212D",
    },
    titleView: {
        alignItems: 'center',
        padding: 10,
        marginTop: 50,
    },
    title: {
        fontSize: 40,
        fontWeight: 900,
        fontFamily: 'Copperplate',
        color: "#E78F81",
    },
    contentView: {
        padding: 20,
        paddingTop: 10,
    },
    subtitileView: {
        alignItems: 'center',
        padding: 20,
    },
    subtitile: {
        fontSize: 30,
        fontWeight: 900,
        fontFamily: 'Copperplate',
        color: "#E78F81",
    },
    reserva: {
        margin: 10,
        padding: 10,
        backgroundColor: "#EAD7BB",

    },
    titleContentReserva: {
        fontSize: 25,
        fontWeight: 900,
        fontFamily: 'Cochin',
        marginBottom: 5,
        color: "#1E212D",
    },
    subtitleContentReserva: {
        fontSize: 15,
        color: "#1E212D",
        fontFamily: "KohinoorTelugu-Regular",
    },
    actionReserva: {
        alignItems: "center",
        padding: 5,
    },
    buttonBackground: {
        backgroundColor: "#E78F81",
    },
    buttonColor: {
        color: "#E78F81",
    },
    buttonBorder: {
        borderColor: "#E78F81",
        borderWidth: 2,
    }
})

export default reservaStyle;