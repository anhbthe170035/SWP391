function updatePrices() {
    let totalPrice = 0;
    let totalDiscountedPrice = 0;

    document.querySelectorAll('input[name="selectedItems"]:checked').forEach(checkbox => {
        let row = checkbox.closest('tr');
        let quantity = parseInt(row.querySelector('.quantity-input').value);
        let price = parseInt(row.querySelector('.price').innerText);
        let discount = parseInt(row.querySelector('.discount').innerText);

        let itemPrice = price * quantity;
        let discountAmount = (price * (100 - discount)) / 100;
        let itemDiscountedPrice = (itemPrice - discountAmount * quantity);

        totalPrice += itemPrice;
        totalDiscountedPrice += itemDiscountedPrice;
    });

    let finalPrice = totalPrice - totalDiscountedPrice;

    document.querySelector('#totalPrice').innerText = totalPrice;
    document.querySelector('#totalDiscountedPrice').innerText = totalDiscountedPrice;
    document.querySelector('#finalPrice').innerText = finalPrice;

    // Update the hidden input field with the final price
    document.querySelector('#finalPriceInput').value = finalPrice;
}

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('input[name="selectedItems"]').forEach(checkbox => {
        checkbox.addEventListener('change', updatePrices);
    });

    document.querySelectorAll('.quantity-input').forEach(input => {
        input.addEventListener('change', updatePrices);
    });

    // Initial update
    updatePrices();
});

