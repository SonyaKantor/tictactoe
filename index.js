let player ="X";
let cells = [[0,0,0],[0,0,0],[0,0,0]];
function init() {
    doCompletion();
}
function start(){
    doCompletion("start");
}
function turn(x,y){
    btn = document.getElementById(x+"-"+y);
    var empty = false;
    for(var i = 0; i<3;i++){
        for(var j = 0; i<3;i++)
          if(cells[i][j]===0)
              empty = true;
    }
    if(empty===false)
        var res = document.getElementById("result");
        res.innerHTML = "You tied";
    if(cells[x][y]===0){
       btn.innerHTML = "<h2>"+player+"</h2";
       cells[x][y]= 1;
       doCompletion(x+""+y)
    }
    
}
function doCompletion(p_action) {
    var url = "/TicTacToe/Servlet";
    url += "?action=" + p_action;
    console.log(url);
    action = p_action;
    req = new XMLHttpRequest();
    console.log(req);
    req.open("GET", url, true);
    console.log(req);
    req.onreadystatechange = callback;
    req.send(null);


}

function callback() {
    if (req.readyState === 4) {
        if (req.status === 200) {
             console.log('callback'+action+req.responseXML);
            parseMessages(req.responseXML);

        }
    }
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}


function parseMessages(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        if(action === "figure")
        {
           var reg = responseXML.getElementsByTagName("start")[0];
           player = reg.getElementsByTagName("figure")[0];
        }
        if(action === "win"){
           var res = document.getElementById("result");
           res.innerHTML = "You won";
        }
        if(action === "lose"){
           var res = document.getElementById("result");
           res.innerHTML = "You lost";
        }
    }
}


