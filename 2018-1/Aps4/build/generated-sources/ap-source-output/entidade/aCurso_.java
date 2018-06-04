package entidade;

import entidade.aModalidadecurso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-01T08:32:32")
@StaticMetamodel(aCurso.class)
public class aCurso_ { 

    public static volatile SingularAttribute<aCurso, String> nomeCurso;
    public static volatile CollectionAttribute<aCurso, aModalidadecurso> modalidadecursos;
    public static volatile SingularAttribute<aCurso, Long> idCurso;

}