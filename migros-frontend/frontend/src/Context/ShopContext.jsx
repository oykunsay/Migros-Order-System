import React, { createContext, useState, useEffect } from "react";

export const ShopContext = createContext(null);

const ShopContextProvider = ({ children }) => {
  const [allProduct, setAllProduct] = useState([]);
  const [cartItems, setCartItems] = useState({});

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then((res) => res.json())
      .then((data) => {
        setAllProduct(data);
        const initialCart = {};
        data.forEach((item) => {
          initialCart[item.id] = 0;
        });
        setCartItems(initialCart);
      });
  }, []);

  const addToCart = (itemId, quantity = 1) => {
    setCartItems((prev) => ({
      ...prev,
      [itemId]: (prev[itemId] || 0) + quantity,
    }));
  };

  const removeFromCart = (itemId) => {
    setCartItems((prev) => ({
      ...prev,
      [itemId]: Math.max((prev[itemId] || 0) - 1, 0),
    }));
  };

  const updateCartItemCount = (newAmount, itemId) => {
    setCartItems((prev) => ({ ...prev, [itemId]: Math.max(newAmount, 0) }));
  };

  const getTotalCartItems = () =>
    Object.values(cartItems).reduce((sum, val) => sum + val, 0);
  const getTotalCartAmount = () =>
    allProduct.reduce(
      (sum, item) => sum + (cartItems[item.id] || 0) * item.price,
      0
    );

  return (
    <ShopContext.Provider
      value={{
        allProduct,
        cartItems,
        addToCart,
        removeFromCart,
        updateCartItemCount,
        getTotalCartItems,
        getTotalCartAmount,
      }}
    >
      {children}
    </ShopContext.Provider>
  );
};

export default ShopContextProvider;
