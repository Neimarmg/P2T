
function carregatTurmaDisciplina(){
    buscaTurmaDisciplina();
    setInterval(buscaTurmaDisciplina(),15000);    
}

function buscaTurmaDisciplina(){
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
    
    xhttp.open("GET","http://localhost:8084/TF/api/turmadisciplinas",true);
    xhttp.send();
}

function montarHTML(listaTurmaDisciplina) {
 
    document.querySelector("table").innerHTML =
            `<tr id="tr">
                <th id="thead">id </th>
                <th id="thead">Disciplina</th>
                <th id="thead">Turno</th>
                <th id="thead">Curso</th>
                <th id="thead">Data inicio</th>
                <th id="thead">Data fim</th>
                <th id="thead">Ativa</th>
                
                <th id="thead"></th>
             </tr>`;
    for (let i in listaTurmaDisciplina) {
        let tr = document.createElement("tr");        
        let linha = 
               `<td id="td">${listaTurmaDisciplina[i].idTurmaDisciplina}</td>
                <td id="td">${listaTurmaDisciplina[i].nomeDisciplina}</td>
                <td id="td">${listaTurmaDisciplina[i].descricao}</td>
                <td id="td">${listaTurmaDisciplina[i].nomeCurso}</td>
                <td id="td">${listaTurmaDisciplina[i].dataInicio}</td>
                <td id="td">${listaTurmaDisciplina[i].dataFim}</td>
                <td id="td">
                    <input type="checkbox" value=${listaTurmaDisciplina[i].ativa}>
                    </input>               
                </td>       
                
                
               <td id="td">
                    <button id="btnPlanoAula"> 
        
                        <img src="../images/planodeAulas.png" id="btn"/> 
        
                    </button>
          
                    <button id="btnHorarioAturma"> 
                        <img src="../images/horario.png" id="btn"/>                    
                    </button>
          
                    <button id="btnTtumaDisc"> 
                        <img src="../images/lupa.png" id="btn"/>                    
                    </button>
                </td>
            `;
       
        tr.innerHTML = linha;
        document.querySelector("table").appendChild(tr);
    }
}



function carregaMenu(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");
            
            var listaMenus = JSON.parse(this.responseText);
            
            montarHTMLMenu(listaMenus);
            console.log(this.responseText);
      }
    };
    
    xhttp.open("GET","http://localhost:8084/TF/api/menus",true);
    xhttp.send();
}


function montarHTMLMenu(listaMenus) {
 
    document.querySelector("select").innerHTML =
            `<option value"idMenu"></option>
        `;
    for (let i in listaMenus) {
        let option = document.createElement("option");        
        let linha = 
               `<option value=${listaMenus[i].idMenu}>
                    ${listaMenus[i].nomeMenu}
                </option>    
            `;
        console.log(document.getElementById('select'));
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
       
    }
}


