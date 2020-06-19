package pc.business_logic;

import pc.domain.Policy;
import pc.domain.PolicyObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PremiumCalculator {

    private final SubObjectsPremiumCalculator subObjectsPremiumCalculator
            = new SubObjectsPremiumCalculator();

    public BigDecimal calculate(Policy policy) {
        BigDecimal premium = BigDecimal.ZERO;
        for (PolicyObject policyObject : policy.getPolicyObjects()) {
            BigDecimal subObjectRiskPremium = subObjectsPremiumCalculator.calculate(policyObject);
            premium = premium.add(subObjectRiskPremium);
        }
        return premium.setScale(2, RoundingMode.HALF_UP);
    }

}
