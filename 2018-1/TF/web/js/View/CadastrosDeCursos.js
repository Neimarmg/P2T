document.getElementById("#formulario").onsubmit = function(event){
    event.preventDefault();
    console.log("Salvar Curso");
    salvarCurso();
    limparCamposFormulario();
     //limparCamposFormulario();//Adicionar no final da requisição do POST          
     //buscarMotores(); //Adicionar no final da requisição do POST
};

var elementoBody = document.querySelector("body");
elementoBody.onload = carregarCursos();



function enviarCurso(curso) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
           // limparCamposFormulario();
            salvarCurso();
        }
    }
    xhttp.open("POST","http://localhost:8080/TF/api/curso/novo", true);
    xhttp.setRequestHeader("content-type","application/json");
    xhttp.send(JSON.stringify(curso));
}

function salvarCurso(){
    let nomecursos = document.querySelector("#curso").value;
    let idModalidade = document.querySelector("#idModalidade").value;;
    
    console.log(nomecursos);
    console.log(idModalidade);
       
    let curso = {};
    
    curso.nomecursos = nomecursos;
    curso.idModalidade = idModalidade;
    
    enviarCurso(curso);
}


function limparCamposFormulario(){
    document.getElementById("curso").value="";
    document.getElementById("idModalidade").value="";
    document.getElementById("idCurso").value="";
}






function carregaComboBoxCurso(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxCurso = JSON.parse(this.responseText);            
            
            montarHTMLComboBoxCurso(listaComboBoxCurso);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/curso",true);
    xhttp.send();
}


function montarHTMLComboBoxCurso(listaComboBoxCurso) {
 
    document.querySelector("select").innerHTML =
            `<option value"idMenu"></option>
        `;
    for (let i in listaComboBoxCurso) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaComboBoxCurso[i].idCurso}>
                    ${listaComboBoxCurso[i].nomeCurso}
                </option>    
            `;
        console.log(document.getElementById('select'));
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
       
    }
}



/*

function val() {
     
    console.log(document.querySelector("#idCurso").value="12" );
 
}*/
