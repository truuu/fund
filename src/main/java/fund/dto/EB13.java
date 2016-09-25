package fund.dto;

import java.util.Date;;

public class EB13 {

	int ID;
	Date createDate;
	byte[] file_body;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public byte[] getFile_body() {
		return file_body;
	}
	public void setFile_body(byte[] file_body) {
		this.file_body = file_body;
	}


}
