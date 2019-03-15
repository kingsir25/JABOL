package cobol.lang.words;

import java.util.ArrayList;
import java.util.List;

public 	class GroupItem extends CobolItem{

	int layNo;
	String name;
	private ArrayList<GroupItem> cis;


	public GroupItem(){}
	public GroupItem(int layNo,String name){
		this.layNo=layNo;
		this.name=name;

	}
	public GroupItem(GroupItem gi){
		this.layNo=gi.getLayNo();
		this.name=gi.getName();

	}

	@Override
	public byte[] getByteSpace() {
		// TODO Auto-generated method stub
		return super.getByteSpace();
	}
	@Override
	public void setByteSpace(String byteSpace) {
		// TODO Auto-generated method stub
		super.setByteSpace(byteSpace);
	}
	public int getLayNo() {
		return layNo;
	}
	public void setLayNo(Integer layNo) {
		this.layNo = layNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<GroupItem> getCis() {
		return cis;
	}
	public void setCis(ArrayList<GroupItem> cis) {
		this.cis = cis;
	}
}
