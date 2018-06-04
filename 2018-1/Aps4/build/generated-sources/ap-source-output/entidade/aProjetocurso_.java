package entidade;

import entidade.aModalidadecurso;
import entidade.gFiliais;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-01T08:32:32")
@StaticMetamodel(aProjetocurso.class)
public class aProjetocurso_ { 

    public static volatile SingularAttribute<aProjetocurso, Double> valorCurso;
    public static volatile CollectionAttribute<aProjetocurso, gFiliais> filial;
    public static volatile SingularAttribute<aProjetocurso, Date> datacriacao;
    public static volatile SingularAttribute<aProjetocurso, String> descricaoProjeto;
    public static volatile CollectionAttribute<aProjetocurso, aModalidadecurso> modalidadecurso;
    public static volatile SingularAttribute<aProjetocurso, Long> idProjetoCurso;

}