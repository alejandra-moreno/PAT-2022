//CAMBIAR CONTRASEÑA

let actualizarBoton = document.getElementById("btnActualizar");

const validationPassword = async() =>{
  console.log("Dentro de la función actualizar");
  let user = document.getElementById("repeatUser").value;
  let email = document.getElementById("repeatEmail").value;
  let contraseña = document.getElementById("Password").value;
  let contraseña2 = document.getElementById("Password2").value;
  let error = document.getElementById("error_message");
  let text;
  
    if((user=="") || (user.length>50) || (user.length<3)){
        text = "Por favor, ingrese un usuario válido";
        error.innerHTML = text;
        return false;
    }
    if((email=="") || (email.length<6) || (email.length>50) ||
        (!email.includes("@")) || (!email.includes("."))){
            text = "Por favor, ingrese un correo electrónico válido";
            error.innerHTML = text;
            return false;
    }
    if(contraseña < 9){
        text = "Por favor, ingrese una contraseña válida";
        error.innerHTML = text;
        return false;
    }
    if(contraseña2 != contraseña){
        text = "La contraseña introducida no coincide";
        error.innerHTML = text;
        return false;
    }
    alert("La contraseña se ha actualizado, muchas gracias!");
    error.innerHTML = null;



    let request = await fetch("/api/v1/users/"+user+"/"+contraseña,{
        method : "PUT",
        credentials: "same-origin", 
        headers: { 
            "Content-Type": "application/json",
        },
        
        dataType: "json"
        }).catch(console.error);

        if(request.ok) {
            console.log("Success!");
        }
}
actualizarBoton.addEventListener("click",(validationPassword));
