
document.addEventListener("click", async (e)=>{
    const $fragment = document.createDocumentFragment();
    try {

        if(e.target.matches("#tablaEntradas .see")){
            const id = e.target.getAttribute("data-id");
                const $template = document.getElementById("template-entradas-detalles").content;
            const data = await fetch(`http://localhost:8000/lineaP/${id.toString()}`);
            const response = await data.json();
            let suma = 0;

            document.querySelector("#product-table  tbody").innerHTML = "";
            response.forEach((detalle)=>{
                $template.querySelector(".nombreProducto").textContent = detalle.productos.nombre;
                $template.querySelector(".cantidad").textContent = detalle.cantidad;
                $template.querySelector(".precio").textContent = detalle.productos.precio;
                $template.querySelector(".subtotal").textContent = detalle.productos.precio * detalle.productos.cantidad;
                suma += detalle.productos.precio * detalle.productos.cantidad;
                const $clone = document.importNode($template, true);
                $fragment.appendChild($clone);

            });
            document.querySelector("#product-table tbody").appendChild($fragment);
                        document.querySelector(".total-entrada").textContent="Total: ";
            document.querySelector(".total-entrada").textContent +="$"+ suma;
            document.getElementById('miModal').classList.toggle("is-open");

        }
        
         if(e.target.matches("#tablaVentas .see")){
            const id = e.target.getAttribute("data-id");
                const $template = document.getElementById("template-entradas-detalles").content;

            const data = await fetch(`http://localhost:8000/detalle-ventas/${id.toString()}`);
            const response = await data.json();
            let suma = 0;

            document.querySelector("#product-table  tbody").innerHTML = "";
            response.forEach((detalle)=>{
                $template.querySelector(".nombreProducto").textContent = detalle.productos.nombre;
                $template.querySelector(".cantidad").textContent = detalle.cantidad;
                $template.querySelector(".precio").textContent = detalle.productos.precio;
                $template.querySelector(".subtotal").textContent = detalle.productos.precio * detalle.productos.cantidad;
                suma += detalle.productos.precio * detalle.productos.cantidad;
                const $clone = document.importNode($template, true);
                $fragment.appendChild($clone);

            });
            document.querySelector("#product-table tbody").appendChild($fragment);
                        document.querySelector(".total-entrada").textContent="Total: ";
            document.querySelector(".total-entrada").textContent +="$"+ suma;
            document.getElementById('miModal').classList.toggle("is-open");

        }
        
    } catch (error) {
        console.log(error);
    }

});



document.addEventListener("click", (e)=>{
    if(e.target.matches(".close-button")){
            document.getElementById('miModal').classList.toggle("is-open");
    }
});