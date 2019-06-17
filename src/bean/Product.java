package bean;

public class Product {
	private String pkind;
	private String pcode;
	private String pname;
	private int pprice;
	private String pcontents;
	private String porifilename;
	private String psysfilename;
	private int pqty;
	
	public String getPkind() {
		return pkind;
	}
	public void setPkind(String pkind) {
		this.pkind = pkind;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public String getPcontents() {
		return pcontents;
	}
	public void setPcontents(String p_contents) {
		this.pcontents = p_contents;
	}
	public String getPorifilename() {
		return porifilename;
	}
	public void setPorifilename(String porifilename) {
		this.porifilename = porifilename;
	}
	public String getPsysfilename() {
		return psysfilename;
	}
	public void setPsysfilename(String psysfilename) {
		this.psysfilename = psysfilename;
	}
	public int getPqty() {
		return pqty;
	}
	public void setPqty(int pqty) {
		this.pqty = pqty;
	}
}
