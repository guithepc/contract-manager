package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Departament;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {
	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter department's name: ");
		String departmentName = sc.nextLine();
		
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String workerName = sc.nextLine();
		System.out.print("Level (JUNIOR or MID_LEVEL or SENIOR): ");
		String workerLevel = sc.nextLine();
		System.out.print("Base salary: ");
		double workerBaseSalary = sc.nextDouble();
		
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), workerBaseSalary, new Departament(departmentName));
		
		System.out.println("How many contracts for this worker?");
		int contractsNumber = sc.nextInt();
		
		for (int i = 1; i <= contractsNumber; i++) {
			System.out.println("Enter contract #" + i + " data: ");
			System.out.print("Data(DD/MM/YYYY): ");
			Date contractDate = sf.parse(sc.next());
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration in hours: ");
			int durationInHours = sc.nextInt();
			HourContract hourContract = new HourContract(contractDate, valuePerHour, durationInHours);
			worker.addContract(hourContract);
		}
		
		System.out.println();
		System.out.print("Enter the month and year to calculate the income (MM/YYYY): ");
		String monthNYear = sc.next();
		int month = Integer.parseInt(monthNYear.substring(0, 2));
		int year = Integer.parseInt(monthNYear.substring(3));
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartament().getName());
		System.out.println("Income for " + monthNYear + ": " + String.format("%.2f", worker.income(year, month)));
		sc.close();
	}
}
