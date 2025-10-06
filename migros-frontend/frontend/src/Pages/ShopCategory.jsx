import React, { useContext, useEffect, useState } from "react";
import { ShopContext } from "../Context/ShopContext";
import { Link } from "react-router-dom";
import "./CSS/ShopCategory.css";

const ShopCategory = ({ banner, category }) => {
  const { allProduct } = useContext(ShopContext);
  const [filteredProducts, setFilteredProducts] = useState([]);

  useEffect(() => {
    if (allProduct && allProduct.length > 0) {
      const filtered = allProduct.filter(
        (product) => product.category === category
      );
      setFilteredProducts(filtered);
    }
  }, [allProduct, category]);

  return (
    <div className="shop-category">
      <img
        src={banner}
        alt={category}
        style={{ width: "100%", marginBottom: "20px" }}
      />
      <h2 style={{ textTransform: "capitalize", marginBottom: "20px" }}>
        {category}
      </h2>

      <div className="product-list">
        {filteredProducts.length === 0 ? (
          <p>Loading products...</p>
        ) : (
          filteredProducts.map((product) => (
            <div key={product.id} className="product-card">
              <Link to={`/product/${product.id}`}>
                <img
                  src={product.imageUrl}
                  alt={product.name}
                  width={150}
                  onClick={() => window.scrollTo(0, 0)} 
                />
              </Link>
              <h3>{product.name}</h3>
              <p>₺{product.price}</p>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default ShopCategory;
