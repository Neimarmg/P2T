$(document).ready(function(){
    setInterval(loadJson,5000);    
});

function loadJson(){
    $.get("motores.json",
        function(jsonObject){
            trataJson(jsonObject);
        }
    );
}

function trataJson(jsonObject){
    $("main").empty();
    for(var indice in jsonObject){
        
        var dl = $("<dl></dl>");
        if(indice %3 == 1){
            dl.addClass("central");
        }
        
        var ddFigura = $("<dd></dd>");
        var figura = $("<figure></figure>");
        var imagem = $("<img></img>");
        imagem.attr("src",jsonObject[indice].imagem);
        imagem.attr("alt",jsonObject[indice].nome);
        figura.append(imagem);
        ddFigura.append(figura);
        dl.append(ddFigura);
        
        var dt = $("<dt></dt>");
        var link = $("<a></a>");
        link.attr("href","#");
        link.append(jsonObject[indice].nome);
        dt.append(link);
        dl.append(dt);
        
        var ddDescricao = $("<dd></dd>");
        ddDescricao.append(jsonObject[indice].descricao);
        dl.append(ddDescricao);

        var ddUso = $("<dd></dd>");
        ddUso.append(jsonObject[indice].uso);
        dl.append(ddUso);
        
        $("main").append(dl);
        console.log($("main").html());
        
        

        if(indice %3 == 2){
            $("main").append("<div class='clear'></div>");
        } 
    }
}

