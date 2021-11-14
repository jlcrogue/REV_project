package com.revature.banking.models;

public class accounts {
	
	private Integer num;
	private String type;
	private Double funds;
	private Integer login_id;
	private String status;
	
	public accounts() { super(); }
	
	public accounts(Integer num, String type, Double funds, Integer login_id, String status) {
		this.num = num;
		this.type = type;
		this.funds = funds;
		this.login_id = login_id;
		this.status = status;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getFunds() {
		return funds;
	}

	public void setFunds(Double funds) {
		this.funds = funds;
	}

	public Integer getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "accounts [num=" + num + ", type=" + type + ", funds=" + funds + ", login_id=" + login_id + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funds == null) ? 0 : funds.hashCode());
		result = prime * result + ((login_id == null) ? 0 : login_id.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		accounts other = (accounts) obj;
		if (funds == null) {
			if (other.funds != null)
				return false;
		} else if (!funds.equals(other.funds))
			return false;
		if (login_id == null) {
			if (other.login_id != null)
				return false;
		} else if (!login_id.equals(other.login_id))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	

}
