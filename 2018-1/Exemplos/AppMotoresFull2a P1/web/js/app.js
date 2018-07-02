document.getElementById("formulario").onsubmit = function(event){
     event.preventDefault();
     console.log("Salvar Motor");
     salvarMotor();
};

var elementoBody = document.querySelector("body");
elementoBody.onload = buscarMotores;



function buscarMotores() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let main = document.querySelector("main");

            let listaMotores = JSON.parse(this.responseText);
            console.log(listaMotores);
            montarHTML(listaMotores);
        }
    }

    xhttp.open("GET", "http://localhost:8084/AppMotoresFull2a/api/motor", true);
    xhttp.send();
}

function montarHTML(listaMotores) {
    document.querySelector("table").innerHTML =
            `<tr>
                <th>Nome</th>
                <th>Descricao</th> 
                <th>Uso</th>
             </tr> `;
    for (let ind in listaMotores) {


        let tr = document.createElement("tr");        
        let linha = 
                `   <td>${listaMotores[ind].nome}</td>
                    <td>${listaMotores[ind].descricao}</td>
                    <td>${listaMotores[ind].uso}</td>
                `;
        tr.innerHTML = linha;
        
        document.querySelector("table").appendChild(tr);

    }
}

function enviarMotor(motor) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            limparCamposFormulario();
           buscarMotores();
        }
    }

    xhttp.open("POST", "http://localhost:8084/AppMotoresFull2a/api/motor", true);
    xhttp.setRequestHeader("content-type","application/json");
    xhttp.send(JSON.stringify(motor));
}

function salvarMotor(){
    let nome = document.getElementById("txtnome").value;
    let descricao = document.getElementById("txtdescricao").value;
    let uso = document.getElementById("txtuso").value;
    
    let motor = new Motor(nome, descricao, uso);
    console.log(motor);    
    enviarMotor(motor);
}
function limparCamposFormulario(){
    document.getElementById("txtnome").value="";
    document.getElementById("txtdescricao").value="";
    document.getElementById("txtuso").value="";
}




