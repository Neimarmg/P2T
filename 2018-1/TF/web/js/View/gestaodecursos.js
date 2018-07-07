/*document.getElementById("formulario").onsubmit = function(event){
     event.preventDefault();
     console.log("Salvar Curso");
     salvarCurso();
     //limparCamposFormulario();//Adicionar no final da requisição do POST          
     //buscarMotores(); //Adicionar no final da requisição do POST
};
*/



/*
function enviarCurso(motor) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            limparCamposFormulario();
            buscarCursos();
        }
    }
    xhttp.open("POST","http://localhost:8080/TF/api/curso/novo", true);
    xhttp.setRequestHeader("content-type","application/json");
    xhttp.send(JSON.stringify(motor));
}

function salvarCurso(){
    let nome = document.getElementById("txtnome").value;
    let descricao = document.getElementById("txtdescricao").value;
    let uso = document.getElementById("txtuso").value;
    
    let motor = {};
    motor.nome = nome;
    motor.descricao = descricao;
    motor.uso = uso;
    
    enviarCurso(motor);
}
function limparCamposFormulario(){
    document.getElementById("txtnome").value="";
    document.getElementById("txtdescricao").value="";
    document.getElementById("txtuso").value="";
}

*/