class MotorHttpService {
    
    static get URI(){
        return "http://localhost:8084/AppMotoresFull2MVC/api/motor";
    }
    
    constructor(){
        console.log("MotorHttpService");
    }
    
    //ok e err sao funcoes de callback
    enviaMotor(motor, ok, err) {
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
        xhttp.open("POST", MotorHttpService.URI, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(motor));
    }

    buscarMotores(ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    //Map é como varrer todos os itens do array e fazer uma ação com ele, retornando um novo array 
                    //com esses itens modificados
                    //Note que no caso da Arrow Function, está retornando para cada item um novo, um objeto Motor
                    //Tudo isso para transformar o nosso JSON (que retorna Object) em array de motores (objeto do tipo Motor).
                    let listaMotores = JSON.parse(xhttp.responseText)
                            .map(item => new Motor(item.nome, item.descricao, item.uso));
                    ok(listaMotores);
                } else {                    
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("GET", MotorHttpService.URI, true);
        xhttp.send();
 
    }
}

