<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spencer.ItemApp.models.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Activity Feed</title>
    <!-- Include Moment.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <!-- Include Moment-Timezone with data -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.34/moment-timezone-with-data.min.js"></script>
    <!-- Include date-fns (non-minified version) -->
    <script src="https://cdn.jsdelivr.net/npm/date-fns@2.16.1"></script>
    <script src="/js/itemscript.js" defer></script>
    <c:choose>
        <c:when test='${role.equals("ADMIN")}'>
            <script src="/js/adminscript.js" defer></script>
        </c:when>
        <c:otherwise>
            <script src="/js/script.js" defer></script>
        </c:otherwise>
    </c:choose>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <script src="https://kit.fontawesome.com/1a10ff1a82.js" crossorigin="anonymous"></script>
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
            <li id="adminNav" class="navbar"><a>Admin</a></li>
        </c:if>
        <li id="activityNav" class="navbar selected endNavbar"><a>Activity</a></li>
    </ul>
</div>
<div id="activeView" class="viewWrapper">
    <div class="formBar">
        <div class="locationWrapper">
            <div class="colored-wrapper top-form">
                <div class="formContainer">
                    <div id="uploadFormHider">
                        <h1 id="uploadFormHeader" class="formHeader collapsedHeader">
                            Upload New File <i id="uploadCaret" class="fa fa-caret-down" aria-hidden="true"></i>
                        </h1>
                    </div>
                    <div id="uploadFormHidden" class="hide">
                        <hr/>
                        <form id="sendUploadForm" method="POST" action="item/upload" enctype="multipart/form-data">
                            <label class="formLabel">Select Date</label>
                            <input id="_csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input id="fileDate" class="formDate" type="date" name="date" required>
                            <input id="uploadButton" type="file" name="file" required>
                            <input id="sendUploadButton" type="submit" name="submit" value="upload">
                            <div id="loader" class="skill hide">
                                <div class="outer">
                                    <div class="inner">
                                    </div>
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
                    <div id="searchFormHider">
                        <h1 id="searchFormHeader" class="formHeader collapsedHeader">Search By <i id="searchCaret"
                                                                                                  class="fa fa-caret-down"
                                                                                                  aria-hidden="true"></i>
                        </h1>
                    </div>
                    <div id="searchFormHidden" class="hide">
                        <hr/>
                        <form id="superSearch">
                            <label class="formLabel">
                                Start Date
                            </label>
                            <input type="date" id="startDate" class="formDate">
                            <label class="formLabel">
                                End Date
                            </label>
                            <input type="date" id="endDate" class="formDate">
                            <br/>
                            <label class="formLabel">Search Type</label>
                            <select id="searchType" class="searchFormSelect">
                                <option value="">
                                </option>
                                <option value="id">
                                    Id
                                </option>
                                <option value="sku">
                                    Sku
                                </option>
                            </select>
                            <label class="formLabel">Status</label>
                            <select id="activeStatus" class="searchFormSelect">
                                <option value="B" selected>
                                    Both
                                </option>
                                <option value="N">
                                    Inactive
                                </option>
                                <option value="Y">
                                    Active
                                </option>
                            </select>
                            <br/>
                            <label class="formLabel">
                                Enter the search values separated by comma; white space is ignored.
                            </label>
                            <br/>
                            <textarea id="bigBox" class="bigbox" name="values"
                                      palceholder="Seperate each value by semi colon, white space is negligable"></textarea>
                            <br/>
                            <input type="submit" value="Search" name="Search">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="hidder" class="hide">
        <div class="colored-wrapper table-margin">
            <div id="tableShadow" class="tableShadow">
                <div id="tableLoader" class="skill2 hide">
                    <div class="outer2">
                        <div class="inner2">
                        </div>
                    </div>
                    <svg xlmns="http://www.w3.org/2000/svg" version="1.1" width="40px" height="40px">
                        <defs>
                            <linearGradient id="tableGradientColor">
                                <stop offset="0%" stop-color="#DA22FF"/>
                                <stop offset="100%" stop-color="#9733EE"/>
                            </linearGradient>
                        </defs>
                        <circle class="tableCircle" cx="20" cy="20" r="10" stroke-linecap="round"/>
                    </svg>
                </div>
            </div>
            <table id="activityTable">
                <thead>
                <tr>
                    <th id="headerRow" colspan=12>
                        <h1 id="tableHeader"></h1>
                        <hr class="inverted"/>
                    </th>
                </tr>
                </thead>
                <tbody id="itemBody">
                <tr class="toprow header">
                    <th onclick="changeSort('id')">
                        Id&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('sku')">
                        Sku&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('date')">
                        Date&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('brand')">
                        Brand&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('dept')">
                        Department&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('itemClass')">
                        Class&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('originalPrice')">
                        Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('salePrice')">
                        Sale Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('activeFlag')">
                        Active&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('imageFile')">
                        Image&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('variants')">
                        Variants&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                    <th onclick="changeSort('rating')">
                        Rating&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
                    </th>
                </tr>
                <tr id="searchRow">
                    <td>
                        <input id="idSearch" name="idSearch" type="text" placeholder="Search..." value="${ id }">
                    </td>
                    <td>
                        <input id="skuSearch" name="idSearch" type="text" placeholder="Search..." value="${ sku }">
                    </td>
                    <td>
                        <input id="dateSearch" name="dateSearch" type="text" placeholder="Search..." value="${ date }">
                    </td>
                    <td>
                        <input id="brandSearch" name="brandSearch" type="text" placeholder="Search..."
                               value="${ brand }">
                    </td>
                    <td>
                        <input id="deptSearch" name="deptSearch" type="text" placeholder="Search..." value="${ dept }">
                    </td>
                    <td>
                        <input id="itemClassSearch" name="itemClassSearch" type="text" placeholder="Search..."
                               value="${ itemClass }">
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                        <input id="activeFlagSearch" name="activeFlagSearch" type="text" placeholder="Search..."
                               value="${ activeFlag }">
                    </td>
                    <td>
                        <input id="imageFileSearch" name="imageFileSearch" type="text" placeholder="Search..."
                               value="${ imageFile }">
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <input id="url" type="hidden" name="contentURL" value="${ url }">
                <tr class="footer">
                    <th id="bottomrow" class="bottomrow" colspan=12>
                    </th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
</body>
</html>