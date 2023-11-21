import {Router} from "express";

import CreditsController from "../controller/CreditsController.js";

const router = new Router();

router.get('/api/order/:id', CreditsController.findById);
router.post('/api/order/create', CreditsController.createOrder);

export default router;