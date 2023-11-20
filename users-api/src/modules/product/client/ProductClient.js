import axios from 'axios';

import {PRODUCT_API_URL} from '../../../config/secrets/secrets.js';


class ProductCliente {
    async checkProductStock(products, token) {
        try {
            const headers = {
                authorization: token,

            };
            console.info(`Sending request to PRODUCT API with data: ${JSON.stringify(products)}`)
            axios.post(`${PRODUCT_API_URL}/check-stock`, { headers }, products)
            .then(res => {
                return true;
            })
            .catch ((err) => {
                return false
            })
        } catch(err) {
            console.error(err.response.message)
            return false
        }
    }
} 

export default new ProductCliente();

