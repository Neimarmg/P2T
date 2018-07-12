package entidade;

import entidade.aCurso;
import entidade.aProjetocurso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-12T09:29:43")
@StaticMetamodel(aDisciplinas.class)
public class aDisciplinas_ { 

    public static volatile CollectionAttribute<aDisciplinas, aCurso> cursos;
    public static volatile CollectionAttribute<aDisciplinas, aProjetocurso> projetocurso;
    public static volatile SingularAttribute<aDisciplinas, String> nomeDisciplina;
    public static volatile SingularAttribute<aDisciplinas, Long> idDisciplina;
    public static volatile SingularAttribute<aDisciplinas, String> ementa;

}