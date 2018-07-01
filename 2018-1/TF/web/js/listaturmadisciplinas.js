
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
            
            montarHTML(listaTurmaDisciplina);
            console.log(this.responseText);
      }
    }
    
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
                <th id="thead">
                    Ativa <input type="checkbox"/>
                </th>
                
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
                <td id="td">${listaTurmaDisciplina[i].ativa}</td>       
                
                
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



/*

function ligaLuz(){
     document.getElementById("btn").src="../images/novo.png";
}

function apagaLuz(){
    document.getElementById("btn").src="../images/salvar.png";
}
*/