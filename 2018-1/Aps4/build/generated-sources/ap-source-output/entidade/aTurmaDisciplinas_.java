package entidade;

import entidade.aDisciplinas;
import entidade.aUnidadeHabiltacao;
import entidade.gTurnos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-01T08:32:32")
@StaticMetamodel(aTurmaDisciplinas.class)
public class aTurmaDisciplinas_ { 

    public static volatile SingularAttribute<aTurmaDisciplinas, Long> idTurmaDisciplina;
    public static volatile SingularAttribute<aTurmaDisciplinas, Date> dataFim;
    public static volatile CollectionAttribute<aTurmaDisciplinas, aDisciplinas> disciplina;
    public static volatile CollectionAttribute<aTurmaDisciplinas, gTurnos> turno;
    public static volatile SingularAttribute<aTurmaDisciplinas, Date> dataInicio;
    public static volatile SingularAttribute<aTurmaDisciplinas, Boolean> ativa;
    public static volatile CollectionAttribute<aTurmaDisciplinas, aUnidadeHabiltacao> unidadeHabiltacao;
    public static volatile SingularAttribute<aTurmaDisciplinas, EntityManagerFactory> emf;

}