import React, { useState, useEffect } from "react";
import save_icon from "../Components/Assets/Frontend_Assets/save_icon.png";
import edit_icon from "../Components/Assets/Frontend_Assets/edit.png";
import cancel_icon from "../Components/Assets/Frontend_Assets/cancel_icon.png";
import "./CSS/UserDetails.css";
import OrdersTab from "./OrdersTab";

const UserDetails = () => {
  const storedCustomer = JSON.parse(localStorage.getItem("customer"));

  const [customer, setCustomer] = useState(null);
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    password: "",
  });
  const [editingField, setEditingField] = useState({
    name: false,
    email: false,
    phoneNumber: false,
    password: false,
  });
  const [addresses, setAddresses] = useState([]);
  const [newAddress, setNewAddress] = useState({
    addressType: "",
    addressDetails: "",
    city: "",
    district: "",
  });
  const [activeTab, setActiveTab] = useState("profile");


  const fieldLabels = {
    addressType: "Address Type",
    addressDetails: "Address Details",
    city: "City",
    district: "District",
    name: "Name",
    email: "Email",
    phoneNumber: "Phone Number",
    password: "Password",
  };

  useEffect(() => {
    if (!storedCustomer) return;

    fetch(`http://localhost:8080/customers/${storedCustomer.id}`)
      .then((res) => res.json())
      .then((data) => {
        setCustomer(data);
        setFormData({
          name: data.name,
          email: data.email,
          phoneNumber: data.phoneNumber,
          password: "",
        });
        setAddresses(
          data.addresses?.map((addr) => ({
            id: addr.id,
            addressDetails: addr.addressDetails,
            city: addr.city,
            district: addr.district,
            addressType: addr.addressType,
            editing: false,
          })) || []
        );
      })
      .catch((err) => console.error(err));
  }, []);

  if (!customer) return <p style={{ padding: "20px" }}>Please login.</p>;

  const handleFieldCancel = (field) => {
    setEditingField({ ...editingField, [field]: false });
    setFormData({ ...formData, [field]: customer[field] || "" });
  };

  const handleFieldSave = (field) => {
    const updatedCustomer = { ...customer, [field]: formData[field] };
    fetch(`http://localhost:8080/customers/${customer.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedCustomer),
    })
      .then((res) => res.json())
      .then((data) => {
        setCustomer(data);
        setFormData({ ...formData, [field]: data[field] });
        setEditingField({ ...editingField, [field]: false });
      })
      .catch((err) => console.error(err));
  };

  const handleAddressSave = (idx) => {
    const addressToSave = addresses[idx];
    if (!addressToSave.id) return;

    fetch(
      `http://localhost:8080/customers/${customer.id}/addresses/${addressToSave.id}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          addressDetails: addressToSave.addressDetails,
          city: addressToSave.city,
          district: addressToSave.district,
          addressType: addressToSave.addressType,
        }),
      }
    )
      .then((res) => res.json())
      .then((updatedAddr) => {
        setAddresses(
          addresses.map((a, i) =>
            i === idx ? { ...updatedAddr, editing: false } : a
          )
        );
      })
      .catch((err) => console.error(err));
  };

  const handleAddAddress = () => {
    fetch(`http://localhost:8080/customers/${customer.id}/addresses`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newAddress),
    })
      .then((res) => res.json())
      .then((savedAddr) => {
        setAddresses([...addresses, { ...savedAddr, editing: false }]);
        setNewAddress({ addressType: "", addressDetails: "", city: "", district: "" });
      })
      .catch((err) => console.error(err));
  };

  return (
    <div className="user-details-container">
      <div className="sidebar">
        <div
          className={`sidebar-item ${activeTab === "profile" ? "active" : ""}`}
          onClick={() => setActiveTab("profile")}
        >
          My Profile
        </div>
        <div
          className={`sidebar-item ${activeTab === "orders" ? "active" : ""}`}
          onClick={() => setActiveTab("orders")}
        >
          Orders
        </div>
        <div
          className={`sidebar-item ${activeTab === "addresses" ? "active" : ""}`}
          onClick={() => setActiveTab("addresses")}
        >
          Addresses
        </div>
      </div>

      <div className="user-content">
        {activeTab === "profile" && (
          <div>
            <h3>User Information</h3>
            {["name", "email", "phoneNumber", "password"].map((field) => (
              <div className="field-row" key={field}>
                {!editingField[field] ? (
                  <img
                    src={edit_icon}
                    alt="Edit"
                    className="field-icon"
                    onClick={() =>
                      setEditingField({ ...editingField, [field]: true })
                    }
                  />
                ) : (
                  <>
                    <img
                      src={save_icon}
                      alt="Save"
                      className="field-icon"
                      onClick={() => handleFieldSave(field)}
                    />
                    <img
                      src={cancel_icon}
                      alt="Cancel"
                      className="field-icon"
                      onClick={() => handleFieldCancel(field)}
                    />
                  </>
                )}
                <label className="field-label">{fieldLabels[field]}</label>
                <input
                  type={field === "password" ? "password" : "text"}
                  value={formData[field]}
                  readOnly={!editingField[field]}
                  onChange={(e) =>
                    setFormData({ ...formData, [field]: e.target.value })
                  }
                />
              </div>
            ))}
          </div>
        )}

        {activeTab === "orders" && <OrdersTab customerId={customer.id} />}

        {activeTab === "addresses" && (
          <div>
            <h3>Add New Address</h3>
            <div className="info-card">
              {["addressType", "addressDetails", "city", "district"].map(
                (field) => (
                  <div className="field-row" key={field}>
                    <label className="field-label">{fieldLabels[field]}</label>
                    <input
                      type="text"
                      value={newAddress[field]}
                      onChange={(e) =>
                        setNewAddress({ ...newAddress, [field]: e.target.value })
                      }
                    />
                  </div>
                )
              )}
              <button className="add-btn" onClick={handleAddAddress}>
                Add Address
              </button>
            </div>

            <h3>Saved Addresses</h3>
            {addresses.map((addr, idx) => (
              <div className="info-card" key={addr.id}>
                {["addressType", "addressDetails", "city", "district"].map(
                  (field) => (
                    <div className="field-row" key={field}>
                      {!addr.editing ? (
                        <img
                          src={edit_icon}
                          alt="Edit"
                          className="field-icon"
                          onClick={() =>
                            setAddresses(
                              addresses.map((a, i) =>
                                i === idx ? { ...a, editing: true } : a
                              )
                            )
                          }
                        />
                      ) : (
                        <>
                          <img
                            src={save_icon}
                            alt="Save"
                            className="field-icon"
                            onClick={() => handleAddressSave(idx)}
                          />
                          <img
                            src={cancel_icon}
                            alt="Cancel"
                            className="field-icon"
                            onClick={() =>
                              setAddresses(
                                addresses.map((a, i) =>
                                  i === idx ? { ...a, editing: false } : a
                                )
                              )
                            }
                          />
                        </>
                      )}
                      <label className="field-label">
                        {fieldLabels[field]}
                      </label>
                      <input
                        type="text"
                        value={addr[field]}
                        readOnly={!addr.editing}
                        onChange={(e) =>
                          setAddresses(
                            addresses.map((a, i) =>
                              i === idx
                                ? { ...a, [field]: e.target.value }
                                : a
                            )
                          )
                        }
                      />
                    </div>
                  )
                )}
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default UserDetails;
