# Práctica Final Programación de Aplicaciones Telemáticas

Práctica desarrollada por Alejandra Moreno y Blanca de Pedro. 
Para la realización de la práctica se ha utilizado la API de Spotify, de la cual se ha obtenido información sobre artistas, sus álbumes y canciones, y podcasts. Dicha información será manipulada por el usuario a su gusto a lo largo de la web mediante los métodos PUT, POST, DELETE y GET. El usuario podrá acceder a su perfil personal, donde encontrará toda su información, desde los datos de registro hasta la información relacionada con Spotify, sin embargo tendrá que autentificarse para poder visualizarla.



# Requerimientos básicos


## Bases de Datos

Se han empleado seis bases de datos para el desarrollo de la aplicación: "USER", "SONG", "ALBUM", "ARTIST", "EPISODE", "FAVOURITE".

- USER: En ella se va a almacenar la información personal del cliente: su nombre, edad, email, usuario y contraseña. Estos datos serán necesarios para registrarse, si no se ha iniciado sesión y podrá acceder a ellos y modificarlos si así lo desea. 
Nota: el nombre y el usuario no son el mismo campo, a lo largo de la aplicación se identificará mediante el usuario.
- SONG, ALBUM, ARTIST, EPISODE: Cada tabla tiene unos atributos distintos. Dicha información se utilizará para hacer joins con los elementos que el usuario haya marcado como favorito. Posteriormente se podrá visualizar en vista profile.html
- FAVOURITE: Está compuesto por dos caves principales "USER_ID", que es el id del usuario y "FAV_ID", que puede ser el id de uno de los cuatro elementos, el cual se diferenciará con el campo "TIPO". Hay dos claves principales dado que un usuario puede tener varios favoritos y un mismo elemento puede ser favorito de varios usuarios.

## Vistas

La página web está compuesta por doce vistas: 

- Index.html: Es la página principal y desde la cual se puede acceder a cualquier otra página. Tiene un menú en la parte superior que cambia en función de si está dado de alta o no:
-- Si no se ha registrado aun o no ha iniciado sesión le saldrá:
--Si ya está dado de alta podrá cerrar la sesión o visitar su perfil:

- Register.html: En esta página se introducen los datos del usuario en caso de que no tenga una cuenta ya creada. Si se introduce algún campo erróneamente o se deja en blanco aparecerá un error. Si el usuario ya está registrado en la base de datos no le permitirá registrase y tendrá que probar con otro nuevo. Una vez se haya registrado con éxito volverá de forma predeterminada al inicio.
- ![image](https://user-images.githubusercontent.com/78315378/169789304-14311f58-cc7c-446e-8128-5e02bcb31324.png)

- Login.html: Si no es la primera vez que utiliza la página web y ya tiene una cuenta solo tendrá que introducir el usuario y la contraseña para iniciar sesión. En caso de que ocurra un error aparecerá por pantalla un mensaje.
- Forgot-password.html: En caso de que se haya olvidado la contraseña tendrá la opción de restaurarla introduciendo el usuario, la contraseña dos veces y el correo.
- Albumes.html, Artistas.html, Canciones.html, Podcast.html: Inicialmente se mostrará un buscador donde se podrá introducir el nombre de lo que se quiera buscar y al darle al botón aparecerá toda la información encontrada. Para cada elemento existirá la opción de añadirlo a sus favoritos y posteriormente se podrá visualizar en el profile.
- Profile.html: Está compuesto por dos partes principales la información personal del usuario y la información correspondiente a los favoritos. 
Se imprimirá por pantalla la información personal y existirá la opción de actualizarla indicando el campo, introduciendo el nuevo valor y pulsando el botón actualizar. También se podrá eliminar la información de la base de datos o volver al inicio. 
A su vez se podrá visualizar por pantalla la información de sus  favoritos filtrando. Se seleccionará si es artista, canción, álbum o podcast y si se pulsa el botón se mostrará por pantalla.
- Portfolio.html, Pricing.html, Service.html: Son páginas complementarias que ofrecen información sobre la página web como pueden ser distintos contratos o servicios que se ofrecen. 

# API

# Testing y Seguridad

## Pruebas Unitarias
## End to End
## Testing

# Dependencias
