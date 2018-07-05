
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
    
    xhttp.open("GET","http://localhost:8084/TF/api/planodeaula",true);
    xhttp.send();
}

function montarHTML(listaTurmaDisciplina) {
 
    document.querySelector("table").innerHTML =
            `<tr id="tr">
                <th id="thead">id </th>
                <th id="thead">id Disciplina</th>
                <th id="thead">id Turno</th>
                <th id="thead">Data</th>
                <th id="thead">id horario</th>
                <th id="thead">Conteudo</th>
                <th id="thead">Confirmação</th>
                
                <th id="thead"></th>
             </tr>`;
    for (let i in listaTurmaDisciplina) {
        let tr = document.createElement("tr");        
        let linha = 
               `<td id="td">${listaTurmaDisciplina[i].idPlanoDeAula}</td>
                <td id="td">${listaTurmaDisciplina[i].idTurmaDisciplina}</td>
                <td id="td">${listaTurmaDisciplina[i].idTurno}</td>
                <td id="td">${listaTurmaDisciplina[i].dataAula}</td>
                <td id="td">${listaTurmaDisciplina[i].idHorariosAulas}</td>
                <td id="td">${listaTurmaDisciplina[i].conteudo}</td>
                
                <td id="td">
                    <input type="checkbox" value=${listaTurmaDisciplina[i].confirmada}>
                    </input>               
                </td>       
                
                
               <td id="td">
                    <a href="cadastrodehorariosdoprofessor.html">
                        <button id="btnCadastroProfessor">       
                            <img src="../images/professor.png" id="btn"/>        
                        </button>
                    </a>
                    <a href="cadastrodehorarios.html">
                        <button id="btnHorarioAturma"> 
                            <img src="../images/horario.png" id="btn"/>                    
                        </button>
                    </a>
        
                    <a href="cadastrodeturmadisciplina.html">
                        <button id="btnTtumaDisc"> 
                            <img src="../images/lupa.png" id="btn"/>                    
                        </button>
                    </a>    
                </td>
            `;
       
        tr.innerHTML = linha;
        document.querySelector("table").appendChild(tr);
    }
}




