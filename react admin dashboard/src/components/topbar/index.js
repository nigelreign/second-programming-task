import React from "react";

const Topbar = (props) => {
  return (
    <>
      <nav class="navbar navbar-light navbar-top navbar-expand">
        <div class="navbar-logo">
          <button
            class="btn navbar-toggler navbar-toggler-humburger-icon"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarVerticalCollapse"
            aria-controls="navbarVerticalCollapse"
            aria-expanded="false"
            aria-label="Toggle Navigation"
          >
            <span class="navbar-toggle-icon">
              <span class="toggle-line"></span>
            </span>
          </button>{" "}
          <a class="navbar-brand me-1 me-sm-3" href>
            <div class="d-flex align-items-center">
              <div class="d-flex align-items-center">
                <p class="logo-text ms-2 d-none d-sm-block"></p>
              </div>
            </div>
          </a>
        </div>
      </nav>
    </>
  );
};

export default Topbar;
