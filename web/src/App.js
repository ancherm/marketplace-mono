import './App.css';
import {useState} from "react";
import ProductCard from "./components/product-card/ProductCard";
import ProductCardList from "./components/product-card/ProductCardList";

function App() {
    const [productCard, setProductCart] = useState([
        {id: 1, photo: "photo1", title: "title1", price: "price1"},
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
            <ProductCardList productCard={productCard}/>
        </div>
    );



}



export default App;
