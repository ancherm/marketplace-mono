import React from 'react';
import Navbar from "../components/ui/navbar/Navbar";
import ProductCardList from "../components/product-card/ProductCardList";
import {useState} from "react";

const CatalogPage = () => {
    const [productCard, setProductCart] = useState([
        {id: 1, photo: "pics/Tragic_City.jpg", title: "ЛСП", price: "1500"},
        {id: 2, photo: "photo2", title: "title2", price: "price2"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
        {id: 3, photo: "photo3", title: "title3", price: "price3"},
    ])

    return (
        <ProductCardList productCard={productCard}/>
    );
};

export default CatalogPage;