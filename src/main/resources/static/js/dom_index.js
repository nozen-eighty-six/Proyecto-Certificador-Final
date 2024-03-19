//import slider from "./carrusel.js";
//import userDeviceInfo from "./deteccion_dispositivo.js";
//import networkStatus from "./deteccion_red.js";
//import webCam from "./deteccion_webcam.js";
//import searchFilters from "./filtro_busquedas.js";
//import getGeolocation from "./geolocalizacion.js";
//No me reconoce la sintaxis
//import hamburgerMenu from "./hamburger_dom.js";
//const openHamburger = require('./hamburger.js');
//import { hamburgerMenu } from './hamburger_dom.js';

//import speechReader from "./narrador.js";
//import ajustarPantalla from "./nueva_ventana_ajustable.js";
//import responsiveMedia from "./objeto_responsive.js";
/*Importamos con destructuración */
//import { reloj, alarma } from "./reloj.js";
//import evitarEventoScroll from "./scroll_secundario_menu.js";
//import scrollSpy from "./scroll_spy.js";
//import draw from "./sorteo.js";
//import { moveBall, tecladoEvento } from "./teclado.js";
//import darkTheme from "./tema_oscuro.js";
//import temporizador from "./temporizador.js";
//import scrolltop from "./top_arrow.js";
//import contactFormValidations from "./validaciones_formulario.js";
//import smartVideo from "./video_inteligente.js";
const d = document;
  
  function hamburgerMenu(panelBtn, panel, menuLink){

    /*Delegación de eventos */
    const d = document;

    d.addEventListener("click", (e)=>{
            if(e.target.matches(".nav-btn") ||  e.target.matches(`.nav-btn *`)){
        	d.querySelector(panel).classList.toggle("is-active");
            d.querySelector(panelBtn).classList.toggle("is-active");
            d.querySelector(panelBtn).classList.toggle("none");
            d.querySelector(".menu-navegacion").classList.toggle("none");
            d.querySelector(".contenido").style.flexBasis="99%";
            d.querySelector(".contenido").classList.toggle("margin-auto");
            d.querySelector(".contenido").classList.toggle("height-95");
        	
        }
        //matches está esperando un selector válido
        if (e.target.matches(panelBtn) || e.target.matches(`${panelBtn} *`)) {   
            d.querySelector(panel).classList.toggle("is-active");
            d.querySelector(panelBtn).classList.toggle("is-active");
            d.querySelector(".menu-navegacion").classList.toggle("none");
            d.querySelector(panelBtn).classList.toggle("none");
            d.querySelector(".contenido").style.flexBasis="78.5%";
            d.querySelector(".contenido").classList.toggle("margin-auto");
        	d.querySelector(".contenido").classList.toggle("height-95");
        	
        	}

        /*
        if(e.target.matches(menuLink)){
            d.querySelector(panel).classList.remove("is-active");
            d.querySelector(panelBtn).classList.remove("is-active");
            //d.querySelector(".menu-navegacion").style.display("is-active");
            d.body.style.overflow = "visible";
        }*/


    });
   }

d.addEventListener("DOMContentLoaded", (e) => {

  hamburgerMenu(".panel-btn", ".panel", ".menu a");
  
  
  //Código Hamburger


  
  
  
  
  /*
  reloj("#reloj", "#activar-reloj", "#desactivar-reloj");
  alarma("../../assets/alarma.mp3", "#activar-alarma", "#desactivar-alarma");
  temporizador("tempori", "May 23, 2024 00:00:20", "Feliz cumpleaños 🤓");
  scrolltop(".btn-top");
  responsiveMedia(
    "youtube",
    "(min-width: 1024px)",
    `<a href="https://www.youtube.com/watch?v=6IwUl-4pAzc" target="_blank" rel="noopener">Ver video</a>
    `,
    `<iframe width="560" height="315" src="https://www.youtube.com/embed/6IwUl-4pAzc?si=qDYv0gVGCyI4kp78" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>`
  );
  responsiveMedia(
    "gmaps",
    "(min-width: 1024px)",
    `<a href="https://maps.app.goo.gl/DC9D1vPgWLCGFyAU9" target="_blank" rel="noopener">Ver mapa</a>
    `,
    `<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3902.3269763368585!2d-76.95913952525515!3d-12.020995888213792!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9105c4313a850775%3A0x20264d3dd644efb7!2sIE%200089%20Manuel%20Gonz%C3%A1lez%20Prada!5e0!3m2!1ses-419!2spe!4v1702907794568!5m2!1ses-419!2spe" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>`
  );
  userDeviceInfo("user-device");
  webCam("webcam");
  getGeolocation("geolocation");
  searchFilters(".card-filter", ".card");
  draw("#winner-btn", ".player");
  evitarEventoScroll(".menu", ".panel-btn");
  slider();
  scrollSpy();
  smartVideo();
  contactFormValidations();
  
*/
});



//El evento "keydown" es el momento que apretas la tecla
/* d.addEventListener("keydown",(e)=>{

    tecladoEvento(e);
}); */

//El evento "keydown" es el momento que sueltas la tecla
//Eso también quiere decir que, aun mantengas la tecla presionada, no se realiza el
//evento
/* d.addEventListener("keyup",(e)=>{

    tecladoEvento(e);
}); */

//El evento "keydown" es el momento que tengas presionado la tecla
//Se lanzará el evento cuando no dejes de presionar la tecla
//Dato: Se lanzará sin límites, ya que se tiene presionado
/* d.addEventListener("keypress",(e)=>{

    tecladoEvento(e);
}); */

//Creamos nuestros shortcuts
/*
d.addEventListener("keydown", (e) => {
  tecladoEvento(e);
  moveBall(e, ".ball", ".stage");
});
*/
//La retiramos debido a que no podemos delegar dos veces el evento DOMContentLoad
//darkTheme(".dark-theme-btn", "dark-mode");
//ajustarPantalla(".btnAbrir", ".enlace", ".btnCerrar", ".ancho", ".alto");
//Ya que el navegador identificará si hemos perdido la conexión
//networkStatus();
//speechReader();