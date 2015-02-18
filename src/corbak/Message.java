package corbak;

/**
 * Struct definition : Message
 * 
 * @author OpenORB Compiler
*/
public final class Message implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member sign
     */
    public corbak.Signature sign;

    /**
     * Struct member text
     */
    public String text;

    /**
     * Struct member estChiffre
     */
    public boolean estChiffre;

    /**
     * Default constructor
     */
    public Message()
    { }

    /**
     * Constructor with fields initialization
     * @param sign sign struct member
     * @param text text struct member
     * @param estChiffre estChiffre struct member
     */
    public Message(corbak.Signature sign, String text, boolean estChiffre)
    {
        this.sign = sign;
        this.text = text;
        this.estChiffre = estChiffre;
    }

}
