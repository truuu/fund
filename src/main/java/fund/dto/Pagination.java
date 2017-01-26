package fund.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Pagination {
    int currentPage = 1;
    int pageSize = 10;
    int order;
    int srchType;
    String srchText;
    int recordCount;
    String startDate;
    String endDate;








    int corporateId;
    int type;//소속교회, 회원구분 등 codeGroupID
    String codeName;// 개인, 단체, 교회 등 code 이름
    String nameForSearch;//후원인관리에서 이름으로 검색때 사용

	public String getNameForSearch() { return nameForSearch; 	}
	public void setNameForSearch(String nameForSearch) { this.nameForSearch = nameForSearch; }
    public int getType() { return type; }
    public void setType(int type) { this.type = type; }
    public String getCodeName() { return codeName; }
    public void setCodeName(String codeName) { this.codeName = codeName; }
    public int getCorporateId() { return corporateId; }
    public void setCorporateId(int corporateId) { this.corporateId = corporateId; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }
    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
    public int getSrchType() { return srchType; }
    public void setSrchType(int srchType) { this.srchType = srchType; }
    public String getSrchText() { return srchText; }
    public void setSrchText(String srchText) { this.srchText = srchText; }
    public int getRecordCount() { return recordCount; }
    public void setRecordCount(int recordCount) { this.recordCount = recordCount; }

    public void setPg(int currentPage) { this.currentPage = currentPage; }
    public void setSz(int pageSize) { this.pageSize = pageSize; }
    public void setOd(int order) { this.order = order; }
    public void setSs(int srchType) { this.srchType = srchType; }
    public void setSt(String srchText) { this.srchText = srchText; }
    public void setSd(String startDate) { this.startDate = startDate; }
    public void setEd(String endDate) { this.endDate = endDate; }
    public void setCp(int corporateId) { this.corporateId = corporateId; }

    public int getPg() { return currentPage; }
    public int getSz() { return pageSize; }
    public int getOd() { return order; }
    public int getSs() { return srchType; }
    public String getSt() { return srchText; }
    public String getSd() { return startDate; }
    public String getEd() { return endDate; }
    public int getCp() { return corporateId; }

    public String getQueryString() throws UnsupportedEncodingException {
        String temp = (srchText == null) ? "" : URLEncoder.encode(srchText, "UTF-8");
        return String.format("pg=%d&sz=%d&od=%d&ss=%d&st=%s&sd=%s&ed=%s&cp=%s",
                currentPage, pageSize, order, srchType, temp,
                startDate == null ? "" : startDate,
                endDate == null ? "" : endDate,
                corporateId);
    }

    public List<Page> getPageList() {
        ArrayList<Page> list = new ArrayList<Page>();
        int pageCount = (recordCount + pageSize - 1) / pageSize;
        int basePage = ((currentPage - 1) / 10) * 10;
        if (basePage > 0)
            list.add(new Page("Prev", basePage));
        for (int i = 1; i <= 10 && basePage + i <= pageCount; ++i)
            list.add(new Page(basePage + i, currentPage == basePage + i));
        if (basePage + 11 <= pageCount)
            list.add(new Page("Next", basePage + 11));
        return list;
    }

    public class Page {
        String label;
        int number;
        String cssClass;

        public Page(int number, boolean active) {
            this.label = "" + number;
            this.number = number;
            this.cssClass = active ? "active" : "";
        }

        public Page(String label, int number) {
            this.label = label;
            this.number = number;
            this.cssClass = "";
        }

        public Object getLabel() { return label; }
        public int getNumber() { return number; }
        public String getCssClass() { return cssClass; }
    }

}