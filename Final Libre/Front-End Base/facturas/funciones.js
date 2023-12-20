document.addEventListener("DOMContentLoaded", function(event) {
  loadProducts();
});


function loadProducts(){
  if (document.getElementById('facturasTable')){
    const api = new XMLHttpRequest();
    api.open("GET", `http://localhost:8081/factura/getAll`, true);
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
  var thisElement = document.getElementById('facturas');
  thisElement.innerHTML = "";
 // thisElement.innerHTML = thisElement.innerHTML+ "<tbody>";

  for (var x =0; x <data.length; x++){
      thisElement.innerHTML = thisElement.innerHTML + "<tr><td>" + data[x].nro1 +"-"+ data[x].nro2 +"</td> <td>" + data[x].comprador.nombre +"</td> <td>" + data[x].vendedor.user +"</td> <td>" + data[x].fecha +"</td></tr>";  
  };
  //thisElement.innerHTML = thisElement.innerHTML + "</tbody>";
}

function funcionNoDisponible(){
  alert("La funcionalidad no está disponible por el momento");
}

function viewProduct(codigo){
  document.getElementById("productoForm").style.display = "block";
  document.getElementById("verProducto").style.display = "block";
  document.getElementById("crearProducto").style.display = "none";
  document.getElementById('btnCrear').style.display = "none";
  const api = new XMLHttpRequest();
    api.open("GET", `http://localhost:8081/producto/find/`+codigo, true);
    api.setRequestHeader("Content-Type", "application/json");
    api.setRequestHeader("token", sessionStorage.getItem("token"));
    api.send();
    api.onload = function() {
        if (api.status != 200) {
          alert("code: "+ api.status+"/n "+ api.responseText);
        } else {
          var data = JSON.parse(api.responseText);
          document.getElementById('codigo').value = data.codigo;
          document.getElementById('nombre').value = data.nombre;
          document.getElementById('desc').value = data.descripcion;
          document.getElementById('stock').value = data.stock;
          document.getElementById('precio').value = data.precio;
        }
      };
    api.onerror = function() {
        alert("Request failed");
    };
}

function create(){
  var codigo = document.getElementById('codigo').value;
  var nombre = document.getElementById('nombre').value;
  var desc = document.getElementById('desc').value;
  var stock = document.getElementById('stock').value;
  var precio = document.getElementById('precio').value;
  const api = new XMLHttpRequest();
  api.open("POST", `http://localhost:8081/producto/create`, true);
  api.setRequestHeader("Content-Type", "application/json");
  api.setRequestHeader("token", sessionStorage.getItem("token"));

  let json = JSON.stringify({
      "codigo": codigo,
      "nombre": nombre,
      "descripcion": desc,
      "stock": +stock,
      "precio": +precio
    });
  api.send(json);
  api.onload = function() {
      if (api.status != 201) {
        alert("code: "+ api.status+"/n "+ api.responseText);
      } else {
          alert(api.responseText);
          document.getElementById("productoForm").style.display = "none";
          loadProducts();
      }
    };
  api.onerror = function() {
      alert("Request failed");
  };
}

function closeForm() {
  document.getElementById("productoForm").style.display = "none";
}

function newFactura(){
  document.getElementById("productoForm").style.display = "block";
  document.getElementById("verProducto").style.display = "none";
  document.getElementById("crearProducto").style.display = "block";
  document.getElementById('btnCrear').style.display = "block";
  
  document.getElementById('codigo').value = "";
  document.getElementById('nombre').value = "";
  document.getElementById('desc').value = "";
  document.getElementById('stock').value = 0;
  document.getElementById('precio').value = 0;
}

function redirectTo(redirection){
  location.href = redirection+".html";
}