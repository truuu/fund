package fund.controller;
import java.io.*;
import java.util.Iterator;
import java.util.List;

import fund.dto.EB13_CommitmentDetail;

public class EB13CreateFile {
	public static void eb13CreateFile(String fileName, List<EB13_CommitmentDetail> eb13List) throws IOException{

		//Iterator<EB13_CommitmentDetail> iterator = eb13List.iterator();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
		bufferedWriter.write("�׽�Ʈ");

		if(bufferedWriter != null)
			System.out.println("���� ���������� ������");
		bufferedWriter.close();
		System.out.println(fileName);

	}
}
