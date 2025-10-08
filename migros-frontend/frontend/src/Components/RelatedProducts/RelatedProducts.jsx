import React, { useContext } from "react";
import "./RelatedProducts.css";
import { ShopContext } from "../../Context/ShopContext";
import Item from "../Item/Item";

const RelatedProducts = ({ currentProductId }) => {
  const { allProduct } = useContext(ShopContext);

  const related = allProduct
    .filter((product) => product.id !== currentProductId)
    .slice(0, 6); 

  return (
    <div className="relatedproducts">
      <h1>Related Products</h1>
      <hr />
      <div className="relatedproducts-item">
        {related.map((item) => (
          <Item
            key={item.id}
            id={item.id}
            name={item.name}
            image={item.image_url}
            new_price={item.price}
          />
        ))}
      </div>
    </div>
  );
};

export default RelatedProducts;
