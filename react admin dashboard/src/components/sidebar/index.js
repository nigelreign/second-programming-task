import React from "react";
import { Link } from "react-router-dom";

const Sidebar = (props) => {
  return (
    <>
      <nav class="navbar navbar-light navbar-vertical navbar-vibrant navbar-expand-lg">
        <div class="collapse navbar-collapse" id="navbarVerticalCollapse">
          <div class="navbar-vertical-content scrollbar">
            <ul class="navbar-nav flex-column" id="navbarVerticalNav">
              <li class="nav-item">
                <Link class="nav-link active" to="/">
                  <div class="d-flex align-items-center">
                    <span class="nav-link-icon">
                      <span data-feather="cast"></span>
                    </span>
                    <span class="nav-link-text">Dashbboard</span>
                  </div>
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
};

export default Sidebar;
