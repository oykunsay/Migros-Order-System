import React, { useState } from "react";
import "./AddProduct.css";
import upload_area from "../../assets/Admin_Assets/upload_area.svg";

const AddProduct = () => {
  const [imagePreview, setImagePreview] = useState(null);
  const [productDetails, setProductDetails] = useState({
    name: "",
    price: "",
    stock: "",
    category: "vegetables",
    imageUrl: "",
  });

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImagePreview(URL.createObjectURL(file));
      setProductDetails({ ...productDetails, imageUrl: `/assets/Admin_Assets/${file.name}` }); 
    }
  };

  const changeHandler = (e) => {
    setProductDetails({ ...productDetails, [e.target.name]: e.target.value });
  };

  const Add_Product = async () => {
    try {
      const response = await fetch("http://localhost:8080/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(productDetails),
      });

      if (!response.ok) throw new Error("Failed to add product");

      const data = await response.json();
      console.log("Product added:", data);
      alert("Product added successfully!");
      setProductDetails({
        name: "",
        price: "",
        stock: "",
        category: "vegetables",
        imageUrl: "",
      });
      setImagePreview(null);
    } catch (error) {
      console.error("Error:", error);
      alert("Failed to add product!");
    }
  };

  return (
    <div className="add-product">
      <div className="addproduct-itemfield">
        <p>Product title</p>
        <input
          value={productDetails.name}
          onChange={changeHandler}
          type="text"
          name="name"
          placeholder="Product Title"
        />
      </div>

      <div className="addproduct-price-stock">
        <div className="addproduct-itemfield price-field">
          <p>Price</p>
          <input
            value={productDetails.price}
            onChange={changeHandler}
            type="number"
            name="price"
            placeholder="Price"
          />
        </div>
        <div className="addproduct-itemfield price-field">
          <p>Stock</p>
          <input
            value={productDetails.stock}
            onChange={changeHandler}
            type="number"
            name="stock"
            placeholder="Stock"
          />
        </div>
      </div>

      <div className="addproduct-bottom">
        <div className="addproduct-itemfield">
          <p>Product Category</p>
          <select
            value={productDetails.category}
            onChange={changeHandler}
            name="category"
            className="add-product-selector"
          >
            <option value="vegetables">Vegetables</option>
            <option value="food">Food</option>
            <option value="bakeries">Bakeries</option>
          </select>
        </div>

        <div className="addproduct-itemfield">
          <label htmlFor="file-input">
            <img
              src={imagePreview || upload_area}
              className="upload-addproduct-thumbnail-img"
              alt="Upload Preview"
            />
          </label>
          <input type="file" id="file-input" hidden onChange={handleFileChange} />
        </div>
      </div>

      <button onClick={Add_Product} className="addproduct-btn">
        Add Product
      </button>
    </div>
  );
};

export default AddProduct;
