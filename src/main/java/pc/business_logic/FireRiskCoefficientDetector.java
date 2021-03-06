package pc.business_logic;

import pc.domain.RiskType;
import pc.domain.PolicySubObject;

import java.math.BigDecimal;
import java.util.List;

class FireRiskCoefficientDetector {

	private static final BigDecimal STANDARD_FIRE_RISK_COEFFICIENT = new BigDecimal("0.014");
	private static final BigDecimal OVERPRICED_FIRE_RISK_COEFFICIENT = new BigDecimal("0.024");
	private static final BigDecimal PRICE_BORDER = new BigDecimal("100.00");

	public BigDecimal detect(List<PolicySubObject> policySubObjects) {
		BigDecimal sumOfInsuredObjectsFromFire = policySubObjects.stream()
				.filter(subInsuranceObject -> subInsuranceObject.isInsuredFrom(RiskType.FIRE))
				.map(PolicySubObject::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		if (sumOfInsuredObjectsFromFire.compareTo(PRICE_BORDER) <= 0) {
			return STANDARD_FIRE_RISK_COEFFICIENT;
		} else {
			return OVERPRICED_FIRE_RISK_COEFFICIENT;
		}
	}

}