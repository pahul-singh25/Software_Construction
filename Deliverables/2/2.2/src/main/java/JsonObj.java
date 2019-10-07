import netscape.javascript.JSObject;

public class JsonObj {

    private int IntVal;
    private String StringVal;
    private JSObject JsonVal;

    private boolean isString=false;
    private boolean isNumber=false;
    private boolean isJson=false;


    public JsonObj(int value)
    {
        isNumber=true;
        this.IntVal=value;
    }

    public JsonObj(String value)
    {
        isString=true;
        this.StringVal=value;
    }

    public JsonObj(JSObject value)
    {
        isJson=true;
        this.JsonVal =value;
    }

    public boolean isString()  {
        return isString;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public boolean isJson() {
        return isJson;
    }


    public int getIntVal() {
        return IntVal;
    }

    public String getStringVal() {
        return StringVal;
    }

    public JSObject getJsonVal() {
        return JsonVal;
    }







}
