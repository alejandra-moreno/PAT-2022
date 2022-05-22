//GET INFO USUARIO

let titulo = document.getElementById("tituloProfile");
let correo = document.getElementById("correo");
let nombre = document.getElementById("nombre");
let edad = document.getElementById("edad");
usuario = sessionStorage.getItem("userId");

if(usuario == null || usuario == " "){
    titulo.innerHTML = "No ha iniciado sesión";
}
else{
    titulo.innerHTML = "Bienvenido "+usuario;
}

let infoCliente = async() => {

    let request = await fetch("/api/v1/users/"+usuario, {
          method : "GET"}
    ).catch(console.error);

    if(request.ok) {
      
      data = await request.json();
      console.log(data);
      correo.innerHTML = "Correo electrónico: "+data["userEmail"];
      nombre.innerHTML = "Nombre: "+data["userName"];
      edad.innerHTML = "Edad: "+data["userAge"];

      //ACTUALIZAR INFO REGISTRO
let actualizar_boton = document.getElementById("btnActualizar");

const actualizarInfo = async() => {

    let idI = usuario;
    let contraseñaI = data["userPassword"];
    let nombreI;
    let emailI;
    let edadI;

    let cambioSelect =
      document.getElementById("cambioSelect").options[
        document.getElementById("cambioSelect").selectedIndex
      ].text;

    console.log(cambioSelect);
    if(cambioSelect=="Nombre"){
        nombreI = document.getElementById("idCambiar").value;
        emailI = data["userEmail"];
        edadI = data["userAge"];
        nombre.innerHTML = "Nombre: "+nombreI;
        
    }
    else if(cambioSelect=="Correo Electrónico"){
        nombreI = data["userName"];
        emailI = document.getElementById("idCambiar").value;
        edadI = data["userAge"];
        correo.innerHTML = "Correo electrónico: "+emailI;

    }else if(cambioSelect=="Edad"){
        nombreI = data["userName"];
        emailI = data["userEmail"];
        edadI = document.getElementById("idCambiar").value;
        edad.innerHTML = "Edad: "+edadI;
    }    
    console.log(idI);

    let request = await fetch("/api/v1/users/"+usuario, {
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
    }
}

infoCliente();

//BORRAR USUARIO
let borrar_boton = document.getElementById("btnEliminar");

const deleteUser=async ()=>{
    let usuarioInput=usuario;

    console.log(usuarioInput);

    let request=await fetch("/api/v1/users/"+usuarioInput, {
        method: "DELETE",
        credentials: "same-origin",
        dataType:"json",
    }).catch(console.error);
    
    if(request.ok){
        window.location.href = "./index.html";
        console.log("El usuario ha sido borrado");
        sessionStorage.removeItem("userId");
    }
}

borrar_boton.addEventListener("click",(deleteUser));


//FAVORITOS
let btnFav = document.getElementById("btnFavoritos");
const visualizarFav = async() => {
    let favoritosSelect =
      document.getElementById("favoritosSelect").options[
        document.getElementById("favoritosSelect").selectedIndex
      ].text;

      console.log(favoritosSelect);
      let url;
      let i;
      if(favoritosSelect=="Artistas"){
        url = "/api/v1/users/favourites/artist/"+usuario;

        let request = await fetch(url, {
            method : "GET"}
        ).catch(console.error);
    
        if(request.ok) {
            data = await request.json();
            console.log(data);
            
            const detailDiv = document.querySelector('#favorites-detail');
            detailDiv.innerHTML = '';
            for(i = 0; i<data.length;i++){
                
                html = 
                `
                <div class="row">
                    <div class="col-4 pt-3 mt-2">
                        <img src="${data[i].artistImage}" alt="" style="height: 25rem;width: 25rem;"> 
                        <p class="form-label col-sm-12 mt-3 my-3"><b>${data[i].artistName}</b></p>
                    </div>
                    <div class="col-4 pt-3 mb-4 mt-4">
                        <p class="px-4">Géneros musicales: <i>${data[i].artistGenres}</i></p>
                        <p class="px-4">Seguidores: <i>${data[i].artistFollowers}</i></p>
                    </div>
                </div>
                <hr>
                `;
            detailDiv.insertAdjacentHTML('beforeend', html)
            }
        }
      }else if(favoritosSelect=="Álbumes"){
        url = "/api/v1/users/favourites/album/"+usuario;
        let request = await fetch(url, {
            method : "GET"}
        ).catch(console.error);
        if(request.ok) {
            data = await request.json();
            console.log(data);
    
            
            const detailDiv = document.querySelector('#favorites-detail');
            detailDiv.innerHTML = '';
            for(i = 0; i<data.length;i++){
                
                html = 
                `
                <div class="row">
                <div class="col-5 pt-3 mt-2">
                    <img src="${data[i].albumImage}" alt="" style="height: 25rem;width: 25rem;"> 
                    <p class="form-label col-sm-12 mt-3"><b>${data[i].albumName} de ${data[i].albumArtist}</b></p>
                    <p class="form-label col-sm-12 mt-3">Fecha de publicación: ${data[i].albumDate}</p>
                    <p class="form-label col-sm-12 mt-3">Número de canciones: ${data[i].albumTracks}</p>
                </div>     
            </div>
            <hr>
            `;
            detailDiv.insertAdjacentHTML('beforeend', html)
            }
        
        }
      }else if(favoritosSelect=="Canciones"){
        url = "/api/v1/users/favourites/song/"+usuario;

        let request = await fetch(url, {
            method : "GET"}
        ).catch(console.error);
    
        if(request.ok) {
            data = await request.json();
            console.log(data);
            
            const detailDiv = document.querySelector('#favorites-detail');
            detailDiv.innerHTML = '';
            for(i = 0; i<data.length;i++){
                html =
                `
            <div class="row">
                <div class="col-5 pt-3 mt-2">
                    <p class="form-label col-sm-12 mt-3"><b>${data[i].songName}</b></p>
                </div>
                <div class="col-7 pt-3 mb-4 mt-4">  
                    <p class="py-3 px-4">Artista: ${data[i].songArtist}</p>
                    <p class="py-3 px-4">Album: ${data[i].songAlbum}</p>
                    <p class="py-3 px-4">Duarción: ${data[i].songDuration} minutos</p>
                </div>      
            </div>
            <hr>
            `;
            detailDiv.insertAdjacentHTML('beforeend', html)
            }
        }

      }else if(favoritosSelect=="Podcasts"){

        url = "/api/v1/users/favourites/podcast/"+usuario;
        let request = await fetch(url, {
            method : "GET"}
        ).catch(console.error);
    
        if(request.ok) {
            data = await request.json();
            console.log(data);
            
            const detailDiv = document.querySelector('#favorites-detail');
            detailDiv.innerHTML = '';
            for(i = 0; i<data.length;i++){
                html = 
                `
                <div class="row">
                    <div class="col-5 pt-3 mt-2">
                        <img src="${data[i].episodeImage}" alt="" style="height: 25rem;width: 25rem;"> 
                        <p class="form-label col-sm-12 mt-3"><b>${data[i].episodeName}</b></p>
                        
                    </div>
                    <div class="col-7 pt-3 mb-4 mt-4">
                        <h6>DESCRIPCIÓN:</h3>
                        <p class="btn btn-success col-sm-10" style="text-align: justify;"">${data[i].episodeDescription}</p>
                        <p class="pt-3">Publicado por:   <i>${data[i].episodePublisher}</i></p>
                        <p class="pt-3">Numero de episodios:  <i>${data[i].episodeTracks}</i></p>
                    </div>      
                </div>
                <hr>
                `;
            detailDiv.insertAdjacentHTML('beforeend', html)
            }
        }
      }
      
    
}
btnFav.addEventListener("click",(visualizarFav));



