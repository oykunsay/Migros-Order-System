import React from "react";
import "./Hero.css";
import hand_icon from "../Assets/Frontend_Assets/hand_icon.png";
import arrow_icon from "../Assets/Frontend_Assets/arrow.png";
import hero_image from "../Assets/Frontend_Assets/hero_image.png";
const Hero = () => {
  return (
    <div className="hero">
      <div className="hero-left">
        <div>
          <div className="hero-hand-icon">
            <p>new</p>
            <img src={hand_icon} alt="" />
          </div>
          <p>products</p>
          <p>for everyone</p>
        </div>
        <div className="hero-latest-btn">
          <div>Latest Products</div>
          <img src={arrow_icon} alt="" />
        </div>
      </div>

      <div className="hero-right">
        <img src={hero_image} alt=""></img>
      </div>
    </div>
  );
};

export default Hero;
