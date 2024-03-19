// Función para cargar proveedores al cargar la página
     document.addEventListener("DOMContentLoaded", e=>{
      const $selectProveedor = document.querySelector("#cboproveedor")
	const 	productosContainer = document.querySelector("div#productos");
	          productosContainer.classList.add("none");

      console.log($selectProveedor)

      $selectProveedor.addEventListener("change", e=>{
	const $divProductos = document.querySelector("#productos");
         console.log(e.target.value);
         
        cargarProductos(e.target.value);
      });


      function cargarProductos(id) {
        // Obtener el id del proveedor seleccionado
        var proveedorId = document.getElementById("proveedor").value;

        // Realizar una solicitud AJAX para obtener productos por proveedor
        var urlProductos =
          "http://localhost:8000/productos/"+id;

        fetch(urlProductos)
          .then((response) => response.json())
          .then((productos) => {
            // Mostrar los productos con checkboxes y campos de cantidad
            mostrarProductos(productos);
          })
          .catch((error) =>
            console.error("Error al obtener productos:", error)
          );
      }

      function mostrarProductos(productos) {
        // Limpiar la lista de productos
        var productosContainer = document.getElementById("productos");
        productosContainer.innerHTML = "";

        // Mostrar los productos con checkboxes y campos de cantidad
        productos.forEach(function (producto) {
          var checkbox = document.createElement("input");
          var label = document.createElement("label");
          checkbox.type = "checkbox";
          checkbox.className = "producto-checkbox";
          var cantidadInput = document.createElement("input");
          cantidadInput.type = "text";
          cantidadInput.placeholder = "Ingresar cantidad"
          cantidadInput.className = "cantidad-input";
          cantidadInput.disabled = true;

          label.textContent  = producto.nombre;

          // Asignar el id del producto como data para asociar checkbox y cantidad
          checkbox.setAttribute("data-producto-id", producto.id);
          cantidadInput.setAttribute("data-producto-id", producto.id);

          var container = document.createElement("div");
          container.classList.add("contenedor-check")
          container.appendChild(checkbox);
          container.appendChild(label);
          // Agregar los elementos al DOM
          productosContainer
            .appendChild(document.createElement("div"))
            .appendChild(container);

          productosContainer.lastChild.appendChild(cantidadInput);


          // Habilitar/deshabilitar campos de cantidad al marcar/desmarcar checkbox
          checkbox.addEventListener("change", function () {
            cantidadInput.disabled = !checkbox.checked;
          });
        });
      }

      function confirmarPedido() {
        // Obtener productos seleccionados y sus cantidades
        var productosSeleccionados = [];
        var checkboxes = document.getElementsByClassName("producto-checkbox");
        for (var i = 0; i < checkboxes.length; i++) {
          var checkbox = checkboxes[i];
          var productoId = checkbox.getAttribute("data-producto-id");
          var cantidadInput = document.querySelector(
            '.cantidad-input[data-producto-id="' + productoId + '"]'
          );
          var cantidad = cantidadInput.value;
          if (checkbox.checked && cantidad.trim() !== "") {
            productosSeleccionados.push({
              id: productoId
            });
          }
        }

        // Obtener la fecha de entrega
        var fechaEntrega = document.getElementById("fechaEntrega").value;

        // Obtener el id del proveedor seleccionado
        var proveedorId = document.getElementById("proveedor").value;

        // Crear objeto que representa el pedido
        var pedido = {
          id: "PE16",
          proveedor: {
            id: proveedorId,
          },
          fPedido: "2024-03-04",
          fEntrega: fechaEntrega,
          estado: "Enviado",
          itemsProducto: productosSeleccionados
        };

        // Generar un límite único
        const boundary = `----MyBoundary${new Date().getTime()}`;

        const formData = new FormData();
        //formData.append("img", imagenSeleccionado);
        formData.append("pedido", JSON.stringify(pedido));

        // Mostrar el contenido de FormData
        console.log("FormData content:");
        for (const pair of formData.entries()) {
          console.log(pair[0] + ", " + pair[1]);
        }

        // Configurar opciones para la solicitud Fetch
        const options = {
          method: "POST",
          body: formData,
        };

        // Realizar la solicitud Fetch
        fetch("http://localhost:8000/pedidos/save", options)
          .then((response) => {
            if (!response.ok) {
              throw new Error(
                `Error ${response.status}: ${response.statusText}`
              );
            }
            console.log("Exito");
            guardarLinea();    
            // Puedes agregar código para manejar la respuesta aquí si es necesario
            // return response.json(); // Si esperas una respuesta en formato JSON
          })
          .catch((error) => {
            console.error(error.message);
            // Puedes manejar errores aquí según tus necesidades
          });


        //No aplicamos validación, ya que tan solo podemos evitar pasarle la variable data
        //"data" y lo tomaría como null al igual que la variable method
      }

      function guardarLinea() {

        
        const formData = new FormData();
        //formData.append("img", imagenSeleccionado);
        
        // Obtener productos seleccionados y sus cantidades
        var productosSeleccionados = [];
        var checkboxes = document.getElementsByClassName("producto-checkbox");
        for (var i = 0; i < checkboxes.length; i++) {
          var checkbox = checkboxes[i];
          var productoId = checkbox.getAttribute("data-producto-id");
          var cantidadInput = document.querySelector(
            '.cantidad-input[data-producto-id="' + productoId + '"]'
          );
          var cantidad = cantidadInput.value;
          if (checkbox.checked && cantidad.trim() !== "") {
            productosSeleccionados.push({
              pedido: {
                id:"PE16"
              },
              productos:{
                id: productoId
              },
              cantidad: cantidad,
            });
          }
        }

        formData.append("lineapedido", JSON.stringify(productosSeleccionados));


        fetch("http://localhost:8000/lineaP/save", {method: "POST", body: formData})
        .then(res=>console.log(res))
        .catch(err=>console.log(err));
      }

      document.addEventListener("click", e=>{
        const $productosContainer = document.querySelector("#productos");
        
        if(e.target.matches(".abrirProductos")){
            $productosContainer.classList.toggle("none");
            $productosContainer.classList.add("block");

        }
      })
     
     });