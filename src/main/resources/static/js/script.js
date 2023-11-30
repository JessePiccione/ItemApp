function loadMainPageContent(event) {
    loadNavbar(event);
}

function loadNavbar(event) {
    document.getElementById("homeNav").addEventListener("click", (event) => {
        location.assign("/home");
    });
    document.getElementById("activityNav").addEventListener("click", (event) => {
        location.assign("/itemview");
    });
}

document.addEventListener("DOMContentLoaded", loadMainPageContent);