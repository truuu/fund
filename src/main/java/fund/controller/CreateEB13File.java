package fund.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fund.dto.EB13_CommitmentDetail;

public class CreateEB13File {
	public static void createEB13File(List<EB13_CommitmentDetail> eb13List) throws IOException{
		Date today = new Date();
		System.out.println(today);
		SimpleDateFormat yymmdd = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat mmdd = new SimpleDateFormat("MMdd");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/parkeunsun/Documents/EB13"+mmdd.format(today)));
		String sponsorNo = "";
		String bankCode = "";
		String accountNo = "";
		String jumin = "";
		int dataCount = 0;
		
		String h = "H000000009983010152EB13"+mmdd.format(today)+yymmdd.format(today);
		String padded = String.format("%-120s",h);
		bufferedWriter.write(padded);
		
		for(EB13_CommitmentDetail i : eb13List){
			sponsorNo = i.getSponsorNo();
			bankCode = i.getEtc1();
			accountNo = i.getAccountNo();
			jumin = i.getJumin();
			String r = "R000000019983010152"+yymmdd.format(today)+"1";
			sponsorNo = sponsorNo.replaceAll("-", "");
			String sponsor = String.format("%-20s",sponsorNo);
			String code = bankCode;
			String account = String.format("%-16s",accountNo);
			String birth = String.format("%-16s",jumin);
			String space = String.format("%-51s",birth);
			bufferedWriter.write(r+sponsor+code+account+space);
			dataCount++;
		}
		
		String t = "T999999999983010152EB13"+mmdd.format(today);
		String count = String.format("%08d",dataCount);
		String alterclose = "000000000000000000000000";
		String space2 = String.format("%-77s", alterclose);
		bufferedWriter.write(t+count+count+space2);
		
		bufferedWriter.close();
	}

}
