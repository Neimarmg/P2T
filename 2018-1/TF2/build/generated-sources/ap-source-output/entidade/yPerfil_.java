package entidade;

import entidade.gFiliais;
import entidade.gUtilitarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-12T09:29:43")
@StaticMetamodel(yPerfil.class)
public class yPerfil_ { 

    public static volatile CollectionAttribute<yPerfil, gFiliais> filial;
    public static volatile SingularAttribute<yPerfil, Long> idPerfil;
    public static volatile SingularAttribute<yPerfil, String> descricaoPerfil;
    public static volatile CollectionAttribute<yPerfil, gUtilitarios> tipoPerfil;

}