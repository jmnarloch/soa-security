<%--
~ Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<html>
<head>
<title>Dashboard</title>

<meta charset="utf-8">

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">

    @import url('../style/main.css');
</style>

<script src="../script/jquery-1.7.1.js"></script>
<script src="../script/jquery.maskedinput.js"></script>
<script src="../script/jquery.validate.js"></script>
<script src="../script/jquery-json.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../script/bootbox.js"></script>
<script src="../script/common.js"></script>
<script src="../script/dashboard.js"></script>

<script language="javascript" type="text/javascript">

// represents the main div where all the data is being presented
var mainContentList = "#ctnMainList";

// the security list
var securityList = new SecurityList();

function displayServices() {

    getUserServices(mainContentList);
}

function displayDataSources() {

    getDataSources(mainContentList);
}

function displayKeyStores() {

    getKeyStores(mainContentList);
}

function initWebServiceModal() {

    // clears the security list
    securityList.clear();
    securityList.display('#securityConfigurationList');

    getWebServiceSecurityTypes("#webServiceSecurityType");

    jQuery('#securityEnabled').change(function () {

        if (jQuery(this).is(':checked')) {

            // resets the drop down
            jQuery("#webServiceSecurityType").val(jQuery("#webServiceSecurityType option:first").val()).select();
            show('#securityConfiguration');
        } else {

            hide('#securityConfiguration');
        }
    });

    jQuery('#webServiceSecurityType').change(function () {

        selectedValue = jQuery(this).val();

        hide('#divAddSecurityConfiguration');
        hide('#dataSourceConfiguration');
        hide('#keyStoreConfiguration');
        if (selectedValue == 8 || selectedValue == 9) {

            show('#dataSourceConfiguration');
            show('#divAddSecurityConfiguration');
        } else if (selectedValue == 10 || selectedValue == 11) {

            show('#keyStoreConfiguration');
            show('#divAddSecurityConfiguration');

        }
    });

    jQuery('#userKeyStores').select(function () {

        if (jQuery(this).children('option').size() > 0) {

            keyStoreId = jQuery(this).val();

            getKeyStoreAliases(keyStoreId, '#clientAlias');
            getKeyStoreAliases(keyStoreId, '#serverAlias');
        }
    });

    jQuery('#addNewSecurityConfiguration').unbind().click(function () {

        hide(this);
        jQuery("#webServiceSecurityType").val(jQuery("#webServiceSecurityType option:first").val()).select();
        show('#securityConfigurationOptions');

        // prevents from form post
        return false;
    });

    jQuery('#addSecurityConfiguration').unbind().click(function () {

        securityType = jQuery('#webServiceSecurityType').val();

        var isValid = true;
        if (securityType == 8 || securityType == 9) {

            jQuery('#dataSourceConfiguration').wrap('<form />').parent().validate();
            isValid = jQuery('#dataSourceConfiguration').parent().valid();
            jQuery('#dataSourceConfiguration').unwrap();

            if (isValid) {
                securityList.add(createDatabaseSecurity());
            }
        } else if (securityType == 10 || securityType == 11) {

            jQuery('#keyStoreConfiguration').wrap('<form />').parent().validate();
            isValid = jQuery('#keyStoreConfiguration').parent().valid();
            jQuery('#keyStoreConfiguration').unwrap();

            if (isValid) {
                securityList.add(createKeyStoreSecurity());
            }
        }

        if (isValid) {
            securityList.display('#securityConfigurationList');

            // hides the options
            hide('#dataSourceConfiguration');
            hide('#keyStoreConfiguration');
            hide('#securityConfigurationOptions');
            show('#addNewSecurityConfiguration');
        }

        // prevents from form post
        return false;
    });

    getUserDataSources('#userDataSources');
    getUserKeyStores('#userKeyStores');
}

