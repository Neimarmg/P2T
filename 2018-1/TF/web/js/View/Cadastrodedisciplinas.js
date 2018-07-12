function carregaComboBoxDisciplinas(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaComboBoxDisciplinas = JSON.parse(this.responseText);            
            
            montarHTMLComboBoxDisciplinas(listaComboBoxDisciplinas);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:80800/TF/api/disciplina",true);
    xhttp.send();
}


function montarHTMLComboBoxDisciplinas(listaComboBoxDisciplinas) {
    for (let i in listaComboBoxDisciplinas) {        
        let linha =+`<option value=${listaComboBoxDisciplinas[i].idDisciplina}>${listaComboBoxDisciplinas[i].nomeDisciplina}</option>`;
        document.getElementById("#idDisciplinas").innerHTML = linha;
       
    }
}