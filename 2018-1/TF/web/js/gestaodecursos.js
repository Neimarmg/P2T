/*document.getElementById("formulario").onsubmit = function(event){
     event.preventDefault();
     console.log("Salvar Curso");
     salvarCurso();
     //limparCamposFormulario();//Adicionar no final da requisição do POST          
     //buscarMotores(); //Adicionar no final da requisição do POST
};

var elementoBody = document.querySelector("body");
elementoBody.onload = buscarCursos;



function buscarCursos() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let main = document.querySelector("main");

            let listaCurso = JSON.parse(this.responseText);
            console.log(listaCurso);
            montarHTML(listaCurso);
        }
    }

    xhttp.open("GET", "http://localhost:8084/TF/api/curso/", true);
    xhttp.send();
}

function montarHTML(listaCursos) {
    document.querySelector("table").innerHTML =
            `<tr>
                <th>Nome</th>
                <th>Descricao</th> 
                <th>Uso</th>
             </tr> `;
    for (let ind in listaCursos) {


        let tr = document.createElement("tr");        
        let linha = 
                `   <td>${listaCursos[ind].idCurso}</td>
                    <td>${listaCursos[ind].modalidadecursos}</td>
                    <td>${listaCursos[ind].nomeCurso}</td>
                `;
        tr.innerHTML = linha;
        
        document.querySelector("table").appendChild(tr);

    }
}

function enviarCurso(motor) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            limparCamposFormulario();
            buscarCursos();
        }
    }
    xhttp.open("POST","http://localhost:8084/TF/api/curso/novo", true);
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
