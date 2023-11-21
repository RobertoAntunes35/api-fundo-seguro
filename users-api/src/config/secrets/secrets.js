const env = process.env;

export const MONGO_DB_URL = env.MONGO_DB_URL ? env.MONGO_DB_URL :
"mongodb+srv://rohantunes35:roberto123@developmentroberto.shceiam.mongodb.net/transactions";
// "mongodb://localhost:27017/debts_credits"


export const API_SECRET = env.API_SECRET ? env.API_SECRET :
"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";

export const RABBIT_MQ_URL = env.RABBIT_MQ_URL ? env.RABBIT_MQ_URL :
"amqp://localhost:5672";

export const CREDITS_API_URL = env.CREDITS_API_URL ? env.CREDITS_API_URL : 'http://localhost:8081/api/credits' 