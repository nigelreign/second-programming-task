import React, { useEffect, useState } from "react";

// import Sidebar from "components/sidebar";
import Topbar from "components/topbar";
import axios from "axios";
import { Modal, Form, Spin, notification } from "antd";

const Dashboard = (props) => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState(false);
  const [form] = Form.useForm();
  const url = `http://localhost:8000/api/v1/user/`;


  const showModal = () => {
    console.log("showModal");
    setIsModalVisible(true);
  };

  const onSubmit = (values) => {
    //submit values to an endpoint using axios
    setIsLoading(true);

    const headers = {
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    };

    if (values.password !== values.password2) {
      notification.error({
        message: "Error",
        description: "Passwords do not match",
      });
      setIsLoading(false);
      return;
    }
    const payload = JSON.stringify({
      ...values,
    });
    axios
      .post(url, payload, headers)
      .then((res) => {
        if (res.status === 201) {
          setIsModalVisible(false);
          setIsLoading(false);
          getUsers()
          form.resetFields();
          notification.success({
            message: "Success",
            description: "Admin added successfully",
            style: {
              top: 70,
            },
          });
        } else {
          setIsLoading(false);
          notification.error({
            message: "Error",
            description: "Failed to add admin",
            style: {
              top: 70,
            },
          });
        }
      })
      .catch((error) => {
        setIsLoading(false);
        setTimeout(() => {}, 1000);
        notification.error({
          message: "Error",
          description: "Failed to add admin",
          style: {
            top: 70,
          },
        });
      });
  };

  useEffect(() => {
    getUsers();
  }, []);

  const getUsers = () => {
    //submit values to an endpoint using axios
    setIsLoading(true);

    const headers = {
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    };

    axios
      .get(url, headers)
      .then((res) => {
        if (res.status === 200) {
          setIsLoading(false);
          setData(res.data.results);
        }
      })
      .catch((error) => {
        setIsLoading(false);
      });
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <>
      <main class="main" id="top">
        <div class="container-fluid px-0">
          {/* <Sidebar /> */}
          <Topbar />
          <div class="content pt-1">
            <div class="row g-4">
              <div class="col-12 col-xl-12 order-1">
                <div
                  class="card shadow-none border border-300 my-5"
                  data-component-card
                >
                  <div class="card-header p-4 border-bottom border-300 bg-soft">
                    <div class="row g-3 justify-content-between align-items-end">
                      <div class="col-12 col-md">
                        <h3 class="text-900 mb-0" data-anchor>
                          MOBILE APP ADMINS
                        </h3>
                      </div>
                      <div class="col col-md-auto">
                        <nav
                          class="nav nav-underline justify-content-end border-0 doc-tab-nav align-items-center"
                          role="tablist"
                        >
                          <a
                            href
                            onClick={showModal}
                            className="btn btn-sm btn-phoenix-primary preview-btn ms-2"
                          >
                            <span class="me-2" data-feather="eye"></span>
                            Add Admin
                          </a>
                        </nav>
                      </div>
                    </div>
                  </div>
                  <div class="card-body p-0">
                    <div class="p-4 code-to-copy">
                      <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                          </tr>
                        </thead>
                        <tbody>
                          {data &&
                            data.map((item, index) => {
                              return (
                                <tr>
                                  <th scope="row">{index + 1}</th>
                                  <td>{item.first_name}</td>
                                  <td>{item.last_name}</td>
                                  <td>{item.email}</td>
                                </tr>
                              );
                            })}
                        </tbody>
                      </table>
                      <center>
                        <Spin spinning={isLoading} />
                      </center>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <Modal
          title="Add Users"
          visible={isModalVisible}
          footer={false}
          onCancel={handleCancel}
        >
          <Spin spinning={isLoading}>
            <div class="card-body p-0">
              <div class="p-4 code-to-copy">
                <Form form={form} onFinish={onSubmit}>
                  <div class="mb-0">
                    <Form.Item
                      name="first_name"
                      rules={[
                        {
                          required: true,
                          message: "Please input this field!",
                        },
                      ]}
                    >
                      <input
                        class="form-control form-control-sm"
                        id="sizingInput1"
                        placeholder="First Name"
                      />
                    </Form.Item>
                  </div>
                  <div class="mb-0">
                    <Form.Item
                      name="last_name"
                      rules={[
                        {
                          required: true,
                          message: "Please input this field!",
                        },
                      ]}
                    >
                      <input
                        class="form-control form-control-sm"
                        id="sizingInput1"
                        placeholder="Last Name"
                      />
                    </Form.Item>
                  </div>
                  <div class="mb-0">
                    <Form.Item
                      name="email"
                      rules={[
                        {
                          required: true,
                          message: "Please input this field!",
                        },
                      ]}
                    >
                      <input
                        type="email"
                        class="form-control form-control-sm"
                        id="sizingInput1"
                        placeholder="Email address"
                      />
                    </Form.Item>
                  </div>
                  <div class="mb-0">
                    <Form.Item
                      name="password"
                      rules={[
                        {
                          required: true,
                          message: "Please input this field!",
                        },
                      ]}
                    >
                      <input
                        type="password"
                        class="form-control form-control-sm"
                        id="sizingInput1"
                        placeholder="Password"
                      />
                    </Form.Item>
                  </div>
                  <div class="mb-0">
                    <Form.Item
                      name="password2"
                      rules={[
                        {
                          required: true,
                          message: "Please input this field!",
                        },
                      ]}
                    >
                      <input
                        type="password"
                        class="form-control form-control-sm"
                        id="sizingInput1"
                        placeholder="Verify password"
                      />
                    </Form.Item>
                  </div>

                  <br />
                  <br />
                  <div class="mb-0">
                    <input
                      class="btn btn-primary btn-block btn-lg"
                      type="submit"
                      placeholder=".form-control-sm"
                      value="Submit"
                    />
                  </div>
                </Form>
              </div>
            </div>
          </Spin>
        </Modal>
      </main>
    </>
  );
};

export default Dashboard;
