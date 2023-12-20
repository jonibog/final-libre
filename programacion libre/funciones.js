
function login(){
    var user = document.getElementById('user').value;
    var pass = document.getElementById('pass').value;
    
    const api = new XMLHttpRequest();

    api.open("POST", `http://localhost:8081/login`, true);
    api.setRequestHeader("contend_type ","application/json");
    let json = JSON.stringify({
        "user": user,
        "password": pass
      });
    api.send(json);
    api.onload = function() {
        if (api.status != 200) {
          alert("code: "+ api.status+"/n "+ api.responseText);
        } else {
            alert(api.responseText) 
            if(api.responseText == "admin"){
              location.href="./admin.html"
            }
        }
      };
    api.onerror = function() {
        alert("Request failed");
    };

}