function editService(id) {

    showWebServiceDialog('Add web service', 'editWSService').on({show:function () {

        // temporary make all ajax request synchronous
        jQuery.ajaxSetup({async:false});
        try {
            initWebServiceModal();

            retrieveData("Service?serviceId=" + id, function (data) {

                jQuery.each(data, function (name, value) {
                    $(":input[name='" + name + "']").val(value);
                });

                jQuery("input[name='securityEnabled']").attr('checked', data.securityEnabled).change();
                jQuery(':input[name="endpointAddress"]').val(data.endpoints[0]);

                if (data.securityEnabled) {

                    jQuery.each(data.serviceSecurities, function (index, value) {

                        securityList.add(value);
                    });

                    securityList.display('#securityConfigurationList');
                }
            });
        } finally {

            jQuery.ajaxSetup({async:true});
        }
    }, hidden:function () {
        jQuery(this).remove();
    }}).modal({"backdrop":"static"});

    // on web service create
    jQuery("form[name='editWSService']").submit(function () {

        jQuery(this).validate();

        if (jQuery(this).valid()) {
            var serviceDTO = jQuery(this).serializeObject();
            delete serviceDTO.endpointAddress;
            serviceDTO["securityEnabled"] = jQuery("input[name='securityEnabled']").is(':checked') ? true : false;
            serviceDTO["endpoints"] = [jQuery(':input[name="endpointAddress"]').val()];

            if (serviceDTO.securityEnabled) {
                serviceDTO["serviceSecurities"] = securityList.getAll();
            }

            jQuery.postJSON("Service", serviceDTO,function (data) {

                jQuery('.modal').modal('hide');
                displayServices();
                showSuccessAlert("The service has been successfully updated");
            }).error(defaultAjaxErrorHandler);
        }

        return false;
    });
}

function performDeleteService(id) {

    jQuery.ajaxDelete("Service?serviceId=" + id,function () {

        displayServices();
        showSuccessAlert("The service has been successfully deleted");
    }).error(defaultAjaxErrorHandler);
}

function deleteService(id) {

    bootbox.confirm("Are you sure that you want to delete this service?", function (result) {
        if (result) {

            performDeleteService(id);
        }
    });
}

function editDataSource(id) {

    showDataSourceDialog('Edit data source', 'editDataSource').on({show:function () {

        retrieveData("DataSource?dataSourceId=" + id, function (data) {

            jQuery.each(data, function (name, value) {
                $(":input[name='" + name + "']").val(value);
            });
        });
    }, hidden:function () {
        jQuery(this).remove();
    }}).modal({"backdrop":"static"});

    jQuery("form[name='editDataSource']").submit(function () {

        jQuery(this).validate();

        if (jQuery(this).valid()) {
            var dataSourceDTO = jQuery(this).serializeObject();
            jQuery.postJSON("DataSource?dataSourceId=" + dataSourceDTO.dataSourceId, dataSourceDTO,function (data) {

                jQuery('.modal').modal('hide');
                displayDataSources();
                showSuccessAlert("The data source has been successfully updated");
            }).error(defaultAjaxErrorHandler);
        }

        return false;
    });
}

function performDeleteDataSource(id) {

    jQuery.ajaxDelete("DataSource?dataSourceId=" + id,function () {

        displayDataSources();
        showSuccessAlert("The data source has been successfully deleted");
    }).error(defaultAjaxErrorHandler);
}

function deleteDataSource(id) {

    bootbox.confirm("Are you sure that you want to delete this data source?", function (result) {
        if (result) {

            performDeleteDataSource(id);
        }
    });
}

function performDeleteKeyStore(id) {

    jQuery.ajaxDelete("KeyStore?keyStoreId=" + id,function () {

        displayKeyStores();
        showSuccessAlert("The key store has been successfully deleted");
    }).error(defaultAjaxErrorHandler);
}

function deleteKeyStore(id) {

    bootbox.confirm("Are you sure that you want to delete this key store?", function (result) {
        if (result) {

            performDeleteKeyStore(id);
        }
    });
}

function hideSubmenus() {

    jQuery('#submenu div').addClass('hidden');
}

function showSubmenu(name) {

    hideSubmenus();

    show(name);
}

function createDatabaseSecurity() {

    return {

        securityTypeId:jQuery('#webServiceSecurityType').val(),
        securityTypeName:jQuery('#webServiceSecurityType option:selected').text(),
        dataSourceId:jQuery('#userDataSources').val()
    };
}

function createKeyStoreSecurity() {

    return {

        securityTypeId:jQuery('#webServiceSecurityType').val(),
        securityTypeName:jQuery('#webServiceSecurityType option:selected').text(),
        keyStoreId:jQuery('#userKeyStores').val(),
        serverAlias:jQuery('#serverAlias').val(),
        clientAlias:jQuery('#clientAlias').val()
    };
}

