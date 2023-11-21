const env = process.env;

export const MONGO_DB_URL = env.MONGO_DB_URL ? env.MONGO_DB_URL :
"mongodb+srv://rohantunes35:roberto123@developmentroberto.shceiam.mongodb.net/sales_api";

export const API_SECRET = env.API_SECRET ? env.API_SECRET :
"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";

export const RABBIT_MQ_URL = env.RABBIT_MQ_URL ? env.RABBIT_MQ_URL :
"amqp://127.0.0.1:5672";

export const PRODUCT_API_URL = env.PRODUCT_API_URL ? env.PRODUCT_API_URL : 'http://localhost:8081/api/credits' 