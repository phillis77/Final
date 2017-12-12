package pkgCore;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Retirement {

    private int iYearsToWork;
    private double dAnnualReturnWorking;
    private int iYearsRetired;
    private double dAnnualReturnRetired;
    private double dRequiredIncome;
    private double dMonthlySSI;

    public Retirement(int iYearsToWork, double dAnnualReturnWorking, int iYearsRetired, double dAnnualReturnRetired, double dRequiredIncome, double dMonthlySSI) {
        this.iYearsToWork = iYearsToWork;
        this.dAnnualReturnWorking = dAnnualReturnWorking;
        this.iYearsRetired = iYearsRetired;
        this.dAnnualReturnRetired = dAnnualReturnRetired;
        this.dRequiredIncome = dRequiredIncome;
        this.dMonthlySSI = dMonthlySSI;
    }
   

    public double AmountToSave(double totalAmountSave) {
        double amountToSave = FinanceLib.pmt(dAnnualReturnWorking / 12, iYearsToWork * 12, 0, totalAmountSave, false);
        return amountToSave;
    }

    public double TotalAmountSaved() {
        double totalAmountSave = FinanceLib.pv(dAnnualReturnRetired / 12, iYearsRetired * 12, dRequiredIncome - dMonthlySSI, 0, false);
        return totalAmountSave;
    }
}
