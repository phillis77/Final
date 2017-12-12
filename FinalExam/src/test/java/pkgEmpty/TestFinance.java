package pkgEmpty;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.Test;
public class TestFinance {

	
	@Test 
	public void TestAmounts()
	{
		double dMonthsToWork = 40 * 12;
		double rAnnualReturnWorking = 0.07 / 12;
		double dMonthsRetired = 20 * 12;
		double rAnnaulReturnRetired = 0.02 / 12;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		System.out.println("pv:"+rAnnaulReturnRetired+","+dMonthsRetired+","+(dRequiredIncome - dMonthlySSI)+","+",0,false");
		double pv = FinanceLib.pv(rAnnaulReturnRetired, dMonthsRetired, dRequiredIncome - dMonthlySSI, 0, false);
		System.out.println("pmt:"+rAnnualReturnWorking+","+dMonthsToWork+","+",0,"+pv+",false");
		double pmt = FinanceLib.pmt(rAnnualReturnWorking, dMonthsToWork, 0, pv, false);
		
		System.out.println(pv);
		System.out.println(pmt);
	}
	
	
	@Test
	public void TestPV()
	{
		double r = 0.025 / 12;
		double n = 20 * 12;
		double y = 10000-2642;
		double f = 0;
		boolean t = false;
		double pv = FinanceLib.pv(r, n, y, f, t);
		
		//System.out.println(pv);
		
	}
	
	
	@Test
	public void TestPMT() {
		double r = 0.042 / 12;
		double n = 60;
		double p = 30000;
		double f = 0;
		boolean t = false;
		
		double d = FinanceLib.pmt(r, n, p, f, t);
		
		//System.out.println(d);
		
		
		
	}

}
