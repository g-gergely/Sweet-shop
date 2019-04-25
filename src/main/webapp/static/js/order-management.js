window.addEventListener('load', () => {

    let lineItemIds = document.querySelectorAll("[data-id]");
    let quantityFields = document.querySelectorAll("input");
    let priceFields = document.querySelectorAll('[data-price]');
    let totalFields = document.querySelectorAll("td:last-child");

    for (let i=0; i<quantityFields.length; i++) {
        quantityFields[i].addEventListener('change', () => {
            if (parseFloat(quantityFields[i].value) === 0) {
                removeLineItem(lineItemIds[i].dataset.id);
            }

            let sumOfProducts = parseFloat(quantityFields[i].value) * parseFloat(priceFields[i].dataset.price);

            totalFields[i].textContent = sumOfProducts.toFixed(1) + " USD";
            totalFields[i].dataset.total = sumOfProducts;
            sumTotalsUp();
            serverUpdateLineItem("/order", parseInt(lineItemIds[i].dataset.id), parseInt(quantityFields[i].value))
        });
    }
});

function sumTotalsUp() {
    let totalFields = document.querySelectorAll("td:last-child");
    let orderTotalField = document.querySelector("span");

    let orderTotalPrice = 0;
    for (let i=0; i<totalFields.length; i++) {
        orderTotalPrice += parseFloat(totalFields[i].dataset.total);
    }

    orderTotalField.textContent = orderTotalPrice.toFixed(1) + " USD";
}


function serverUpdateLineItem(url, lineId, quantity) {
    return fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "LineId": lineId,
            "Quantity": quantity
        }
    })
}


function removeLineItem(id) {
    let lineItem = document.querySelector("[data-id='" + id + "']");
    lineItem.remove();
}