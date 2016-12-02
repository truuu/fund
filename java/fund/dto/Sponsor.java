package fund.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import fund.service.AES128UtilService;


public class Sponsor {
   //AES128UtilService cipherService=new AES128UtilService(); //����� ��ȣȭ ����
   int id;

   String sponsorNo; //
   
   @Size(min=1,message="�̸��� �Է��ϼ���!")
   String name; //
   String juminNo; //
    int sponsorType1ID; //
   int sponsorType2ID; //
    int churchID; //
   
   @NotEmpty(message="��¥�� �Է����ּ���")
    String signUpDate; //  Date -> String date�� ������ 400 �Ķ����Ÿ�Թ��� �����ؾ���
   
   String mobilePhone; //
   
   String recommender; //
   
   String recommenderRelation; //
   
   
   boolean mailReceiving; //
   
   
   int mailTo;
   
   String homeAddress;
   String homePhone; //

   String email; //

   String company; //

   String department; //

   String position; //

   String officePhone;
   
   String etc; //
   String officeAddress;
   
   
   
   String address;
   String postCode;
   String sponsorType1;
   String sponsorType2;
   String church;
   

   String homeRoadAddress;
   

   String homeDetailAddress;

   String homePostCode;

   String officeRoadAddress;

   String officeDetailAddress;

   String officePostCode;
   
   
   int sort; // ���п� ȸ���Է� 0 - ȸ������ 1
   int sponsorCount; // �Ŀ��α���2�� �⿬���� ȸ����
   int castCount; // �Ŀ��α���2�� �⿬���� �⿬��
   //long sum;//�Ŀ��α���2�� �⿬���� �ݾ�
   int sum;//�Ŀ��α���2�� �⿬���� �ݾ�
   
   double persent;//�Ŀ��κ��� %��
   
   
   public double getPersent() {
      return persent;
   }
   public void setPersent(double persent) {
      this.persent = persent;
   }
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
   //DM�߼۶� address postCode

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