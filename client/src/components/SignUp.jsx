// import React from "react";
// import {
//     Card,
//     Input,
//     Checkbox,
//     Button,
//     Typography,
//   } from "@material-tailwind/react";

//   export function Signup(){
//     return (
//         <div className="py-8 lg:py-20">
//         <Card color="transparent" shadow={false}>
//          <Typography variant="h4" color="blue-gray">
//            Sign Up
//          </Typography>
//          <Typography color="gray" className="mt-1 font-normal">
//            Nice to meet you! Enter your details to register.
//          </Typography>
//          <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
//            <div className="mb-1 flex flex-col gap-6">
//              <Typography variant="h6" color="blue-gray" className="-mb-3">
//                Your Name
//              </Typography>
//              <Input
//                size="lg"
//                placeholder="name@mail.com"
//                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
//                labelProps={{
//                  className: "before:content-none after:content-none",
//                }}
//              />
//              <Typography variant="h6" color="blue-gray" className="-mb-3">
//                Your Email
//              </Typography>
//              <Input
//                size="lg"
//                placeholder="name@mail.com"
//                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
//                labelProps={{
//                  className: "before:content-none after:content-none",
//                }}
//              />
//              <Typography variant="h6" color="blue-gray" className="-mb-3">
//                Password
//              </Typography>
//              <Input
//                type="password"
//                size="lg"
//                placeholder="********"
//                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
//                labelProps={{
//                  className: "before:content-none after:content-none",
//                }}
//              />
//            </div>
//            <Checkbox
//              label={
//                <Typography
//                  variant="small"
//                  color="gray"
//                  className="flex items-center font-normal"
//                >
//                  I agree the
//                  <a
//                    href="#"
//                    className="font-medium transition-colors hover:text-gray-900"
//                  >
//                    &nbsp;Terms and Conditions
//                  </a>
//                </Typography>
//              }
//              containerProps={{ className: "-ml-2.5" }}
//            />
//            <Button className="mt-6" fullWidth>
//              sign up
//            </Button>
//            <Typography color="gray" className="mt-4 text-center font-normal">
//              Already have an account?{" "}
//              <a href="#" className="font-medium text-gray-900">
//                Sign In
//              </a>
//            </Typography>
//          </form>
//        </Card>
//        </div>
//      );
//   }
import React,{useState} from "react";
import {
  Card,
  Input,
  Checkbox,
  Button,
  Typography,
} from "@material-tailwind/react";
import axios from "axios";
import { Link } from "react-router-dom";
export function Signup() {
    
    
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
      });
      const [error, setError] = useState("");
      const [success, setSuccess] = useState("");
      
      
      const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");
        console.log("form submitted");
        console.log(formData)
        try{
            const response = await axios.post("http://localhost:8080/api/auth/register",formData);
            setSuccess("Registration successful!");
            console.log(response.data);
        }catch(error){
            setError(err.response?.data?.message || "An error occurred during registration.");
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
            Sign Up
          </Typography>
          <Typography color="gray" className="mt-1 font-normal text-center">
            Nice to meet you! Enter your details to register.
          </Typography>
          <form className="mt-8 mb-2" onSubmit={handleSubmit}>
            <div className="mb-1 flex flex-col gap-6">
              <Typography variant="h6" color="blue-gray" className="-mb-3">
                Create username
              </Typography>
              <Input
              name="username"
                size="lg"
                placeholder="John Doe"
                className="!border-t-blue-gray-200 focus:!border-t-gray-900"
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
                onChange={handleChange}
              />
              <Typography variant="h6" color="blue-gray" className="-mb-3">
                Your Email
              </Typography>
              <Input
              name="email"
                size="lg"
                placeholder="name@mail.com"
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
                size="lg"
                placeholder="********"
                className="!border-t-blue-gray-200 focus:!border-t-gray-900"
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
                onChange={handleChange}
              />
            </div>
            <Checkbox
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
            />
            <Button type="submit" className="mt-6" fullWidth>
              Sign Up
            </Button>
            <Typography color="gray" className="mt-4 text-center font-normal">
              Already have an account?{" "}
              <Link to="/login" className="font-medium text-gray-900">
                Sign In
              </Link>
            </Typography>
          </form>
        </Card>
      </div>
    </div>
  );
}