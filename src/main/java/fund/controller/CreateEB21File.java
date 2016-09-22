package fund.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fund.dto.EB21_commitmentDetail;

public class CreateEB21File {
	public static void createEB21File(List<EB21_commitmentDetail> eb21List,String paymentDate_old) throws IOException, ParseException{
		Date paymentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paymentDate_old);
		SimpleDateFormat yymmdd = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat mmdd = new SimpleDateFormat("MMdd");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/parkeunsun/Documents/EB21"+mmdd.format(paymentDate)+".txt"));
		String sponsorNo = "";
		String bankCode = "";
		String accountNo = "";
		String jumin = "";
		int amountPerMonth;
		int dataCount = 0;
		int amountSum = 0;
		
		String h = "H000000009983010152EB21"+mmdd.format(paymentDate)+yymmdd.format(paymentDate);
		String h2 = "005459315922014245";
		String padded = String.format("%-117s",h2);
		bufferedWriter.write(h+padded);
		
		for(EB21_commitmentDetail i : eb21List){
			dataCount++;
			sponsorNo = i.getSponsorNo();
			bankCode = i.getEtc1();
			accountNo = i.getAccountNo();
			jumin = i.getJumin();
			amountPerMonth = i.getAmountPerMonth();
			String r = "R"+ String.format("%08d",dataCount)+"9983010152";
			sponsorNo = sponsorNo.replaceAll("-", "");
			String sponsor = String.format("%-25s",sponsorNo);
			String code = bankCode;
			String account = String.format("%-16s",accountNo);
			String amount = String.format("%013d",amountPerMonth);
			String birth = String.format("%-13s",jumin);
			String space = String.format("%-18s",birth);
			String donationPurpose = "성공회대발전기금  ";
			String space2 = String.format("%-34s","1");
			bufferedWriter.write(r+code+account+amount+space+donationPurpose+sponsor+space2);

			amountSum = amountSum + amountPerMonth;
		}
		
		String t = "T999999999983010152EB21"+mmdd.format(paymentDate);
		String count = String.format("%08d",dataCount);
		String sum = String.format("%013d",amountSum);
		String alterclose = "000000000000000000000";
		String space2 = String.format("%-94s", alterclose);
		bufferedWriter.write(t+count+count+sum+space2);
		
		bufferedWriter.close();
	}
}