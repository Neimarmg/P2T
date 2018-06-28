document.getElementById("table").onsubmit = function(event){
     event.preventDefault();
     console.log("Salvar Motor");
     salvarMotor();
     //limparCamposFormulario();//Adicionar no final da requisição do POST          
     //buscarMotores(); //Adicionar no final da requisição do POST
};

var elementoBody = document.querySelector("body");
elementoBody.onload = buscarMotores;



function buscarMotores() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let main = document.querySelector("main");

            let listaMotores = JSON.parse(this.responseText);
            console.log(listaMotores);
            montarHTML(listaMotores);
        }
    }

    xhttp.open("GET", "http://localhost:8084/AppMotoresFull/api/motor", true);
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
            limparCamposFormulario()
            buscarMotores();
        }
    }

    xhttp.open("POST", "http://localhost:8084/AppMotoresFull/api/motor", true);
    xhttp.setRequestHeader("content-type","application/json");
    xhttp.send(JSON.stringify(motor));
}

function salvarMotor(){
    let nome = document.getElementById("txtnome").value;
    let descricao = document.getElementById("txtdescricao").value;
    let uso = document.getElementById("txtuso").value;
    
    let motor = {};
    motor.nome = nome;
    motor.descricao = descricao;
    motor.uso = uso;
    
    enviarMotor(motor);
}
function limparCamposFormulario(){
    document.getElementById("txtnome").value="";
    document.getElementById("txtdescricao").value="";
    document.getElementById("txtuso").value="";
}

//function montarHTML(listaMotores) {
//    document.querySelector("table").innerHTML =
//            `<tr>
//                <th>Nome</th>
//                <th>Descricao</th> 
//                <th>Uso</th>
//             </tr> `;
//    for (var ind in listaMotores) {
//
//
//
//        var tr = document.createElement("tr");
//
//        var tdNome = document.createElement("td");
//        var textoNome = document.createTextNode(listaMotores[ind].nome);
//        tdNome.appendChild(textoNome);
//        tr.appendChild(tdNome);
//
//        var tdDescricao = document.createElement("td");
//        var textoDescricao = document.createTextNode(listaMotores[ind].descricao);
//        tdDescricao.appendChild(textoDescricao);
//        tr.appendChild(tdDescricao);
//
//        var tdUso = document.createElement("td");
//        var textoUso = document.createTextNode(listaMotores[ind].uso);
//        tdUso.appendChild(textoUso);
//        tr.appendChild(tdUso);
//
//        document.querySelector("table").appendChild(tr);
//
//    }
//}