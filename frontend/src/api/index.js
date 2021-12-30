import axios from "axios";
import { API_BASE_URL } from "../config/index";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
      Authorization: null,
    },
  });
  return instance;
}

export { apiInstance };
