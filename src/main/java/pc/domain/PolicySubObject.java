package pc.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PolicySubObject {

	private String subObjectName;
	private BigDecimal sumInsured;
	private List<RiskType> riskTypes;

	public PolicySubObject(String name, BigDecimal sum) {
		this.subObjectName = name;
		this.sumInsured = sum;
		this.riskTypes = new ArrayList<>();
	}

	public void addPolicyRisk(RiskType riskType) {
		this.riskTypes.add(riskType);
	}

	public String getSubObjectName() {
		return subObjectName;
	}

	public BigDecimal getSumInsured() {
		return sumInsured;
	}

	public List<RiskType> getPolicyRisks() {
		return riskTypes;
	}

	public BigDecimal getPrice() {
		return sumInsured;
	}

	public boolean isInsuredFrom(RiskType riskType) {
		return this.getPolicyRisks().contains(riskType);
	}

}
