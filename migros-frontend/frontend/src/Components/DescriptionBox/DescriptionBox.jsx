import React from "react";
import "./DescriptionBox.css";

const DescriptionBox = (props) => {
  
  return (
    <div className="descriptionbox">
      <div className="descriptionbox-navigator">
        <div className="descriptionbox-nav-box">Description</div>
        <div className="descriptionbox-nav-box fade"> Reviews (122)</div>
      </div>
      <div className="descriptionbox-description">
        <p>
          Discover Migros’ wide range of products designed to make everyday
          shopping simple and reliable. From fresh groceries to household
          essentials.
        </p>
        <p>
          Migros combines quality with affordability to deliver the best
          experience for every customer. Shop with confidence and enjoy the
          convenience of trusted products backed by Migros’ long-standing
          reputation.
        </p>
      </div>
    </div>
  );
};

export default DescriptionBox;
