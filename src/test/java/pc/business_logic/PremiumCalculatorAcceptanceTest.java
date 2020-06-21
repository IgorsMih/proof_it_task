package pc.business_logic;

import org.junit.Test;
import pc.domain.*;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class PremiumCalculatorAcceptanceTest {

    @Test
    // Test 1 requested by ProofIT (one policy, one object and two sub-objects) should return premium = 2.28 EUR
    public void acceptanceTest1ProofIT() {
        PremiumCalculator premiumCalculator = new PremiumCalculator();

        PolicyObject house = new PolicyObject(PolicyObjectName.HOUSE);

        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED);
        policy.addPolicyObject(house);

        PolicySubObject mobilePhone = new PolicySubObject("Samsung", new BigDecimal("100.00"));
        PolicySubObject keyboard = new PolicySubObject("Logitech", new BigDecimal("8.00"));
        mobilePhone.addPolicyRisk(RiskType.FIRE);
        keyboard.addPolicyRisk(RiskType.THEFT);

        house.addPolicySubObject(mobilePhone);
        house.addPolicySubObject(keyboard);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(premium.compareTo(new BigDecimal("2.28")), 0);
    }

    @Test
    // Test 2 requested by ProofIT (one policy, one object and two sub-objects) should return premium = 17.13 EUR
    public void acceptanceTest2ProofIT() {
        PremiumCalculator premiumCalculator = new PremiumCalculator();

        PolicyObject house = new PolicyObject(PolicyObjectName.HOUSE);

        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED);
        policy.addPolicyObject(house);

        PolicySubObject notebook = new PolicySubObject("Dell", new BigDecimal("500.00"));
        PolicySubObject keyboard = new PolicySubObject("Logitech", new BigDecimal("102.51"));
        notebook.addPolicyRisk(RiskType.FIRE);
        keyboard.addPolicyRisk(RiskType.THEFT);

        house.addPolicySubObject(notebook);
        house.addPolicySubObject(keyboard);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(premium.compareTo(new BigDecimal("17.13")), 0);
    }

    @Test
    public void testPolicyForOneInsuranceObject() {
        PremiumCalculator premiumCalculator = new PremiumCalculator();

        PolicyObject house = new PolicyObject(PolicyObjectName.HOUSE);
        PolicyObject apartment = new PolicyObject(PolicyObjectName.APARTMENT);

        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.APPROVED);
        policy.addPolicyObject(house);
        policy.addPolicyObject(apartment);

        PolicySubObject notebook = new PolicySubObject("Dell", new BigDecimal("100.00"));
        PolicySubObject tv = new PolicySubObject("Samsung", new BigDecimal("8.00"));
        notebook.addPolicyRisk(RiskType.FIRE);
        tv.addPolicyRisk(RiskType.THEFT);

        house.addPolicySubObject(notebook);
        house.addPolicySubObject(tv);

        for (PolicyObject policyObject : policy.getPolicyObjects()) {
            System.out.print("\n" + policyObject.getPolicyObjectName() + " - ");
            for (PolicySubObject policySubObject : policyObject.getPolicySubObjects()) {
                System.out.print(policySubObject.getSubObjectName() + " - " + policySubObject.getPolicyRisks() + " ");
            }
        }

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(premium.compareTo(new BigDecimal("2.28")), 0);
    }



}