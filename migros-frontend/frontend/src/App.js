import "./App.css";
import Navbar from "./Components/Navbar/Navbar";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Shop from "./Pages/Shop";
import Product from "./Pages/Product";
import UserDetails from "./Pages/UserDetails";
import Cart from "./Pages/Cart";
import LoginSignup from "./Pages/LoginSignup";
import ShopCategory from "./Pages/ShopCategory";
import Footer from "./Components/Footer/Footer";
import vegetables_banner from "./Components/Assets/Frontend_Assets/banner_vegetables.jpg";
import drinks_banner from "./Components/Assets/Frontend_Assets/banner_drinks.jpg";
import bakeries_banner from "./Components/Assets/Frontend_Assets/banner_bakeries.jpg";

import ShopContextProvider from "./Context/ShopContext";

function App() {
  return (
    <ShopContextProvider>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Shop />} />
          <Route
            path="/vegetables"
            element={
              <ShopCategory banner={vegetables_banner} category="vegetables" />
            }
          />
          <Route
            path="/drinks"
            element={<ShopCategory banner={drinks_banner} category="drinks" />}
          />
          <Route
            path="/bakeries"
            element={
              <ShopCategory banner={bakeries_banner} category="bakeries" />
            }
          />
          <Route path="/product/:productId" element={<Product />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/login" element={<LoginSignup />} />
          <Route path="/user-details" element={<UserDetails />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </ShopContextProvider>
  );
}

export default App;
