package entidade;

import entidade.aTurmaDisciplinas;
import entidade.gTurnos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(aHorariosAulas.class)
public class aHorariosAulas_ { 

    public static volatile SingularAttribute<aHorariosAulas, Long> idHorariosAulas;
    public static volatile SingularAttribute<aHorariosAulas, String> dataFim;
    public static volatile CollectionAttribute<aHorariosAulas, gTurnos> turno;
    public static volatile SingularAttribute<aHorariosAulas, String> dataInicio;
    public static volatile CollectionAttribute<aHorariosAulas, aTurmaDisciplinas> turmaDisciplinas;

}