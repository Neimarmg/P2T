package entidade;

import entidade.gFiliais;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(gPredio.class)
public class gPredio_ { 

    public static volatile SingularAttribute<gPredio, Integer> qtPavimentos;
    public static volatile SingularAttribute<gPredio, Boolean> ativo;
    public static volatile CollectionAttribute<gPredio, gFiliais> filial;
    public static volatile SingularAttribute<gPredio, Long> idPredio;
    public static volatile SingularAttribute<gPredio, String> nomePredio;

}