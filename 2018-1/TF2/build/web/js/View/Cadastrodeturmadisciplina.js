

function carregaComboBoxDisciplinasTurma(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxDisciplinas = JSON.parse(this.responseText);            
            
            montarHTMLComboBoxDisciplinas(listaComboBoxDisciplinas);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/disciplina",true);
    xhttp.send();
}


function montarHTMLComboBoxDisciplinas(listaComboBoxDisciplinas) {
    
    document.querySelector("#idDisciplinas").innerHTML =
            `<option></option>
        `;
    for (let i in listaComboBoxDisciplinas) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaComboBoxDisciplinas[i].idDisciplina}>
                    ${listaComboBoxDisciplinas[i].nomeDisciplina}
                </option>    
            `;
       
        option.innerHTML = linha;
        document.querySelector("#idDisciplinas").appendChild(option);
    }
}

