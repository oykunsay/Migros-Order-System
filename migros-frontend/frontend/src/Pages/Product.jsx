import React, { useContext } from "react";
import { useParams } from "react-router-dom";
import { ShopContext } from "../Context/ShopContext";
import ProductDisplay from "../Components/ProductDisplay/ProductDisplay";

const Product = () => {
  const { productId } = useParams();
  const { allProduct } = useContext(ShopContext);

  if (!allProduct || allProduct.length === 0) {
    return <p>Loading product...</p>;
  }
  const product = allProduct.find((p) => Number(p.id) === Number(productId));

  if (!product) {
    return <p>Product not found.</p>;
  }

  return <ProductDisplay product={product} />;
};

export default Product;
