package pc.tests;

import pc.business_logic.PremiumCalculator;
import pc.domain.*;

import java.math.BigDecimal;

public class PremiumCalculatorAcceptanceTest {

	private final PremiumCalculator premiumCalculator = new PremiumCalculator();

	public static void main(String[] args) {
		PremiumCalculatorAcceptanceTest test = new PremiumCalculatorAcceptanceTest();
		test.testPolicyForOneInsuranceObject();
	}

	public void testPolicyForOneInsuranceObject() {
		PolicyObject house1 = new PolicyObject(PolicyObjectName.HOUSE);
		PolicyObject apartment1 = new PolicyObject(PolicyObjectName.APARTMENT);

		Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.APPROVED);
		policy.addPolicyObject(house1);
		policy.addPolicyObject(apartment1);

		PolicySubObject notebook = new PolicySubObject("Dell", new BigDecimal("100.00"));
		PolicySubObject tv = new PolicySubObject("Samsung", new BigDecimal("8.00"));
		notebook.addPolicyRisk(RiskType.FIRE);
		tv.addPolicyRisk(RiskType.THEFT);

		house1.addPolicySubObject(notebook);
		house1.addPolicySubObject(tv);

		for (PolicyObject policyObject : policy.getPolicyObjects()) {
			System.out.println(policyObject.getPolicyObjectName());
			System.out.println(policyObject);
		}

		BigDecimal premium = premiumCalculator.calculate(policy);
		if (premium.compareTo(new BigDecimal("2.28")) == 0) {
			System.out.println("OK! " + premium);
		} else {
			System.out.println("FAIL! " + premium);
		}
	}

}
