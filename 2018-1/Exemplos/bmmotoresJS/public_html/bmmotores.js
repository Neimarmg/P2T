var elementoBody = document.querySelector("body");
elementoBody.onload = carregarMotores;

function carregarMotores(){
    setInterval(buscarMotores,3000);    
}
var i=0;
function buscarMotores(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4 && this.status==200){
            var main = document.querySelector("main");
            
            var listaMotores = JSON.parse(this.responseText);
            
            montarHTML(listaMotores);
            console.log(this.responseText);
        }
    }
    
    xhttp.open("GET","motores.json",true);
    xhttp.send();
}

function montarHTML(listaMotores){
    var str = "";
    
    for(var i in listaMotores[0].length)

        if (mod % 3 === 3)
            str += " <dl class='central'>";
        else
            str += "<dl>";

            str = listaMotores;
            str = "<dl>";
            str+= "<dd>";
            str+= "<figure>";
            str+= "<img src='"+ listaMotores[i].imagem+
                    "' alt='"+listaMotores[i].nome+"' />";
            str+= "</figure>";
            str+= "</dd>";
            str+= "<dt>";
            str+= "<a href='#'>"+listaMotores[i].nome+"</a>";
            str+= "</dt>";
            str+= "<dd>" +listaMotores[i].descricao+"</dd>";
            str+= "<dd>Uso: "+listaMotores[i].uso+"</dd>";
            str+= "</dl>";

        if (mod === 2)
            str += "<div class='clear'></div>" 
    
    var elementoMain = document.querySelector("main");
    elementoMain.innerHTML = str;
}
        


