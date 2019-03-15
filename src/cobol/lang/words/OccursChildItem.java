package cobol.lang.words;

import java.util.ArrayList;

public class OccursChildItem extends ChildItem {
	ArrayList<ChildItem> cis;
	int[] dimension = {0};

public OccursChildItem(int ocr) {
	super();
	this.dimension[dimension.length-1] = ocr;
	if (ocr >0) {
	this.cis = new ArrayList<ChildItem>(ocr);
	}else {
		this.cis = new ArrayList<ChildItem>();
	}
}
public OccursChildItem() {
	super();
	this.cis = new ArrayList<ChildItem>();
}
public ChildItem get(int index) {
	return this.cis.get(index);
}

}
