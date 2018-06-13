package entidade;

import entidade.aHorariosAulas;
import entidade.gTurnos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(aPlanoDeAula.class)
public class aPlanoDeAula_ { 

    public static volatile SingularAttribute<aPlanoDeAula, String> conteudo;
    public static volatile SingularAttribute<aPlanoDeAula, Long> idPlanoDeAula;
    public static volatile CollectionAttribute<aPlanoDeAula, aHorariosAulas> horariosAulas;
    public static volatile SingularAttribute<aPlanoDeAula, Boolean> confirmada;
    public static volatile SingularAttribute<aPlanoDeAula, String> dataAula;
    public static volatile CollectionAttribute<aPlanoDeAula, gTurnos> turno;

}