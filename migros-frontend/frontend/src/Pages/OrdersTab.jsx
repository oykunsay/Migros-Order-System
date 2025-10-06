import React, { useEffect, useState } from "react";
import "./CSS/UserDetails.css";

const OrdersTab = ({ customerId }) => {
  const [orders, setOrders] = useState([]);
  const [expandedOrders, setExpandedOrders] = useState({});

  useEffect(() => {
    fetch("http://localhost:8080/orders")
      .then((res) => res.json())
      .then((data) => {
        const customerOrders = data.filter(
          (order) => order.customerId === customerId
        );
        setOrders(customerOrders);
      })
      .catch((err) => console.error(err));
  }, [customerId]);

  const toggleOrder = (id) => {
    setExpandedOrders((prev) => ({ ...prev, [id]: !prev[id] }));
  };

  if (!orders.length) return <p style={{ padding: "20px" }}>No orders yet.</p>;

  return (
    <div className="orders-tab">
      {orders.map((order, index) => (
        <div key={order.id} className="order-card">
          <div className="order-header" onClick={() => toggleOrder(order.id)}>
            <h4>Order #{index + 1}</h4>
            <p>Date: {order.orderDate}</p>
          </div>

          {expandedOrders[order.id] && (
            <div className="order-details">
              {order.orderDetails.map((detail, idx) => (
                <div key={idx} className="order-detail-row">
                  <p className="product">
                    {detail.productName} (x{detail.quantity})
                  </p>
                  <p className="price">₺{detail.price.toFixed(2)}</p>
                </div>
              ))}
              <div className="order-total">
                Total: ₺{order.totalPrice.toFixed(2)}
              </div>
            </div>
          )}
        </div>
      ))}
    </div>
  );
};

export default OrdersTab;
