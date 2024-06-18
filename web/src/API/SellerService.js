import axios from "axios";

export default class SellerService {
    static addProduct(formData) {
        return axios.post("http://localhost:8080/api/seller/product/add", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    }

    static getCategories() {
        const response = axios.get('http://localhost:8080/api/categories/get-all');
        return response
    }
}