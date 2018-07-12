
class MotorController {
    constructor() {
        this._service = new MotorHttpService();
        this.motorView = new MotorView("#motores");
        
    }

    salvar(event) {
        
        event.preventDefault();
        let nome = document.querySelector("#txtnome").value;
        let descricao = document.querySelector("#txtdescricao").value;
        let uso = document.querySelector("#txtuso").value;
        let motor = new Motor(nome, descricao, uso);
        
        const self = this;
        
        this._service.enviaMotor(motor,
            function(){
                self.limparCamposFormulario();
                self.carregaMotores();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }

    carregaMotores() {
        console.log("Carrega Motores");
        const self = this;
        this._service.buscarMotores(
            function(listaMotores){
                console.log(listaMotores);
                self.motorView.atualiza(listaMotores);
            },
            function(msg){
                console.log(msg);
            }
        );
    }
    
    montarHTML(listaMotores) {
        document.querySelector("table").innerHTML =
                `<tr>
                <th>Nome</th>
                <th>Descricao</th> 
                <th>Uso</th>
             </tr> `;
        for (let ind in listaMotores) {
            let tr = document.createElement("tr");
            let linha =
                    `   <td>${listaMotores[ind].nome}</td>
                    <td>${listaMotores[ind].descricao}</td>
                    <td>${listaMotores[ind].uso}</td>
                `;
            tr.innerHTML = linha;
            document.querySelector("table").appendChild(tr);
        }
    }

    limparCamposFormulario() {
        document.querySelector("#txtnome").value = "";
        document.querySelector("#txtdescricao").value = "";
        document.querySelector("#txtuso").value = "";
    }
}

