import React, { useContext, useState } from "react";
import "./ProductDisplay.css";
import star_icon from "../Assets/Frontend_Assets/star_icon.png";
import star_dull_icon from "../Assets/Frontend_Assets/star_dull_icon.png";
import { ShopContext } from "../../Context/ShopContext";

const ProductDisplay = ({ product }) => {
  const { addToCart } = useContext(ShopContext);
  const [amount, setAmount] = useState(1); 

  if (!product) return <p>Loading product...</p>;

  return (
    <div className="productdisplay">
      <div className="productdisplay-left">
        <div className="productdisplay-img">
          <img
            className="productdisplay-main-img"
            src={product.imageUrl}
            alt={product.name}
          />
        </div>
      </div>

      <div className="productdisplay-right">
        <h1>{product.name}</h1>

        <div className="productdisplay-right-stars">
          <img src={star_icon} alt="" />
          <img src={star_icon} alt="" />
          <img src={star_icon} alt="" />
          <img src={star_icon} alt="" />
          <img src={star_dull_icon} alt="" />
          <p>(20 reviews)</p>
        </div>

        <div className="productdisplay-right-prices">
          {product.old_price && (
            <div className="productdisplay-right-price-old">
              ₺{product.old_price}
            </div>
          )}
          <div className="productdisplay-right-price-new">₺{product.price}</div>
        </div>

        <div className="productdisplay-right-amount">
          <h1>Select Amount</h1>
          <div className="productdisplay-right-amounts">
            {[1, 2, 3, 4, 5].map((num) => (
              <div
                key={num}
                className={amount === num ? "selected" : ""}
                onClick={() => setAmount(num)}
              >
                {num}
              </div>
            ))}
          </div>
        </div>

        <button onClick={() => addToCart(product.id, amount)}>
          Add {amount} to Cart
        </button>
      </div>
    </div>
  );
};

export default ProductDisplay;
