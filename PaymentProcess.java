package edu.mtsu.csci3033;
import java.util.Scanner;

/*
 * CSCI3033 Fall18 Project 1
 * Brandon Hunter
 * 
 * Individual Project:
 *   Build and process payment for transaction amount and optional donation
 *   
 *   Once all inputs are received, process transaction through CreditCard class
 */
public class PaymentProcess {
	private static final boolean False = false;
	private static final boolean True = false;

	public static void main(String[] args) {
		// Passing true to Customer class populates customer info with test data
		Customer customer = new Customer(true);
		double amount = 105.78;
		double donation = 0;
		

		
		//YOUR CODE GOES BELOW THIS LINE
		CreditCard card = new CreditCard(customer, amount, donation);
		Scanner input = new Scanner(System.in);
		int size;
		String cName;
		String cNum;
		String ccVV;
		String cDate;
		String cZip;
		String type = null;
		char choice;
		String a1;
		String a2;
		String a3;
		String a4;
		boolean confirm = False;
		
		//Customer ID
		if(customer.custID.isEmpty()) {
			customer.custID = "";
			for(int i = 0; i <= 5; i++){
				int rand = (int)(Math.random() * 10);
				customer.custID = customer.custID + rand;
			}
		}
		//First and last name MUST be atleast >= 3 characters long
		//Card holder name
		System.out.println("Please enter name on the card: ");
		
		//First name
		System.out.print("First full name: ");
		customer.firstName = input.next();
		size = customer.firstName.length();
		while(size < 3){
			System.out.print("Invalid first name. Please enter your full first name: ");
			customer.firstName = input.next();
			size = customer.firstName.length();
		}
		
		//Last name
		System.out.print("Last full name: ");
		customer.lastName = input.next();
		size = customer.lastName.length();
		while(size < 3){
			System.out.print("Invalid first name. Please enter your full last name: ");
			customer.lastName = input.next();
			size = customer.lastName.length();
		}
		cName = customer.firstName + " " + customer.lastName;
		card.setNameOnCard(cName);

		//Credit Card Number
		System.out.print("Please enter the credit card number: ");
		cNum = input.next();
		//Checks if card number is a valid number
		while(!cNum.matches("[0-9]+") || cNum.length() < 13 || cNum.length() > 19){
			System.out.println("Invalid card number. Try again.");
			cNum = input.next();
		}
		//Checks valid card number if it is either a Visa or a Master Card
		while(!cNum.substring(0,1).equals("4") && !cNum.substring(0,1).equals("5")){
			System.out.println("Card not accepted. Visa and Master Card ONLY. Please try again");
			cNum = input.next();
			//Re-checks for valid card number 
			while(!cNum.matches("[0-9]+") || cNum.length() < 13 || cNum.length() > 19){
				System.out.println("Invalid card number. Try again.");
				cNum = input.next();
			}
		}
		//Determines if card type is a Visa or Master
		if(cNum.substring(0,1).equals("4"))
			type = "Visa";
		else if(cNum.substring(0,1).equals("5"))
			type = "MC";
		card.setCardNumber(cNum);
		
		//C V V Code
		System.out.print("CVV number: ");
		ccVV = input.next();
		//Checks for valid CVV code
		while(!ccVV.matches("[0-9]+") || ccVV.length() < 3 || ccVV.length() > 5){
			System.out.println("Invalid CVV code. Please try again.");
			ccVV = input.next();
		}
		card.setCVV(ccVV);
		
		//Expiration Date 
		System.out.print("Expiration date: ");
		cDate = input.next();
		//Checks format requirements are met
		while(!cDate.matches("[0-9/]+") || cDate.length() != 5 || !Character.isDigit(cDate.charAt(0)) || 
				!Character.isDigit(cDate.charAt(1)) || cDate.charAt(2) != '/' || !Character.isDigit(cDate.charAt(3)) || 
				!Character.isDigit(cDate.charAt(4))) {
			System.out.println("Invalid expiration date.Please try again(MM/YY)");
			cDate = input.next();
		}
		//Checks expireation date for valid month
		while((!cDate.substring(0,1).equals("1") && !cDate.substring(0,1).equals("0"))  || (cDate.substring(0,1).equals("1") && !cDate.substring(1,2).equals("0") && !cDate.substring(1,2).equals("1") && !cDate.substring(1,2).equals("2"))) {
			System.out.println("Invalid month Please try again.");
			cDate = input.next();
			//Re-checks format requirements are met 
			while(!cDate.matches("[0-9/]+") || cDate.length() != 5 || !Character.isDigit(cDate.charAt(0)) || 
					!Character.isDigit(cDate.charAt(1)) || cDate.charAt(2) != '/' || !Character.isDigit(cDate.charAt(3)) || 
					!Character.isDigit(cDate.charAt(4))) {
				System.out.println("Invalid expiration date.Please try again(MM/YY)");
				cDate = input.next();
			}
		}
		
		card.setCardExpiration(cDate);
		//Shipping and Billing Address
		
		//Shipping 
		System.out.print("Shipping address: ");
		a1 = input.nextLine();
		a1 = input.nextLine();
		customer.address = a1;
		System.out.print("City: ");
		customer.city = input.nextLine();
		System.out.print("State: ");
		customer.ST = input.next();
		System.out.print("Zip Code: ");
		cZip = input.next();
		//Checks for a valid Zip-code
		while(!cZip.matches("[0-9]+") || cZip.length() != 5 ) {
			System.out.println("Invalid zip-code. Please try again.");
			cZip = input.next();
		}
		customer.zipCode = cZip;
		System.out.print("Make billing address the same as shipping address? (Y/N)");
		choice = input.next().charAt(0);
		//If letter is lower case convert to upper
		if(choice >= 90 && choice <= 122)
			choice = (char) (choice - 32);
		if(choice == 89)// "Y"
			card.setCardBillingAddress(customer.address, customer.city, customer.ST, customer.zipCode);
		else if(choice == 78) {//If "N" Get billing addresss
			System.out.print("Billing Address: ");
			a1 = input.nextLine();
			a1 = input.nextLine();
			System.out.print("City: ");
			a2 = input.nextLine();
			System.out.print("State: ");
			a3 = input.next();
			System.out.print("Zip Code: ");
			a4 = input.next();
			while(!a4.matches("[0-9]+") || a4.length() != 5 ) {
				System.out.println("Invalid zip-code. Please try again.");
				a4 = input.next();
			}
			card.setCardBillingAddress(a1, a2, a3, a4);
		}
		//Choice is NOT Y or N and must re-enter
		while(choice != 89 && choice != 78){
			System.out.print("Invalid answer. Please select Y or N: ");
			choice = input.next().charAt(0);
			//If letter is lower case convert to upper
			if(choice <= 122 && choice >= 90)
				choice = (char) (choice - 32);
			if(choice == 89 )
				card.setCardBillingAddress(customer.address, customer.city, customer.ST, customer.zipCode);
		}
		//Donation
		System.out.print("Would you like to make a donation? (Y/N): ");
		choice = input.next().charAt(0);
		//Converts lower to cap
		if(choice >= 90 && choice <=122)
			choice = (char) (choice - 32);
		//Choice is NOT Y or N and must re-enter
		while(choice != 89 && choice != 78){
			System.out.print("Invalid answer. Please select Y or N: ");
			choice = input.next().charAt(0);
			//If letter is lower convert to upper
			if(choice >= 90 && choice <=122)
				choice = (char) (choice - 32);
		}
		if(choice == 89) {
			System.out.print("Would you like to donate to a specific charity? (Y/N)");
			choice = input.next().charAt(0);
			//If letter is lower case convert to upper
			if(choice >= 90 && choice <= 122)
				choice = (char) (choice - 32);
			//Choice is NOT Y or N and must re-enter
			while(choice != 89 && choice != 78){
				System.out.print("Invalid answer. Please select Y or N: ");
				choice = input.next().charAt(0);
				//If letter is lower convert to upper
				if(choice >= 90 && choice <=122)
					choice = (char) (choice - 32);
			}
			if(choice == 89) {
				System.out.print("What charity would you like to donate to? ");
				customer.charity = input.nextLine();
				customer.charity = input.nextLine();
			}
			else if(choice == 78)
				customer.charity = "";
			System.out.print("Would you like to donate $1, $5 or other(o): $");
			choice = input.next().charAt(0);
			while(choice != 49 && choice != 53 && choice != 79 && choice != 111 ) {
				System.out.print("Invalid answer. Please choose from the following: $1, $5, or other(o): $");
				choice = input.next().charAt(0);
			}
			if( choice == 49)
				donation =+ 1;
			else if(choice == 53)
				donation =+ 5;
			else if(choice == 79 || choice == 111) {
				System.out.print("Please enter the amount: $");
				donation = input.nextDouble();
			}
		}
		//Summary
		System.out.print("\n");
		
		//Card info
		System.out.println("Name of card holder: " + card.getCustomerFirstName() + " " + card.getCustomerLastName());
		System.out.println("Card number: xxxxxxxxxxxx" + card.getCardNumber() + " " + type);
		System.out.println("Expiration Date: " + card.getCardExpiration() + "\n");
		System.out.println("Shipping Address: " );
		System.out.println(customer.address + "," + customer.city + "," + customer.ST + "," + customer.zipCode + "\n");
		System.out.println("Billing Address: " );
		System.out.println(card.getCardBillingAddress() + "\n");
		System.out.println("Purchase amount: $" + amount);
		if(!customer.charity.isEmpty())
			System.out.println("Donation amount to " + customer.charity + ": $" + donation);
		else
			System.out.println("Donation amount: $" + donation);
		card.setTransactionAmount(amount, donation);
		System.out.println("Total amount: $" + card.getTransactionAmount());
		System.out.print("Continue(1) Edit(2) or Cancel(3): ");
		choice = input.next().charAt(0);
		//Choice Check//
		while(choice != 49 && choice != 50 && choice != 51) {
			System.out.println("Invalid option. Please try again");
			System.out.print("Continue(1) Edit(2) or Cancel(3): \n");
			choice = input.next().charAt(0);
		}
		if(choice == 49) {//Continue (No changes or cancel)
			confirm = True;
			//Transaction Process
			System.out.println(card.processTransaction());
			System.out.println(card.getTransactionDate());
			System.out.println("Item(s) total: $" + amount);
			if(!customer.charity.isEmpty())
				System.out.println("Donation to " + customer.charity + ": $" + donation);
			else
				System.out.println("Donation: $" + donation);
			System.out.println("Total amount: $" + card.getTransactionAmount() + "\n");
			System.out.println(card.getNameOnCard());
			System.out.println("Card number: xxxxxxxxxxxx" + card.getCardNumber() + " " + type);
			System.out.println("Shipping Address: " );
			System.out.println(customer.address + "," + customer.city + "," + customer.ST + "," + customer.zipCode + "\n");
			System.out.println("Billing Address: " );
			System.out.println(card.getCardBillingAddress() + "\n");
			System.out.println("Thank you for shopping with us!");
			input.close();
			System.exit(0);
		}//End of "if" from Confirm choice
		while( !confirm) { //Edit or Cancel before Confirming
			if(choice == 50) {//Edit
				System.out.println("What would you like to edit?: ");
				System.out.println("(0)Name");//Name
				System.out.println("(1)Card number");//Card Information
				System.out.println("(2)Shipping address");//Shipping Address
				System.out.println("(3)Billing address");//Billing Address
				System.out.println("(4)Donation");//Donation
				choice = input.next().charAt(0);
				//Put Checks on the switch cases
				switch(choice) {
				case 48: //Name
					//First and last name MUST be atleast >= 3 characters long
					//Card holder name
					System.out.println("Please enter name on the card: ");
					
					//First name
					System.out.print("First full name: ");
					customer.firstName = input.next();
					size = customer.firstName.length();
					while(size < 3){
						System.out.print("Invalid first name. Please enter your full first name: ");
						customer.firstName = input.next();
						size = customer.firstName.length();
					}
					
					//Last name
					System.out.print("Last full name: ");
					customer.lastName = input.next();
					size = customer.lastName.length();
					while(size < 3){
						System.out.print("Invalid first name. Please enter your full last name: ");
						customer.lastName = input.next();
						size = customer.lastName.length();
					}
					cName = customer.firstName + " " + customer.lastName;
					card.setNameOnCard(cName);
					break;
					
				case 49: //Card Information
					System.out.print("Please enter the credit card number: ");
					cNum = input.next();
					//Checks if Card Number is a vailid number//
					while(!cNum.matches("[0-9]+") || cNum.length() < 13 || cNum.length() > 19){
						System.out.println("Invalid card number. Try again.");
						cNum = input.next();
					}
					//Checks if card if it is a Master Card or a Visa//
					while(!cNum.substring(0,1).equals("4") && !cNum.substring(0,1).equals("5")){
						System.out.println("Card not accepted. Visa and Master Card ONLY. Please try again");
						cNum = input.next();
						while(!cNum.matches("[0-9]+") || cNum.length() < 13 || cNum.length() > 19){
							System.out.println("Invalid card number. Try again.");
							cNum = input.next();
						}
					}
					//Checks which type of card: Visa or Master
					if(cNum.substring(0,1).equals("4"))
						type = "Visa";
					else if(cNum.substring(0,1).equals("5"))
						type = "MC";
					card.setCardNumber(cNum);
					
					//C V V Code
					System.out.print("CVV number: ");
					ccVV = input.next();
					while(!ccVV.matches("[0-9]+") || ccVV.length() < 3 || ccVV.length() > 5){
						System.out.println("Invalid CVV code. Please try again.");
						ccVV = input.next();
					}
					card.setCVV(ccVV);
					
					//Expiration Date 
					System.out.print("Expiration date: ");
					cDate = input.next();
					while(!cDate.matches("[0-9/]+") || cDate.length() != 5 || !Character.isDigit(cDate.charAt(0)) || 
							!Character.isDigit(cDate.charAt(1)) || cDate.charAt(2) != '/' || !Character.isDigit(cDate.charAt(3)) || 
							!Character.isDigit(cDate.charAt(4))) {
						System.out.println("Invalid expiration date.Please try again(MM/YY)");
						cDate = input.next();
					}
					while((!cDate.substring(0,1).equals("1") && !cDate.substring(0,1).equals("0"))  || (cDate.substring(0,1).equals("1") && !cDate.substring(1,2).equals("0") && !cDate.substring(1,2).equals("1") && !cDate.substring(1,2).equals("2"))) {
						System.out.println("Invalid month Please try again.");
						cDate = input.next();
						while(!cDate.matches("[0-9/]+") || cDate.length() != 5 || !Character.isDigit(cDate.charAt(0)) || 
								!Character.isDigit(cDate.charAt(1)) || cDate.charAt(2) != '/' || !Character.isDigit(cDate.charAt(3)) || 
								!Character.isDigit(cDate.charAt(4))) {
							System.out.println("Invalid expiration date.Please try again(MM/YY)");
							cDate = input.next();
						}
					}
					
					card.setCardExpiration(cDate);
					break;
					
				case 50://Shipping Address
					System.out.print("Shipping address: ");
					a1 = input.nextLine();
					a1 = input.nextLine();
					customer.address = a1;
					System.out.print("City: ");
					customer.city = input.nextLine();
					System.out.print("State: ");
					customer.ST = input.next();
					System.out.print("Zip Code: ");
					cZip = input.next();
					//Checks for a valid Zip-code
					while(!cZip.matches("[0-9]+") || cZip.length() != 5 ) {
						System.out.println("Invalid zip-code. Please try again.");
						cZip = input.next();
					}
					customer.zipCode = cZip;
					System.out.print("Make billing address the same as shipping address? (Y/N)");
					choice = input.next().charAt(0);
					//If letter is lower case convert to upper
					if(choice >= 90 && choice <= 122)
						choice = (char) (choice - 32);
					if(choice == 89)// "Y"
						card.setCardBillingAddress(customer.address, customer.city, customer.ST, customer.zipCode);
					else if(choice == 78) {//If "N" Get billing addresss
						System.out.print("Billing Address: ");
						a1 = input.nextLine();
						a1 = input.nextLine();
						System.out.print("City: ");
						a2 = input.nextLine();
						System.out.print("State: ");
						a3 = input.next();
						System.out.print("Zip Code: ");
						a4 = input.next();
						while(!a4.matches("[0-9]+") || a4.length() != 5 ) {
							System.out.println("Invalid zip-code. Please try again.");
							a4 = input.next();
						}
						card.setCardBillingAddress(a1, a2, a3, a4);
					}
					//Choice is NOT Y or N and must re-enter
					while(choice != 89 && choice != 78){
						System.out.print("Invalid answer. Please select Y or N: ");
						choice = input.next().charAt(0);
						//If letter is lower case convert to upper
						if(choice <= 122 && choice >= 90)
							choice = (char) (choice - 32);
						if(choice == 89 )
							card.setCardBillingAddress(customer.address, customer.city, customer.ST, customer.zipCode);
					}
					break;
					
				case 51://Billing Address
					System.out.print("Billing Address: ");
					a1 = input.nextLine();
					a1 = input.nextLine();
					System.out.print("City: ");
					a2 = input.nextLine();
					System.out.print("State: ");
					a3 = input.next();
					System.out.print("Zip Code: ");
					a4 = input.next();
					while(!a4.matches("[0-9]+") || a4.length() != 5 ) {
						System.out.println("Invalid zip-code. Please try again.");
						a4 = input.next();
					}
					card.setCardBillingAddress(a1, a2, a3, a4);
					break;
					
				case 52://Donation
					donation = 0;
					System.out.print("Would you like to make a donation? (Y/N): ");
					choice = input.next().charAt(0);
					//Converts lower to cap
					if(choice >= 90 && choice <=122)
						choice = (char) (choice - 32);
					//Choice is NOT Y or N and must re-enter
					while(choice != 89 && choice != 78){
						System.out.print("Invalid answer. Please select Y or N: ");
						choice = input.next().charAt(0);
						//If letter is lower convert to upper
						if(choice >= 90 && choice <=122)
							choice = (char) (choice - 32);
					}
					if(choice == 89) {
						System.out.print("Would you like to donate to a specific charity? (Y/N)");
						choice = input.next().charAt(0);
						//If letter is lower case convert to upper
						if(choice >= 90 && choice <= 122)
							choice = (char) (choice - 32);
						//Choice is NOT Y or N and must re-enter
						while(choice != 89 && choice != 78){
							System.out.print("Invalid answer. Please select Y or N: ");
							choice = input.next().charAt(0);
							//If letter is lower convert to upper
							if(choice >= 90 && choice <=122)
								choice = (char) (choice - 32);
						}
						if(choice == 89) {
							System.out.print("What charity would you like to donate to? ");
							customer.charity = input.nextLine();
							customer.charity = input.nextLine();
						}
						else if(choice == 78)
							customer.charity = "";
						System.out.print("Would you like to donate $1, $5 or other(o): $");
						choice = input.next().charAt(0);
						while(choice != 49 && choice != 53 && choice != 79 && choice != 111 ) {
							System.out.print("Invalid answer. Please choose from the following: $1, $5, or other(o): $");
							choice = input.next().charAt(0);
						}
						if( choice == 49)
							donation =+ 1;
						else if(choice == 53)
							donation =+ 5;
						else if(choice == 79 || choice == 111) {
							System.out.print("Please enter the amount: $");
							donation = input.nextDouble();
						}
					}
					break;
					
				default:
					break;
					
					
				}//End of switch
				//Card info
				System.out.println("Name of card holder: " + card.getCustomerFirstName() + " " + card.getCustomerLastName());
				System.out.println("Card number: xxxxxxxxxxxx" + card.getCardNumber());
				System.out.println("Expiration Date: " + card.getCardExpiration() + "\n");
				System.out.println("Shipping Address: " );
				System.out.println(customer.address + "," + customer.city + "," + customer.ST + "," + customer.zipCode + "\n");
				System.out.println("Billing Address: " );
				System.out.println(card.getCardBillingAddress() + "\n");
				System.out.println("Purchase amount: $" + amount);
				if(!customer.charity.isEmpty())
					System.out.println("Donation to " + customer.charity + ": $" + donation);
				else
					System.out.println("Donation: $" + donation);
				card.setTransactionAmount(amount, donation);
				System.out.println("Total amount: $" + card.getTransactionAmount());
				System.out.print("Continue(1) Edit(2) or Cancel(3)");
				choice = input.next().charAt(0);
			}//End of "if" from Edit choice
			else if(choice == 51) {//Cancel
				System.out.println("Transaction canceled");
				input.close();
				System.exit(0);
			}//End of "if" from Cancel choice
			if(choice == 49) {//Continue after Edit
				confirm = True;
				//Transaction Process
				System.out.println(card.processTransaction());
				System.out.println(card.getTransactionDate());
				System.out.println("Item(s) total: $" + amount);
				if(!customer.charity.isEmpty())
					System.out.println("Donation to " + customer.charity + ": $" + donation);
				else
					System.out.println("Donation: $" + donation);
				System.out.println("Total amount: $" + card.getTransactionAmount() + "\n");
				System.out.println(card.getNameOnCard());
				System.out.println("Card number: xxxxxxxxxxxx" + card.getCardNumber() + " " + type);
				System.out.println("Shipping Address: " );
				System.out.println(customer.address + "," + customer.city + "," + customer.ST + "," + customer.zipCode + "\n");
				System.out.println("Billing Address: " );
				System.out.println(card.getCardBillingAddress() + "\n");
				System.out.println("Thank you for shopping with us!");
				input.close();
				System.exit(0);
				
			}
		}//End of while

		
	}

}
