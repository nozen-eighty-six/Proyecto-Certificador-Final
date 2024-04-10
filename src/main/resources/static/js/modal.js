document.addEventListener("DOMContentLoaded", e=>{

    document.addEventListener("click", e=>{
        console.log(e.target);
        if(e.target.matches(".modal .close") || e.target.matches(".modal .btn-confirm")){
            e.target.closest(".modal").classList.remove("is-active");
        }
    });
})