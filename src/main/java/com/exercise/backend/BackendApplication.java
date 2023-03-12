package com.exercise.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class BackendApplication {
	private static int paymentSnowball;
	private static int totalBalance;

	private static int startingAmountMonthly;

	private static int[] debtPayments;

	private static int[] debtBalances;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		inputPrompt();
		//int startingAmountMonthly = 100;
		//int[] debtPayments = {25, 35, 15, 10, 50};
		//int[] debtBalances = {100, 500, 200, 300, 400};
		List<Debt> debtList = new ArrayList<Debt>();

		int balanceIncrement = 0;
		for (int i : debtPayments) {
			Debt newDebt = new Debt(debtBalances[balanceIncrement], i);
			debtList.add(newDebt);
			balanceIncrement++;
		}

		Collections.sort(debtList, new SortByBalance());
		for(Debt d : debtList){
			System.out.println("Balance: "+ d.balance + " Payment: " + d.payment);
		}

		paymentSnowball = startingAmountMonthly;
		totalBalance = Arrays.stream(debtBalances).sum();
		int monthCount = 1;

		System.out.println("Starting total for month " + monthCount + " : " + totalBalance + " snowball start:" + paymentSnowball);
		monthCount++;

		while (totalBalance > 0 && debtList.size() != 0) {
			for (int i = 0; i < debtList.size(); i++) {
				if (debtList.get(i).balance < debtList.get(i).payment) {
					int remainingPayment = debtList.get(i).payment;
					recursivePaymentApplication(i, debtList, remainingPayment);
				}
				else{
					debtList.get(i).balance -= debtList.get(i).payment;
					totalBalance -= debtList.get(i).payment;
				}

			}
			//apply to lowest balance
			if (debtList.get(0).balance < paymentSnowball) {
				totalBalance -= debtList.get(0).balance;
				int remainingPayment = paymentSnowball - debtList.get(0).balance;
				paymentSnowball += debtList.get(0).payment;
				debtList.remove(0);
				recursivePaymentApplication(0, debtList, remainingPayment);
			} else {
				debtList.get(0).balance -= paymentSnowball;
				totalBalance-= paymentSnowball;
			}

			//totalBalance = debtList.stream().mapToInt(debt -> debt.balance).sum();
			System.out.println(monthCount + " " + totalBalance + " " + " " + paymentSnowball);
			monthCount++;
		}
		System.out.println("After " + (monthCount-1) + " months, you have freed up $ " + (paymentSnowball-startingAmountMonthly) + "!");
	}
	public static void recursivePaymentApplication(int index, List<Debt> debtList, int remainingPayment) {
		if (remainingPayment <= 0 || debtList.size()==0) {
			return;
		}

		debtList.get(index).balance -= remainingPayment;
		totalBalance -= remainingPayment;
		remainingPayment -= debtList.get(index).balance;

		if (debtList.get(index).balance <= 0) {
			paymentSnowball += debtList.get(index).payment;
			debtList.remove(index);
			//index--;

		}

		if(index+1 >debtList.size()){
			recursivePaymentApplication(0, debtList, remainingPayment);
		}
		else{
			//index++;
			index = (index + 1) % debtList.size();
			recursivePaymentApplication(index, debtList, remainingPayment);
		}
	}

	public static void inputPrompt(){
		Scanner in = new Scanner(System.in);

		//obtain balances from user
		System.out.println("Welcome to the Debt Snowball Simulation! To start, please enter debt balances and press n to quit");
		List<Integer> balances = new ArrayList<>();
		while(true){
			String line = in.nextLine();
			if("n".equalsIgnoreCase(line)){
				break;
			}
			balances.add(Integer.parseInt(line));
		}
		debtBalances = balances.stream().mapToInt(i -> i).toArray();

		//obtain payments from user
		int counter = balances.size();
		System.out.println("Now, please enter " +counter+  " minimum payments");
		List<Integer> payments = new ArrayList<>();
		while(payments.size()< counter){
			String line = in.nextLine();
			if("n".equalsIgnoreCase(line)){
				break;
			}
			payments.add(Integer.parseInt(line));
		}
		debtPayments = payments.stream().mapToInt(i -> i).toArray();

		//obtain starting amount monthly
		System.out.println("Finally, enter the dollar amount extra you can apply to your overall debt balance, /n" +
				"which will apply from the least to the greatest balances.  As balances are paid off, their respective payment amounts will be added to this to create the snowball effect./n" +
				" Zeroes are acceptable inputs.");
		startingAmountMonthly = Integer.parseInt(in.nextLine());
	}

}
