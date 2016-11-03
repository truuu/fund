package fund.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


public class Sponsor {
	int id;

	String sponsorNo; //
	
	@Size(min=1,message="이름을 입력하세요!")
	String name; //
	
	
	@Size(min = 13, max = 13,message="정확히 입력해주시요!")
	String juminNo; //
	
	
	int sponsorType1ID; //
	
	
	int sponsorType2ID; //
	
	int churchID; //
	
	@NotNull(message="날짜를 선택하세요!")
    String signUpDate; //  Date -> String date시 에러남 400 파라미터타입문제 질문해야함
	
	String mobilePhone; //
	
	@Size(min=1,message="추천인을 입력하세요!")
	String recommender; //
	
	String recommenderRelation; //
	
	
	boolean mailReceiving; //
	
	
	int mailTo;
	
	String homeAddress;
	String homePhone; //
	
	
	@Email(message="형식에 맞게 입력해주세요!")
	String email; //
	
	@Null(message="회사를 입력하세요!")
	String company; //
	
	@Null(message="부서를 입력하세요!")
	String department; //
	
	@Null(message="직위를 입력하세요!")
	String position; //
	
	@Null(message="사무실번호를 입력하세요!")
	String officePhone;
	
	String etc; //
	String officeAddress;
	
	
	
	String address;
	String postCode;
	String sponsorType1;
	String sponsorType2;
	String church;
	
	@Null(message="모두 입력해주세요!")
	String homeRoadAddress;
	
	@Null(message="모두 입력해주세요!")
	String homeDetailAddress;
	
	@Null(message="모두 입력해주세요!")
	String homePostCode;
	
	@Null(message="모두 입력해주세요!")
	String officeRoadAddress;
	
	@Null(message="모두 입력해주세요!")
	String officeDetailAddress;
	
	@Null(message="모두 입력해주세요!")
	String officePostCode;
	
	
	int sort; // 구분용 회원입력 0 - 회원수정 1
	int sponsorCount; // 후원인구분2별 출연내역 회원수
	int castCount; // 후원인구분2별 출연내역 출연수
	//long sum;//후원인구분2별 출연내역 금액
	int sum;//후원인구분2별 출연내역 금액
	
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getSponsorCount() {
		return sponsorCount;
	}
	public void setSponsorCount(int sponsorCount) {
		this.sponsorCount = sponsorCount;
	}
	public int getCastCount() {
		return castCount;
	}
	public void setCastCount(int castCount) {
		this.castCount = castCount;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getHomeRoadAddress() {
		return homeRoadAddress;
	}
	public void setHomeRoadAddress(String homeRoadAddress) {
		this.homeRoadAddress = homeRoadAddress;
	}
	public String getHomeDetailAddress() {
		return homeDetailAddress;
	}
	public void setHomeDetailAddress(String homeDetailAddress) {
		this.homeDetailAddress = homeDetailAddress;
	}
	public String getHomePostCode() {
		return homePostCode;
	}
	public void setHomePostCode(String homePostCode) {
		this.homePostCode = homePostCode;
	}
	public String getOfficeRoadAddress() {
		return officeRoadAddress;
	}
	public void setOfficeRoadAddress(String officeRoadAddress) {
		this.officeRoadAddress = officeRoadAddress;
	}
	public String getOfficeDetailAddress() {
		return officeDetailAddress;
	}
	public void setOfficeDetailAddress(String officeDetailAddress) {
		this.officeDetailAddress = officeDetailAddress;
	}
	public String getOfficePostCode() {
		return officePostCode;
	}
	public void setOfficePostCode(String officePostCode) {
		this.officePostCode = officePostCode;
	}
	public String getSponsorType1() {
		return sponsorType1;
	}
	public void setSponsorType1(String sponsorType1) {
		this.sponsorType1 = sponsorType1;
	}
	public String getSponsorType2() {
		return sponsorType2;
	}
	public void setSponsorType2(String sponsorType2) {
		this.sponsorType2 = sponsorType2;
	}
	public String getChurch() {
		return church;
	}
	public void setChurch(String church) {
		this.church = church;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	//DM발송때 address postCode

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSponsorNo() {
		return sponsorNo;
	}
	public void setSponsorNo(String sponsorNo) {
		this.sponsorNo = sponsorNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJuminNo() {
		return juminNo;
	}
	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}
	public int getSponsorType1ID() {
		return sponsorType1ID;
	}
	public void setSponsorType1ID(int sponsorType1ID) {
		this.sponsorType1ID = sponsorType1ID;
	}
	public int getSponsorType2ID() {
		return sponsorType2ID;
	}
	public void setSponsorType2ID(int sponsorType2ID) {
		this.sponsorType2ID = sponsorType2ID;
	}
	public int getChurchID() {
		return churchID;
	}
	public void setChurchID(int churchID) {
		this.churchID = churchID;
	}
	public String getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getRecommender() {
		return recommender;
	}
	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}
	public String getRecommenderRelation() {
		return recommenderRelation;
	}
	public void setRecommenderRelation(String recommenderRelation) {
		this.recommenderRelation = recommenderRelation;
	}
	public boolean isMailReceiving() {
		return mailReceiving;
	}
	public void setMailReceiving(boolean mailReceiving) {
		this.mailReceiving = mailReceiving;
	}
	public int getMailTo() {
		return mailTo;
	}
	public void setMailTo(int mailTo) {
		this.mailTo = mailTo;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}

}