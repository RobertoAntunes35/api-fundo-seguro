import CreditsService from "../service/CreditsService.js";


class CreditsController {
    async createOrder(req, res) {
        let credits = await CreditsService.createCredits(req);
        return res.status(credits.status).json(credits)
    }

    async findById(req, res) {
        let order = await CreditsService.findById(req);
        return res.status(order.status).json(order)
    }
}
export default new CreditsController();