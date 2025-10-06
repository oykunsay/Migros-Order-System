import React, { useState, useRef, useContext } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import "./Navbar.css";
import logo from "../Assets/Frontend_Assets/logo.png";
import user_icon from "../Assets/Frontend_Assets/user.png";
import cart_icon from "../Assets/Frontend_Assets/cart_icon.png";
import nav_dropdown from "../Assets/Frontend_Assets/nav_dropdown.png";
import { ShopContext } from "../../Context/ShopContext";

const Navbar = () => {
  const [menu, setMenu] = useState("shop");
  const { getTotalCartItems } = useContext(ShopContext);
  const menuRef = useRef();
  const location = useLocation();
  const navigate = useNavigate();

  const dropdown_toggle = () => {
    menuRef.current.classList.toggle("nav-menu-visible");
  };

  const isLoggedIn = !!localStorage.getItem("customer");

  return (
    <div className="navbar">
      <div className="nav-logo">
        <Link
          to="/"
          style={{
            display: "flex",
            alignItems: "center",
            textDecoration: "none",
          }}
          onClick={() => setMenu("shop")}
        >
          <img src={logo} alt="Logo" />
          <p>MIGROS</p>
        </Link>
      </div>

      <img
        className="nav-dropdown"
        onClick={dropdown_toggle}
        src={nav_dropdown}
        alt="Dropdown"
      />

      <ul ref={menuRef} className="nav-menu">
        <li onClick={() => setMenu("shop")}>
          <Link to="/" style={{ textDecoration: "none" }}>
            Shop
          </Link>
          {(menu === "shop" || location.pathname === "/") && <hr />}
        </li>
        <li onClick={() => setMenu("vegetables")}>
          <Link to="/vegetables" style={{ textDecoration: "none" }}>
            Vegetables
          </Link>
          {location.pathname === "/vegetables" && <hr />}
        </li>
        <li onClick={() => setMenu("drinks")}>
          <Link to="/drinks" style={{ textDecoration: "none" }}>
            Drinks
          </Link>
          {location.pathname === "/drinks" && <hr />}
        </li>
        <li onClick={() => setMenu("bakeries")}>
          <Link to="/bakeries" style={{ textDecoration: "none" }}>
            Bakery
          </Link>
          {location.pathname === "/bakeries" && <hr />}
        </li>
      </ul>

      <div className="nav-login-cart">
        {isLoggedIn ? (
          <button
            onClick={() => {
              localStorage.removeItem("customer");
              window.location.replace("/");
            }}
          >
            Logout
          </button>
        ) : (
          <Link to="/login">
            <button>Login</button>
          </Link>
        )}

        <Link to="/cart">
          <img src={cart_icon} alt="Cart" />
        </Link>
        <div className="nav-cart-count">{getTotalCartItems()}</div>

        <img
          src={user_icon}
          alt="User"
          className="user-icon"
          style={{ cursor: "pointer", marginLeft: "15px" }}
          onClick={() => {
            if (isLoggedIn) {
              navigate("/user-details");
            } else {
              navigate("/login"); 
            }
          }}
        />
      </div>
    </div>
  );
};

export default Navbar;
