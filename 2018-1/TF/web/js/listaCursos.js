
function carregarCursos(){
    buscarCursos()();
    setInterval(buscarCursos(),15000);    
}

function buscarCursos(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
            var main = document.querySelector("main");
            
            var listaCursos = JSON.parse(this.responseText);
            
            montarHTML(listaCursos);
            console.log(this.responseText);
      }
    }
    
    xhttp.open("GET","http://localhost:8080/TF/api/curso",true);
    xhttp.send();
}

function montarHTML(listaCursos) {
 
    document.querySelector("table").innerHTML =
            `<tr>
                <th>idCurso</th>
                <th>nomeCurso</th> 
                
             </tr>`;
    for (let ind in listaCursos) {
        let tr = document.createElement("tr");        
        let linha = 
                `
                <td>${listaCursos[ind].idCurso}</td>
                <td>${listaCursos[ind].nomeCurso}</td>
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