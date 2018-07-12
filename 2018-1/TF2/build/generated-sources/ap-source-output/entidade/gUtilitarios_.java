package entidade;

import entidade.gAplicacao;
import entidade.gTipoutilitarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-12T09:29:43")
@StaticMetamodel(gUtilitarios.class)
public class gUtilitarios_ { 

    public static volatile SingularAttribute<gUtilitarios, String> Obs;
    public static volatile CollectionAttribute<gUtilitarios, gTipoutilitarios> tipoutilitario;
    public static volatile SingularAttribute<gUtilitarios, String> nomeUtilitario;
    public static volatile CollectionAttribute<gUtilitarios, gAplicacao> aplicacao;
    public static volatile SingularAttribute<gUtilitarios, Boolean> favorita;
    public static volatile SingularAttribute<gUtilitarios, Long> idUtilitario;

}