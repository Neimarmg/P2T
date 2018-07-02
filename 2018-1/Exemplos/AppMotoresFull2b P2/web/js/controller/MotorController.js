class MotorController {
    constructor(){
    }
    
    
    salvar(event) {
        event.preventDefault();
        let nome = document.querySelector("#txtnome").value;
        let descricao = document.querySelector("#txtdescricao").value;
        let uso = document.querySelector("#txtuso").value;

        let motor = new Motor(nome, descricao, uso);
        console.log(motor);
        //enviarMotor(motor);
    }
}

