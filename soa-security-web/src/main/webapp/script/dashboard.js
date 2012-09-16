/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

function getUserServices(target) {

    retrieveData("Service/User", function (data) {

        jQuery(target).empty();
        if (data.length > 0) {
            var rows = ["<thead><tr><td>Name</td><td>WSDL</td><td>Edit</td><td>Remove</td></tr></thead>"];
            jQuery.each(data, function (index, element) {

                rows.push("<tr><td>" + element.serviceName + "</td>"
                    + "<td><a href='../services" + element.serviceUrl + "?WSDL'>WSDL</a></td>"
                    + "<td><button class='btn' onclick='editService(" + element.serviceConfigurationId + ");'>Edit</button></td>"
                    + "<td><button class='btn btn-danger' onclick='deleteService(" + element.serviceConfigurationId + ")'>Delete</button></td>"
                    + "</tr>");
            });

            jQuery("<table/>", { html:rows.join(""), class:"table table-striped table-hover"}).appendTo(target);
        } else {

            jQuery('<div class="alert alert-info">No services were defined</div>').appendTo(target);
        }
    });
}

function getDataSources(target) {

    retrieveData("DataSource/User", function (data) {

        jQuery(target).empty();
        if (data.length > 0) {
            var rows = ["<thead><tr><td>Name</td><td>Edit</td><td>Remove</td></tr></thead>"];
            jQuery.each(data, function (index, element) {

                rows.push("<tr><td>" + element.dataSourceName + "</td>"
                    + "<td><button class='btn' onclick='editDataSource(" + element.dataSourceId + ");'>Edit</button></td>"
                    + "<td><button class='btn btn-danger' onclick='deleteDataSource(" + element.dataSourceId + ")'>Delete</button></td>"
                    + "</tr>");
            });

            jQuery("<table/>", { html:rows.join(""), class:"table table-striped table-hover"}).appendTo(target);
        } else {

            jQuery('<div class="alert alert-info">No data sources were defined</div>').appendTo(target);
        }
    });
}

function getKeyStores(target) {

    retrieveData("KeyStore/User", function (data) {

        jQuery(target).empty();
        if (data.length > 0) {
            var rows = ["<thead><tr><td>Name</td><td>Remove</td></tr></thead>"];
            jQuery.each(data, function (index, element) {

                rows.push("<tr><td>" + element.keyStoreName + "</td>"
                    + "<td><button class='btn btn-danger' onclick='deleteKeyStore(" + element.keyStoreId + ")'>Delete</button></td>"
                    + "</tr>");
            });

            jQuery("<table/>", { html:rows.join(""), class:"table table-striped table-hover"}).appendTo(target);
        } else {

            jQuery('<div class="alert alert-info">No key stores were defined</div>').appendTo(target);
        }
    });
}

/**
 * <p>Retrieves the list of all available data sources and binds it into a drop down.</p>
 *
 * @param target the id of the target drop down
 */
function getUserDataSources(target) {

    retrieveData("DataSource/User", function (data) {

        jQuery(target).empty();
        jQuery.each(data, function (index, element) {

            jQuery(target).append(jQuery("<option/>", { value:element.dataSourceId })
                .text(element.dataSourceName));
        });

        // triggers the select event
        jQuery(target).select();
    });
}

/**
 * <p>Retrieves the list of all available key stores and binds it into a drop down.</p>
 *
 * @param target the id of the target drop down
 */
function getUserKeyStores(target) {

    retrieveData("KeyStore/User", function (data) {

        jQuery(target).empty();
        jQuery.each(data, function (index, element) {

            jQuery(target).append(jQuery("<option/>", { value:element.keyStoreId })
                .text(element.keyStoreName));
        });

        // triggers the select event
        jQuery(target).select();
    });
}

/**
 * <p>Retrieves the list of all key store aliases and binds it into a drop down.</p>
 *
 * @param target the id of the target drop down
 */
function getKeyStoreAliases(keyStoreId, target) {

    retrieveData("KeyStore/Aliases?keyStoreId=" + keyStoreId, function (data) {

        jQuery(target).empty();
        jQuery.each(data, function (index, element) {

            jQuery(target).append(jQuery("<option/>", { value:element })
                .text(element));
        });
    });
}

/**
 * <p>Retrieves the service types.</p>
 * @param target the target drop down to which the result will be added.
 */
function getServiceTypes(target) {

    jQuery(target).empty();
    retrieveData("Lookup/ServiceType", function (data) {

        jQuery.each(data, function (index, element) {
            jQuery(target)
                .append(jQuery("<option/>", { value:element.id })
                .text(element.name));
        });
    });
}

/**
 * <p>Retrieves the web service security types.</p>
 * @param target the target drop down to which the result will be added
 */
function getWebServiceSecurityTypes(target) {

    jQuery(target).empty();
    retrieveData("Lookup/WebServiceSecurityType", function (data) {

        jQuery.each(data, function (index, element) {
            jQuery(target)
                .append(jQuery("<option/>", { value:element.id })
                .text(element.name));
        });
    });
}

