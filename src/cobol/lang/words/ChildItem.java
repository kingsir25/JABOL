package cobol.lang.words;

public class ChildItem extends GroupItem{
	String type;

	int size;

	String value;

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	ChildItem(){}
	public ChildItem(int layNo,String name,String type,int size,String value){
		super(layNo,name);
		this.type=type;
		this.size=size;
		this.value=value;
		this.length =size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
