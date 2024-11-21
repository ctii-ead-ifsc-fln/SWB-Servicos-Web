//const API_BASE_URL = window.API_BASE_URL || "http://localhost:8083";
//console.log('A PARTIR DO CONFIG.JS: ', API_BASE_URL);
const API_BASE_URL = localStorage.getItem('API_BASE_URL') || 'http://localhost:8081';
console.log('Config API_BASE_URL:', API_BASE_URL);
