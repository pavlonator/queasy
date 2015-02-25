var guid = getQueryParameters().guid;
var currentPage = 1;

$(document).ready(function(){
    $.get( "/Queasy/meta?guid="+guid, onMetaReceived, "json");
});

function onMetaReceived(data){
    var htmlTemplate = $("#startPageTemplate").html();
    htmlTemplate = htmlTemplate
            .replace("#slides", data.numPages)
            .replace("#many", (data.numPages!=1)?"s":"");
    $("#main").html(htmlTemplate);
}

function start(){
    $.get( "/Queasy/page?guid=", {guid:guid, page:currentPage}, onPageReceived, "json");
}

function onPageReceived(data){
    currentPage = data.page;

    var htmlTemplate = $("#templateCommon").html();

    var slideBodyHtml = '';
    if(data.type=='test/radio'){
        $.each(obj, function(i, val) {
            slideBodyHtml += $("#template_input_radio").html().replace("#value", i).replace("#label", val);
        });
    }

    var buttonsHtml = '';
    if(data.hasPrev){
        buttonsHtml+=$("#template_btn_Prev").html();
    }
    if(data.hasNext){
        buttonsHtml+=$("#template_btn_Next").html();
    }

    htmlTemplate = htmlTemplate
        .replace("#caption", data.caption)
        .replace("#buttons", buttonsHtml)
        .replace("#slideBody", slideBodyHtml);
    $("#main").html(htmlTemplate);
}

function prev(){
    if(currentPage>1){
        $.get( "/Queasy/page?guid=", {guid:guid, page:currentPage-1}, onPageReceived, "json");
    }
}

function next(){
    //TODO: implement gathering answer data and sending it to server
    //$.get( "/Queasy/answer?guid=", {guid:guid, page:currentPage}, onPageReceived, "json");
}


function getQueryParameters () {
    return (document.location.search).replace(/(^\?)/,'').split("&").map(function(n){return n = n.split("="),this[n[0]] = n[1],this}.bind({}))[0];
}