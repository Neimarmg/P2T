let controller = new MotorController();

//bind: associa o controller como this. Se nao tiver, ele vai achar que a referencia é o document e não o controller
//Ou seja,quando chamarmos o carregaMotores e este chamar this._service, ele vai pensar que o this é referente ao controller e não ao document.
document.querySelector("body")
        .addEventListener('load',controller.carregaMotores.bind(controller));

//bind: associa o controller como this. Se nao tiver, ele vai achar que a referencia é o document e não o controller
//Ou seja,quando chamarmos o salvar e este chamar this._service, ele vai pensar que o this é referente ao controller e não ao document.
document.querySelector("#formulario")
        .addEventListener('submit',controller.salvar.bind(controller));












