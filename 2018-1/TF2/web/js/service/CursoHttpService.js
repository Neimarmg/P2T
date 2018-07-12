class CursoHttpService {
    
    static get URI(){        
        return "http://localhost:8080/TF/api/curso/novo";
        
    }
    
    constructor(){
        console.log("CursoHttpService");
    }
    
    //ok e err sao funcoes de callback
    enviaCurso(curso, ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 201) {
                    ok();
                } else {
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("POST", CursoHttpService.URI, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(curso));
    }

    buscarCurso(ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {                    
                    let listaCursos = JSON.parse(xhttp.responseText)
                            .map(item => new Cursos(item.nome, item.descricao, item.uso));
                    ok(listaCursos);
                } else {                    
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("GET", CursoHttpService.URI, true);
        xhttp.send();
 
    }
}

