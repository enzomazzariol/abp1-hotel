import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Image, TouchableOpacity, Modal, ScrollView, ActivityIndicator, Alert, Button } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import perfilStyle from '../styles/perfilStyle';
import { getUsuarioById, updateUsuario } from '../dao/UsuariosDAO';
import { getUsuario } from '../dao/perfilDao';


const Perfil = () => {
  const [usuario, setUsuario] = useState({
    id: '',
    nombre: '',
    email: '',
    imagen: '',
    password: '',
    rol: ''
  });

  const [loading, setLoading] = useState(true); // Estado de carga
  const [error, setError] = useState(null); // Estado para manejar errores

  const [modalVisible, setModalVisible] = useState(false);
  const [newImagen, setNewImagen] = useState('');
  const [isEditing, setIsEditing] = useState(false); 

  // Estados para manejar los nuevos valores de los campos editables
  const [newNombre, setNewNombre] = useState('');
  const [newEmail, setNewEmail] = useState('');
  const [newPassword, setNewPassword] = useState('');

  useEffect(() => {
    cargarUsuario();
}, []);
  
  async function cargarUsuario() {
    const usuarioData = await getUsuario();
    console.log("Datos del usuario:", usuarioData);  
    setUsuario(usuarioData);
  }

  // Función para seleccionar una imagen de la galería
  const pickImage = async () => {
    const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
    if (status !== 'granted') {
      alert('Se necesitan permisos para acceder a las fotos');
      return;
    }

    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],
      quality: 1,
    });

    if (!result.canceled) {
      setNewImagen(result.assets[0].uri);
    }
  };

  // Función para cambiar la imagen de perfil
  const handleImageChange = () => {
    if (newImagen) {
      setUsuario({ ...usuario, imagen: newImagen });
      setModalVisible(false);
    } else {
      alert('Por favor selecciona una imagen válida');
    }
  };

  // Función para guardar los cambios en el backend
  const handleSaveChanges = async () => {
    if (!newNombre.trim() || !newEmail.trim()) {
      Alert.alert('Error', 'Todos los campos son obligatorios');
      return;
    }

    try {
      const updatedUser = await updateUsuario({
        id: usuario.id,
        nombre: newNombre,
        email: newEmail,
        password: newPassword || null, // Solo actualiza la contraseña si se proporciona
      });

      if (updatedUser) {
        setUsuario(updatedUser); // Actualiza el estado con los datos nuevos
        setIsEditing(false); // Salir del modo edición
        Alert.alert('Éxito', 'Cambios guardados con éxito');
      } else {
        throw new Error('Error en la actualización');
      }
    } catch (error) {
      Alert.alert('Error', `Error al guardar los cambios: ${error.message}`);
    }
  };

 

  if (error) {
    return (
      <View style={perfilStyle.errorContainer}>
        <Text style={perfilStyle.errorText}>Error: {error}</Text>
      </View>
    );
  }

  return (
    <ScrollView style={perfilStyle.container}>
      <View style={perfilStyle.profileImgContainer}>
        <View style={perfilStyle.imgWrapper}>
          <Image
            source={{
              uri: usuario.imagen || 'https://img.pokemondb.net/artwork/vaporeon.jpg',
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

      {/* Edición de datos */}
      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Nombre</Text>
        {isEditing ? (
          <TextInput
            style={perfilStyle.profileValue}
            value={newNombre}
            onChangeText={setNewNombre}
            placeholder="Nuevo nombre de usuario"
          />
        ) : (
          <Text style={perfilStyle.profileValue}>{usuario.nombre}</Text>
        )}
      </View>

      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Correo Electrónico</Text>
        {isEditing ? (
          <TextInput
            style={perfilStyle.profileValue}
            value={newEmail}
            onChangeText={setNewEmail}
            placeholder="Nuevo correo electrónico"
          />
        ) : (
          <Text style={perfilStyle.profileValue}>{usuario.email}</Text>
        )}
      </View>

      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Contraseña</Text>
        {isEditing ? (
          <TextInput
            style={perfilStyle.profileValue}
            secureTextEntry
            value={newPassword}
            onChangeText={setNewPassword}
            placeholder="Nueva contraseña"
          />
        ) : (
          <Text style={perfilStyle.profileValue}>********</Text>
        )}
      </View>

      {isEditing ? (
        <TouchableOpacity
          style={perfilStyle.editButton}
          onPress={handleSaveChanges}
        >
          <Text style={perfilStyle.editButtonText}>Guardar Cambios</Text>
        </TouchableOpacity>
      ) : (
        <TouchableOpacity
          style={perfilStyle.editButton}
          onPress={() => setIsEditing(true)}
        >
          <Text style={perfilStyle.editButtonText}>Editar</Text>
        </TouchableOpacity>
      )}

      <TouchableOpacity style={perfilStyle.logoutButton} onPress={() => {}}>
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




