import React,{useState} from "react";
import {
    Card,
    Input,
    Checkbox,
    Button,
    Typography,
  } from "@material-tailwind/react";
import axios from "axios";
import {  useNavigate } from "react-router-dom";



  export function Login({ onLogin }){
    const [formData, setFormData] = useState({
        username: "",
        
        password: "",
      });

      
      const [error, setError] = useState("");
      const [success, setSuccess] = useState("");
      const [user, setUser] = useState(null);
      
      const navigate = useNavigate(); 

      const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");
        console.log("form submitted");
        console.log(formData)
        try{
            const response = await axios.post("http://localhost:8080/api/auth/login",formData);
            console.log('Login response:', response.data);
            const { accessToken, tokenType, id, username, email } = response.data;
            // Set the user state
            onLogin(response.data); // Call the onLogin function passed from App
            navigate('/');  // Use navigate for re
      
           setSuccess('Login successful!');


           try {
            const cartResponse = await axios.post(`http://localhost:8080/api/carts/${id}`, null, {
              headers: {
                Authorization: `${tokenType} ${accessToken}`
              }
            });
           
             // Set the user state
             onLogin(response.data, cartResponse.data); // Call the onLogin function passed from App
             navigate('/');  // Use navigate for re
       
            setSuccess('Login successful!');
           
          } catch (cartError) {
            console.error('Error creating shopping cart:', cartError);
          }






        }catch(error){
            console.error('Login error:', error);
      setError(error.response?.data?.message || 'An error occurred during login.');
      
        }
      }
      const handleChange =(e) =>{
        setFormData({
            ...formData, [e.target.name]: e.target.value
        })
       
      }

      console.log(formData)
    return (
        <div className="flex justify-center items-center min-h-screen bg-gray-100">
        <div className="w-full max-w-md px-4">
          <Card color="transparent" shadow={false} className="bg-white p-6 rounded-lg shadow-md">
            <Typography variant="h4" color="blue-gray" className="text-center">
             Log in
            </Typography>
            <Typography color="gray" className="mt-1 font-normal text-center">
             Log into your account
            </Typography>
            <form className="mt-8 mb-2" onSubmit={handleSubmit}>
              <div className="mb-1 flex flex-col gap-6">
                <Typography variant="h6" color="blue-gray" className="-mb-3">
                  Create username
                </Typography>
                <Input
                name="username"
                value={formData.username}
                  size="lg"
                  placeholder="John Doe"
                  className="!border-t-blue-gray-200 focus:!border-t-gray-900"
                  labelProps={{
                    className: "before:content-none after:content-none",
                  }}
                  onChange={handleChange}
                />
                
                <Typography variant="h6" color="blue-gray" className="-mb-3">
                  Password
                </Typography>
                <Input
                  type="password"
                  name="password"
                  value={formData.password}
                  size="lg"
                  placeholder="********"
                  className="!border-t-blue-gray-200 focus:!border-t-gray-900"
                  labelProps={{
                    className: "before:content-none after:content-none",
                  }}
                  onChange={handleChange}
                />
              </div>
              {/* <Checkbox
                label={
                  <Typography
                    variant="small"
                    color="gray"
                    className="flex items-center font-normal"
                  >
                    I agree to the
                    <a
                      href="#"
                      className="font-medium transition-colors hover:text-gray-900 ml-1"
                    >
                      Terms and Conditions
                    </a>
                  </Typography>
                }
                containerProps={{ className: "-ml-2.5 mt-4" }}
              /> */}
              <Button type="submit" className="mt-6" fullWidth >
                Log in
              </Button>
              {/* <Typography color="gray" className="mt-4 text-center font-normal">
                Already have an account?{" "}
                <Link to="/login" className="font-medium text-gray-900">
                  Sign In
                </Link>
              </Typography> */}
            </form>
          </Card>
        </div>
      </div>
    )
  }