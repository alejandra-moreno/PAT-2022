const APIController = (function() {
    
    const clientId = '80acfc9b7bf644efa5ffb18e72288913';
    const clientSecret = '6c5bab352eb148c79dd5cc1e5ff2f045';

    // private methods
    const _getToken = async () => {

        const result = await fetch('https://accounts.spotify.com/api/token', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded', 
                'Authorization' : 'Basic ' + btoa(clientId + ':' + clientSecret)
            },
            body: 'grant_type=client_credentials'
        });

        const data = await result.json();
        return data.access_token;
    }
    
    const _getGenres = async (token) => {

        const result = await fetch(`https://api.spotify.com/v1/browse/categories?locale=es`, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer ' + token}
        });

        const data = await result.json();
        return data.categories.items;
    }

    const _getPlaylistByGenre = async (token, genreId) => {

        const limit = 10;
        
        const result = await fetch(`https://api.spotify.com/v1/browse/categories/${genreId}/playlists?limit=${limit}`, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer ' + token}
        });

        const data = await result.json();
        return data.playlists.items;
    }

    const _getTracks = async (token, tracksEndPoint) => {

        const limit = 10;

        const result = await fetch(`${tracksEndPoint}?limit=${limit}`, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer ' + token}
        });

        const data = await result.json();
        return data.items;
    }

    const _getTrack = async (token, trackEndPoint) => {

        const result = await fetch(`${trackEndPoint}`, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer ' + token}
        });

        const data = await result.json();
        return data;
    }

    //FUNCION OBTENER A LOS ARTISTAS
    const artistaId = document.querySelector('#artistasId');
    const  _getArtistsSearch = async (token) => {

        const detailDiv = document.querySelector('#artist-detail');
        detailDiv.innerHTML = '';
        const result = await fetch(`https://api.spotify.com/v1/search?q=${artistaId.value}&type=artist`, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer ' + token}
        });

        const data = await result.json();
        console.log(data);
        return data.artists.items;
    }

    //FUNCION OBTENER LOS ALBUMES DEL ARTISTA
    const _getAlbumsArtistSearch = async (token, artists) => {
        const result = await fetch(`${artists.href}/albums`, {
        method: 'GET',
        headers: { 'Authorization' : 'Bearer ' + token}
        });

        const respuesta = await result.json();
        return respuesta;
    }

    return {
        getToken() {
            return _getToken();
        },
        getGenres(token) {
            return _getGenres(token);
        },
        getPlaylistByGenre(token, genreId) {
            return _getPlaylistByGenre(token, genreId);
        },
        getTracks(token, tracksEndPoint) {
            return _getTracks(token, tracksEndPoint);
        },
        getTrack(token, trackEndPoint) {
            return _getTrack(token, trackEndPoint);
        },
        getArtistsSearch(token){
            return _getArtistsSearch(token);
        },
        getAlbumsArtistSearch(token, artists){
            return _getAlbumsArtistSearch(token, artists);
        }
    }
})();

