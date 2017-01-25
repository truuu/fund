package fund.dto;

public class Code {
		int id;
		int codeGroupId;
		String codeName;
		String etc1;
		String etc2;
		String etc3;
		String codeGroupName;

		public String getName() {
			return codeGroupName;
		}
		public void setName(String codeGroupName) {
			this.codeGroupName = codeGroupName;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCodeGroupId() {
			return codeGroupId;
		}
		public void setCodeGroupId(int codeGroupId) {
			this.codeGroupId = codeGroupId;
		}
		public String getCodeName() {
			return codeName;
		}
		public void setCodeName(String codeName) {
			this.codeName = codeName;
		}
		public String getEtc1() {
			return etc1;
		}
		public void setEtc1(String etc1) {
			this.etc1 = etc1;
		}
		public String getEtc2() {
			return etc2;
		}
		public void setEtc2(String etc2) {
			this.etc2 = etc2;
		}
		public String getEtc3() {
			return etc3;
		}
		public void setEtc3(String etc3) {
			this.etc3 = etc3;
		}



}
