package entidade;

import entidade.gMatriz;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-01T08:32:32")
@StaticMetamodel(gFiliais.class)
public class gFiliais_ { 

    public static volatile CollectionAttribute<gFiliais, gMatriz> matrizs;
    public static volatile SingularAttribute<gFiliais, String> cnpj;
    public static volatile SingularAttribute<gFiliais, Long> idFilial;
    public static volatile SingularAttribute<gFiliais, String> descricao;

}