package pc.business_logic;

import org.junit.Test;
import pc.domain.*;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class PremiumCalculatorAcceptanceTest {

    @Test
    // Test 1 requested by ProofIT (one policy, one object and two sub-objects with standard Fire Risk Coefficient)
    // Should return premium = 2.28 EUR
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
    // Test 2 requested by ProofIT (one policy, one object and two sub-objects with overpriced Fire Risk Coefficient)
    // Should return premium = 17.13 EUR
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
    // Test 3 (one policy, one object and fore sub-objects with overpriced Fire Risk Coefficient)
    // Should return premium = 17.13 EUR
    public void acceptanceTest3() {
        PremiumCalculator premiumCalculator = new PremiumCalculator();

        PolicyObject house = new PolicyObject(PolicyObjectName.HOUSE);

        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED);
        policy.addPolicyObject(house);

        PolicySubObject mobilePhone1 = new PolicySubObject("Samsung", new BigDecimal("320.00"));
        PolicySubObject mobilePhone2 = new PolicySubObject("SONY", new BigDecimal("180.00"));
        PolicySubObject keyboard = new PolicySubObject("Logitech", new BigDecimal("72.51"));
        PolicySubObject mouse = new PolicySubObject("Logitech", new BigDecimal("30.00"));
        mobilePhone1.addPolicyRisk(RiskType.FIRE);
        mobilePhone2.addPolicyRisk(RiskType.FIRE);
        keyboard.addPolicyRisk(RiskType.THEFT);
        mouse.addPolicyRisk(RiskType.THEFT);

        house.addPolicySubObject(mobilePhone1);
        house.addPolicySubObject(mobilePhone2);
        house.addPolicySubObject(keyboard);
        house.addPolicySubObject(mouse);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(premium.compareTo(new BigDecimal("17.13")), 0);
    }

    @Test
    // Test 4 (one policy, two objects and two sub-objects for each object with overpriced Fire Risk Coefficient)
    // Should return premium = 17.13 EUR
    public void acceptanceTest4() {
        PremiumCalculator premiumCalculator = new PremiumCalculator();

        PolicyObject house = new PolicyObject(PolicyObjectName.HOUSE);
        PolicyObject apartment = new PolicyObject(PolicyObjectName.APARTMENT);

        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED);
        policy.addPolicyObject(house);
        policy.addPolicyObject(apartment);

        PolicySubObject mobilePhone1 = new PolicySubObject("Samsung", new BigDecimal("320.00"));
        PolicySubObject mobilePhone2 = new PolicySubObject("SONY", new BigDecimal("180.00"));
        PolicySubObject keyboard = new PolicySubObject("Logitech", new BigDecimal("72.51"));
        PolicySubObject mouse = new PolicySubObject("Logitech", new BigDecimal("30.00"));
        mobilePhone1.addPolicyRisk(RiskType.FIRE);
        mobilePhone2.addPolicyRisk(RiskType.FIRE);
        keyboard.addPolicyRisk(RiskType.THEFT);
        mouse.addPolicyRisk(RiskType.THEFT);

        house.addPolicySubObject(mobilePhone1);
        house.addPolicySubObject(mobilePhone2);
        apartment.addPolicySubObject(keyboard);
        apartment.addPolicySubObject(mouse);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(premium.compareTo(new BigDecimal("17.13")), 0);
    }

}