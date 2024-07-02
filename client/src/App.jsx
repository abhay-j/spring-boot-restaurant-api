import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {  useNavigate } from "react-router-dom";
import { CustomNavBar } from './components/Navbar'
import {Menu} from './components/Menu'
import { Signup } from './components/SignUp';
import {Login} from './components/Login';
import {Cart} from './components/Cart'
function App() {
  const [user, setUser] = useState(null);
  const [shoppingCartResponse, setShoppingCartResponse] = useState(null);
  const navigate = useNavigate();

  const handleLogin = (userData, shoppingCartResponseData) => {
    setUser(userData);
    console.log(userData);
    localStorage.setItem('token', userData.accessToken);
    console.log("this is from app.jsx. State has been updated");
    setShoppingCartResponse(shoppingCartResponseData);
  };
  
console.log(shoppingCartResponse);


  const handleLogout = () => {
    setUser(null);
    localStorage.removeItem('token');
    setShoppingCartResponse(null);
    navigate("/");
  };

  return (
   
    <div className='container mx-auto max-w-screen-x1 py-4'>
      <CustomNavBar user={user}  onLogout={handleLogout}/>
      
      <Routes>
        <Route path="/" element={<Menu user={user} shoppingCartResponse={shoppingCartResponse}/>} />
        {/* <Route path="/menu" element={<Menu user={user} />} />
       */}
        <Route path="/cart" element={<Cart user={user} shoppingCartResponse={shoppingCartResponse} />}  />
     
      
        <Route path="/login"  element={<Login onLogin={handleLogin}/>} />
        <Route path="/signup" element={<Signup />} /> 
      </Routes>
    </div>
  
  )
}

export default App
