import React from 'react';
import { View, Text, Image } from 'react-native';
import { TextInput, Button } from 'react-native-paper';
import perfilStyle from '../../styles/perfilStyle'; 

// Ejemplo de usuario en formato JSON
const usuario = {
  nombre: 'Juan Pérez',
  email: 'juan.perez@example.com',
  imagen: 'https://www.example.com/juan-perez.jpg', // URL de la imagen de perfil
  rol: 'admin' // Puede ser 'admin' o 'user'
};

const Perfil = () => {
  return (
    <View style={perfilStyle.container}>
      <Text style={perfilStyle.title}>Perfil de {usuario.nombre}</Text>

      {/* Imagen de perfil */}
      <View style={perfilStyle.profileImage}>
        <View style={perfilStyle.imgWrapper}>
          <Image
            style={perfilStyle.img}
            source={{
              uri: usuario.imagen ? usuario.imagen : 'https://www.example.com/default-profile.png',
            }}
          />
        </View>
        <Button
          mode="contained"
          onPress={() => { /* Función para cambiar imagen */ }}
          style={perfilStyle.changeImageButton}
        >
          Cambiar foto
        </Button>
      </View>

      {/* Información del usuario */}
      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Nombre:</Text>
        <Text style={perfilStyle.profileValue}>{usuario.nombre}</Text>
        <Button mode="outlined">
          Editar
        </Button>
      </View>

      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Correo Electrónico:</Text>
        <Text style={perfilStyle.profileValue}>{usuario.email}</Text>
        <Button mode="outlined" >
          Editar
        </Button>
      </View>

      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Contraseña:</Text>
        <Text style={perfilStyle.profileValue}>********</Text>
        <Button mode="outlined" >
          Editar
        </Button>
      </View>

      {/* Botón de cerrar sesión */}
      <Button
        mode="contained"
        onPress={() => { /* Función para cerrar sesión */ }}
      >
        Cerrar sesión
      </Button>

      {/* Condición para mostrar el botón de admin */}
      {usuario.rol === 'admin' && (
        <Button
          mode="contained"
          onPress={() => { /* Función para acceder al panel de admin */ }}
        >
          Admin
        </Button>
      )}
    </View>
  );
};

export default Perfil;
