package controllers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumericDocument extends PlainDocument {
        
	private static final long serialVersionUID = 1L;
	private int decimalPrecision;
	
	public NumericDocument(int decimalPrecision) {
		super();
		this.decimalPrecision = decimalPrecision;
	}
	
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str != null){
			if (!this.isNumeric(str)) {
				return;
			}
			if(super.getLength()>=decimalPrecision)
				return;
			if(super.getLength()==0&&rowneZero(str)){
				return;
			}
			
			super.insertString(offset, str, attr);
		}
		return;
	}
	
	private boolean isNumeric(String s) {
		return s.matches("\\d*\\.?\\d+");
	}
        
	private boolean rowneZero(String str){
		int liczba = Integer.parseInt(str);
		return liczba==0;
	}
}