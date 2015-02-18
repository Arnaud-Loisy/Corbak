package corbak;

/**
 * Exception definition : droitsInsufisants
 * 
 * @author OpenORB Compiler
 */
public final class droitsInsufisants extends org.omg.CORBA.UserException
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
    public droitsInsufisants()
    {
        super(droitsInsufisantsHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     * @param id id exception member
     */
    public droitsInsufisants(String raison, short id)
    {
        super(droitsInsufisantsHelper.id());
        this.raison = raison;
        this.id = id;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     * @param id id exception member
     */
    public droitsInsufisants(String orb_reason, String raison, short id)
    {
        super(droitsInsufisantsHelper.id() +" " +  orb_reason);
        this.raison = raison;
        this.id = id;
    }

}
