function carregaComboBoxProfessor(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxProfessor = JSON.parse(this.responseText);            
            
            montarHTMLComboBoxProfessor(listaComboBoxProfessor);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/professor",true);
    xhttp.send();
}


function montarHTMLComboBoxProfessor(listaComboBoxProfessor) {
 
    document.querySelector("select").innerHTML =
            `<option value"idProfessor"></option>
        `;
    for (let i in listaComboBoxProfessor) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaComboBoxProfessor[i].idProfessor}>
                    ${listaComboBoxProfessor[i].pessoas[0].nome}
                </option>    
            `;
        console.log(document.getElementById('select'));
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
       
    }
}