package cobol.lang.words;

import java.util.ArrayList;

public class OccursGroupItem extends GroupItem {
	ArrayList<GroupItem> gis;

public ArrayList<GroupItem> getGis() {
		return gis;
	}
public OccursGroupItem(int layNo,String name,int ocr) {
	super(layNo,name);
	if (ocr >0) {
	this.gis = new ArrayList<GroupItem>(ocr);
	}else {
		this.gis = new ArrayList<GroupItem>();
	}
}
public OccursGroupItem(GroupItem gi,int ocr) {
	super(gi.getLayNo(),gi.getName());
	if (ocr >0) {
	this.gis = new ArrayList<GroupItem>(ocr);
	}else {
		this.gis = new ArrayList<GroupItem>();
	}
	for(int i=0;i<ocr;i++) {
		this.gis.add(new GroupItem(gi));
	}
}
public OccursGroupItem() {
	super();
	this.gis = new ArrayList<GroupItem>();
}

}
