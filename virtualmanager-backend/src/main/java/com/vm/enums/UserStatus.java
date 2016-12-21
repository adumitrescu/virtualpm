package com.vm.enums;

public enum UserStatus {
	
	INACTIVE(0),
	ACTIVE(1); 
	
	private int numVal;

	UserStatus(int numVal) {
        this.numVal = numVal;
    }
	
    public int getNumVal() {
        return numVal;
    }
}
