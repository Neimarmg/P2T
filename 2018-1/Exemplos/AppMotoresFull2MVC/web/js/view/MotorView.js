class MotorView{
    constructor(seletor){
        this.elemento = document.querySelector(seletor);
    }
    
    atualiza(listaMotores){
        console.log(this.template(listaMotores));
        this.elemento.innerHTML = this.template(listaMotores);
    }
    
    template(listaMotores){
        return `<table border='1px'>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Descricao</th> 
                    <th>Uso</th>
                <tr>
             </thead>
             <tbody>
                ${listaMotores.map(motor =>
                    `<tr>
                        <td>${motor.nome}</td>
                        <td>${motor.descricao}</td>
                        <td>${motor.uso}</td>
                    </tr>
                    `).join('')}
             </tbody>
        </table>`;
    }
}