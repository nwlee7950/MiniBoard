import { apiInstance } from "./index.js";

const api = apiInstance();

function getUsers(success, fail){
    api.get(`/users`).then(success).catch(fail);
}

function getUser(userid, success, fail){
    api.get(`/users/${userid}`).then(success).catch(fail);
}

function registUser(user, success, fail){
    api.post(`/users`, JSON.stringify(user)).then(success).catch(fail);
}

function modifyUser(user, success, fail){
    api.put(`/users`, JSON.stringify(user)).then(success).catch(fail);
}

function deleteUser(userid, success, fail){
    api.delete(`/users/${userid}`).then(success).catch(fail);
}

function loginUser(user, success, fail){
    api.post(`/users/login`, JSON.stringify(user)).then(success).catch(fail);
}