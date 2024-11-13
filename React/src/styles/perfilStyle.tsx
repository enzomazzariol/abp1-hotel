import { StyleSheet } from "react-native";

const style= StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1E212D',
    padding: 20,
  },
  profileImgContainer: {
    alignItems: 'center',
    marginBottom: 40,
  },
  imgWrapper: {
    width: 250,
    height: 250,
    borderRadius: 125,
    backgroundColor: '#f0f0f0',
    justifyContent: 'center',
    alignItems: 'center',
    overflow: 'hidden',
  },
  img: {
    width: '100%',
    height: '100%',
    resizeMode: 'cover',
    borderRadius: 125,
  },
  changeImageButton: {
    backgroundColor: '#007bff',
    color: '#fff',
    paddingVertical: 8,
    paddingHorizontal: 15,
    borderRadius: 5,
    marginTop: 10,
  },
  changeImageButtonText: {
    color: '#fff',
  },
  profileContent: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: 20,
  },
  profileText: {
    fontSize: 18,
    color: '#E78F81',
    fontFamily: 'Figtree',
    fontWeight: '400',
  },
  profileValue: {
    fontSize: 16,
    color: '#000',
    backgroundColor: '#FAF3E0',
    borderRadius: 5,
    padding: 5,
    width: '60%',
  },
  editButton: {
    backgroundColor: 'transparent',
    borderColor: '#28a745',
    borderWidth: 2,
    paddingVertical: 5,
    paddingHorizontal: 20,
    borderRadius: 5,
  },
  editButtonText: {
    color: '#28a745',
  },
  logoutButton: {
    backgroundColor: 'transparent',
    borderColor: '#ED3E22',
    borderWidth: 2,
    paddingVertical: 5,
    paddingHorizontal: 20,
    borderRadius: 5,
    marginTop: 20,
  },
  logoutButtonText: {
    color: '#ED3E22',
  },
  adminButton: {
    backgroundColor: '#E78F81',
    paddingVertical: 8,
    paddingHorizontal: 20,
    borderRadius: 5,
    marginTop: 20,
  },
  adminButtonText: {
    color: 'white',
    fontSize: 16,
    fontWeight: 'bold',
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    backgroundColor: '#fff',
    padding: 20,
    borderRadius: 10,
    width: '80%',
    alignItems: 'center',
  },
  modalTitle: {
    fontSize: 18,
    marginBottom: 20,
  },
  modalInput: {
    width: '100%',
    padding: 10,
    borderRadius: 5,
    borderWidth: 1,
    marginBottom: 20,
  },
  modalButton: {
    backgroundColor: '#007bff',
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderRadius: 5,
    marginBottom: 10,
  },
  modalButtonText: {
    color: '#fff',
  },
});

export default style;