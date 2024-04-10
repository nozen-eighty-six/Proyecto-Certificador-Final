//const d = document;
var checkboxes = document.getElementsByClassName("producto-checkbox");
var checkbox = document.createElement("input");
checkbox.type = "checkbox";
checkbox.checked = false; //una pregunta, si declaro eso, no aparece marcado?

document.addEventListener("DOMContentLoaded", (e) => {
    slider();
    });

 function slider(){
    const $btnPrev = document.querySelector(".slider-btns .prev"),
     $btnNext = document.querySelector(".slider-btns .next"),
     $slides = document.querySelectorAll(".slider-products .product");
    
    
     console.log("Longitud de los slides: "+$slides.length);
     //length -->Me devuelve la cantidad de elementos

     let i =0;
     document.addEventListener("click", e=>{

        if(e.target === $btnPrev || e.target.matches(".slider-btns .prev *")){
            e.preventDefault();
            $slides[i].classList.remove("active");
            i--;

            if(i<0){
                //Si es menor a cero, que agregue el último valor
                i= $slides.length -1;
            }
            //Le agregamos la clase active

            $slides[i].classList.add("active");
        }
        if(e.target === $btnNext || e.target.matches(".slider-btns .next *")){
            e.preventDefault();
            //Quitamos al actual
            $slides[i].classList.remove("active");
            /*Llega al elemento 3 de 0-3, le removemos la clase active
            le sumamos ++, pero ahora es 4, entonces ahora entrará al if y se restablecerá 
            al primer elemento
            */
            i++;
            

            if(i>=$slides.length){
                i= 0;
            }

            $slides[i].classList.add("active");
        }
     });
}