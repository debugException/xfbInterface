/*
 * @(#)api.js 1.0.0 14/02/13
 * Copyright 2014© Emagsoftware Technology Co., Ltd. All Rights reserved.
 */

var currentApi;
var systemVariables = {

}

function setContextPath(contextPath){
    systemVariables.contextPath = contextPath;
}

function getContextPath(){
    return systemVariables.contextPath;
}

function initApiCategory() {
    var categorySelect = $("#apiCategoryId");
    $.each(api_defines, function (i, module) {
        categorySelect.append($("<option>").attr("value", module.category).text(module.name));
    })
    $("option:eq(0)", categorySelect).attr("selected", true);
    $("#api").bind("change", apiChange);
    categoryChange();
}
function categoryChange() {
    var category = $("option:selected", $("#apiCategoryId")).val();
    var apiSelect = $("#api");
    apiSelect.empty();
    $.each(api_defines, function (i, apiModule) {
        if (category !== apiModule.category)return;
        $.each(apiModule.api, function (j, api) {

            apiSelect.append($("<option>").attr("value", api.name).attr("category", category).text(api.name));
        })
        return false;
    })
    apiChange();
}


function apiChange() {
    var apiName = $("option:selected", $("#api")).val();
    var category = $("option:selected", $("#api")).attr("category");
    var apiModule = $.grep(api_defines, function (module) {
        return module.category === category
    })[0]

    var api = $.grep(apiModule.api, function (api) {
        return api.name === apiName
    })[0];
    currentApi = api;
    var fieldTable = $("#businessParams").empty();
    $.each(api.fields, function (i, field) {
        if (field.required == null) field.required = true;
        fieldTable.append($(template.render("fieldRow", field)))
    })
    if (api.isHdcApi)
        $("#hdcParams").show();
    else
        $("#hdcParams").hide();
}

function loginSuccess(data) {
    var data = $.parseJSON(data);
    $("input[name=userToken]").val(data.login_resp.authentication)
}

api_callback.loginSuccess = loginSuccess;

function setObjectProperty(object, name, value) {
    if (!value)return;
    object[name] = value;
}

function formatDate(date){

    var padding = function(value){
        value = ""+value;
        return value.length==2?value:"0"+value;
    }
    return ""+date.getFullYear() + padding(date.getMonth()+1)+padding(date.getDate())+padding(date.getHours())+padding(date.getMinutes())+padding(date.getSeconds());

}


function submit() {
    var uri = currentApi.uri;
    var headers = {}
    var data = { }

    $("#systemForm input,#businessForm :input:visible").each(function () {
        var input = $(this)
        if (input.attr("isHeader")) {
            setObjectProperty(headers, input.attr("name"), input.val())
        } else {
            setObjectProperty(data, input.attr("name"), input.val())
        }
        
    })
    


    if (api.needAppId == false) {
        delete data.appId;
        delete data.appKey;
    }
    var appKey = data.appKey;
    var clientKey = data.clientKey;
    var userToken = data.userToken;
    delete  data.appKey;
    delete  data.clientKey;
    delete  data.userToken;
    var timestamp = formatDate(new Date());
    getServerSign(uri,clientKey,appKey,timestamp,headers,data,function(signValue){
        if(!userToken)userToken="";
        headers["authentication"]="userToken="+userToken+";timestamp="+timestamp+";sign="+signValue;
        $.ajax({url: uri, type: "get", headers: headers, data: data, dataType: "text",
                success: function (response) {
                    showRequestInfo(uri,headers,data);
                    $("#resultShow").val(response.toString().trim().replace(/\n[\s| | ]*\r/g, '\r'));
                    if (currentApi.callback) {
                        api_callback[currentApi.callback](response);
                    }
                }, cache: false, error: function (XMLHttpRequest, textStatus, errorThrown) { 
                	alert(JSON.stringify(XMLHttpRequest));
                    alert("请求出错1,status:"+textStatus);
                }});
    })


}

function showRequestInfo(uri,headers,data){
    try {
           $("#params").text("URI:   "+ uri+"  \r\nHeader:\r\n" + JSON.stringify(headers) + "\r\n" +
               "Body:\r\n " + JSON.stringify(data))
       } catch (e) {

       }
}

function getServerSign(uri,clientKey,appKey,timestamp,headers,data,callback){
    headers = {}
    headers["_system_uri"] = uri;
    headers["_system_timestamp"] = timestamp;
    headers["_system_appKey"] = appKey;
    headers["_system_clientKey"] = clientKey;
    $.ajax({url: "getServerSign.jsp", type: "post", headers: headers, data: data, dataType: "json",
            success: function (response) {
                var signValue = response.signValue;
                callback(signValue);
            }, cache: false, error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("请求出错2,status:"+textStatus);
            }});

}




