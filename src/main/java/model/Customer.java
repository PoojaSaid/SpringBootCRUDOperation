package model;

public class Customer {
	
	private String custId= null;
	private String custName= null;
	private String custProfession= null;
	
	public Customer() {
		super();
	}
	public Customer(String custId, String custName, String custProfession) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custProfession = custProfession;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustProfession() {
		return custProfession;
	}
	public void setCustProfession(String custProfession) {
		this.custProfession = custProfession;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custProfession=" + custProfession + "]";
	}
	
	

}
