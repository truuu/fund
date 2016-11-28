package fund.dto;
import java.util.Date;

public class EB21 {

	int ID;
	Date createDate;
	Date paymentDate;
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
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}




}
