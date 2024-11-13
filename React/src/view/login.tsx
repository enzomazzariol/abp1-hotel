import { useState } from "react";
import { View, Text, Image, StyleSheet } from "react-native";
import { Provider as PaperProvider, TextInput } from "react-native-paper";
import { Button } from 'react-native-paper';
import loginStyle from "../styles/loginStyle";
import styles from "../styles/styles";

export const Login = ({ navigation }) => {

    const [usuario, setUsuario] = useState("");
    const [password, setPassword] = useState("");

    return (
            <View style={loginStyle.container}>
                <View style={loginStyle.imageContainer}>
                    <Image source={require('../assets/img/Imagen_portada_home.jpg')} style={loginStyle.img} />
                    <View style={loginStyle.overlay}>
                        <Text style={loginStyle.overlayText}>Bellmunt Hotel</Text>
                    </View>
                </View>
                <View style={loginStyle.loginContainer}>
                    <View style={loginStyle.viewTitle}>
                        <Text style={loginStyle.title}>Login</Text>
                    </View>
                    <TextInput label="Usuario" style={loginStyle.inputBackground} value={usuario} onChangeText={setUsuario} />
                    <TextInput label="Password" style={loginStyle.inputBackground} value={password} onChangeText={setPassword} secureTextEntry />
                    <Button style={loginStyle.button} icon="login" mode="contained" onPress={() => { navigation.navigate('Home') }}>
                        Acceder
                    </Button>
                </View>
            </View>
    );
}

export default Login;
