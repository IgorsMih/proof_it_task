package pc.demo;

import pc.business_logic.PremiumCalculator;
import pc.domain.*;

import java.math.BigDecimal;

public class PremiumCalculatorAcceptanceDemo {

	private final PremiumCalculator premiumCalculator = new PremiumCalculator();

	public static void main(String[] args) {
		PremiumCalculatorAcceptanceDemo test = new PremiumCalculatorAcceptanceDemo();
		test.testPolicyForOneInsuranceObject();
	}

	public void testPolicyForOneInsuranceObject() {
		PolicyObject house = new PolicyObject(PolicyObjectName.HOUSE);
		PolicyObject apartment = new PolicyObject(PolicyObjectName.APARTMENT);

		Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.APPROVED);
		policy.addPolicyObject(house);
		policy.addPolicyObject(apartment);

		PolicySubObject notebook = new PolicySubObject("Dell", new BigDecimal("500.00"));
		PolicySubObject tv = new PolicySubObject("Samsung", new BigDecimal("2500.00"));
		notebook.addPolicyRisk(RiskType.FIRE);
		tv.addPolicyRisk(RiskType.THEFT);

		PolicySubObject notebook2= new PolicySubObject("HP", new BigDecimal("1200.00"));
		PolicySubObject tv2 = new PolicySubObject("LG", new BigDecimal("750.00"));
		notebook2.addPolicyRisk(RiskType.FIRE);
		notebook2.addPolicyRisk(RiskType.THEFT);
		tv2.addPolicyRisk(RiskType.FIRE);
		tv2.addPolicyRisk(RiskType.THEFT);

		house.addPolicySubObject(notebook);
		house.addPolicySubObject(tv);
		apartment.addPolicySubObject(notebook2);
		apartment.addPolicySubObject(tv2);

		System.out.println("Policy number = " + policy.getPolicyNumber() + "\nPolicy status = " + policy.getPolicyStatus());

		for (PolicyObject policyObject : policy.getPolicyObjects()) {
			System.out.print("\n" + policyObject.getPolicyObjectName() + " - ");
			for (PolicySubObject policySubObject : policyObject.getPolicySubObjects()) {
				System.out.print(policySubObject.getSubObjectName() + " - " + policySubObject.getSumInsured() + " " +
						policySubObject.getPolicyRisks() + " ");
			}
		}

		BigDecimal premium = premiumCalculator.calculate(policy);
		System.out.println("\nPremium = " + premium);
	}

}
