/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

function getUsers(target) {

    retrieveData("User/All", function (data) {

        jQuery(target).empty();

        if (data.length > 0) {
            var rows = ["<thead><tr><td>Name</td><td>Edit</td><td>Status</td><td>Enable</td><td>Delete</td></tr></thead>"];
            jQuery.each(data, function (index, element) {

                rows.push("<tr><td>" + element.userName + "</td>"
                    + "<td><button class='btn' onclick='editUser(" + element.userId + ");'>Edit</button></td>"
                    + "<td>" + (element.enabled ? "Enabled" : "Disabled") + "</td>"
                    + "<td><button class='btn " + (element.enabled ? "btn-danger" : "btn-success") + "' onclick='enableUser(" + element.userId + ", " + !element.enabled + ");'> " + (element.enabled ? "Disable" : "Enable") + "</button></td>"
                    + "<td><button class='btn btn-danger' onclick='deleteUser(" + element.userId + ")'>Remove</button></td>"
                    + "</tr>");
            });

            jQuery("<table/>", { html:rows.join(""), class:"table table-striped table-hover"}).appendTo(target);
        } else {

            jQuery('<div class="alert alert-info">No users were defined</div>').appendTo(target);
        }
    });
}

function getUserRoles(target) {

    jQuery(target).empty();
    retrieveData("Lookup/UserRole", function (data) {

        jQuery.each(data, function (index, element) {
            jQuery(target)
                .append(jQuery("<option/>", { value:element.id })
                .text(element.name));
        });
    });
}

function showUserDialog(title, formName) {

    return jQuery("<div id='userModal' class='modal hide fade'>" +
        "        <div class='modal-header'>" +
        "            <h3> " + title + " </h3>" +
        "        </div>" +
        "        <form name='" + formName + "' class='form-horizontal'>" +
        "        <div class='modal-body'>" +
        "                <div class='control-group'>" +
        "                <input type='hidden' id='userId' name='userId' value='0'/>" +
        "                <label class='control-label' for='userName'>Name</label>" +
        "                <div class='controls'>" +
        "                    <input id='userName' name='userName' type='text' class='required' placeholder='Name'>" +
        "                    </div>" +
        "                </div>" +
        "                <div class='control-group'>" +
        "                    <label class='control-label' for='userPassword'>Password</label>" +
        "                    <div class='controls'>" +
        "                        <input id='userPassword' name='userPassword' class='required' type='password'>" +
        "                    </div>" +
        "                </div>" +
        "                <div class='control-group'>" +
        "                    <label class='control-label' for='confirmPassword'>Confirm password</label>" +
        "                    <div class='controls'>" +
        "                        <input id='confirmPassword' type='password'>" +
        "                    </div>" +
        "                </div>" +
        "                <div class='control-group'>" +
        "                    <label class='control-label' for='emailAddress'>Email</label>" +
        "                    <div class='controls'>" +
        "                        <input id='emailAddress' name='emailAddress' type='text' class='required email'" +
        "                        placeholder='Email'>" +
        "                    </div>" +
        "                </div>" +
        "                <div class='control-group'>" +
        "                    <label class='control-label' for='userRoleId'>User role</label>" +
        "                    <div class='controls'>" +
        "                            <select id='userRoleId' name='userRoleId'></select>" +
        "                    </div>" +
        "               </div>" +
        "               <div class='control-group'>" +
        "                   <label class='control-label' for='enabled'>Enabled</label>" +
        "                   <div class='controls'>" +
        "                       <input id='enabled' name='enabled' type='checkbox' checked='true'>" +
        "                   </div>" +
        "               </div>" +
        "           </div>" +
        "           <div class='modal-footer'>" +
        "               <button type='submit' class='btn btn-primary'>Save</button>" +
        "               <button type='button' class='btn btn-danger' data-dismiss='modal'>Cancel</button>" +
        "           </div>" +
        "       </form>" +
        "   </div>").appendTo("body");
}