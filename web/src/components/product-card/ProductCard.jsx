import React from 'react';
import classes from "./ProductCard.module.css";
import Button from "@mui/material/Button";

const ProductCard = (props) => {

    return (
        <div className={classes.productCard}>
            <div className={classes.productCard__photo}>
                <img src={props.card.photo} alt={props.card.title} />
            </div>
            <div className={classes.productCard__title}>
                {props.card.title}
            </div>
            <div className={classes.productCard__price}>
                {props.card.price} ₽
            </div>
            <Button
                className={classes.productCard__btn}
                variant="contained"
                style={{
                    borderRadius: "10px",
                    fontSize: "14px",
                    cursor: "pointer",
                    backgroundColor: "limegreen",
                    color: "black"
                }}
            >
                В корзину
            </Button>

        </div>
    );
};

export default ProductCard;