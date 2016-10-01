package fund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.mapper.*;

@Service
public class ReceiptService {
	
	@Autowired
	PaymentMapper paymentMapper;
	
	public String validateBeforeInsert(String[] pid){
		int len=pid.length;
		int[] sid= new int[len];
		for(int i=0;i<pid.length;i++){
			sid[i]=paymentMapper.selectById(pid[i]);
			if(sid[0]!=sid[i]){
				return "하나의 후원인만 선택해주세요.";
			}
		}
		
		return null;
	}
	
}
