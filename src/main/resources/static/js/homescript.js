function loadDashBoard(event){
    let totalFeedsHeader = document.getElementById("totalFeedsHeader");
    let lastFeedHeader = document.getElementById("lastFeedHeader");
    let totalUsersHeader = document.getElementById("totalUsersHeader");
    let lastUserHeader = document.getElementById("lastUserHeader");
    //TODO finish implementing in the header logic for the above headers
    console.log(totalFeedsHeader);
    console.log(lastFeedHeader);
    console.log(totalUsersHeader);
    console.log(lastUserHeader);
}
function loadHomePageDOMContent(event){
    loadDashBoard(event);
}
document.addEventListener("DOMContentLoaded", loadHomePageDOMContent);