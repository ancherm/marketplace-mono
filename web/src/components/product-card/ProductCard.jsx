import React from 'react';
import classes from "./ProductCard.module.css";

const ProductCard = (props) => {

    return (
        <div className={classes.productCard}>
            <div className="productCard__photo">
                {props.card.photo}
            </div>
            <div className="productCard__title">
                {props.card.title}
            </div>
            <div className="productCard__price">
                {props.card.price}
            </div>
            <div className="productCard__btn">
                <button>В корзину</button>
            </div>
        </div>
    );
};

export default ProductCard;