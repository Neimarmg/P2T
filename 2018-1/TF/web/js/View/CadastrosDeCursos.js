   /* global err, ok */

    function  inserir() {
         let curso ={};
        //event.preventDefault();
        let idCurso = document.querySelector("#idCurso").value;
        let nomeCurso = document.querySelector("#nomeCurso").value;
        let modalidadecursos = "[{idModalidade: "+document.querySelector("#idModalidade").value+"}]";
        
        
  
        curso.nomeCurso = nomeCurso;
        curso.modalidadecursos = modalidadecursos;
       
        console.log("sss         "+JSON.stringify(curso));
        enviaCurso(curso);
        
       
    }
    

    function enviaCurso(curso) {
        let xhttp = new XMLHttpRequest();
        
        xhttp.open("POST","http://localhost:8082/TF/api/curso/novo", true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(curso));
    }





/*

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
*/

