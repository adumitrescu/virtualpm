package com.vm.enums;

public enum UserType {
	PROFESSIONAL(0),
	NORMAL(1);
	
	private int numVal;

	UserType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
//    
//    @Override
//    public String toString() {
//    	return ordinal()+"";
//    }
}
