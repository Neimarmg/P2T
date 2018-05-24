var palpite;
var valorRondomico;
var tentativaRealizadas;


function sorteiaRondomico(){ 
    valorRondomico = Math.floor(Math.random()*100)+1;
}


function soteiaValor(){
    palpite =  document.querySelector("#tentativa").value;
    if (palpite === valorRondomico){
        document.querySelector("#resultado").innerHTML = "Acertou";
        return palpite
    }else{
        document.querySelector("#resultado").innerHTML = "Errou";
        return 0;
    }    
    listaApostas();

}



function listaApostas(){
    
    return tentativas =+ palpite +" ";
}

function excutaAposta(){
    palpite =document.querySelector("#tentativa").value;
    if (soteiaValor != palpite){          
        document.querySelector("#resultado").innerHTML = soteiaValor();    

    }else{

    }
}


function iniciaJogo(){
    sorteiaRondomico();
}



