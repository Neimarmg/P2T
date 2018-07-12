
function carregarCursos(){
    buscarCursos()();
    setInterval(buscarCursos(),15000);    
}

function buscarCursos(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
           //var main = document.querySelector("main");
            
            var listaCursos = JSON.parse(this.responseText);
            console.log(this.responseText);
            montarHTML(listaCursos);            
      }
    }
    
    xhttp.open("GET","http://localhost:8080/TF/api/curso",true);
    xhttp.send();
}

function montarHTML(listaCursos) {
 
    document.querySelector("table").innerHTML =
            `<tr id="tr">
                <th id="thead">id curso</th>
                <th id="thead">Nome curso</th>
                <th id="thead">Modalidade curso</th>
                
                <th id="thead"></th>
             </tr>`;
    for (let i in listaCursos) {
        let tr = document.createElement("tr");        
        let linha = 
               `<td id="td" type="text" value=${listaCursos[i].idCurso}> ${listaCursos[i].idCurso}</td>
                <td id="td" type="text" value=${listaCursos[i].nomeCurso}> ${listaCursos[i].nomeCurso}</td>               
                <td id="td" type="text" value=${listaCursos[i].modalidadecursos[0].idModalidade}> ${listaCursos[i].modalidadecursos[0].descricao}</td> 
                
                <td id="btnLista">
                    <a href="cadastrodecursos.html?id=${listaCursos[i].idCurso}">
                        <img src="../images/lupa.png" id="btn"/>                    
                    </a>
                </td>
            `;
       
        tr.innerHTML = linha;
        document.querySelector("table").appendChild(tr);
    }
}




/*
function buscavalor{
    window.*alert(document.getElementById("td").value;
}
function apagaLuz(){
    document.getElementById("btn").src="../images/salvar.png";
}
*/