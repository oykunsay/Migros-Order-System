import React, { useEffect, useState } from "react";
import "./ListProduct.css";
import cross_icon from "../../assets/Admin_Assets/cross_icon.png";
import edit_icon from "../../assets/Admin_Assets/edit.png";
import save_icon from "../../assets/Admin_Assets/save_icon.png";
import cancel_icon from "../../assets/Admin_Assets/cancel_icon.png";

const ListProduct = () => {
  const [allProducts, setAllProducts] = useState([]);
  const [editProductId, setEditProductId] = useState(null);
  const [editData, setEditData] = useState({});
  const [imagePreview, setImagePreview] = useState(null);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const res = await fetch("http://localhost:8080/products");
      const data = await res.json();
      setAllProducts(data);
    } catch (err) {
      console.error("Failed to fetch products:", err);
    }
  };

  const removeProduct = async (id) => {
    try {
      await fetch(`http://localhost:8080/products/${id}`, { method: "DELETE" });
      setAllProducts(allProducts.filter((p) => p.id !== id));
    } catch (err) {
      console.error("Failed to remove product:", err);
    }
  };

  const startEdit = (product) => {
    setEditProductId(product.id);
    setEditData({ ...product });
    setImagePreview(product.imageUrl || null);
  };

  const cancelEdit = () => {
    setEditProductId(null);
    setEditData({});
    setImagePreview(null);
  };

  const handleChange = (e) => {
    setEditData({ ...editData, [e.target.name]: e.target.value });
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setEditData({ ...editData, imageFile: file });
      setImagePreview(URL.createObjectURL(file));
    }
  };

  const saveEdit = async () => {
    try {
      const updatedProduct = {
        name: editData.name,
        price: Number(editData.price),
        stock: Number(editData.stock),
        category: editData.category,
        imageUrl: editData.imageUrl,
      };

      const resText = await fetch(`http://localhost:8080/products/${editProductId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedProduct),
      });

      if (!resText.ok) throw new Error("Failed to update product text fields");

      if (editData.imageFile) {
        const formData = new FormData();
        formData.append("file", editData.imageFile);

        const resImage = await fetch(
          `http://localhost:8080/products/${editProductId}/image`,
          {
            method: "POST",
            body: formData,
          }
        );

        if (!resImage.ok) throw new Error("Failed to update product image");
      }

      cancelEdit();
      fetchProducts();
    } catch (err) {
      console.error("Update failed:", err);
      alert("Failed to update product");
    }
  };

  return (
    <div className="list-product">
      <h1>All Products List</h1>
      <div className="listproduct-header">
        <span>Edit</span>
        <span>Title</span>
        <span>Price</span>
        <span>Stock</span>
        <span>Category</span>
        <span>Remove</span>
      </div>

      <div className="listproduct-body">
        {allProducts.map((product) => (
          <div key={product.id} className="listproduct-row">
            <span>
              {editProductId === product.id ? (
                <>
                  <img src={save_icon} alt="Save" className="edit-icon" onClick={saveEdit} />
                  <img src={cancel_icon} alt="Cancel" className="edit-icon" onClick={cancelEdit} />
                </>
              ) : (
                <img src={edit_icon} alt="Edit" className="edit-icon" onClick={() => startEdit(product)} />
              )}
            </span>

            <span>
              {editProductId === product.id ? (
                <input type="text" name="name" value={editData.name} onChange={handleChange} />
              ) : (
                product.name
              )}
            </span>

            <span>
              {editProductId === product.id ? (
                <input type="number" name="price" value={editData.price} onChange={handleChange} />
              ) : (
                `₺${product.price}`
              )}
            </span>

            <span>
              {editProductId === product.id ? (
                <input type="number" name="stock" value={editData.stock} onChange={handleChange} />
              ) : (
                product.stock
              )}
            </span>

            <span>
              {editProductId === product.id ? (
                <select name="category" value={editData.category} onChange={handleChange}>
                  <option value="shop">Shop</option>
                  <option value="vegetables">Vegetables</option>
                  <option value="food">Food</option>
                  <option value="drinks">Drinks</option>
                  <option value="bakeries">Bakeries</option>
                </select>
              ) : (
                product.category
              )}
            </span>

            <span>
              {editProductId === product.id ? (
                <>
                  <input type="file" onChange={handleFileChange} />
                  {imagePreview && <img src={imagePreview} alt="Preview" className="preview-image" />}
                </>
              ) : (
                <img src={cross_icon} alt="Remove" className="remove-icon" onClick={() => removeProduct(product.id)} />
              )}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ListProduct;
