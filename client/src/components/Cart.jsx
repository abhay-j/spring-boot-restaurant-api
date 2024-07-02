import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Card, CardBody, Typography, Avatar, IconButton } from "@material-tailwind/react";

export function Cart({ shoppingCartResponse, user }) {
  const [cartItems, setCartItems] = useState([]);
  const [error, setError] = useState(null);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    const fetchCartItems = async () => {
      if (shoppingCartResponse && shoppingCartResponse.success) {
        try {
          const response = await axios.get(`http://localhost:8080/api/carts/${shoppingCartResponse.cartId}/items`, {
            headers: {
              Authorization: `Bearer ${user.accessToken}`
            }
          });
          setCartItems(response.data);
        } catch (err) {
          console.error('Error fetching cart items:', err);
          setError('Failed to fetch cart items. Please try again later.');
        }
      }
    };

    fetchCartItems();
  }, [shoppingCartResponse]);

  useEffect(() => {
    const total = cartItems.reduce((sum, item) => sum + (item.product.price ), 0);
    setTotalPrice(total);
  }, [cartItems]);



  if (error) {
    return <div className="text-red-500">{error}</div>;
  }


  const handleDeleteItem = async (itemId) => {
    try {
        // /api/carts/{cartId}/items/{itemId}
      await axios.delete(`http://localhost:8080/api/carts/${shoppingCartResponse.cartId}/items/${itemId}`, {
        headers: {
          Authorization: `Bearer ${user.accessToken}`
        }
      });
      setCartItems(cartItems.filter(item => item.id !== itemId));
    } catch (err) {
      console.error('Error deleting cart item:', err);
      setError('Failed to delete item. Please try again later.');
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <div className="w-full max-w-md px-4">
        <Card className="w-96">
          <CardBody>
            <div className="mb-4 flex items-center justify-between">
              <Typography variant="h5" color="blue-gray" className="">
                Cart Items
              </Typography>
            </div>
            <div className="divide-y divide-gray-200">
              {cartItems.map(({ id, product }) => (
                <div
                  key={id}
                  className="flex items-center justify-between pb-3 pt-3 last:pb-0"
                >
                  <div className="flex items-center gap-x-3">
                    <Avatar size="sm" src={product.url} alt={product.title} />
                    <div>
                      <Typography color="blue-gray" variant="h6">
                        {product.title}
                      </Typography>
                      {/* <Typography variant="small" color="gray">
                        Quantity: {quantity}
                      </Typography> */}
                    </div>
                  </div>
                  <Typography color="blue-gray" variant="h6">
                    ${(product.price ).toFixed(2)}
                  </Typography>
                  <IconButton
                      variant="text"
                      color="red"
                      size="sm"
                      onClick={() => handleDeleteItem(id)}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        strokeWidth={1.5}
                        stroke="currentColor"
                        className="w-5 h-5"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"
                        />
                      </svg>
                    </IconButton>
                </div>
              ))}
            </div>
            {cartItems.length === 0 ? (
              <Typography className="text-center mt-4" color="gray">
                Your cart is empty.
              </Typography>
            ) : (
              <div className="mt-4 pt-4 border-t border-gray-200">
                <Typography color="blue-gray" variant="h6" className="font-bold">
                  Total: ${totalPrice.toFixed(2)}
                </Typography>
              </div>
            )}
          </CardBody>
        </Card>
      </div>
    </div>
  );
}