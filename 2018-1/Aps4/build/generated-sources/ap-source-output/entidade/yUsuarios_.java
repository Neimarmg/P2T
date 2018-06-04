package entidade;

import entidade.gPessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-01T08:32:32")
@StaticMetamodel(yUsuarios.class)
public class yUsuarios_ { 

    public static volatile SingularAttribute<yUsuarios, Integer> senha;
    public static volatile CollectionAttribute<yUsuarios, gPessoa> pessoa;
    public static volatile SingularAttribute<yUsuarios, String> idUsuario;
    public static volatile SingularAttribute<yUsuarios, Long> id;
    public static volatile SingularAttribute<yUsuarios, Boolean> status;

}