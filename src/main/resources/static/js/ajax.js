const d = document,
$section = d.querySelector(".contenido");

	const $template = d.querySelector("#loader").content;
	const $fragment = d.createDocumentFragment();
	const $fragmentButtons = d.createDocumentFragment();

      const getHTML = (options) => {
        let { url, success, error } = options;

        const xmr = new XMLHttpRequest();

        xmr.addEventListener("readystatechange", (e) => {
          if (!xmr.readyState == 4) return;

          if (xmr.status >= 200 && xmr.status < 300) {
            let html = xmr.responseText;
            success(html);
          } else {
            let message = xmr.responseText || "Ocurrió un error";
            error(`Error ${xmr.status}: ${message}`);
          }
        });

        xmr.open("GET", url);
        xmr.setRequestHeader("Content-type", "text/html; charset=utf-8");

        xmr.send();
      };
      
      let productsData, productsAll;
      const options = {
		  method: "GET",
		  headers: {
		    "Content-Type": "text/html; charset=utf-8",
		    // Otras cabeceras si es necesario
		  },
		};
	const optionsAll = {
		  method: "GET",
		  headers: {
		    "Content-Type": "application/json",
		    // Otras cabeceras si es necesario
		  },
		};
      
      function procesoHtmlButtons(url,controlador, table){
      		      
	const $div = d.createElement("div");
	$div.classList.add("links");
		      	Promise.all([
		  fetch(url, options),
		  fetch("http://localhost:8000/"+controlador+"/listar",optionsAll),
		  /*De no ser exitoso, que se rechace y con ello me mandará al Catch
		  Con el map hago que me generé un nuevo arreglo, en este caso un arreglo
		  con dos objetos
		  */
		])
    .then((responses) =>
      Promise.all(
        responses.map((res) => {
          // Verificar el tipo de contenido
          const contentType = res.headers.get("content-type");
          
          if (contentType && contentType.includes("application/json")) {
            // Si es JSON, analizar la respuesta como JSON
            return res.json();
          } else {
            // Si no es JSON, simplemente retornar el texto (HTML en este caso)
            return res.text();
          }
        })
      )
    )
	  .then((json) => {
	    console.log(json);
	    productsData = json[0];
	    productsAll = json[1].length;
	    


		let cantidadButton = productsAll / 5;

       for (let index = 0; index < cantidadButton; index++) {
            if(index === 0){
              $div.innerHTML += `<button class="active" data-table=${table}  data-ruta="http://localhost:8000/${controlador}/listar-siguientes-${5 * (index + 1)}">${index + 1}</button>`;
            }  
            else{
              $div.innerHTML += `<button  data-table=${table} data-ruta="http://localhost:8000/${controlador}/listar-siguientes-${5 * (index + 1)}">${index + 1}</button>`;
			  
            }

          }
          $fragmentButtons.appendChild($div);
          	    
	    //Si exitoso
	      const $clone = d.importNode($template, true);
		  $fragment.appendChild($clone);
		  $section.appendChild($fragment);
				      
				      
		  //d.querySelector(".loader").style.display="bolck"
	      setTimeout(() => {
			$section.innerHTML ="";
		    $section.innerHTML= productsData;
		    $section.appendChild($fragmentButtons);
	
		    }, 2000)

	  })
	  .catch((err) => {
	    console.log(err);
	    //let message =
	      //err.statusText || "Ocurrió un error en la conexión con la APi Stripe";
	    //$tacos.innerHTML = `<p>Error ${err.status}: ${message}</p>`;
	  });

      
      
    }
      
      
      d.addEventListener("DOMContentLoaded", e=>{
      getHTML({
                url: "/administrador/usuarios",
                /*Este success recibe lo del método getHTML*/
                success: (html)=>{
                	console.log("Jalando el html para la gestión");
						console.log(e.target.href);
				      
				      const $clone = d.importNode($template, true);
				      $fragment.appendChild($clone);
				      $section.appendChild($fragment);
				      
				      
				      //d.querySelector(".loader").style.display="bolck"
				       setTimeout(() => {
				       		$section.innerHTML ="";
				          $section.innerHTML= html	
				      }, 2000)
                
                //$section.innerHTML= html
                },
                error: (err)=> {}//$section.innerHTML= err
            });
      });


      d.addEventListener("click", e=>{
      
      
      console.log("Ejecutando click");
				console.log(e.target);
			   const $todosA = document.querySelectorAll(".navegacion a");
		       if(e.target.matches(".barra-lateral .navegacion a")){
			      	 
					  e.preventDefault();
					  e.stopPropagation();
					  $todosA.forEach(el=> el.removeAttribute("id"));
					  e.target.setAttribute("id","inbox");
				  
				  
				  procesoHtmlButtons(e.target.href, e.target.dataset.controlador, e.target.dataset.table)
				}
				
			
			
		    if(e.target.matches(".navegacion span")|| e.target.matches(".navegacion ion-icon")){
		
		        const $a = e.target.closest("a");
				
				e.preventDefault();
				$todosA.forEach(el=> el.removeAttribute("id"));
        		$a.setAttribute("id","inbox");
				procesoHtmlButtons($a.href, $a.dataset.controlador,$a.dataset.table);

		    }
		});

