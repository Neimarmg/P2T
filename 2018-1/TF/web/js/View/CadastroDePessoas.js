function carregaComboBoxPessoa(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxPessoa = JSON.parse(this.responseText);            
            
            montarHTMLComboBoxPessoa(listaComboBoxPessoa);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/pessoa",true);
    xhttp.send();
}


function montarHTMLComboBoxPessoa(listaComboBoxPessoa) {
 
    document.querySelector("select").innerHTML =
            `<option value"idPessoa"></option>
        `;
    for (let i in listaComboBoxPessoa) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaComboBoxPessoa[i].idPessoa}>
                    ${listaComboBoxPessoa[i].nome}
                </option>    
            `;
        console.log(document.getElementById('select'));
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
       
    }
}