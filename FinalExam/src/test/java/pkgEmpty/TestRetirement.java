package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

public class TestRetirement {

	@Test
	public void test() {
		int dYearsToWork = 40;
		double rAnnualReturnWorking = 0.07;
		int dYearsRetired = 20;
		double rAnnaulReturnRetired = 0.02;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		Retirement r = new Retirement(dYearsToWork,rAnnualReturnWorking,dYearsRetired,rAnnaulReturnRetired,dRequiredIncome,dMonthlySSI);
		assertEquals(r.TotalAmountSaved(),-1454485.55,0.01);
		assertEquals(r.AmountToSave(r.TotalAmountSaved()),554.13,0.01);
	}

}
