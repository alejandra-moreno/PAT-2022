//BORRAR USUARIO
let borrar_boton = document.getElementById("btnEliminar");

const deleteUser=async ()=>{
    let usuarioInput=document.getElementById("exampleId").value;

    console.log(usuarioInput);

    let request=await fetch("/api/v1/users/"+usuarioInput, {
        method: "DELETE",
        credentials: "same-origin",
        dataType:"json",
    }).catch(console.error);
    
    if(request.ok){
        console.log("El usuario ha sido borrado");
    }
}

borrar_boton.addEventListener("click",(deleteUser));

//REGISTRAR/CREAR USUARIO
let submitbtn = document.getElementById("btnRegistro");
submitbtn.addEventListener("click",

(postCliente = async() => {
      
      let nombreI = document.getElementById("exampleFirstName").value;
      let idI = document.getElementById("exampleId").value;
      let emailI = document.getElementById("exampleInputEmail").value;
      let edadI = document.getElementById("exampleAge").value;
      let contraseñaI = document.getElementById("exampleInputPassword").value;
      let contraseña2I = document.getElementById("exampleRepeatPassword").value;
      let error = document.getElementById("error_message");
      let text;

      if((nombreI=="") || (nombreI.length>50) || (nombreI.length<3)){
        text = "Por favor, ingrese un nombre válido";
        error.innerHTML = text;
        return false;
      }
      if((emailI=="") || (emailI.length<6) || (emailI.length>50) ||
      (!emailI.includes("@")) || (!emailI.includes("."))){
        text = "Por favor, ingrese un correo electrónico válido";
        error.innerHTML = text;
        return false;
      }
      if(contraseñaI < 9){
        text = "Por favor, ingrese una contraseña válida";
        error.innerHTML = text;
        return false;
      }
      if(contraseña2I != contraseñaI){
        text = "La contraseña introducida no coincide";
        error.innerHTML = text;
        return false;
      }
      error.innerHTML = null;

      let request = await fetch("/api/v1/users", {
          method : "POST",
          credentials: "same-origin", 
          headers: { 
            "Content-Type": "application/json"
            
          },
          body: JSON.stringify({
              userId : idI,
              userName : nombreI,
              userPassword : contraseñaI,
              userEmail : emailI,
              userAge : parseInt(edadI),
          }),
          dataType: "json"
    }).catch(console.error);

  if(request.ok) {
      console.log("Success!");
      sessionStorage.setItem("userId",idI);
      window.location.href = "./index.html";
    }
  })
);


//ACTUALIZAR INFO REGISTRO

let actualizar_boton = document.getElementById("btnActualizar");

const actualizarInfo = async() => {

    let nombreI = document.getElementById("exampleFirstName").value;
    let idI = document.getElementById("exampleId").value;
    let emailI = document.getElementById("exampleInputEmail").value;
    let edadI = document.getElementById("exampleAge").value;      
    let contraseñaI = document.getElementById("exampleInputPassword").value;

    console.log(idI);

    let request = await fetch("/api/v1/users/"+idI, {
      method : "PUT",
      credentials: "same-origin", 
      headers: { 
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
          userId : idI,
          userName : nombreI,
          userPassword : contraseñaI,
          userEmail : emailI,
          userAge : parseInt(edadI),
      }),
          dataType: "json",
      }).catch(console.error);

      if(request.ok) {
        console.log("Success!");
      }
}

actualizar_boton.addEventListener("click",(actualizarInfo));
