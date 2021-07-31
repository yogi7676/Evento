package extras;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitLength extends PlainDocument {

	private int limit;
	
	public LimitLength() {
		super();
	}

	public LimitLength(int limit) {
		super();
		this.limit=limit;
	}

	public void insertString(int offset,String str,AttributeSet attr) throws BadLocationException{
		if(str==null)return;
		
		if((getLength()+str.length()) <=limit) {
			super.insertString(offset, str, (javax.swing.text.AttributeSet) attr);
		}
	}
}
