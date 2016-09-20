package fund.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fund.dto.EB13_CommitmentDetail;
import fund.dto.EB21_commitmentDetail;

public class CreateEB21File {
	public static void createEB21File(List<EB21_commitmentDetail> eb21List,String paymentDate) throws IOException{

		SimpleDateFormat yymmdd = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat mmdd = new SimpleDateFormat("MMdd");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/parkeunsun/Documents/EB21"+mmdd.format(paymentDate)+".txt"));
		String sponsorNo = "";
		String bankCode = "";
		String accountNo = "";
		String jumin = "";
		int dataCount = 0;
		int amountSum = 0;
		
		String h = "H000000009983010152EB21"+mmdd.format(paymentDate)+yymmdd.format(paymentDate);
		String h2 = "005459315922014245";
		String padded = String.format("%-112s",h2);
		bufferedWriter.write(h+padded);
		
		for(EB21_commitmentDetail i : eb21List){
			sponsorNo = i.getSponsorNo();
			//bankCode = i.getEtc1();
			accountNo = i.getAccountNo();
			jumin = i.getJumin();
			String r = "R000000019983010152"+yymmdd.format(paymentDate)+"1";
			sponsorNo = sponsorNo.replaceAll("-", "");
			String sponsor = String.format("%-20s",sponsorNo);
			String code = bankCode;
			String account = String.format("%-16s",accountNo);
			String birth = String.format("%-16s",jumin);
			String space = String.format("%-51s",birth);
			bufferedWriter.write(r+sponsor+code+account+space);
			
		}
		
		String t = "T999999999983010152EB13"+mmdd.format(paymentDate);
		String count = "00000001";//원래는 r갯수 세어야함..
		String alterclose = "000000000000000000000000";
		String space2 = String.format("%-77s", alterclose);
		bufferedWriter.write(t+count+count+space2);
		
		bufferedWriter.close();
	}
}