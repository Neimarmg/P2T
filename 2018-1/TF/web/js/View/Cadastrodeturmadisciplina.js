
function carregaComboBoxTurmaDisciplina() {
    document.createElement("select").innerHTML =
            `<option value"idUnidadeHabiltacao"></option>
        `;
    for (let i in listaComboBoxDisciplinas) {
        let option = document.createElement("option");        
        let linha = 
               `cacarregaComboBoxDisciplinas()
            `;
       
        option.innerHTML = linha;
        document.querySelector("select").appendChild(option);
       
    }
    
}