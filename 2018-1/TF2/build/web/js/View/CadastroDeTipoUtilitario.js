function carregaComboBoxTipoUtilitario(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxTipoUtilitario = JSON.parse(this.responseText);            
            
            montarHTMLComboBoxTipoUtilitario(listaComboBoxTipoUtilitario);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/tipoutilitarios",true);
    xhttp.send();
}


function montarHTMLComboBoxTipoUtilitario(listaComboBoxTipoUtilitario) {
 
    document.querySelector("select").innerHTML =
            `<option value"idTipoUtilitario"></option>
        `;
    for (let i in listaComboBoxTipoUtilitario) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaComboBoxTipoUtilitario[i].idTipoUtilitario}>
                    ${listaComboBoxTipoUtilitario[i].descricao}
                </option>    
            `;
        console.log(document.getElementById('select'));
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
       
    }
}