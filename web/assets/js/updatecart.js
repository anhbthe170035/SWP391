// Define the function to handle updating the cart quantity
function updateAmount(cdeid, newQuantity) {
    fetch('updateQuantity', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            cdeid: cdeid,
            quantity: newQuantity
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            console.log("Quantity updated successfully.");
        } else {
            console.log("Failed to update quantity.");
        }
    })
    .catch(error => {
        console.log("Error updating quantity:", error);
    });
}

// Set up event listeners when the DOM content is loaded
document.addEventListener('DOMContentLoaded', function() {
    const quantityInputs = document.querySelectorAll('.quantity-input');

    quantityInputs.forEach(input => {
        input.addEventListener('change', function() {
            const cdeid = this.getAttribute('data-cdeid');
            const newQuantity = this.value;
            updateAmount(cdeid, newQuantity);
        });
    });
});