function uploadKeyStoreFile(keyStoreId, formName, callback) {

    var formData = new FormData(jQuery(formName)[0]);
    jQuery.ajax({
        url:'KeyStore/File?keyStoreId=' + keyStoreId,
        type:'POST',
        contentType:'multipart/form-data',
        success: callback,
        error:defaultAjaxErrorHandler,
        data:formData,
        cache:false,
        contentType:false,
        processData:false
    });
}

function showWebServiceDialog(title, formName) {

    return jQuery("<div id='wsServiceModal' class='modal hide fade'>" +
        "    <div class='modal-header'>" +
        "        <h3> " + title + "</h3>" +
        "    </div>" +
        "    <form name='" + formName + "' class='form-horizontal'>" +
        "        <div class='modal-body'>" +
        "            <input type='hidden' name='serviceConfigurationId' value='0'/>" +
        "            <input type='hidden' name='serviceTypeId' value='3'/>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='serviceName'>Name</label>" +

        "                <div class='controls'>" +
        "                    <input id='serviceName' name='serviceName' type='text' class='required' placeholder='Name'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='serviceUrl'>Service url</label>" +

        "                <div class='controls'>" +
        "                    <input id='serviceUrl' name='serviceUrl' type='text' class='required serviceUrl'" +
        "                           placeholder='/ServiceUrl'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='serviceContractAddress'>WSDL address</label>" +
        "                <div class='controls'>" +
        "                    <input id='serviceContractAddress' name='serviceContractAddress' type='text'" +
        "                           class='required' placeholder='http://server:port/service.wsdl'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='endpointAddress'>Endpoint address</label>" +

        "                <div class='controls'>" +
        "                    <input id='endpointAddress' name='endpointAddress' type='text'" +
        "                           class='required' placeholder='http://server:port/service'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='securityEnabled'>Security</label>" +

        "                <div class='controls'>" +
        "                    <input id='securityEnabled' name='securityEnabled' type='checkbox'/>" +
        "                </div>" +
        "            </div>" +
        "            <div id='securityConfiguration' class='hidden'>" +
        "                <div id='securityConfigurationList'></div>" +
        "                <button id='addNewSecurityConfiguration' class='btn btn-success'><i class='icon-plus'></i> Add</button>" +
        "                <div id='securityConfigurationOptions' class='hidden'>" +
        "                    <div class='control-group'>" +
        "                        <label class='control-label' for='webServiceSecurityType'>Security type</label>" +
        "                        <div class='controls'>" +
        "                            <select id='webServiceSecurityType'></select>" +
        "                        </div>" +
        "                    </div>" +
        "                    <div id='dataSourceConfiguration' class='hidden'>" +
        "                        <div class='control-group'>" +
        "                            <label class='control-label' for='userDataSources'>Data source</label>" +

        "                            <div class='controls'>" +
        "                                <select id='userDataSources' class='required'></select>" +
        "                            </div>" +
        "                        </div>" +
        "                    </div>" +
        "                    <div id='keyStoreConfiguration' class='hidden'>" +
        "                        <div class='control-group'>" +
        "                            <label class='control-label' for='userKeyStores'>Key store</label>" +
        "                            <div class='controls'>" +
        "                                <select id='userKeyStores' class='required'></select>" +
        "                            </div>" +
        "                        </div>" +
        "                        <div class='control-group'>" +
        "                            <label class='control-label' for='clientAlias'>Client alias</label>" +
        "                            <div class='controls'>" +
        "                                <select id='clientAlias' class='required'></select>" +
        "                            </div>" +
        "                        </div>" +
        "                        <div class='control-group'>" +
        "                            <label class='control-label' for='serverAlias'>Server alias</label>" +
        "                            <div class='controls'>" +
        "                                <select id='serverAlias' class='required'></select>" +
        "                            </div>" +
        "                        </div>" +
        "                    </div>" +
        "                    <div id='divAddSecurityConfiguration' class='hidden'>" +
        "                        <div class='control-group'>" +
        "                            <div class='controls'>" +
        "                                <button id='addSecurityConfiguration' class='btn btn-success'>Add</button>" +
        "                            </div>" +
        "                        </div>" +
        "                    </div>" +
        "                </div>" +
        "            </div>" +
        "        </div>" +
        "        <div class='modal-footer'>" +
        "            <button type='submit' class='btn btn-primary'>Save</button>" +
        "            <button type='button' class='btn btn-danger' data-dismiss='modal'>Cancel</button>" +
        "        </div>" +
        "    </form>" +
        "</div>").appendTo("body");
}

