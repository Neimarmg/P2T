package entidade;

import entidade.aCurso;
import entidade.aProjetocurso;
import entidade.gFiliais;
import entidade.gMatriz;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-01T08:32:32")
@StaticMetamodel(aUnidadeHabiltacao.class)
public class aUnidadeHabiltacao_ { 

    public static volatile CollectionAttribute<aUnidadeHabiltacao, gFiliais> filial;
    public static volatile CollectionAttribute<aUnidadeHabiltacao, aCurso> curso;
    public static volatile CollectionAttribute<aUnidadeHabiltacao, aProjetocurso> projetocursos;
    public static volatile SingularAttribute<aUnidadeHabiltacao, String> nomeHabilitacao;
    public static volatile CollectionAttribute<aUnidadeHabiltacao, gMatriz> matriz;
    public static volatile SingularAttribute<aUnidadeHabiltacao, Long> ididUnidadeHabiltacao;

}