import { StyleSheet } from 'react-native';

const perfilStyle = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1E212D', // Similar al fondo oscuro en el JSP
    padding: 40,
  },

  title: {
    color: '#E78F81', // Color del título
    fontFamily: 'Figtree', // Similar a la fuente de Figtree
    fontSize: 40,
    fontWeight: '900',
    textAlign: 'center',
    marginTop: 20,
    lineHeight: 40,
  },

  profileImgContainer: {
    alignItems: 'center',
    marginBottom: 20,
    marginTop: 20,
  },

  imgWrapper: {
    borderRadius: 125, // Estilo redondeado para la imagen
    overflow: 'hidden',
    width: 250,
    height: 250,
    backgroundColor: '#f0f0f0', // Color de fondo cuando la imagen está cargada
    justifyContent: 'center',
    alignItems: 'center',
  },

  img: {
    width: '100%',
    height: '100%',
    objectFit: 'cover', // Similar a "object-fit: cover" en CSS
    borderRadius: 125,
  },

  changeImageButton: {
    marginTop: 10,
    padding: 10,
    backgroundColor: '#E78F81', // Color del botón cambiar imagen
    borderRadius: 5,
  },

  changeImageButtonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  
  profileContent: {
    marginBottom: 20,
  },

  profileText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#E78F81', // Estilo de texto del perfil
  },

  profileValue: {
    marginVertical: 10,
    padding: 10,
    backgroundColor: '#FAF3E0', // Fondo similar al color en JSP
    borderRadius: 5,
    color: '#000', // Color de texto de los valores
  },

  editButton: {
    padding: 10,
    backgroundColor: '#28a745', // Color del botón de editar
    borderRadius: 5,
    marginTop: 5,
  },
  
  editButtonText: {
    color: '#fff',
  },

  logoutButton: {
    marginTop: 20,
    padding: 10,
    marginBottom: 40,
    backgroundColor: '#dc3545', // Color del botón de cerrar sesión
    borderRadius: 5,
  },

  logoutButtonText: {
    color: '#fff',
  },

  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // Fondo oscuro para el modal
  },

  modalContent: {
    width: 300,
    padding: 20,
    borderRadius: 10,
    alignItems: 'center',
    backgroundColor: "#FAF3E0",
  },

  modalTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 20,
  },

  modalButton: {
    padding: 10,
    backgroundColor: '#E78F81', // Color del botón en el modal
    borderRadius: 5,
    marginTop: 10,
    width: "50%",
  },
  modalButtonText: {
    textAlign: 'center',
    color: '#fff',
    fontWeight: 'bold',
  },

  buttonAdmin: {
    backgroundColor: 'transparent',
    borderColor: '#E78F81',
    color: '#E78F81',
    padding: 5,
    fontSize: 16,
    borderRadius: 5,
    width: 130,
    textAlign: 'center',
    marginTop: 20,
    alignSelf: 'center',
  },

  previewImage: {
    width: 100,
    height: 100,
    marginTop: 20,
    borderRadius: 50, // Estilo redondeado
    overflow: 'hidden',
  },

  profileContentText: {
    fontSize: 18,
    color: '#E78F81',
    fontFamily: 'Figtree',
    fontWeight: '400',
  },

  profileContentValue: {
    fontSize: 16,
    color: '#000000',
    backgroundColor: '#FAF3E0',
    padding: 10,
    borderRadius: 5,
    marginTop: 5,
  },

  buttonLoginOut: {
    backgroundColor: 'transparent',
    borderColor: '#ED3E22',
    color: '#ED3E22',
    padding: 5,
    fontSize: 16,
    borderRadius: 5,
    width: 130,
    textAlign: 'center',
  },

  buttonLoginOutText: {
    color: '#ED3E22',
    textAlign: 'center',
  },
  buttonAdminText: {
    color: '#E78F81',
    textAlign: 'center',
    marginBottom: 30,
  },
  buttonChangeImg: {
    backgroundColor: 'transparent',
    borderColor: '#0056b3',
    color: '#0056b3',
    padding: 5,
    fontSize: 16,
    borderRadius: 5,
    width: 130,
    textAlign: 'center',
  },
  buttonChangeImgText: {
    color: '#0056b3',
    textAlign: 'center',
  },
  loader: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  errorContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  errorText: {
    fontSize: 18,
    color: 'red',
    textAlign: 'center',
  },
});

export default perfilStyle;

