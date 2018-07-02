var elementoBody = document.querySelector("body");
elementoBody.onload = carregarMotores;

function carregarMotores(){
    buscarMotores();
    setInterval(buscarMotores,15000);    
}
var i=0;
function buscarMotores(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
            var main = document.querySelector("main");
            
            var listaMotores = JSON.parse(this.responseText);
            
            montarHTML(listaMotores);
            console.log(this.responseText);
        }
    }
    
    xhttp.open("GET","http://localhost:8084/AppMotoresFull2c/api/motor",true);
    xhttp.send();
}

function montarHTML(listaMotores){
    var str = "";
    
    for(var ind in listaMotores){
        if( ind % 3 === 1)
            str += "<dl class='central'>";
        else
            str += "<dl>";
        
        str+= "<dd>";
        str+= "<figure>";
        str+= "<img src='"+ "img/motor-1.jpg"+
                "' alt='"+listaMotores[ind].nome+"' />";
        str+= "</figure>";
        str+= "</dd>";
        str+= "<dt>";
        str+= "<a href='#'>"+listaMotores[ind].nome+"</a>";
        str+= "</dt>";
        str+= "<dd>" +listaMotores[ind].descricao+"</dd>";
        str+= "<dd>Uso: "+listaMotores[ind].uso+"</dd>";
        str+= "</dl>";
        
        if (ind %3 === 2)
            str+= "<div class='clear'></div>";
        
    }

    var elementoMain = document.querySelector("main");
    elementoMain.innerHTML = str;
}
        


