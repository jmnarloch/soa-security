/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

function show(target) {

    jQuery(target).removeClass('hidden');
}

function hide(target) {

    jQuery(target).addClass('hidden');
}

function clearForm(form) {

    jQuery(form).validate().resetForm();
    jQuery(form)[0].reset();
}

function showAlert(type, title, message) {

    jQuery($("<div class='alert alert-block " + type + " fade in'>"
        + "<h4 class=alert-heading'> " + title + " </h4>"
        + "<p> " + message + " </p></div>"))
        .appendTo("#alerts")
        .delay(4000).fadeOut('slow', function () {
        jQuery(this).remove();
    });
}

function showSuccessAlert(message) {

    showAlert("alert-success", "Success!", message);
}

function showErrorAlert(message) {

    showAlert("alert-error", "Error!", message);
}

function defaultAjaxErrorHandler(jqXHR, status, error) {

    // hides any modal dialog box
    jQuery('.modal').modal('hide');

    showErrorAlert(error);
}

function selectNavItemActive(target) {

    jQuery("ul.nav li.active").removeClass("active");

    jQuery(target).parent().addClass("active");
}

function removeLinksSelection() {

    jQuery("a.selectedItem").removeClass("selectedItem");
}

function retrieveData(url, success) {

    jQuery.getJSON(url, success).error(defaultAjaxErrorHandler);
}

function displayTable(columnNames, data, target) {

    var cols = [];
    jQuery.each(columnNames, function (index, element) {
        cols.push("<td>" + element + "</td>");
    });

    var rows = [];
    jQuery.each(data, function (index, element) {

        rows.push("<tr><td>" + element.name + "</td></tr>");
    });

    var content = jQuery("<thead/>",
        { html:"<tr>" + cols.join("") + "</tr>" }
    ).outerHTML + rows.join("");

    jQuery(target).empty();
    jQuery("<table/>", { html:content}).appendTo(target);
}

function displayLoadingAnimation() {

    $("body").addClass("loading");
}

function hideLoadingAnimation() {

    $("body").removeClass("loading");
}

jQuery(document).ready(function () {

    jQuery("body").on({
        ajaxStart:displayLoadingAnimation,
        ajaxStop:hideLoadingAnimation
    });
});