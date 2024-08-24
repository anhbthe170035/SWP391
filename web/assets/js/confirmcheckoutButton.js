document.getElementById('checkoutForm').addEventListener('submit', function(event) {
    var addressField = document.getElementById('address');
    if (addressField.value.trim() === '') {
        alert('Please input your address');
        event.preventDefault(); // Prevent form submission
    }
});
