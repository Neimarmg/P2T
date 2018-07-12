
class CursoController {
    constructor() {
        this._service = new CurHttpService();
        this.cursoView = new MotorViewView("#Curso");
        
    }

    salvar(event) {
        
        event.preventDefault();
        let idCurso = document.querySelector("#idCurso").value;
        let nomeCurso = document.querySelector("#nomeCurso").value;
        let idModalidade = document.querySelector("#idModalidade").value;
        let curso = new curso(idCurso, nomeCurso, idModalidade);
        
        const self = this;
        
        this._service.enviaCurso(curso,
            function(){
                self.limparCamposFormulario();
                self.carregaMotores();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }

    carregaCursos() {
        console.log("Carrega Motores");
        const self = this;
        this._service.buscarC(
            function(listaCurso){
                console.log(listaCurso);
                self.motorView.atualiza(listaCurso);
            },
            function(msg){
                console.log(msg);
            }
        );
    }
    
    montarHTML(listaCurso) {
        document.querySelector("table").innerHTML =
                `<tr>
                <th>IdCurso</th>
                <th>Curso</th> 
                <th>Modalidade</th>
             </tr> `;
        for (let i in listaCurso) {
            let tr = document.createElement("tr");
            let linha =
                    `   <td>${listaCurso[i].idCurso}</td>
                    <td>${listaCurso[i].momeCurso}</td>
                    <td>${listaCurso[i].idModalidade}</td>
                `;
            tr.innerHTML = linha;
            document.querySelector("table").appendChild(tr);
        }
    }

    limparCamposFormulario() {
        document.querySelector("#idCurso").value = "";
        document.querySelector("#nomeCurso").value = "";
        document.querySelector("#idModalidade").value = "";
    }
}

