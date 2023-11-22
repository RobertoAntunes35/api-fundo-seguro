import {Router} from "express";

import CreditsController from "../controller/CreditsController.js";

const router = new Router();

router.get('/api/credits/:id', CreditsController.findById);
router.post('/api/credits/create', CreditsController.createCredits);

export default router;