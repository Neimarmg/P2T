package entidade;

import entidade.gPessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(aProfessor.class)
public class aProfessor_ { 

    public static volatile CollectionAttribute<aProfessor, gPessoa> pessoas;
    public static volatile SingularAttribute<aProfessor, Boolean> ativo;
    public static volatile SingularAttribute<aProfessor, Long> idProfessor;

}