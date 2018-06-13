package entidade;

import entidade.gTipomenu;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(gMenus.class)
public class gMenus_ { 

    public static volatile SingularAttribute<gMenus, String> nomeMenu;
    public static volatile CollectionAttribute<gMenus, gTipomenu> tipomenu;
    public static volatile SingularAttribute<gMenus, Boolean> favorito;
    public static volatile SingularAttribute<gMenus, Long> idMenu;

}