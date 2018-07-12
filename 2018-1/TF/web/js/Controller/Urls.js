class Urls{
       
    static get Porta(){
        return 8080;
    }    
    
    static get host(){
        return "http://localhost:";
    }
            
            
    static get CursoApi(desc){
        if (desc === "post" ){
           return  "http://localhost:8080/TF/api/curso/novo";
        }else if (desc === "get" ){
            return "http://localhost:8080/TF/api/curso"; 
        }        
    }

    
    static get TurmaDisciplinaApi(desc){
        if (desc === "post" ){
           return  Urls.gethost()+Urls.getPorta()+"/TF/curso/novo";
        }else if (desc === "get" ){
            return Urls.gethost()+Urls.getPorta()+"/TF/curso/"; 
        }        
    }

}






