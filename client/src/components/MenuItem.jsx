import React from "react";
import {
    Card,
    CardHeader,
    CardBody,
    CardFooter,
    Typography,
    Button,
  } from "@material-tailwind/react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export function MenuItem({data, user, shoppingCartResponse}){
    const {categroy, description, id, price, title, url} = data;
    const navigate = useNavigate();

    const handleAddToCart = async () =>{
       
        if (!user) {
            navigate("/login");
          }
       
        
        try {
            //http://localhost:8080/api/carts/1/items
            const response = await axios.post(`http://localhost:8080/api/carts/${shoppingCartResponse.cartId}/items`, {
              product: {
                id: data.id,
              },
              quantity: 1  // You can modify this as needed
            }, {
              headers: {
                Authorization: `Bearer ${user.accessToken}`
              }
            });
      
           console.log(response.data);
          } catch (error) {
            console.error('Error adding item to cart:', error);
          }
    }

    
    return(<div>
      <Card className="w-96">
      <CardHeader shadow={false} floated={false} className="h-96">
        <img
          src={url}
          alt="card-image"
          className="h-full w-full object-cover"
        />
      </CardHeader>
      <CardBody>
        <div className="mb-2 flex items-center justify-between">
          <Typography color="blue-gray" className="font-medium">
            {title}
          </Typography>
          <Typography color="blue-gray" className="font-medium">
          $ {price}
          </Typography>
        </div>
        <Typography
          variant="small"
          color="gray"
          className="font-normal opacity-75"
        >
         {description}
        </Typography>
      </CardBody>
      <CardFooter className="pt-0">
        <Button
          ripple={false}
          fullWidth={true}
          className="bg-blue-gray-900/10 text-blue-gray-900 shadow-none hover:scale-105 hover:shadow-none focus:scale-105 focus:shadow-none active:scale-100"
          onClick={handleAddToCart}
        >
          Add to Cart
        </Button>
      </CardFooter>
    </Card>
    </div>)
}