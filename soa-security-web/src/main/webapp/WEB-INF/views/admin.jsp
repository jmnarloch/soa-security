<%--
  ~ Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
  --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<html>
<head>
    <title>User administration</title>

    <meta charset="utf-8">

    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">

        @import url('../style/main.css');
    </style>

    <script src="../script/jquery-1.7.1.js"></script>
    <script src="../script/jquery.maskedinput.js"></script>
    <script src="../script/jquery.validate.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../script/bootbox.js"></script>
    <script src="../script/jquery-json.js"></script>
    <script src="../script/common.js"></script>
    <script src="../script/admin.js"></script>

    <script language="javascript" type="text/javascript">

        function displayUsersList() {

            getUsers("#ctnMainList");
        }

        function editUser(id) {

            showUserDialog('Edit user', 'editUser').on({show:function () {

                // temporary make all ajax request synchronous
                jQuery.ajaxSetup({async:false});

                try {
                    getUserRoles("select[name='userRoleId']");

                    retrieveData("User?id=" + id, function (data) {

                        jQuery.each(data, function (name, value) {
                            $(":input[name='" + name + "']").val(value);
                        });

                        jQuery("input[name='enabled']").attr('checked', data.enabled);
                    });
                } finally {

                    jQuery.ajaxSetup({async:true});
                }
            }, hidden:function () {
                jQuery(this).remove();
            }}).modal({"backdrop":"static"});

            jQuery("form[name='editUser']").submit(function () {

                jQuery(this).validate();

                if (jQuery(this).valid()) {
                    var userDTO = jQuery(this).serializeObject();
                    userDTO["enabled"] = jQuery("input[name='enabled']").is(':checked') ? true : false;

                    jQuery.postJSON("User?id=" + userDTO.userId, userDTO,function (data) {

                        jQuery('.modal').modal('hide');
                        displayUsersList();
                        showSuccessAlert("User has been successfully updated");
                    }).error(defaultAjaxErrorHandler);
                }

                return false;
            });
        }

        function performDeleteUser(id) {

            jQuery.ajaxDelete("User?id=" + id,function () {

                showSuccessAlert("The user has been successfully deleted");

                displayUsersList();
            }).error(defaultAjaxErrorHandler);
        }

        function deleteUser(id) {

            bootbox.confirm("Are you sure that you want to delete this user?", function (result) {
                if (result) {

                    performDeleteUser(id);
                }
            });
        }

        function performEnableUser(id, enable) {

            jQuery.post("User/Enable?id=" + id + "&enable=" + enable, null,function () {

                showSuccessAlert("The user has been successfully updated");

                displayUsersList();
            }).error(defaultAjaxErrorHandler);
        }

        function enableUser(id, enable) {

            bootbox.confirm("Are you sure that you want to " + (enable ? "enable" : "disable") + " this user?", function (result) {
                if (result) {

                    performEnableUser(id, enable);
                }
            });
        }

        jQuery(document).ready(function () {

            // hook up event handlers
            jQuery("#usersLink").click(function () {
                selectNavItemActive(this);

                displayUsersList();
            });

            jQuery('#btnAddUser').click(function () {

                showUserDialog('Add user', 'addUser').on({show:function () {

                    getUserRoles("select[name='userRoleId']");
                }, hidden:function () {
                    jQuery(this).remove();
                }}).modal({"backdrop":"static"});

                jQuery("form[name='addUser']").submit(function () {

                    jQuery(this).validate();

                    if (jQuery(this).valid()) {
                        var userDTO = jQuery(this).serializeObject();
                        userDTO["enabled"] = jQuery("input[name='enabled']").is(':checked') ? true : false;

                        jQuery.putJSON("User", userDTO,function (data) {

                            jQuery('.modal').modal('hide');
                            displayUsersList();
                            showSuccessAlert("User has been successfully created");
                        }).error(defaultAjaxErrorHandler);
                    }

                    return false;
                });
            });

            // inits the view
            jQuery("#usersLink").click();
        });

    </script>
</head>
<body>

<div class="container">

    <div class="pull-right">

        <sec:authentication property="principal.username"/>

        <a href="../logout" class="btn btn-link">Logout</a>
    </div>

    <div class="row"></div>

    <div class="page-header">
        <h1>SOA Security
            <small>Admin Console</small>
        </h1>
    </div>

    <div class="row"></div>

    <div class="navbar">
        <div class="navbar-inner">

            <ul class="nav">
                <li class="divider-vertical">
                    <a id="usersLink" href="#">Users</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="alerts">

    </div>

    <div id="submenu">

        <div id="usersSubmenu">

            <a id="btnAddUser" class="btn btn-primary">Add user</a>
        </div>
    </div>

    <div id="ctnMainList" class="mainList">

        <!-- TODO fill up the user list -->

    </div>

</div>

<!-- Displays the loading animation -->
<div class="modalLoading"></div>

</body>
</html>