import React, { useState } from 'react';
import { View, Text, TextInput, Image, TouchableOpacity, StyleSheet, ScrollView, Modal } from 'react-native';
import perfilStyle from '../../styles/perfilStyle'; // Importando los estilos

const Perfil = () => {
  // Estado del usuario
  const [usuario, setUsuario] = useState({
    nombre: 'Juan Pérez',
    email: 'juan@example.com',
    password: '********',
    imagen: '', // Puede ser la URL de la imagen o una imagen local
    id: 1,
    rol: 'admin', // o 'usuario'
  });

  // Estado para el modal de la imagen
  const [modalVisible, setModalVisible] = useState(false);
  const [newImagen, setNewImagen] = useState('');

  // Función para cambiar la imagen
  const handleImageChange = () => {
    if (newImagen) {
      setUsuario({ ...usuario, imagen: newImagen }); // Cambiar la imagen de perfil
      setModalVisible(false); // Cerrar el modal
    } else {
      alert('Por favor ingresa una URL válida para la imagen');
    }
  };

  return (
    <ScrollView style={perfilStyle.container}>
      {/* Contenedor de la imagen de perfil */}
      <View style={perfilStyle.profileImgContainer}>
        <View style={perfilStyle.imgWrapper}>
          <Image
            source={{
              uri: usuario.imagen || 'https://img.pokemondb.net/artwork/vaporeon.jpg', // URL predeterminada
            }}
            style={perfilStyle.img}
          />
        </View>
        <TouchableOpacity
          style={perfilStyle.changeImageButton}
          onPress={() => setModalVisible(true)} // Abrir modal para cambiar imagen
        >
          <Text style={perfilStyle.changeImageButtonText}>Cambiar foto</Text>
        </TouchableOpacity>
      </View>

      {/* Contenedor para el nombre del usuario */}
      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Nombre</Text>
        <View style={perfilStyle.profileValue}>
          <Text>{usuario.nombre}</Text>
        </View>
        <TouchableOpacity style={perfilStyle.editButton} onPress={() => { /* Lógica para editar nombre */ }}>
          <Text style={perfilStyle.editButtonText}>Editar</Text>
        </TouchableOpacity>
      </View>

      {/* Contenedor para el correo electrónico */}
      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Correo Electrónico</Text>
        <View style={perfilStyle.profileValue}>
          <Text>{usuario.email}</Text>
        </View>
        <TouchableOpacity style={perfilStyle.editButton} onPress={() => { /* Lógica para editar email */ }}>
          <Text style={perfilStyle.editButtonText}>Editar</Text>
        </TouchableOpacity>
      </View>

      {/* Contenedor para la contraseña */}
      <View style={perfilStyle.profileContent}>
        <Text style={perfilStyle.profileText}>Contraseña</Text>
        <View style={perfilStyle.profileValue}>
          <Text>{usuario.password}</Text>
        </View>
        <TouchableOpacity style={perfilStyle.editButton} onPress={() => { /* Lógica para editar contraseña */ }}>
          <Text style={perfilStyle.editButtonText}>Editar</Text>
        </TouchableOpacity>
      </View>

      {/* Botón de cerrar sesión */}
      <TouchableOpacity style={perfilStyle.logoutButton} onPress={() => { /* Lógica de logout */ }}>
        <Text style={perfilStyle.logoutButtonText}>Cerrar sesión</Text>
      </TouchableOpacity>

      {/* Si el usuario es Admin, mostrar botón de Admin */}
      {usuario.rol === 'admin' && (
        <TouchableOpacity style={perfilStyle.adminButton} onPress={() => { /* Lógica para admin */ }}>
          <Text style={perfilStyle.adminButtonText}>Admin</Text>
        </TouchableOpacity>
      )}

      {/* Modal para cambiar la imagen de perfil */}
      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)} // Cerrar modal
      >
        <View style={perfilStyle.modalContainer}>
          <View style={perfilStyle.modalContent}>
            <Text style={perfilStyle.modalTitle}>Cambiar Imagen de Perfil</Text>
            <TextInput
              style={perfilStyle.modalInput}
              placeholder="URL de la imagen"
              value={newImagen}
              onChangeText={setNewImagen} // Actualizar el estado de la nueva imagen
            />
            <TouchableOpacity style={perfilStyle.modalButton} onPress={handleImageChange}>
              <Text style={perfilStyle.modalButtonText}>Guardar Cambios</Text>
            </TouchableOpacity>
            <TouchableOpacity
              style={perfilStyle.modalButton}
              onPress={() => setModalVisible(false)} // Cerrar el modal sin cambios
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

