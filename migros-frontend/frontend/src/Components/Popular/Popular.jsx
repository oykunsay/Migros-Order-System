import React, { useContext, useEffect, useState } from "react";
import "./Popular.css";
import Item from "../Item/Item";
import { ShopContext } from "../../Context/ShopContext";

const Popular = () => {
  const { allProduct } = useContext(ShopContext);
  const [popularProducts, setPopularProducts] = useState([]);

  useEffect(() => {
    if (allProduct.length > 0) {
      const sorted = [...allProduct].sort((a, b) => b.price - a.price);
      setPopularProducts(sorted.slice(0, 8));
    }
  }, [allProduct]);

  return (
    <div className="popular">
      <h1>POPULAR PRODUCTS</h1>
      <hr />
      <div className="popular-item">
        {popularProducts.length === 0 ? (
          <p>Loading...</p>
        ) : (
          popularProducts.map((item) => (
            <Item
              key={item.id}
              id={item.id}
              name={item.name}
              imageUrl={item.imageUrl} 
              price={item.price}
            />
          ))
        )}
      </div>
    </div>
  );
};

export default Popular;
