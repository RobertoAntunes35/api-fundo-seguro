import CreditsService from "../service/CreditsService.js";


class OrderController {
    async createOrder(req, res) {
        let order = await CreditsService.createCredits(req);
        return res.status(order.status).json(order)
    }

    async findById(req, res) {
        let order = await CreditsService.findById(req);
        return res.status(order.status).json(order)
    }
}

export default new OrderController();