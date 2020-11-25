
public class Money implements Expression {

	private int amount;
	private String currency;

	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public static Money dollar(int amount) {
		return new Money(amount, "USD");
	}
	
	public static Money franc(int amount) {
		return new Money(amount, "CHF");
	}

	public Expression times(int multiplier) {
		return new Money(amount * multiplier, currency());
	}
	
	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}
	
	public Money reduce(Bank bank, String to) {
		int rate = bank.rate(currency, to);
		return new Money(amount / rate, to);
	}
	
	public int amount() {
		return amount;
	}
	
	public String currency() {
		return currency;
	}

	public boolean equals(Object other) {
		Money money = (Money) other;
		return amount == money.amount &&
			currency().equals(money.currency());
	}

}
