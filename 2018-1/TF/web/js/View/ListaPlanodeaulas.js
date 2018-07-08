


function carregaPlanAula(){
    buscaPlanoAula();
    setInterval(buscaPlanoAula(),15000);    
}

function buscaPlanoAula(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");            
            var listaTurmaDisciplina = JSON.parse(this.responseText);
            console.log(this.responseText);
            montarHTML(listaTurmaDisciplina);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8080/TF/api/planodeaula",true);
    xhttp.send();
}

function montarHTML(listaTurmaDisciplina) {
 
    document.querySelector("table").innerHTML =
            `<tr id="tr">
                <th id="thead">id </th>
                <th id="thead">Disc</th>
                <th id="thead">Turno</th>
                <th id="thead">Data</th>
                <th id="thead">Hora inicio</th>
                <th id="thead">Conteúdo</th>
                <th id="thead">Confirmada</th>
                
                <th id="thead"></th>
             </tr>`;
    
    for (let i in listaTurmaDisciplina) {
        let tr = document.createElement("tr");        
        let linha = 
               `<td id="td">${listaTurmaDisciplina[i].idPlanoDeAula}</td>
                <td id="td">${listaTurmaDisciplina[i].horariosAulas[0].turmaDisciplinas[0].disciplina[0].nomeDisciplina}</td>
                <td id="td">${listaTurmaDisciplina[i].horariosAulas[0].turno[0].descricao}</td>
                <td id="td">${listaTurmaDisciplina[i].dataAula}</td>
                  <td id="td"GUTILITARIOS_GAPLICACAO>${listaTurmaDisciplina[i].horariosAulas[0].turno[0].horaInicio}</td>
                <td id="td">${listaTurmaDisciplina[i].conteudo}</td>
                
                <td id="td">
                    <input id="inputcheckbox" type="checkbox" value=${listaTurmaDisciplina[i].confirmada} ${listaTurmaDisciplina[i].confirmada ?'checked':''}>
                    </input>               
                </td>       
                
                
               <td id="btnLista">
                    <a href="cadastrodehorariosdoprofessor.html">  
                        <img src="../images/professor.png" id="btn"/>
                  </a>
                    <a href="cadastrodehorarios.html">
                        <img src="../images/horario.png" id="btn"/>
                   </a>
        
                    <a href="cadastrodeturmadisciplina.html">
                        <img src="../images/lupa.png" id="btn"/>               
                      </a>    
                </td>
            `;
       
        tr.innerHTML = linha;
        document.querySelector("table").appendChild(tr);
    }
}




