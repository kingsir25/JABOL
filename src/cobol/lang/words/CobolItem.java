package cobol.lang.words;

import java.util.ArrayList;

public class CobolItem {
protected CobolItem fatherItem;
//应该仅有集团项目有子项目
protected CobolItem firstChildItem;
protected CobolItem firstBrotherItem;
protected CobolItem rootItem;
private byte[] byteSpace;
protected int pos;
protected int length;
//occurs
protected int times =0;
//父级occurs项目列表
protected ArrayList<CobolItem> tables; 


@Override
public String toString() {
	// TODO Auto-generated method stub
//	String rs = "";
//	if(ownItem instanceof ChildItem) {
//		rs=((ChildItem) ownItem).getValue();
//	}else if (ownItem instanceof GroupItem) {
//		rs = "GroupItem";
//	}
	return new String(byteSpace);
}
public CobolItem() {}
public CobolItem(CobolItem rootItem) {
	this.rootItem = rootItem;
}

public boolean GREATER_THAN(CobolItem ci) {
	if (this.toString().compareTo(ci.toString())>0) {
		return true;
	}
	return false;
}
public boolean LESS_THAN(CobolItem ci) {
	if (this.toString().compareTo(ci.toString())<0) {
		return true;
	}
	return false;
}

@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	if (this.toString().compareTo(obj.toString())==0) {
		return true;
	}
	return false;
}
public CobolItem getRootItem() {
	return rootItem;
}
public void setRootItem(CobolItem rootItem) {
	this.rootItem = rootItem;
}
public int getPos() {
	return pos;
}
public void setPos(int pos) {
	this.pos = pos;
}
public int getLength() {
	return length;
}
public void setLength(int length) {
	this.length = length;
}
public byte[] getByteSpace() {
	byte[] rs = new byte[this.length];
	if(this == rootItem) {
		return byteSpace;
	}else {
		System.arraycopy(rootItem.getByteSpace(), this.pos, rs, 0, this.length);
	}
	return rs;
}
//occurs table 项目获取
public byte[] getByteSpace(int[] indexs) {
	//dimension
	int d = 0;
	int position = 0;
	if(this.tables.size()>0 && indexs.length>0 && this.tables.size() == indexs.length ) {
		for(int x:indexs) {
			if(x>0) {
				//起始
				if(d==0) {
				position += this.tables.get(d).getPos() + this.tables.get(d).getLength() * x;
				}else if(d>0 && d< this.tables.size()){
					//位差+循环长度
					position += this.tables.get(d).getPos() - this.tables.get(d-1).getPos()  + this.tables.get(d).getLength() * x;
				}
			}
			d++;
		}
		//当前不是occurs变量，非table项目位移差=当前变量的位置- 最后table项目的位置
		if(this.times ==0) {
			position += this.pos - this.tables.get(this.tables.size()-1).getPos();
		}
//		else
//			//当前是occurs变量
//			if(this.times>0) {
//			position +=1;
//		}
	}
	//需要添加特殊异常处理
	byte[] rs = new byte[this.length];
	if(this == rootItem) {
		return byteSpace;
	}else {
		System.arraycopy(rootItem.getByteSpace(), position, rs, 0, this.length);
	}
	return rs;
}
public void setByteSpace(String bs) {
	if(this == rootItem) {
		this.byteSpace = bs.getBytes();
	}else {
		byte[] rootBytes = this.rootItem.getByteSpace();
		System.arraycopy(bs.getBytes(), 0, rootBytes, this.pos, this.length);
		this.rootItem.setByteSpace(rootBytes);
	}
}

public void setByteSpace(byte[] byteSpace) {
	this.byteSpace = byteSpace;
}
public CobolItem getFatherItem() {
	return fatherItem;
}
public void setFatherItem(CobolItem fatherItem) {
	this.fatherItem = fatherItem;
}
public CobolItem getFirstChildItem() {
	return firstChildItem;
}
public void setFirstChildItem(CobolItem firstChildItem) {
	this.firstChildItem = firstChildItem;
}
public CobolItem getFirstBrotherItem() {
	return firstBrotherItem;
}
public void setFirstBrotherItem(CobolItem firstBrotherItem) {
	this.firstBrotherItem = firstBrotherItem;
}
public CobolItem getOwnItem() {
	return rootItem;
}
public void setOwnItem(CobolItem rootItem) {
	this.rootItem = rootItem;
}

public int getTimes() {
	return times;
}
public void setTimes(int times) {
	this.times = times;
}
public ArrayList<CobolItem> getTables() {
	return tables;
}
public void setTables(ArrayList<CobolItem> tables) {
	this.tables = tables;
}

}
