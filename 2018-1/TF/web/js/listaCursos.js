/*document.getElementById("formulario").onsubmit = function(event){
     event.preventDefault();
     console.log("Salvar Curso");
     salvarCurso();
     //limparCamposFormulario();//Adicionar no final da requisição do POST          
     //buscarMotores(); //Adicionar no final da requisição do POST
};

var elementoBody = document.querySelector("body");
elementoBody.onload = buscarCursos;

*/

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
    xhttp.open("GET", "http://localhost:8084/TF/api/curso/14", true);
    xhttp.send();
    
}

function montarHTML(listaCursos) {
 
    document.querySelector("table").innerHTML =
            `<tr>
                <th>idCurso</th>
                <th>nomeCurso</th> 
                <th>idModalidade</th>
             </tr> `;
    for (let ind in listaCursos) {
        let tr = document.createElement("tr");        
        let linha = 
            <td>${listaCursos[ind].idCurso}</td>;
            <td>${listaCursos[ind].nomeCurso}</td>;
            <td>${listaCursos[ind].idModalidade}</td>;
        tr.innerHTML = linha;
        document.querySelector("table").appendChild(tr);
       
  
}



/*

function ligaLuz(){
     document.getElementById("btn").src="../images/novo.png";
}

function apagaLuz(){
    document.getElementById("btn").src="../images/salvar.png";
}
*/