// UI Module
const UIController = (function() {

    //object to hold references to html selectors
    const DOMElements = {
        selectGenre: '#select_genre',
        selectPlaylist: '#select_playlist',
        buttonSubmit: '#btn_submit',
        buttonArtists: '#btn_artists',
        divSongDetail: '#song-detail',
        hfToken: '#hidden_token',
        divSonglist: '.song-list'
    }

    //public methods
    return {

        //method to get input fields
        inputField() {
            return {
                genre: document.querySelector(DOMElements.selectGenre),
                playlist: document.querySelector(DOMElements.selectPlaylist),
                tracks: document.querySelector(DOMElements.divSonglist),
                submit: document.querySelector(DOMElements.buttonSubmit),
                submitArtist: document.querySelector(DOMElements.buttonArtists),
                songDetail: document.querySelector(DOMElements.divSongDetail)
            }
        },

        // need methods to create select list option
        createGenre(text, value) {
            const html = `<option value="${value}">${text}</option>`;
            document.querySelector(DOMElements.selectGenre).insertAdjacentHTML('beforeend', html);
        }, 

        createPlaylist(text, value) {
            const html = `<option value="${value}">${text}</option>`;
            document.querySelector(DOMElements.selectPlaylist).insertAdjacentHTML('beforeend', html);
        },

        // need method to create a track list group item 
        createTrack(id, name) {
            const html = `<a href="#" class="list-group-item list-group-item-action list-group-item-light" id="${id}">${name}</a>`;
            document.querySelector(DOMElements.divSonglist).insertAdjacentHTML('beforeend', html);
        },

        // need method to create the song detail
        createTrackDetail(img, title, artist) {

            const detailDiv = document.querySelector(DOMElements.divSongDetail);
            // any time user clicks a new song, we need to clear out the song detail div
            detailDiv.innerHTML = '';

            const html = 
            `
            <div class="row col-sm-12 px-0">
                <img src="${img}" alt="" style="height: 30rem;">        
            </div>
            <div class="row col-sm-12 px-0">
                <label for="Genre" class="form-label col-sm-12">${title}:</label>
            </div>
            <div class="row col-sm-12 px-0">
                <label for="artist" class="form-label col-sm-12">By ${artist}:</label>
            </div> 
            `;

            detailDiv.insertAdjacentHTML('beforeend', html)
        },

        // need method to create the artist detail
        createArtistDetail(artists, albums, index) {

            let titulo = artists[index].name.replace(/'/g, '’');
            
            const detailDiv = document.querySelector('#artist-detail');
            html = 
            `
            <div class="row">
                <div class="col-4 pt-3 mt-2">
                    <img src="${artists[index].images[0].url}" alt="" style="height: 25rem;width: 25rem;"> 
                    <p class="form-label col-sm-12 mt-3 my-3"><b>${artists[index].name}</b></p>
                    <div class="col-5 my-4 py-1">
                        <button onclick = "getFav('${index}','${artists[index].href}','${titulo}','${artists[index].images[0].url}','${artists[index].genres}','${artists[index].followers.total}')" type="submit" id="btn_artistafav" class="btn btn-success col-sm-12">Añadir a favoritos</button>
                    </div>
                </div>
                <div class="col-4 pt-3 mb-4 mt-4">
                    <h6 class="py-1">Álbumes, singles y versiones:</h6>
            `;
            //Imprimimos todos los albumes de ese artista, quitando los que estan repetidos
            const nombresAlbumes = [];
            let repetido = 0;
            let posicion = 0;
            for(var i=0;i<albums.items.length;i++){
                for(var j=0;j<nombresAlbumes.length;j++){
                    if (albums.items[i].name == nombresAlbumes[j]){
                        repetido = 1;
                    }
                }
                if(repetido == 0){
                    nombresAlbumes[posicion] = albums.items[i].name;
                    posicion++;
                    html = html+
                `
                        <p style="margin: 0.5rem;font-style: italic;" class="px-5"><small>${albums.items[i].name}</small></p> 
                `; 
                }   
                repetido = 0;           
            }
            html = html+
                    `
                    </div>
                    <div class="col-3 pt-3 mb-4 mt-4 px-0">
                    `; 
            if(artists[index].genres.length != 0){
                html = html+
                    `
                        <p class="px-4">Seguidores: <i>${artists[index].followers.total}</i></p>
                        <h6 class="py-3 px-4">Géneros musicales:</h6>
                    `; 
                for(var i=0;i<artists[index].genres.length;i++){
                    html = html+
                    `
                        <p style="margin: 0.5rem;font-style: italic;" class="px-5">${artists[index].genres[i]}</p>
                    `;   
                }             
            }
            html = html+
            `
                        <p class="py-3 px-4">Popularidad: ${artists[index].popularity}/100</p>
                    </div>
            </div>
            <hr>
            `;
            detailDiv.insertAdjacentHTML('beforeend', html)
        },

        resetTrackDetail() {
            this.inputField().songDetail.innerHTML = '';
        },

        resetTracks() {
            this.inputField().tracks.innerHTML = '';
            this.resetTrackDetail();
        },

        resetPlaylist() {
            this.inputField().playlist.innerHTML = '';
            this.resetTracks();
        },
        
        storeToken(value) {
            document.querySelector(DOMElements.hfToken).value = value;
        },

        getStoredToken() {
            return {
                token: document.querySelector(DOMElements.hfToken).value
            }
        }
    }

})();

getFav = async (index, artist_id, artist_name, artist_image, artist_genres, artist_followers) => {
    console.log("Prueba marcar favorito:")
    console.log(index, artist_id+"\n Nombre="+artist_name+"\n Imagen url="+artist_image+"\n Géneros="+artist_genres+"\n Seguidores="+artist_followers);
    let request = await fetch("/api/v1/artist", {
        method : "POST",
        credentials: "same-origin", 
        headers: { 
            "Content-Type": "application/json"  
            },
            body: JSON.stringify({
                artistId : artist_id,
                artistName : artist_name,
                artistImage : artist_image,
                artistGenres : artist_genres,
                artistFollowers : parseInt(artist_followers),
            }),
            dataType: "json"
        }).catch(console.error);
        if(request.ok) {
            console.log("Success!");
        }
    
        usuario = "blancadepedr";
    
        let fav = await fetch("/api/v1/favourites", {
            method : "POST",
            credentials: "same-origin", 
            headers: { 
                "Content-Type": "application/json"  
                },
                body: JSON.stringify({
                    userId : usuario,
                    favId : artist_id,
                    tipo : "artista",
                }),
                dataType: "json"
            }).catch(console.error);
            if(request.ok) {
                console.log("Success!");
            }
}

const APPController = (function(UICtrl, APICtrl) {

    // get input field object ref
    const DOMInputs = UICtrl.inputField();

    // get genres on page load
    const loadGenres = async () => {
        //get the token
        const token = await APICtrl.getToken();           
        //store the token onto the page
        UICtrl.storeToken(token);
        //get the genres
        const genres = await APICtrl.getGenres(token);
        //populate our genres select element
        genres.forEach(element => UICtrl.createGenre(element.name, element.id));
    }

    // create genre change event listener
    DOMInputs.genre.addEventListener('change', async () => {
        //reset the playlist
        UICtrl.resetPlaylist();
        //get the token that's stored on the page
        const token = UICtrl.getStoredToken().token;        
        // get the genre select field
        const genreSelect = UICtrl.inputField().genre;       
        // get the genre id associated with the selected genre
        const genreId = genreSelect.options[genreSelect.selectedIndex].value;             
        // ge the playlist based on a genre
        const playlist = await APICtrl.getPlaylistByGenre(token, genreId);       
        // create a playlist list item for every playlist returned
        playlist.forEach(p => UICtrl.createPlaylist(p.name, p.tracks.href));
    });
     

    // create submit button click event listener
    DOMInputs.submit.addEventListener('click', async (e) => {
        // prevent page reset
        e.preventDefault();
        // clear tracks
        UICtrl.resetTracks();
        //get the token
        const token = UICtrl.getStoredToken().token;        
        // get the playlist field
        const playlistSelect = UICtrl.inputField().playlist;
        // get track endpoint based on the selected playlist
        const tracksEndPoint = playlistSelect.options[playlistSelect.selectedIndex].value;
        // get the list of tracks
        const tracks = await APICtrl.getTracks(token, tracksEndPoint);
        // create a track list item
        tracks.forEach(el => UICtrl.createTrack(el.track.href, el.track.name))        
    });

    DOMInputs.submitArtist.addEventListener('click', async (e) => {
        // prevent page reset
        e.preventDefault();
        // clear tracks
        UICtrl.resetTracks();
        //get the token
        const token = UICtrl.getStoredToken().token; 
        // get artists
        const artists = await APICtrl.getArtistsSearch(token);
        //display information
        for(var i=0;i<artists.length;i++){
            if(artists[i].images.length != 0){
                const albumsArtist = await APICtrl.getAlbumsArtistSearch(token,artists[i]);
                UICtrl.createArtistDetail(artists,albumsArtist,i);
            }
        }
    });

    // create song selection click event listener
    DOMInputs.tracks.addEventListener('click', async (e) => {
        // prevent page reset
        e.preventDefault();
        UICtrl.resetTrackDetail();
        // get the token
        const token = UICtrl.getStoredToken().token;
        // get the track endpoint
        const trackEndpoint = e.target.id;
        //get the track object
        const track = await APICtrl.getTrack(token, trackEndpoint);
        // load the track details
        UICtrl.createTrackDetail(track.album.images[2].url, track.name, track.artists[0].name);
    }); 

    return {
        init() {
            console.log('App is starting');
            loadGenres();
        }
    }

})(UIController, APIController);

// will need to call a method to load the genres on page load
APPController.init();



