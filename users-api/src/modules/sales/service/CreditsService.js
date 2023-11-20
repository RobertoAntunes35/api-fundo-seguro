import { sendMessageCreditsUpdadeQueue } from '../../product/rabbitmq/CreditsUptdate.js';
import * as  httpStatus from "../../../config/constants/httpStatus.js"
import { PENDING, ACCEPTED, REJECTED } from "../status/AccountStatus.js"
import AccountException from "../exception/AccountException.js";
import CreditsRepository from "../repository/CreditsRepository.js";
import ProductClient from '../../product/client/ProductClient.js';


class CreditsService {
    async createCredits(req) {
        try {
            let creditsData  = req.body;
            const { authUser } = req;
            const { authorization } = req.headers;

            this.validadeCreditsData(creditsData)
            let credits = this.createInitialOrderData(creditsData, authUser)
            let createOrder = await this.validadeStockProduct(credits, authorization)
            this.sendMessage(credits)
            let createCredits =  await CreditsRepository.save(credits);
            return {
                status: httpStatus.SUCESS,
                createCredits,
            }
        } catch (err) {
            return {
                status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.message
            }
        }
    }
    validadeCreditsData(data) {
        if(!data || data.credits) {
            throw new AccountException(
                httpStatus.BAD_REQUEST,
                "The credits must be informed")
        }
    }
    async updateCredits(creditsMessage) {
        try {
            const credits = JSON.parse(creditsMessage);
            if (credits.creditsId && credits.status) {
                let existingCredits = await CreditsRepository.findById(credits.credits_id)
                if (existingCredits && order.status !== existingCredits.status) {
                    existingCredits.status = order.status;
                    existingCredits.updatedAt = new Date()
                    await CreditsRepository.save(existingCredits)
                }
            }
            else {
               console.warn('The order message was not complete.') 
            }
        } catch (err) {
            console.error("Could not parse order message from queue");
            console.error(err.message);
        }
    }
    
    async validadeStockProduct(order, token) {
        // creditIsOk = true
    let creditsIsOk = await ProductClient.checkProductStock(
        order.products,
        token
    );
        if (creditsIsOk) {
            throw new AccountException(
                httpStatus.BAD_REQUEST,
                "The credits isn't ok"
                )
        }
    }
    createInitialOrderData(creditsData, authUser) {
        return {
            status: PENDING,
            user: authUser,
            createdAt: new Date(),
            updatedAt: new Date(),
            credits: creditsData,
        }
    } 
    sendMessage(createCredits) {
        const message = {
            userId: createCredits.id,
            credits: createCredits.credits
        }
        sendMessageCreditsUpdadeQueue(message.content.toString());

    }

    async findById(req) {
        const { id } = req.params;
        this.validateInformadedId(id);
        const existingOrder = await CreditsRepository.findById(id);
        if(!existingOrder) {
            throw new AccountException(httpStatus.BAD_REQUEST, "THE ORDER WAS NOT FOUND")
        }
        return {
            status: httpStatus.SUCESS,
            existingOrder,
        }
    } catch (err) {
        return {
            status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
            message: err.message
        }
    }

    validateInformadedId(id) {
        if(!id) {
            throw new AccountException(httpStatus.BAD_REQUEST, "THE ORDER ID MUST BE INFORMED")
        }
    }
}

export default new CreditsService();