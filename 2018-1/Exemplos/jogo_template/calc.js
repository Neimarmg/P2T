var tent = 10;


function resultado(){
    var valor = document.querySelector("#tentativa").value;
    
    if (tent == valor){
        return "Parabens";
    }else{
        return dica(valor);
    }
    console.log(resultado) ;
}


/*function valorSorteado(valor, index ){
    for (var index = 1; index < array.length; index++) {
        if(index.random(index) = index){

            return index;
        }else{
            
        }    
    }
    return valor;
}*/



function dica(valor){
    if(tent < valor){
        return "Mais baixo!"
    }else{
        return "Mais Alto";
    }
}   