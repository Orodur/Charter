[Github](https://github.com/Orodur/Charter)

API provided:

GET /reward?userId=2&start=1686408017
//Query total reward points during a 3-month period starts from given start timestamp, as well as subtotal for each month

POST /reward?userId=1&amount=250
//Create a new transaction whose date is current system time using provided userId and amount.