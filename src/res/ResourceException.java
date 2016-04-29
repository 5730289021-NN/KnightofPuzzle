package res;

public class ResourceException extends Exception {
	
	public static final int KEY_NOT_FOUND = 1;
	public static final int IMAGE_EXTENSION_INCORRECT = 2;
	public static final int IOException = 3;
	
	private int type;
	private String prefix;
	private String postfix;
	
	public ResourceException(int type) {
		this.type = type;
		this.prefix = this.postfix = "";
	}
	public ResourceException(int type, String prefix, String postfix) {
		this.type = type;
		this.postfix = postfix == null ? postfix : "";
		this.prefix = prefix == null ? prefix : "";
	}
	
	public String getMessage(){
		switch(type) {
		case KEY_NOT_FOUND : return prefix + " key not found. " + postfix;
		case IMAGE_EXTENSION_INCORRECT : return "Image extension incorrect";
		case IOException : return "IO Exception";
		}
		return "Error type not defined";
	}
}