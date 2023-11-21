import axios from 'axios';

import {CREDITS_API_URL} from '../../../config/secrets/secrets.js';

class ProductCliente {
    async checkTransationCredits(credits,token) {
        try {
            const headers = {
                Authorization: `${token}`
            }
            console.info(`Sending request to DEBTS-CREDITS API with data: ${JSON.stringify(credits)}`)
            await axios.post(`${CREDITS_API_URL}/check`, {headers }, credits)
            .then((res) => {
                return true;
            })
            .catch((err) => {
                console.err(err.response.message)
                return false
            })
        } catch (err) {

        }
    }
} 

export default new ProductCliente();

