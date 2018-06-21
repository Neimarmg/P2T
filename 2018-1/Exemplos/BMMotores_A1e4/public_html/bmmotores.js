var body = document.querySelector("body");
body.onload=function(){
    setInterval(loadJson,5000);
    
}

function loadJson(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
        trataJson(JSON.parse(this.responseText));
    }
  };
  xhttp.open("GET", "motores.json", true);
  xhttp.send();
}

function trataJson(jsonObject){
    var str = "";
    for(var indice in jsonObject){
        if(indice %3 == 1){
            str+= "<dl class='central'>";
        } else {
            str+="<dl>";
        }
        str+="<dd>";
        str+="<figure>";
        str+="<img src='"+jsonObject[indice].imagem+
                "' alt='"+jsonObject[indice].nome+"' />";
        str+="</figure>";
        str+="</dd>";
        str+="<dt>";
        str+="<a href='#'>"+jsonObject[indice].nome+"</a>";
        str+="</dt>";
        str+="<dd>"+jsonObject[indice].descricao+"</dd>";
        str+="<dd>Uso:"+ jsonObject[indice].uso+"</dd>";
        str+="</dl>";
        if(indice %3 == 2){
            str+= "<div class='clear'></div>";
        } 
    }
    document.querySelector("main").innerHTML = str;
}
/*
var str="";

function trataJson2(jsonObject){
        str="";
        jsonObject.forEach(montaElemento);
        document.querySelector("main").innerHTML = str;
}
function montaElemento(elemento, indice, lista){        
        if(indice % 3 == 1){
            str+="<dl class='central'>";
        }
        else{
            str+="<dl>";
        }
        str+="<dd>";
        str+="<figure>";
        str+="<img src='"+elemento.imagem+
                "' alt='"+elemento.nome+"' />";
        str+="</figure>";
        str+="</dd>";
        str+="<dt>";
        str+="<a href='#'>"+elemento.nome+"</a>";
        str+="</dt>";
        str+="<dd>"+elemento.descricao+"</dd>";
        str+="<dd>Uso:"+ elemento.uso+"</dd>";
        str+="</dl>";
        if(indice%3==2) 
            str+="<div class='clear'></div>";
        
}*/