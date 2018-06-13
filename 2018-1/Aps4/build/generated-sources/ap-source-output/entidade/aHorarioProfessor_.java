package entidade;

import entidade.aPlanoDeAula;
import entidade.aProfessor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(aHorarioProfessor.class)
public class aHorarioProfessor_ { 

    public static volatile SingularAttribute<aHorarioProfessor, Double> valorAula;
    public static volatile CollectionAttribute<aHorarioProfessor, aProfessor> professor;
    public static volatile SingularAttribute<aHorarioProfessor, Long> idaHorarioProfessor;
    public static volatile CollectionAttribute<aHorarioProfessor, aPlanoDeAula> planoDeAulas;

}