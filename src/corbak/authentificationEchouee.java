package corbak;

/**
 * Exception definition : authentificationEchouee
 * 
 * @author OpenORB Compiler
 */
public final class authentificationEchouee extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Exception member id
     */
    public short id;

    /**
     * Default constructor
     */
    public authentificationEchouee()
    {
        super(authentificationEchoueeHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     * @param id id exception member
     */
    public authentificationEchouee(String raison, short id)
    {
        super(authentificationEchoueeHelper.id());
        this.raison = raison;
        this.id = id;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     * @param id id exception member
     */
    public authentificationEchouee(String orb_reason, String raison, short id)
    {
        super(authentificationEchoueeHelper.id() +" " +  orb_reason);
        this.raison = raison;
        this.id = id;
    }

}
