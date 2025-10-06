import React, { useContext } from "react";
import "./RelatedProducts.css";
import { ShopContext } from "../../Context/ShopContext";
import Item from "../Item/Item";

const RelatedProducts = ({ currentProductId }) => {
  const { allProduct } = useContext(ShopContext);

  // Aynı kategoriden veya rastgele diğer ürünler
  const related = allProduct
    .filter((product) => product.id !== currentProductId)
    .slice(0, 6); // max 6 ürün göster

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
