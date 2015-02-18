package corbak;

/**
 * Struct definition : Date
 * 
 * @author OpenORB Compiler
*/
public final class Date implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member year
     */
    public short year;

    /**
     * Struct member month
     */
    public short month;

    /**
     * Struct member day
     */
    public short day;

    /**
     * Struct member hour
     */
    public short hour;

    /**
     * Struct member minute
     */
    public short minute;

    /**
     * Struct member seconde
     */
    public short seconde;

    /**
     * Default constructor
     */
    public Date()
    { }

    /**
     * Constructor with fields initialization
     * @param year year struct member
     * @param month month struct member
     * @param day day struct member
     * @param hour hour struct member
     * @param minute minute struct member
     * @param seconde seconde struct member
     */
    public Date(short year, short month, short day, short hour, short minute, short seconde)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.seconde = seconde;
    }

}
