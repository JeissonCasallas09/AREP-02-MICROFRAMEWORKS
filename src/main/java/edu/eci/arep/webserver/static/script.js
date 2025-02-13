function loadGetMsg() {
    let nameVar = document.getElementById("name").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsg").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/app/helloget?name=" + nameVar);
    xhttp.send();
}

function loadPostMsg(name) {
    let url = "/app/hellopost?name=" + name.value;
    fetch(url, {method: 'POST'})
        .then(x => x.text())
        .then(y => document.getElementById("postrespmsg").innerHTML = y);
}



function getPiFromRest() {
    let url = "/app/pi";
    fetch(url, { method: 'GET' })
        .then(x => x.text())
        .then(y => document.getElementById("getPiRest").innerHTML = y);
}

function getHelloWorldFromRest() {
    let url = "/app/hello";
    fetch(url, { method: 'GET' })
        .then(x => x.text())
        .then(y => document.getElementById("getHelloRest").innerHTML = y);
}

