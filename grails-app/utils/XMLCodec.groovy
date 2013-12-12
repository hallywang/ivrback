import org.apache.commons.lang.StringEscapeUtils

class XMLCodec{
	static encode = { str ->
        if(!str)return "";
	    return StringEscapeUtils.escapeXml(str);
	  }

}