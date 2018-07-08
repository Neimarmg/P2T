function carregaComboBoxuUnidadesHabilitacoes(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxUnidadesHabilitacoes = JSON.parse(this.responseText);            
            
            montarHTMLComboBoxUnidadesHabilitacoes(listaComboBoxUnidadesHabilitacoes);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/unidadeabilitacao",true);
    xhttp.send();
}


function montarHTMLComboBoxUnidadesHabilitacoes(listaComboBoxUnidadesHabilitacoes) {
    document.createElement("select").innerHTML =
            `<option value"idUnidadeHabiltacao"></option>
        `;
    for (let i in listaComboBoxUnidadesHabilitacoes) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaComboBoxUnidadesHabilitacoes[i].idUnidadeHabiltacao}>
                    ${listaComboBoxUnidadesHabilitacoes[i].nomeHabilitacao}
                </option>    
            `;
       
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
       
    }
}

