package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<LineNumberItem>();
	
	private static class LineNumberItem{
		int startPC;
		int lineNum;
		public int getStartPC() {
			return startPC;
		}
		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}
		public int getLineNum() {
			return lineNum;
		}
		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
	}
	public void addLineNumberItem(LineNumberItem item){
		this.items.add(item);
	}
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
		
	}
	
	public static LineNumberTable parse(ByteCodeIterator iter){
		int attrNameIndex=iter.nextU2ToInt();
		int attrLen=iter.nextU4ToInt();
		int lineNumTableLen=iter.nextU2ToInt();
		LineNumberTable lineNumberTable=new LineNumberTable(attrNameIndex, attrLen);
		for(int i=0;i<lineNumTableLen;i++){
			LineNumberItem lineNumberItem=new LineNumberItem();
			int startPC=iter.nextU2ToInt();
			int lineNum=iter.nextU2ToInt();
			lineNumberItem.setStartPC(startPC);
			lineNumberItem.setLineNum(lineNum);
			lineNumberTable.addLineNumberItem(lineNumberItem);
		}
		return lineNumberTable;
	}
	
	

}
