
var slide={};
var session={
    guid : getQueryParameters().guid,
    currentPage : 1,
    numPages: 1
};


$(document).ready(function(){
    $.get( "/Queasy/meta", {guid:session.guid}, onMetaReceived, "json");
});

function onMetaReceived(data){
    var htmlTemplate = $("#startPageTemplate").html();
    session.numPages =data.numPages;

    htmlTemplate = htmlTemplate
            .replace("#slides", data.numPages)
            .replace("#many", (data.numPages!=1)?"s":"");
    $("#main").html(htmlTemplate);
}

function start(){
    $.get( "/Queasy/page", {guid:session.guid, page:session.currentPage}, onPageReceived, "json");
}

function onPageReceived(data){
    session.currentPage = data.page;

    var htmlTemplate = $("#templateCommon").html();

    var slideBodyHtml = '';

    //fill in/update slide attributes
    slide.type = data.type;

    if(data.type == 'learn/text'){
        slideBodyHtml = $("#template_content_text").html().replace("#content", data.content);
    } else if(data.type == 'learn/html'){
        slideBodyHtml = $("#template_content_html").html().replace("#content", data.content);
    } else if(data.type == 'learn/youtube'){
        slideBodyHtml = '<iframe width="100%" height="450" scrolling="no" frameborder="no" src="https://www.youtube.com/embed/'+data.content+'?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe>';
    } else if(data.type == 'learn/soundcloud'){
        slideBodyHtml = '<iframe width="100%" height="450" scrolling="no" frameborder="no" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/'+data.content+'&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true"></iframe>';
    } else if(data.type=='test/radio'){
        $.each(data.options, function(i, val) {
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
    if(session.currentPage > 1){
        $.get( "/Queasy/page", {guid:session.guid, page:session.currentPage - 1}, onPageReceived, "json");
    }
}

function next(){
    if(slide.type.startsWith('learn')){
        if(session.currentPage < session.numPages){
            $.get( "/Queasy/page", {guid:session.guid, page:session.currentPage + 1}, onPageReceived, "json");
        }
    } else {
        //TODO: implement gathering answer data and sending it to server
    }
}


function getQueryParameters () {
    return (document.location.search).replace(/(^\?)/,'').split("&").map(function(n){return n = n.split("="),this[n[0]] = n[1],this}.bind({}))[0];
}

/////////////////////////////
// Prototypes
if (typeof String.prototype.startsWith != 'function') {
    // see below for better implementation!
    String.prototype.startsWith = function (str){
        return this.indexOf(str) == 0;
    };
}