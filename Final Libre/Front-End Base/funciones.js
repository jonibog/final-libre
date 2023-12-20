document.addEventListener("DOMContentLoaded", function(event) {
  loadUsers();
});

function login(){
    var user = document.getElementById('user').value;
    var pass = document.getElementById('pass').value;
    
    const api = new XMLHttpRequest();

    api.open("POST", `http://localhost:8081/usuario/login`, true);
    api.setRequestHeader("Content-Type", "application/json");
  
    let json = JSON.stringify({
        "user": user,
        "password": pass
      });
    api.send(json);
    api.onload = function() {
        if (api.status != 200) {
          alert("code: "+ api.status+"/n "+ api.responseText);
        } else {
          var data = JSON.parse(api.responseText);
          sessionStorage.setItem("role", data.rol);
          sessionStorage.setItem("token", data.token);
          if (data.rol=="admin"){  
            location.href = "./admin.html";
            return;
          }
          if (data.rol== "vendedor" || data.rol== "Vendedor"){  
            location.href = "./vendedor.html";
            return;
          }
          else{
            location.href = "./user.html";
            return;
          }
        }
      };
    api.onerror = function() {
        alert("Request failed");
    };
}


function registry(){
  var user = document.getElementById('user').value;
  var role = document.getElementById('role').value;
  var pass = document.getElementById('pass').value;
  if (!document.getElementById('pass1')) {
    alert("Debe repetir su ontraseña");
  }
  var pass1 = document.getElementById('pass1').value;

  if (pass != pass1){
    alert("Las contraseñas no coinciden!!! "+ pass+" != "+ pass1);
    document.getElementById("pass1").value = "";
    document.getElementById("pass").focus();
    return;
  }
  alert("registrando");
  const api = new XMLHttpRequest();
  api.open("POST", `http://localhost:8081/usuario/registry`, true);
  api.setRequestHeader("Content-Type", "application/json");

  let json = JSON.stringify({
      "user": user,
      "password": pass,
      "role": role
    });
  api.send(json);
  api.onload = function() {
      if (api.status != 201) {
        alert("code: "+ api.status+"/n "+ api.responseText);
      } else {
          alert(api.responseText);            
          loadUsers();
      }
    };
  api.onerror = function() {
      alert("Request failed");
  };
}

function loadUsers(){
  if (document.getElementById('usuariosTable')){
    const api = new XMLHttpRequest();
    api.open("GET", `http://localhost:8081/usuario/getAll`, true);
    api.setRequestHeader("Content-Type", "application/json");
    api.setRequestHeader("token", sessionStorage.getItem("token"));
    api.send();
    api.onload = function() {
        if (api.status != 200) {
          alert("code: "+ api.status+"/n "+ api.responseText);
        } else {
          var data = JSON.parse(api.responseText);
          formatToTable(data);
        }
      };
    api.onerror = function() {
        alert("Request failed");
    };
  }
  //alert("esta funcionando");
}


function formatToTable(data){
  var thisElement = document.getElementById('usuarios');
  thisElement.innerHTML = "";
 // thisElement.innerHTML = thisElement.innerHTML+ "<tbody>";

  for (var x =0; x <data.length; x++){
      thisElement.innerHTML = thisElement.innerHTML + "<tr><td>" + data[x].user +"</td> <td>" + data[x].role +"</td><td><button onclick=\"funcionNoDisponible()\">Eliminar</button><button onclick=\"funcionNoDisponible()\">Editar</button></td></tr>";  
  };
  //thisElement.innerHTML = thisElement.innerHTML + "</tbody>";
}

function funcionNoDisponible(){
  alert("La funcionalidad no está disponible por el momento");
}


function redirectTo(redirection){
  location.href = redirection+".html";
}
