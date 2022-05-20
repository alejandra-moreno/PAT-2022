//LOG IN USUARIO
let submitbtn = document.getElementById("btnLogIn");
submitbtn.addEventListener("click",

(LogInCliente = async() => {
      
    console.log("Se ha pulsado el botón");
      
      let usuario = document.getElementById("loginInEmail").value;
      let contraseña = document.getElementById("loginPassword").value;
      
      let error = document.getElementById("error_message");
      let text;

      
      error.innerHTML = null;

      let request = await fetch("/api/v1/users", {
          method : "GET"}
    ).catch(console.error);

  if(request.ok) {
      
      data = await request.json();
      
      for(let i=0; i<data.length;i++){
          if(data[i].userId == usuario && data[i].userPassword == contraseña){
              sessionStorage.setItem("userId",data[i].userId);
              window.location.href = "./index.html";
          }else{
            text = "La contraseña o el usuario son incorrectos";
            error.innerHTML = text;
          }
      }

      
    }
  })
);
