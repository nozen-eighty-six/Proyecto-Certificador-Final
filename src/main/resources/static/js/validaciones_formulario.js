const d = document;

export default function contactFormValidations() {
  const $form = d.querySelector(".contact-form"),
    //Si no quieres que por ejemplo el subject sea requerido, se le puede
    //quitar el atributo "required" para que no sea tomado en cuenta
    $inputs = d.querySelectorAll(".contact-form [required]");

  console.log($inputs);
  //Estamos creando un span que se coloque después de los inputs,
  //Se le agrega la clase error con none, para que este oculto
  //cuando se detecte algo en los inputs, se le quitará esa clase "none"
  //a los inputs
  $inputs.forEach((inp) => {
    const $span = d.createElement("span");
    $span.id = inp.name;
    $span.textContent = inp.title;
    $span.classList.add("contact-form-error", "none");
    inp.insertAdjacentElement("afterend", $span);
  });

  d.addEventListener("keyup", (e) => {
    if (e.target.matches(".contact-form [required]")) {
      let $input = e.target, //Capturamos al elemento actual que realiza el event
        pattern = $input.pattern || $input.dataset.pattern;
      //Operador de cortocircuito, indica que si la primera condición no funciona
      //Entonces accede al segundo valor
      if (pattern && $input.value !== "") {
        console.log("El input tiene patrón");
        //Guardo mi expresión regultar
        let expRegular = new RegExp(pattern);
        //
        //let expr = new RegExp(expresion) y la usamos mediante expr.exec(valorAVerificar)
        return !expRegular.exec($input.value)
          ? d.getElementById($input.name).classList.add("is-active")
          : d.getElementById($input.name).classList.remove("is-active");
      }
      if (!pattern) {
        console.log("El input  no tiene patrón");

        return $input.value === ""
          ? d.getElementById($input.name).classList.add("is-active")
          : d.getElementById($input.name).classList.remove("is-active");
      }
    }
  });


}
