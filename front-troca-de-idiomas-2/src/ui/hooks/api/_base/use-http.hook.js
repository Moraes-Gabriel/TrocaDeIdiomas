import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalError } from "../../../context/error.context";
import { useGlobalUser } from "../../../context/user.context";
import { useAxios } from "./use-axios.hook";

export function useHttp(baseURL, headers) {
  const instance = useAxios(baseURL, headers);
  const navigate = useNavigate();
  const [, setError] = useGlobalError();
  const [, setUser] = useGlobalUser();


  async function get(url, headers) {
    try {
      const response = await instance.get(url, headers);
      return response.data;
    } catch (error) {
      setError(error?.response?.data);
      if (error?.response?.status === 401) {
        /* localStorage.removeItem("token")
        navigate("/login"); */
      }
    }
  }

  async function post(url, data, headers) {
    try {
      const response = await instance.post(url, data, headers);
      return response;
    } catch (error) {
      setError(error?.response?.data);
      if (error?.response?.status === 401) {
        /* localStorage.removeItem("token")
        navigate("/login"); */
      }
    }
  }

  async function login(url, data, headers) {
    try {
      const response = await instance.post(url, data, headers);
      console.log(response);
      console.log(response.headers["x-auth-token"]);
      localStorage.setItem('user', JSON.stringify(response.data))
      return response.headers["x-auth-token"];
    } catch (error) {
      setError(error?.response?.data);
      if (error?.response?.status === 401) {
        /* localStorage.removeItem("token")
        navigate("/login"); */
      }
    }
  }
  async function put(url, data, headers) {
    try {
      const response = await instance.put(url, data, headers);
      return response.data;
    } catch (error) {
      setError(error?.response?.data);
      if (error?.response?.status === 401) {
        /* localStorage.removeItem("token")
        navigate("/login"); */
      }
    }
  }

  return {
    get,
    post,
    login,
    put,
  };
}
