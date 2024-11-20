import { useState } from "react";
import { View, Text, Image, StyleSheet } from "react-native";
import { Provider as PaperProvider, TextInput } from "react-native-paper";
import { Button } from 'react-native-paper';
import loginStyle from "../styles/loginStyle";
import { url } from "../utils/Constants";
export const Login = ({ navigation }) => {

    const [usuario, setUsuario] = useState(null);
    const [password, setPassword] = useState(null);

    const userLogin = async () => {
        try {
            const response = await fetch(`${url}/loginJSON`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(usuario + '-' + password),
            });
    
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`); 
            }
    
            const data = await response.json();
            console.log('Login exitoso:', data);
            navigation.navigate('Home');

        } catch (error) {
            console.log('Error al iniciar sesión:', error);
            alert('Usuario o contraseña incorrecto. Intenta de nuevo.');
        }
    };
    
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
                    <TextInput 
                        label="Usuario" 
                        style={loginStyle.inputBackground} 
                        value={usuario} 
                        onChangeText={(text) => setUsuario(text)}  
                    />
                    <TextInput 
                        label="Contreseña" 
                        style={loginStyle.inputBackground} 
                        value={password} 
                        onChangeText={(text) => setPassword(text)}  
                        secureTextEntry 
                    />
                    <Button style={loginStyle.button} icon="login" mode="contained" onPress={() => userLogin()}>
                        Acceder
                    </Button>
                </View>
            </View>
    );
}

export default Login;
