package fund.dto;

public class DonationPurpose {
    int id;
    int corporateId;
    int organizationId;
    String name;
    String gubun;

    String organizationName;
    String corporateName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(int corporateID) {
        this.corporateId = corporateID;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationID) {
        this.organizationId = organizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

}
