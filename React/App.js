import { Provider as PaperProvider } from 'react-native-paper';
import { Login } from './src/view/login';
import { StatusBar } from 'react-native';
import {Home} from './src/view/home';
import { NavigationContainer } from '@react-navigation/native';
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';

export default function App() {
  const Stack = createNativeStackNavigator();

  return (
    <PaperProvider>
      <SafeAreaProvider>
          <NavigationContainer>
            <Stack.Navigator initialRouteName="Login">
              <Stack.Screen name="Home" component={Home} options={{headerShown: false}}/>
              <Stack.Screen name="Login" component={Login} options={{headerShown: false}}/>
            </Stack.Navigator>
          </NavigationContainer>
      </SafeAreaProvider>
    </PaperProvider>
  );
}