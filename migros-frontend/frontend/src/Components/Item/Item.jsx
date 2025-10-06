import React from "react";
import "./Item.css";
import { Link } from "react-router-dom";

const Item = ({ id, name, imageUrl, price, oldPrice }) => {
  return (
    <div className="item">
      <Link to={`/product/${id}`} onClick={() => window.scrollTo(0, 0)}>
        <img src={imageUrl} alt={name} className="item-image" />
      </Link>
      <p className="item-name">{name}</p>
      <div className="item-prices">
        <div className="item-price-new">₺{price}</div>
        {oldPrice && <div className="item-price-old">₺{oldPrice}</div>}
      </div>
    </div>
  );
};

export default Item;
