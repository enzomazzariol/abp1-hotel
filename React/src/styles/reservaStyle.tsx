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
    underling: {
        fontWeight: "bold",
        fontFamily: 'Cochin',
        fontSize: 20,
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
    },
    noDataText: {
        fontSize: 18,
        textAlign: "center",
        color: "#EAD7BB",
        fontFamily: "Cochin",
        marginVertical: 20,
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

    modal: {
        backgroundColor: "#FAF3E0",
        padding: 25,
        height: 300,
        width: 340,
        borderRadius: 12,
        marginHorizontal: 40,
        marginTop: 280,
    },

    modalTitle: {
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
    buttonRow: {
        flexDirection: "row", // Alinea los botones en fila
        justifyContent: "space-around", // Espacio uniforme entre los botones
        marginBottom: 10,
    },
    buttonSpacing: {
        flex: 1, // Permite que los botones ocupen espacio uniforme
        marginHorizontal: 5, // Espaciado lateral entre botones
    },
    



});

export default reservaStyle;
