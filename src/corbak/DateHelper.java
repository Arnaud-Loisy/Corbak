package corbak;

/** 
 * Helper class for : Date
 *  
 * @author OpenORB Compiler
 */ 
public class DateHelper
{
    private static final boolean HAS_OPENORB;
    static {
        boolean hasOpenORB = false;
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.openorb.CORBA.Any");
            hasOpenORB = true;
        }
        catch(ClassNotFoundException ex) {
        }
        HAS_OPENORB = hasOpenORB;
    }
    /**
     * Insert Date into an any
     * @param a an any
     * @param t Date value
     */
    public static void insert(org.omg.CORBA.Any a, corbak.Date t)
    {
        a.insert_Streamable(new corbak.DateHolder(t));
    }

    /**
     * Extract Date from an any
     * @param a an any
     * @return the extracted Date value
     */
    public static corbak.Date extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof corbak.DateHolder)
                    return ((corbak.DateHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            corbak.DateHolder h = new corbak.DateHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private static boolean _working = false;

    /**
     * Return the Date TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            synchronized(org.omg.CORBA.TypeCode.class) {
                if (_tc != null)
                    return _tc;
                if (_working)
                    return org.omg.CORBA.ORB.init().create_recursive_tc(id());
                _working = true;
                org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[6];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "year";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "month";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "day";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "hour";
                _members[3].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[4] = new org.omg.CORBA.StructMember();
                _members[4].name = "minute";
                _members[4].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[5] = new org.omg.CORBA.StructMember();
                _members[5].name = "seconde";
                _members[5].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _tc = orb.create_struct_tc(id(),"Date",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the Date IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:corbak/Date:1.0";

    /**
     * Read Date from a marshalled stream
     * @param istream the input stream
     * @return the readed Date value
     */
    public static corbak.Date read(org.omg.CORBA.portable.InputStream istream)
    {
        corbak.Date new_one = new corbak.Date();

        new_one.year = istream.read_short();
        new_one.month = istream.read_short();
        new_one.day = istream.read_short();
        new_one.hour = istream.read_short();
        new_one.minute = istream.read_short();
        new_one.seconde = istream.read_short();

        return new_one;
    }

    /**
     * Write Date into a marshalled stream
     * @param ostream the output stream
     * @param value Date value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, corbak.Date value)
    {
        ostream.write_short(value.year);
        ostream.write_short(value.month);
        ostream.write_short(value.day);
        ostream.write_short(value.hour);
        ostream.write_short(value.minute);
        ostream.write_short(value.seconde);
    }

}
