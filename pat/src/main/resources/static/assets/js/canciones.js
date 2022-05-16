const APIController = (function () {

    const clientId = '80acfc9b7bf644efa5ffb18e72288913';
    const clientSecret = '6c5bab352eb148c79dd5cc1e5ff2f045';

    // private methods
    const _getToken = async () => {

        const result = await fetch('https://accounts.spotify.com/api/token', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': 'Basic ' + btoa(clientId + ':' + clientSecret)
            },
            body: 'grant_type=client_credentials'
        });

        const data = await result.json();
        return data.access_token;
    }

    const _getGenres = async (token) => {

        const result = await fetch(`https://api.spotify.com/v1/browse/categories?locale=es`, {
            method: 'GET',
            headers: { 'Authorization': 'Bearer ' + token }
        });

        const data = await result.json();
        return data.categories.items;
    }

    const _getPlaylistByGenre = async (token, genreId) => {

        const limit = 10;

        const result = await fetch(`https://api.spotify.com/v1/browse/categories/${genreId}/playlists?limit=${limit}`, {
            method: 'GET',
            headers: { 'Authorization': 'Bearer ' + token }
        });

        const data = await result.json();
        return data.playlists.items;
    }

    const _getTracks = async (token, tracksEndPoint) => {

        const limit = 10;

        const result = await fetch(`${tracksEndPoint}?limit=${limit}`, {
            method: 'GET',
            headers: { 'Authorization': 'Bearer ' + token }
        });

        const data = await result.json();
        return data.items;
    }

    const _getTrack = async (token, trackEndPoint) => {

        const result = await fetch(`${trackEndPoint}`, {
            method: 'GET',
            headers: { 'Authorization': 'Bearer ' + token }
        });

        const data = await result.json();
        return data;
    }
    //FUNCION OBTENER LAS CANCIONES
    const cancionId = document.querySelector('#cancionId');
    const _getCancionesSearch = async (token) => {

        const detailDiv = document.querySelector('#cancion-detail');
        detailDiv.innerHTML = '';
        const result = await fetch(`https://api.spotify.com/v1/search?q=${cancionId.value}&type=track`, {
            method: 'GET',
            headers: { 'Authorization': 'Bearer ' + token }
        });

        const data = await result.json();
        console.log(data);
        return data.tracks.items;

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
        getCancionesSearch(token) {
            return _getCancionesSearch(token);
        }
    }
})();


// UI Module
const UIController = (function () {

    //object to hold references to html selectors
    const DOMElements = {
        selectGenre: '#select_genre',
        selectPlaylist: '#select_playlist',
        buttonSubmit: '#btn_submit',
        buttonCancion: '#btn_cancion',
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
                submitCancion: document.querySelector(DOMElements.buttonCancion),
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
        createSongDetail(songs, index, token) {

            const cancion = JSON.stringify(songs[index]);



            const detailDiv = document.querySelector('#cancion-detail');
            html =
                `
            <div class="row">
                <div class="col-5 pt-3 mt-2">
                    <img src="${songs[index].album.images[0].url}" alt="" style="height: 25rem;width: 25rem;"> 
                    <p class="form-label col-sm-12 mt-3"><b>${songs[index].name}</b></p>
                    <div class="col-5 my-4 py-1">
                        <button onclick = "getFav('${index}','${songs[index].href}','${songs[index].name}','${songs[index].artists[0].name}','${songs[index].album.name}','${songs[index].duration_ms}')" type="button" id="btn_cancionfav" class="btn btn-success col-sm-12">Añadir a favoritos</button>
                    </div>
                </div>
                <div class="col-7 pt-3 mb-4 mt-4">
                    `;
            //Comprobamos si la cancion es de un artista o de varios
            if (songs[index].artists.length > 1) {
                html = html +
                    `
                    <h6 class="py-3 px-4">Artistas:</h6>
                    `;
                for (var i = 0; i < songs[index].artists.length; i++) {
                    html = html +
                        `
                        <p style="margin: 0.5rem;font-style: italic;" class="px-5">${songs[index].artists[i].name}</p>
                    `;
                }
            } else {
                html = html +
                    `
                    <h6 class="py-3 px-4">Artista:</h6>
                    <p style="margin: 0.5rem;font-style: italic;" class="px-5">${songs[index].artists[0].name}</p>
                    `;
            }
            //Comrpobamos si es parte de un album o es un single
            if (songs[index].album.album_type == "single") {
                html = html +
                    `
                    <h6 class="py-3 px-4">Esta canción es un <i>single</i></h6>
                    `;
            } else {
                html = html +
                    `
                    <h6 class="py-3 px-4">Álbum:</h6>
                    <p style="margin: 0.5rem;font-style: italic;" class="px-5">${songs[index].album.name}</p>

                    `;
            }
            //Comprobamos si la cancion es explicita o no
            if (songs[index].explicit == false) {
                html = html +
                    `
                    <h6 class="py-3 px-4">Explícita: <i>No</i></h6>
                    `;
            } else {
                html = html +
                    `
                    <h6 class="py-3 px-4">Explícita: <i>Sí</i></h6>
                    `;
            }
            html = html +
                `
                    <p class="py-3 px-4">Popularidad: ${songs[index].popularity}/100</p>
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

getFav = async (index, song_id, song_name, song_artist, song_album, song_duration) => {
    console.log("Prueba marcar favorito:")
    console.log(index, song_id+"\n Nombre="+song_name+"\n Artista="+song_artist+"\n Album="+song_album+"\n Duración="+song_duration);

}

const APPController = (function (UICtrl, APICtrl) {

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

    DOMInputs.submitCancion.addEventListener('click', async (e) => {
        // prevent page reset
        e.preventDefault();
        // clear tracks
        UICtrl.resetTracks();
        //get the token
        const token = UICtrl.getStoredToken().token;
        // get artists
        const canciones = await APICtrl.getCancionesSearch(token);
        //display information
        for (var i = 0; i < canciones.length; i++) {
            if (canciones[i].album.images.length != 0) {
                UICtrl.createSongDetail(canciones, i,token);
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



