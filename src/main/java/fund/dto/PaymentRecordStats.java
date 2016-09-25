package fund.dto;

public class PaymentRecordStats {
	String startDate;  // 납입일 시작
	String endDate;     // 납입일 끝
	Integer srchType1; // 정기/비정기
	Integer srchType2; // 기부목적
	Integer srchType3; // 소속교회
	Integer srchType4; // 납입방법
	Integer srchType5; // 후원인구분2
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getSrchType1() {
		return srchType1;
	}
	public void setSrchType1(Integer srchType1) {
		this.srchType1 = srchType1;
	}
	public Integer getSrchType2() {
		return srchType2;
	}
	public void setSrchType2(Integer srchType2) {
		this.srchType2 = srchType2;
	}
	public Integer getSrchType3() {
		return srchType3;
	}
	public void setSrchType3(Integer srchType3) {
		this.srchType3 = srchType3;
	}
	public Integer getSrchType4() {
		return srchType4;
	}
	public void setSrchType4(Integer srchType4) {
		this.srchType4 = srchType4;
	}
	public Integer getSrchType5() {
		return srchType5;
	}
	public void setSrchType5(Integer srchType5) {
		this.srchType5 = srchType5;
	}
	
	
	
	

}
