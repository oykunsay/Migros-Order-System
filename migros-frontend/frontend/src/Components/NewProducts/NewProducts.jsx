import React, { useContext, useEffect, useState } from "react";
import "./NewProducts.css";
import Item from "../Item/Item"; 
import { ShopContext } from "../../Context/ShopContext";

const NewProducts = () => {
  const { allProduct } = useContext(ShopContext);
  const [newProducts, setNewProducts] = useState([]);

  useEffect(() => {
    if (allProduct.length > 0) {
      const sortedById = [...allProduct].sort((a, b) => b.id - a.id);
      setNewProducts(sortedById.slice(0, 8));
    }
  }, [allProduct]);

  return (
    <div className="new-products">
      <h1>NEW PRODUCTS</h1>
      <hr />
      <div className="collections">
        {newProducts.length === 0 ? (
          <p>Loading...</p>
        ) : (
          newProducts.map((item) => (
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

export default NewProducts;
