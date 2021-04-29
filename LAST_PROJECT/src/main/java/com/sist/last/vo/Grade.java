package com.sist.last.vo;

/**
 * 사용자 레벨: NEW, SILVER, GOLD, VIP
 * @author 오솔미
 *
 */
public enum Grade {
	//NEW(1),SILVER(2),GOLD(3),VIP(4)
	//현재값,nextGrade
	VIP(4,null),GOLD(3,VIP),SILVER(2,GOLD),NEW(1,SILVER);
	
	private final int value;       //현재값
	private final Grade nextGrade; //등업 시 nextGrade
	
	private Grade(int value, Grade nextGrade) {
		this.value = value;
		this.nextGrade = nextGrade;
	}

	public int getValue() {
		return value;
	}
	
	//다음 Grade 추출
	public Grade getNextGrade() {
		return nextGrade;
	}
	
	public static Grade valueOf(int value) {
		switch(value) {
		case 1: return NEW;
		case 2: return SILVER;
		case 3: return GOLD;
		case 4: return VIP;
		default: 
	     		throw new AssertionError("Unknown value:"+value);
		}
	}
	
	
}
