import UserHandler from "../../modules/sales/model/User.js";


export async function createInitialData() {
    await UserHandler.collection.drop()
    
    await UserHandler.create({
        products: [
            {
                productId: 1001,
                quantity: 2,
            },
            {
                productId:1002,
                quantity: 2,
            },
            {
                productId: 1003,
                quantity: 1,
            }
        ],
        user: {
            id: 'akekroekre',
            name: 'Roberto Antunes',
            email: 'robertoantunes@gmail.com',
            cpf_cnpj: '123.456.789-01'
        },
        status: 'APPROVED',
        createdAt: new Date(),
        updatedAt: new Date(),
    });

    await UserHandler.create({
        products: [
            {
                productId: 1001,
                quantity: 2,
            },
            {
                productId:1002,
                quantity: 2,
            },
            {
                productId: 1003,
                quantity: 1,
            }
        ],
        user: {
            id: 'a1231K21',
            name: 'Roberto Antunes De Souza',
            email: 'robertoantunesadadadada@gmail.com',
            cpf_cnpj: '157.895.687-08'
        },
        status: 'REJECTED',
        createdAt: new Date(),
        updatedAt: new Date(),
})
    let initialData = await UserHandler.find() 
    console.log(`The initial Data: ${JSON.stringify(initialData, undefined, 4)}`)
}

// credits: {
//     type: Array, 
//     required: true,
// },
// debts: {
//     type: Array,
//     required: true,
// },
// user: {
//     type: Object,
//     required: true,
// },
// status: {
//     type: String, 
//     required: true, 
// },
// createdAt: {
//     type: Date, 
//     required: true, 
// },
// updatedAt: {
//     type: Date, 
//     required: true
// },