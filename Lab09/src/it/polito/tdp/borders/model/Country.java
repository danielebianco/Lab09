package it.polito.tdp.borders.model;

public class Country {
	
	private int ccode;
	private String StateAbb;
	private String StateNme;
	private int numConf;
	
	public Country(int ccode, String stateAbb, String stateNme) {
		this.ccode = ccode;
		this.StateAbb = stateAbb;
		this.StateNme = stateNme;
		this.numConf = 0;
	}

	public int getCcode() {
		return ccode;
	}

	public void setCcode(int ccode) {
		this.ccode = ccode;
	}

	public String getStateAbb() {
		return StateAbb;
	}

	public void setStateAbb(String stateAbb) {
		StateAbb = stateAbb;
	}

	public String getStateNme() {
		return StateNme;
	}

	public void setStateNme(String stateNme) {
		StateNme = stateNme;
	}
	
	public int getNumConf() {
		return numConf;
	}

	public void setNumConf(int numConf) {
		this.numConf = numConf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((StateAbb == null) ? 0 : StateAbb.hashCode());
		result = prime * result + ((StateNme == null) ? 0 : StateNme.hashCode());
		result = prime * result + ccode;
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
		Country other = (Country) obj;
		if (StateAbb == null) {
			if (other.StateAbb != null)
				return false;
		} else if (!StateAbb.equals(other.StateAbb))
			return false;
		if (StateNme == null) {
			if (other.StateNme != null)
				return false;
		} else if (!StateNme.equals(other.StateNme))
			return false;
		if (ccode != other.ccode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return StateAbb + " " + StateNme + " " + numConf;
	}
	
}
