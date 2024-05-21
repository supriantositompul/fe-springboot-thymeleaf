// profile.js

document.addEventListener("DOMContentLoaded", function() {
    // Get elements
    const nameElement = document.getElementById("name");
    const emailElement = document.getElementById("email");
    const phoneElement = document.getElementById("phone");
    const usernameElement = document.getElementById("username");

    // Sample data (replace with actual data retrieval from backend)
    const userData = {
        name: "suprianto772",
        email: "supri77@gmail.com",
        phone: "082266881234",
        username: "suprianto772",
    };

    // Function to update profile data
    const updateProfileData = () => {
        nameElement.textContent = userData.name;
        emailElement.textContent = userData.email;
        phoneElement.textContent = userData.phone;
        usernameElement.textContent = userData.username;
    };

    // Fetch profile data from backend (replace with actual API endpoint)
    const fetchProfileData = () => {
        // Simulate fetching data with a delay (replace with actual fetch API)
        setTimeout(() => {
            updateProfileData();
        }, 1000); // Delay of 1 second (1000 milliseconds)
    };

    // Call fetchProfileData when the DOM content is loaded
    fetchProfileData();
});