/*function carregaComboBoxModalidadeCurso(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaModalidade = JSON.parse(this.responseText);
            montarHTMLSelecModalidadeCurso(listaModalidade);
            console.log(this.responseText);
      }      
    };    
    xhttp.open("GET","http://localhost:8084/TF/api/filial",true);
    xhttp.send();
}


function montarHTMLSelecModalidadeCurso(listaModalidadeCurso) {
 
    document.querySelector("select").innerHTML =
            `<option value"idFilial"></option>
        `;
    for (let i in listaModalidadeCurso) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaModalidadeCurso[i].idModalidade}>
                    ${listaModalidadeCurso[i].descricao}
                </option>    
            `;
        console.log(document.getElementById('select'));
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
        
    }
}*/