function showDataSourceDialog(title, formName) {

    return jQuery("<div id='dataSourceModal' class='modal hide fade'>" +
        "    <div class='modal-header'>" +
        "        <h3> " + title + " </h3>" +
        "    </div>" +
        "    <form name='" + formName + "' class='form-horizontal'>" +
        "        <div class='modal-body'>" +
        "            <input type='hidden' name='dataSourceId' value='0'/>" +
        "            <input type='hidden' name='dataSourceTypeId' value='5'/>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='dataSourceName'>Name</label>" +
        "                <div class='controls'>" +
        "                    <input id='dataSourceName' name='dataSourceName' type='text' class='required' placeholder='Name'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='driverClass'>Driver class</label>" +
        "                <div class='controls'>" +
        "                    <input id='driverClass' name='driverClass' type='text' placeholder='Driver class'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='connectionUrl'>Connection string</label>" +
        "                <div class='controls'>" +
        "                    <input id='connectionUrl' name='connectionUrl' type='text' class='required' placeholder='jdbc://'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='userName'>User name</label>" +
        "                <div class='controls'>" +
        "                    <input id='userName' name='userName' type='text' placeholder='User'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='userPassword'>User password</label>" +
        "                <div class='controls'>" +
        "                    <input id='userPassword' name='userPassword' type='password'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='userName'>Custom query</label>" +
        "                <div class='controls'>" +
        "                    <input id='userQuery' name='userQuery' type='text'" +
        "                           placeholder='select name, password from UserInfo'/>" +
        "                </div>" +
        "            </div>" +
        "        </div>" +
        "        <div class='modal-footer'>" +
        "            <button type='submit' class='btn btn-primary'>Save</button>" +
        "            <button type='button' class='btn btn-danger' data-dismiss='modal'>Cancel</button>" +
        "        </div>" +
        "    </form>" +
        "</div>").appendTo('body');
}

function showKeyStoreDialog(title, formName) {

    return jQuery("<div id='keyStoreModal' class='modal hide fade'>" +
        "    <div class='modal-header'>" +
        "        <h3>" + title + "</h3>" +
        "    </div>" +
        "    <form name='uploadKeyStoreFile' class='form-horizontal'>" +
        "        <div class='control-group'>" +
        "            <label class='control-label' for='dataSourceName'>Key store file</label>" +
        "            <div class='controls'>" +
        "                <input id='keyStoreFile' name='keyStoreFile' class='required' type='file'/>" +
        "            </div>" +
        "        </div>" +
        "    </form>" +
        "    <form name='" + formName + "' class='form-horizontal'>" +
        "        <div class='modal-body'>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='keyStoreName'>Key store name</label>" +
        "                <div class='controls'>" +
        "                    <input id='keyStoreName' name='keyStoreName' type='text' class='required' placeholder='Name'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='keyStorePassword'>Key store password</label>" +
        "                <div class='controls'>" +
        "                    <input id='keyStorePassword' name='keyStorePassword' type='password'/>" +
        "                </div>" +
        "            </div>" +
        "            <div class='control-group'>" +
        "                <label class='control-label' for='keyPassword'>Key password</label>" +
        "                <div class='controls'>" +
        "                    <input id='keyPassword' name='keyPassword' type='password'/>" +
        "                </div>" +
        "            </div>" +
        "        </div>" +
        "        <div class='modal-footer'>" +
        "            <button type='submit' class='btn btn-primary'>Save</button>" +
        "            <button type='button' class='btn btn-danger' data-dismiss='modal'>Cancel</button>" +
        "        </div>" +
        "    </form>" +
        "</div>").appendTo("body");
}

/**
 *
 * @constructor
 */
function SecurityList() {

    this.elements = new Array();
}

SecurityList.prototype = {

    /**
     * <p>Adds new element to the list.</p>
     * @param obj the object
     */
    add:function (obj) {

        this.elements.push(obj);
    },

    /**
     * <p>Removes the object from the list.</p>
     * @param obj the object to remove
     */
    remove:function (obj) {

        // removes the element from the list
        this.elements = jQuery.grep(this.elements, function (value) {
            return value != obj
        });
    },

    /**
     * <p>Removes the object at specific index
     * @param ind the element index
     */
    removeAt:function (ind) {

        // removes the element at the given position
        this.elements = jQuery.grep(this.elements, function (value, index) {
            return index != ind
        });
    },

    /**
     * <p>Retrieves the list of all elements.</p>
     * @return the list of all elements
     */
    getAll:function () {

        return this.elements;
    },

    /**
     * <p>Displays the list in a form of table in the given DOM element.</p>
     * @param target the DOM target element
     */
    display:function (target) {

        // creates table from the list
        var instance = this;

        jQuery(target).empty();
        if (this.elements.length > 0) {
            var rows = ["<thead><tr><td>Name</td><td>Remove</td></tr></thead>"];
            jQuery.each(this.elements, function (index, element) {

                rows.push("<tr><td>" + element.securityTypeName + "</td>"
                    + "<td><button class='btn btn-danger'>Remove</button></td>"
                    + "</tr>");
            });

            jQuery('<div>Configuration</div>').appendTo(target);
            jQuery("<table/>", { html:rows.join(""), class:"table table-striped table-hover"}).appendTo(target);

            jQuery(target).find('button.btn-danger').click(function() {

                    // retrieves the overlapping tr row
                    parentRow = jQuery(this).closest('tr');

                    // find the index of the row in the table
                    index = parentRow.parent().children().index(parentRow);

                    instance.removeAt(index);
                    // parentRow.remove();
                    instance.display(target);

                    // prevents from form post
                    return false;
                });
        }
    },

    /**
     * <p>Cleares the list and removes all elements.</p>
     */
    clear:function () {

        this.elements = new Array();
    }

};