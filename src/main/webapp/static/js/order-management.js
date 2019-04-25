window.addEventListener('load', () => {
    console.log("Hello");

    let quantityFields = document.querySelectorAll("input");
    let priceFields = document.querySelectorAll('[data-price]');
    let totalFields = document.querySelectorAll("td:last-child");

    for (let i=0; i<quantityFields.length; i++) {
        quantityFields[i].addEventListener('change', () => {
            let sumOfProducts = parseFloat(quantityFields[i].value) * parseFloat(priceFields[i].dataset.price);
            console.log(parseFloat(priceFields[i].dataset.price));

            totalFields[i].textContent = sumOfProducts.toFixed(1) + " USD";
            totalFields[i].dataset.price = sumOfProducts;
            sumTotalsUp();
        });
    }
})

function sumTotalsUp() {
    let totalFields = document.querySelectorAll("td:last-child");
    let orderTotalField = document.querySelector("span");

    let orderTotalPrice = 0;
    for (let i=0; i<totalFields.length; i++) {
        orderTotalPrice += parseFloat(totalFields[i].dataset.price);
    }

    orderTotalField.textContent = orderTotalPrice.toFixed(1) + " USD";
}