package Dao.Jdbc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 *
 * @author lhries
 */
public class DateUtil {
    //withResolverStyle(STRICT): força a data e hora trabalharem corretamente (ex: mes pode ser só 1 a 12)

    /**
     *
     */
    public static DateTimeFormatter formatadorData = 
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    /**
     *
     */
    public static DateTimeFormatter formatadorHora = 
            DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);

    /**
     *
     */
    public static DateTimeFormatter formatadorDataHora = 
            DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);

    /**
     *
     * @param dataString
     * @return
     */
    public static LocalDate stringToDate(String dataString)
    {
        return(LocalDate.parse(dataString,formatadorData));
    }

    /**
     *
     * @param dataString
     * @return
     */
    public static LocalTime stringToTime(String dataString) 
    {
        return(LocalTime.parse(dataString,formatadorHora));
    }

    /**
     *
     * @param dataString
     * @return
     */
    public static LocalDateTime stringToDateTime(String dataString)
    {
        return(LocalDateTime.parse(dataString,formatadorDataHora));
    }
    
    /**
     *
     * @param data
     * @return
     */
    public static String dateToString(LocalDate data){
        return(data.format(formatadorData));
    }

    /**
     *
     * @param hora
     * @return
     */
    public static String timeToString(LocalTime hora){
        return(hora.format(formatadorHora));
    }

    /**
     *
     * @param datahora
     * @return
     */
    public static String dateTimeToString(LocalDateTime datahora){
        return(datahora.format(formatadorDataHora));
    }
     
}
