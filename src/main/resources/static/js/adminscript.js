let adminglobals = {};

function loadNavbar(event) {
    document.getElementById("adminNav").addEventListener("click", (event) => {
        location.assign("/adminview");
    });
    document.getElementById("homeNav").addEventListener("click", (event) => {
        location.assign("/home");
    });
    document.getElementById("activityNav").addEventListener("click", (event) => {
        location.assign("/itemview");
    });
}

function loadMainPageContent(event) {
    loadNavbar();
}

document.addEventListener("DOMContentLoaded", loadMainPageContent);

