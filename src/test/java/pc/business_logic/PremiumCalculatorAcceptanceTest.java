package pc.business_logic;

import org.junit.Test;
import pc.domain.*;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class PremiumCalculatorAcceptanceTest {

/*    private final PremiumCalculator premiumCalculator = new PremiumCalculator();

    public static void main(String[] args) {
        PremiumCalculatorAcceptanceTestStandard test = new PremiumCalculatorAcceptanceTestStandard();
        test.testPolicyForOneInsuranceObject();
    }
*/
    @Test
    public void testPolicyForOneInsuranceObject() {
        PremiumCalculator premiumCalculator = new PremiumCalculator();

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
        assertEquals(premium.compareTo(new BigDecimal("2.28")), 0);
/*
        if (premium.compareTo(new BigDecimal("2.28")) == 0) {
            System.out.println("OK! " + premium);
        } else {
            System.out.println("FAIL! " + premium);
        }
*/
    }

}