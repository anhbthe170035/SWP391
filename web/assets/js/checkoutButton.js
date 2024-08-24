document.getElementById('checkoutButton').addEventListener('click', function(event) {
    var checkboxes = document.querySelectorAll('input[name="selectedItems"]:checked');
    if (checkboxes.length === 0) {
        event.preventDefault(); // Prevent form submission
        alert('Please choose a product to continue');
    }
});