jQuery(document).ready(function () {

    jQuery.validator.addMethod("serviceUrl", function (value, element) {
        return /^\/.+/.test(value);
    }, "The service url must be proceeded with '/'");

    // hook up event handlers
    jQuery("#servicesLink").click(function () {
        showSubmenu('#serviceSubmenu');

        selectNavItemActive(this);

        displayServices();
    });

    jQuery("#dataSourcesLink").click(function () {
        showSubmenu('#dataSourceSubmenu');

        selectNavItemActive(this);

        displayDataSources();
    });

    jQuery("#keyStoresLink").click(function () {
        showSubmenu('#keyStoreSubmenu');

        selectNavItemActive(this);

        displayKeyStores();
    });

    jQuery('#addWSService').click(function () {

        showWebServiceDialog('Add web service', 'addWSService').on({show:function () {

            initWebServiceModal();
        }, hidden:function () {
            jQuery(this).remove();
        }}).modal({"backdrop":"static"});

        // on web service create
        jQuery("form[name='addWSService']").submit(function () {

            jQuery(this).validate();

            if (jQuery(this).valid()) {
                var serviceDTO = jQuery(this).serializeObject();
                delete serviceDTO.endpointAddress;
                serviceDTO["securityEnabled"] = jQuery("input[name='securityEnabled']").is(':checked') ? true : false;
                serviceDTO["endpoints"] = [jQuery(':input[name="endpointAddress"]').val()];

                if (serviceDTO.securityEnabled) {
                    serviceDTO["serviceSecurities"] = securityList.getAll();
                }

                jQuery.putJSON("Service", serviceDTO,function (data) {

                    jQuery('.modal').modal('hide');
                    displayServices();
                    showSuccessAlert("The service has been successfully created");
                }).error(defaultAjaxErrorHandler);
            }

            return false;
        });
    });

    jQuery('#addDataSource').click(function () {

        showDataSourceDialog('Add data source', 'addDataSource').on({hidden:function () {
            jQuery(this).remove();
        }}).modal({"backdrop":"static"});

        jQuery("form[name='addDataSource']").submit(function () {

            jQuery(this).validate();

            if (jQuery(this).valid()) {
                var dataSourceDTO = jQuery(this).serializeObject();
                jQuery.putJSON("DataSource", dataSourceDTO,function (data) {

                    jQuery('.modal').modal('hide');
                    displayDataSources();
                    showSuccessAlert("The data source has been successfully created");
                }).error(defaultAjaxErrorHandler);
            }

            return false;
        });
    });

    jQuery('#addKeyStore').click(function () {

        showKeyStoreDialog('Add key store', 'addKeyStore').on({hidden:function () {
            jQuery(this).remove();
        }}).modal({"backdrop":"static"});

        jQuery("form[name='addKeyStore']").submit(function () {
            var uploadForm = "form[name='uploadKeyStoreFile']";

            jQuery(uploadForm).validate();
            jQuery(this).validate();

            if (jQuery(uploadForm).valid() && jQuery(this).valid()) {
                var keyStoreDTO = jQuery(this).serializeObject();
                jQuery.putJSON("KeyStore", keyStoreDTO,function (data) {
                    var keyStoreId = JSON.parse(data);

                    uploadKeyStoreFile(keyStoreId, uploadForm, function () {
                        jQuery('.modal').modal('hide');
                        displayKeyStores();
                        showSuccessAlert("The key store has been successfully created");
                    });
                }).error(defaultAjaxErrorHandler);
            }

            return false;
        });
    });

// inits the view
    jQuery("#servicesLink").click();
})
;

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
                    <a id="servicesLink" href="#">Services</a>
                </li>
                <li class="divider-vertical">
                    <a id="dataSourcesLink" href="#">Data sources</a>
                </li>
                <li class="divider-vertical">
                    <a id="keyStoresLink" href="#">Key stores</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="alerts">

    </div>

    <div id="submenu">

        <div id="serviceSubmenu" class="hidden">

            <a id="addWSService" href="#" class="btn btn-primary">Add service</a>
        </div>

        <div id="dataSourceSubmenu" class="hidden">

            <a id="addDataSource" href="#" class="btn btn-primary">Add data source</a>
        </div>

        <div id="keyStoreSubmenu" class="hidden">

            <a id="addKeyStore" href="#" class="btn btn-primary">Add key store</a>
        </div>
    </div>

    <div id="ctnMainList" class="mainList">

    </div>

</div>

<!-- Displays the loading animation -->
<div class="modalLoading"></div>

</body>
</html>