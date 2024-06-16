import React from 'react';
import ProductCard from "./ProductCard";
import classes from "./ProductCardList.module.css"

const ProductCardList = ({productCard}) => {
    return (
        <div className={classes.productCardList}>
            {productCard.map(card => <ProductCard card={card} key={card.id}/>)}
        </div>
    );
};

export default ProductCardList;