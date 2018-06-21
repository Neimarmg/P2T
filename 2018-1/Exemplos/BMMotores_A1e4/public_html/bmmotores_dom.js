var body = document.querySelector("body");
body.onload=function(){
    //loadJson();
    setInterval(loadJson,10000);
    
}

function loadJson(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4 && this.status==200){
            trataJson(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET","motores.json",true);
    xhttp.send();
}

function trataJson(jsonObject){
    document.querySelector("main").innerHTML = "";
    for(var indice in jsonObject){
        var dl = document.createElement("dl");
        if(indice %3 == 1){
            dl.className = "central";
        }
        
        var ddFigura = document.createElement("dd");        
        var figura = construirFigura(jsonObject[indice].imagem, 
                            jsonObject[indice].nome);
        ddFigura.appendChild(figura);        
        dl.appendChild(ddFigura);
        
        var dtNome = document.createElement("dt");
        var link = document.createElement("a");
        link.href = "#";
        var textoLink = document.createTextNode(jsonObject[indice].nome); 
        link.appendChild(textoLink);
        dtNome.appendChild(link);
        dl.appendChild(dtNome);
        

        var ddDescricao = document.createElement("dd");        
        var textoDescricao = document.createTextNode(jsonObject[indice].descricao);
        ddDescricao.appendChild(textoDescricao);
        dl.appendChild(ddDescricao);

        var ddUso = document.createElement("dd");        
        var textoUso = document.createTextNode("Uso: "+jsonObject[indice].descricao);
        ddUso.appendChild(textoUso);
        dl.appendChild(ddUso);
        
        console.log(dl);
        document.querySelector("main").appendChild(dl);

        if(indice %3 == 2){
            var divLinha = document.createElement("div");
            divLinha.className="clear";
            console.log(divLinha);
            document.querySelector("main").appendChild(divLinha);
        } 
    }
}

function construirFigura(ima, alt) {
    var figure = document.createElement("figure");
    
    var imagem = document.createElement("img");
    console.log(ima);
    imagem.src = ima;
    imagem.alt = alt;
    
    figure.appendChild(imagem);

    return figure;
}




