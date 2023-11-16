package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("CancelDTO")
public class CancelDTO {
	
	int cancel_id;
	int momo_id;
	String reason1;
	String reason2;
	
	public CancelDTO() {}

	public CancelDTO(int cancel_id, int momo_id, String reason1, String reason2) {
		this.cancel_id = cancel_id;
		this.momo_id = momo_id;
		this.reason1 = reason1;
		this.reason2 = reason2;
	}

	public int getCancel_id() {
		return cancel_id;
	}

	public void setCancel_id(int cancel_id) {
		this.cancel_id = cancel_id;
	}

	public int getMomo_id() {
		return momo_id;
	}

	public void setMomo_id(int momo_id) {
		this.momo_id = momo_id;
	}

	public String getReason1() {
		return reason1;
	}

	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}

	public String getReason2() {
		return reason2;
	}

	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}

	@Override
	public String toString() {
		return "CancelDTO [cancel_id=" + cancel_id + ", momo_id=" + momo_id + ", reason1=" + reason1 + ", reason2="
				+ reason2 + "]";
	}
	
}
