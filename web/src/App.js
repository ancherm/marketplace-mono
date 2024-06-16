import './App.css';
import {useState} from "react";
import ProductCard from "./components/product-card/ProductCard";
import ProductCardList from "./components/product-card/ProductCardList";
import Navbar from "./components/ui/navbar/Navbar";

function App() {
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
        <div className="App">
            <Navbar/>
            <ProductCardList productCard={productCard}/>
        </div>
    );



}



export default App;
