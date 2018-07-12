function carregaComboBoxUnidadesHabilitacoes(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxUnidadesHabilitacoes = JSON.parse(this.responseText);            
            console.log(listaComboBoxUnidadesHabilitacoes);
            montarHTMLComboBoxUnidadesHabilitacoes(listaComboBoxUnidadesHabilitacoes);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/unidadeabilitacao",true);
    xhttp.send();
}

function montarHTMLComboBoxUnidadesHabilitacoes(listaComboBoxUnidadesHabilitacoes) {
    for (let i in listaComboBoxUnidadesHabilitacoes) {        
        let linhas =+`<option value=${listaComboBoxUnidadesHabilitacoes[i].idUnidadeHabiltacao}>${listaComboBoxUnidadesHabilitacoes[i].nomeHabilitacao}</option>`;
        document.getElementById("#idDisciplinas").innerHTML = linhas;
       
    }
}