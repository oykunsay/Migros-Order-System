import React, { useContext } from "react";
import "./CartItems.css";
import remove_icon from "../Assets/Frontend_Assets/cart_cross_icon.png";
import { ShopContext } from "../../Context/ShopContext";

const CartItems = () => {
  const {
    allProduct,
    cartItems,
    removeFromCart,
    updateCartItemCount,
    getTotalCartAmount,
  } = useContext(ShopContext);

  const customer = JSON.parse(localStorage.getItem("customer"));

  const handleQuantityChange = (productId, delta) => {
    const newQuantity = (cartItems[productId] || 0) + delta;
    if (newQuantity >= 0) updateCartItemCount(newQuantity, productId);
  };

  const handleCheckout = () => {
    if (!customer) {
      alert("Please login first!");
      return;
    }

    const itemsToOrder = Object.keys(cartItems)
      .filter((id) => cartItems[id] > 0)
      .map((id) => ({
        productId: parseInt(id),
        quantity: cartItems[id],
      }));

    if (itemsToOrder.length === 0) {
      alert("Cart is empty!");
      return;
    }

    // 🔹 payload'a orderDate ekliyoruz
    const payload = {
      customerId: customer.id,
      orderDate: new Date().toISOString().split("T")[0], // YYYY-MM-DD format
      orderDetails: itemsToOrder,
    };

    console.log("Sending order payload:", payload);

    fetch("http://localhost:8080/orders", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Failed to create order");
        return res.json();
      })
      .then((data) => {
        alert("Order placed successfully!");
        Object.keys(cartItems).forEach((id) => updateCartItemCount(0, id));
      })
      .catch((err) => {
        console.error(err);
        alert("Something went wrong while placing order.");
      });
  };

  return (
    <div className="cartitems">
      <div className="cartitems-format-main">
        <p>Product</p>
        <p>Title</p>
        <p>Price</p>
        <p>Quantity</p>
        <p>Total</p>
        <p>Remove</p>
      </div>
      <hr />

      {allProduct.map((product) => {
        const quantity = cartItems[product.id];
        if (!quantity || quantity <= 0) return null;

        return (
          <div
            key={product.id}
            className="cartitems-format cartitems-format-main"
          >
            <div className="cartitems-image">
              <img src={product.imageUrl} alt={product.name} />
            </div>
            <p>{product.name}</p>
            <p>₺{product.price}</p>

            <div className="cartitems-quantity">
              <button onClick={() => handleQuantityChange(product.id, -1)}>
                -
              </button>
              <span>{quantity}</span>
              <button onClick={() => handleQuantityChange(product.id, 1)}>
                +
              </button>
            </div>

            <p>₺{quantity * product.price}</p>

            <div
              className="cartitems-remove-icon"
              onClick={() => removeFromCart(product.id)}
            >
              <img src={remove_icon} alt="Remove" />
            </div>
          </div>
        );
      })}

      <div className="cartitems-down">
        <div className="cartitems-total">
          <h1>Cart Totals</h1>
          <div>
            <div className="cartitems-total-item">
              <p>Subtotal</p>
              <p>₺{getTotalCartAmount()}</p>
            </div>
            <hr />
            <div className="cartitems-total-item">
              <p>Shipping Fee</p>
              <p>Free</p>
            </div>
            <hr />
            <div className="cartitems-total-item">
              <h3>Total</h3>
              <h3>₺{getTotalCartAmount()}</h3>
            </div>
            <div className="cartitems-checkout">
              <button onClick={handleCheckout}>Proceed to Checkout</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartItems;
