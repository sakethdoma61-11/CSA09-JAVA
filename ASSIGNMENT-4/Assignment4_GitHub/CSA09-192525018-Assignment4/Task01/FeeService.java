public class FeeService {
    public String pay(String regNo, String amountText) {
        if (regNo == null || regNo.isBlank()) return "Enter registration number";
        final double amount;
        try { amount = Double.parseDouble(amountText.trim()); }
        catch (Exception e) { return "Enter a valid amount"; }
        if (amount <= 0) return "Amount must be positive";
        return String.format("Payment accepted for %s: %.2f", regNo, amount);
    }
}
