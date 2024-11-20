import React, { useEffect, useState } from 'react';
import { View, Text, TextInput, Image, TouchableOpacity, Modal, Button, ScrollView } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import perfilStyle from '../styles/perfilStyle';
import { getUsuario } from '../dao/perfilDao';

// Componente de perfil
const Perfil = () => {
  const [usuario, setUsuario] = useState({
    id: '',
    nombre: '',
    email: '',
    imagen: '',
    password: '',
    rol: ''
  });

  const [modalVisible, setModalVisible] = useState(false);
  const [newImagen, setNewImagen] = useState('');

  useEffect(() => {
    cargarUsuario();
}, []);
  
async function cargarUsuario() {
  const usuarioData = await getUsuario();
  console.log("Datos del usuario:", usuarioData);  
  setUsuario(usuarioData);
}
  
  // Función para pedir permisos y seleccionar una imagen de la galería
  const pickImage = async () => {
    // Solicitar permisos para acceder a la galería
    const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
    if (status !== 'granted') {
      alert('Se necesitan permisos para acceder a las fotos');
      return;
    }

    // Abrir la galería de imágenes
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images, // Acepta solo imágenes
      allowsEditing: true, // Permitir edición (recorte) de la imagen
      aspect: [1, 1], // Relación de aspecto 1:1 (cuadrado)
      quality: 1, // Alta calidad
    });

   
    if (!result.canceled) {
      setNewImagen(result.assets[0].uri);
    }
  };

  // Función para cambiar la imagen de perfil
  const handleImageChange = () => {
    if (newImagen) {
      setUsuario({ ...usuario, imagen: newImagen }); // Cambiar la imagen de perfil
      setModalVisible(false); // Cerrar el modal
    } else {
      alert('Por favor selecciona una imagen válida');
    }
  };

  return (
    <ScrollView style={perfilStyle.container}>
      <View style={perfilStyle.profileImgContainer}>
        <View style={perfilStyle.imgWrapper}>
          <Image
            source={{
              uri: usuario.imagen || 'https://img.pokemondb.net/artwork/vaporeon.jpg', // Imagen vaporeon por defecto
            }}
            style={perfilStyle.img}
          />
        </View>
        <TouchableOpacity
          style={perfilStyle.changeImageButton}
          onPress={() => setModalVisible(true)} 
        >
          <Text style={perfilStyle.changeImageButtonText}>Cambiar foto</Text>
        </TouchableOpacity>
      </View>

      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Nombre</Text>
        <View>
        <View style={perfilStyle.profileValue}>
          <Text>{usuario.nombre}</Text>
        </View>
        <TouchableOpacity style={perfilStyle.editButton} onPress={() => { /* Lógica para editar */ }}>
          <Text style={perfilStyle.editButtonText}>Editar</Text>
        </TouchableOpacity>
        </View>
      </View>

      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Correo Electrónico</Text>
        <View style={perfilStyle.profileValue}>
          <Text>{usuario.email}</Text>
        </View>
        <TouchableOpacity style={perfilStyle.editButton} onPress={() => { /* Lógica para editar */ }}>
          <Text style={perfilStyle.editButtonText}>Editar</Text>
        </TouchableOpacity>
      </View>

      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Contraseña</Text>
        <View style={perfilStyle.profileValue}>
          <Text>{usuario.password}</Text>
        </View>
        <TouchableOpacity style={perfilStyle.editButton} onPress={() => { /* Lógica para editar */ }}>
          <Text style={perfilStyle.editButtonText}>Editar</Text>
        </TouchableOpacity>
      </View>

      <TouchableOpacity style={perfilStyle.logoutButton} onPress={() => { /* Lógica para cerrar sesion */ }}>
        <Text style={perfilStyle.logoutButtonText}>Cerrar sesión</Text>
      </TouchableOpacity>

      {/* Botón Admin, visible solo si el usuario es admin */}
      {usuario.rol === 'admin' && (
        <TouchableOpacity style={perfilStyle.buttonAdmin} onPress={() => alert('Accediendo al panel de admin')}>
          <Text style={perfilStyle.buttonAdminText}>Admin</Text>
        </TouchableOpacity>
      )}
       

      {/* Modal para cambiar la imagen de perfil */}
      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)}
      >
        <View style={perfilStyle.modalContainer}>
          <View style={perfilStyle.modalContent}>
            <Text style={perfilStyle.modalTitle}>Cambiar Imagen de Perfil</Text>
            
            {/* Botón para seleccionar imagen de la galería */}
            <Button title="Seleccionar imagen" onPress={pickImage} />

            {/* Mostrar la imagen seleccionada */}
            {newImagen ? (
              <Image source={{ uri: newImagen }} style={perfilStyle.previewImage} />
            ) : null}

            {/* Botones para guardar o cancelar los cambios */}
            <TouchableOpacity style={perfilStyle.modalButton} onPress={handleImageChange}>
              <Text style={perfilStyle.modalButtonText}>Guardar Cambios</Text>
            </TouchableOpacity>
            
            <TouchableOpacity
              style={perfilStyle.modalButton}
              onPress={() => setModalVisible(false)} 
            >
              <Text style={perfilStyle.modalButtonText}>Cancelar</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </ScrollView>
  );
};

export default Perfil;

