import { useState } from "react"
import { View, Text } from "react-native";
import styles from "../styles/styles";
import { Provider as PaperProvider, TextInput } from "react-native-paper";
import { Button } from 'react-native-paper';

export const Login = ({navigation}) =>{

    const [usuario, setUsuario] = useState("");
    const [password, setPassword] = useState("")

    return(
        <PaperProvider>
            <View style={styles.container}>
                <Text style={styles.title}>Login</Text>
                <View style={styles.loginContainer}>
                    <TextInput label="Usuario" style={styles.input}></TextInput>
                    <TextInput label="Password" style={styles.input}></TextInput>
                    <Button icon="camera" mode="contained" onPress={() => {navigation.navigate('Home')}}>Acceder</Button>
                </View>
            </View>
        </PaperProvider>
    )
}

export default Login;