<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spencer.ItemApp.models.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin</title>
    <!-- Include Moment.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <!-- Include Moment-Timezone with data -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.34/moment-timezone-with-data.min.js"></script>
    <!-- Include date-fns (non-minified version) -->
    <script src="https://cdn.jsdelivr.net/npm/date-fns@2.16.1"></script>
    <!-- Include chartjs-adapter-date-fns -->
    <script src="/js/script.js" defer></script>
    <c:if test='${role.equals("ADMIN")}'>
        <script src="/js/adminscript.js" defer></script>
        <script src="/js/adminviewscript.js" defer></script>
    </c:if>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <script src="https://kit.fontawesome.com/1a10ff1a82.js" crossorigin="anonymous"></script>
    <input type="hidden" id="_csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">
</head>
<body>
<div id="pageShadow" class="pageShadow">
</div>
<div class='logoHeader'>
    <img class="imgHeader" src="/img/betterlogo.png"></img>
</div>
<div class="userWrapper">
    <h4 class="usernameHeader">${role}: ${ username }</h4>
    <a class="logout" href="/logout">Log out</a>
</div>
<div id="graphPopup" class="popup">
    <span class="close" onclick="closeGraphPopup()">&times;</span>
    <canvas id="myChart"></canvas>
</div>
<div id="tablePopup" class="popup">
    <span class="close" onclick="closeTablePopup()">&times;</span>
    <table id="myTable"></table>
</div>
<div id="navbar" class="navbar">
    <ul class="navbar">
        <li id="homeNav" class="navbar startNavbar"><a>Home</a></li>
        <c:if test='${role.equals("ADMIN")}'>
            <li id="adminNav" class="navbar selected"><a>Admin</a></li>
        </c:if>
        <li id="activityNav" class="navbar endNavbar"><a>Activity</a></li>
    </ul>
</div>
<div id="activeView" class="viewWrapper">
    <div class="formBar">
        <div class="locationWrapper">
            <div class="colored-wrapper top-form ">
                <div class="formContainer">
                    <div id="editUserHider">
                        <h1 id="editUserHeader" class="formHeader collapsedHeader">
                            Edit User <i id="editUserCaret" class="fa fa-caret-down" aria-hidden="true"></i>
                        </h1>
                    </div>
                    <div id="editUserFormHidden" class="hide">
                        <hr/>
                        <form id="editUserForm">
                            <div class="formRow">
                                <label class="formLabel" for="userSelect">
                                    Select User
                                </label>
                                <select class="formSelector" name="userSelect" id="userSelect">
                                </select>
                            </div>
                            <br/>
                            <div class="formRow">
                                <label class="formLabel" for="roleSelect">
                                    Select Role
                                </label>
                                <select class="formSelector" name="roleSelect" id="roleSelect">
                                    <option value="ADMIN">Admin</option>
                                    <option value="USER">User</option>
                                </select>
                            </div>
                            <br/>
                            <div class="formRow">
                                <label class="formLabel" for>Privileges</label>
                                <select class="formSelector" name="privileges">
                                    <option>
                                        No privileges to grant yet
                                    </option>
                                </select>
                            </div>
                            <br/>
                            <div class="formRow">
                                <input id="searchUser" type="submit" name="submitChanges" value="Submit Changes">
                            </div>
                            <div id="loader" class="skill hide">
                                <div class="outer">
                                    <div class="inner"></div>
                                </div>
                                <svg xlmns="http://www.w3.org/2000/svg" version="1.1" width="40px" height="40px">
                                    <defs>
                                        <linearGradient id="GradientColor">
                                            <stop offset="0%" stop-color="#DA22FF"/>
                                            <stop offset="100%" stop-color="#9733EE"/>
                                        </linearGradient>
                                    </defs>
                                    <circle class="formCircle" cx="20" cy="20" r="10" stroke-linecap="round"/>
                                </svg>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="locationWrapper">
            <div class="colored-wrapper top-form second-form">
                <div class="formContainer">
                    <div id="newUserHider">
                        <h1 id="newUserHeader" class="formHeader collapsedHeader">
                            Register New User <i id="newUserCaret" class="fa fa-caret-down" aria-hidden="true"></i>
                        </h1>
                    </div>
                    <div id="newUserFormHidden" class="hide">
                        <hr/>
                        <form id="newUserForm">
                            <div class="formRow">
                                <label class="formLabel" for="newUsername">
                                    Username
                                </label>
                                <input type="text" id="newUsername" name="username" class="userName" required>
                            </div>
                            <br/>
                            <div class="formRow">
                                <label class="formLabel" for="password">
                                    Password
                                </label>
                                <input type="password" id="password" name="password" required>
                            </div>
                            <br/>
                            <div class="formRow">
                                <label class="formLabel" for="passwordChecker">
                                    Re-enter Password
                                </label>
                                <input type="password" id="passwordChecker" name="passwordCheck" required>
                            </div>
                            <br/>
                            <div class="formRow">
                                <label class="formLabel" for="roleSelect">
                                    Select Role
                                </label>
                                <select id="role" class="formSelector">
                                    <option value="USER">
                                        USER
                                    </option>
                                    <option value="ADMIN">
                                        ADMIN
                                    </option>
                                </select>
                            </div>
                            <br/>
                            <div class="formRow">
                                <input type=submit name="Create User" value="Create User">
                            </div>
                            <div id="loader2" class="skill hide">
                                <div class="outer">
                                    <div class="inner"></div>
                                </div>
                                <svg xlmns="http://www.w3.org/2000/svg" version="1.1" width="40px" height="40px">
                                    <defs>
                                        <linearGradient id="GradientColor">
                                            <stop offset="0%" stop-color="#DA22FF"/>
                                            <stop offset="100%" stop-color="#9733EE"/>
                                        </linearGradient>
                                    </defs>
                                    <circle class="formCircle" cx="20" cy="20" r="10" stroke-linecap="round"/>
                                </svg>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="colored-wrapper">
        <table id="activityTable">
            <thead>
            <tr>
                <th id="headerRow" colspan=4>
                    <h1 id="tableHeader">Users</h1>
                    <hr class="inverted"/>
                </th>
            </tr>
            </thead>
            <tbody id="itemBody">
            <tr class="toprow header">
                <th>
                    Role&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                </th>
                <th>
                    Username&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                </th>
                <th>
                    Privileges&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                </th>
                <th>
                </th>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>