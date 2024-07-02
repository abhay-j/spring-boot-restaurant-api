import React from "react";
import {useState, useEffect} from "react"
import { MenuItem } from "./MenuItem";

export function Menu({user, shoppingCartResponse}){
const [data, setData] = useState([]);

async function fetchData() {
    try {
        const response = await fetch(`http://localhost:8080/api/products`);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const menuData = await response.json();
        // console.log(menuData);
        setData(menuData);  
    } catch (error) {
        console.error("Failed to fetch products:", error);
    }
}


useEffect(()=>{
    fetchData();
},[]);



return(
    <div className="menu py-8 lg:py-20">
        <h1 className="text-3xl lg:text-left text-center lg:text-5xl font-bold">Menu:</h1>
        <div className="grid grid-cols-3 gap-4">
            {
                data.map(data => <MenuItem shoppingCartResponse={shoppingCartResponse} user={user} key={data.id} data={data} />)
            }
        </div>
    </div>
)
}