import React, { useState } from "react";
import "./CSS/LoginSignup.css";
import { useNavigate } from "react-router-dom";

const LoginSignup = () => {
  const [state, setState] = useState("Login");
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    phone: "",
    password: "",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const changeHandler = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const login = async () => {
    try {
      const res = await fetch("http://localhost:8080/customers/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          email: formData.email,
          password: formData.password,
        }),
      });

      if (!res.ok) {
        const errText = await res.text();
        throw new Error(errText || "Login failed");
      }

      const data = await res.json();

      localStorage.setItem("customer", JSON.stringify(data));

      alert(`Welcome back, ${data.name}!`);
      navigate("/");
    } catch (err) {
      setError(err.message);
    }
  };

  const signup = async () => {
    try {
      const res = await fetch("http://localhost:8080/customers/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          name: formData.username,
          email: formData.email,
          phoneNumber: formData.phone,
          password: formData.password,
        }),
      });

      const data = await res.json();

      if (!res.ok) {
        throw new Error(data.error || "Signup failed");
      }

      alert(`Account created! Welcome, ${formData.username}`);
      setState("Login");
      setFormData({ username: "", email: "", phone: "", password: "" });
    } catch (err) {
      console.error("Signup error:", err);
      alert("Signup failed: " + err.message);
    }
  };

  return (
    <div className="loginsignup">
      <div className="loginsignup-container">
        <h1>{state}</h1>
        <div className="loginsignup-fields">
          {state === "Sign Up" && (
            <input
              name="username"
              value={formData.username}
              onChange={changeHandler}
              type="text"
              placeholder="Your Name"
            />
          )}
          <input
            name="email"
            value={formData.email}
            onChange={changeHandler}
            type="email"
            placeholder="E-mail Address"
          />
          {state === "Sign Up" && (
            <input
              name="phone"
              value={formData.phone}
              onChange={changeHandler}
              type="tel"
              placeholder="Phone Number"
            />
          )}
          <input
            name="password"
            value={formData.password}
            onChange={changeHandler}
            type="password"
            placeholder="Password"
          />
        </div>
        {error && <p className="signup-error">{error}</p>}
        <button
          onClick={() => {
            state === "Login" ? login() : signup();
          }}
        >
          Continue
        </button>
        {state === "Sign Up" ? (
          <p className="loginsignup-login">
            Already have an account?{" "}
            <span onClick={() => setState("Login")}>Login here</span>
          </p>
        ) : (
          <p className="loginsignup-login">
            Create an account?{" "}
            <span onClick={() => setState("Sign Up")}>Click here</span>
          </p>
        )}
        {state === "Sign Up" && (
          <div className="loginsignup-agree">
            <input type="checkbox" />
            <p>
              By signing up, you agree to our Terms & Conditions and Privacy
              Policy.
            </p>
          </div>
        )}
      </div>
    </div>
  );
};

export default LoginSignup;
