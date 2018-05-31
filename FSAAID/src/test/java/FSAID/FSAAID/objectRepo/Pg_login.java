package FSAID.FSAAID.objectRepo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FSAID.FSAAID.initiator.Initiator;
import FSAID.FSAAID.wrapper.Wrapper;

public class Pg_login extends Initiator {

	static Wrapper wrpr = new Wrapper();

	public static String btn_signin = "svmx_splash_signin";
	public static String txt_username = "username";
	public static String txt_password = "password";
	public static String btn_Login = "Login";
	public static String btn_oaapprove = "oaapprove";

	public static void login(String un, String pwd) {
		if (driver.findElements(By.id(Pg_login.btn_signin)).size() != 0) {

			driver.findElement(By.id(Pg_login.btn_signin)).click();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// driver.findElement(By.id("username")).sendKeys("Vinod.tharavath@ge.com");
			// driver.findElement(By.id("password")).sendKeys("svmx123#");
			driver.findElement(By.id(Pg_login.txt_username)).sendKeys(un);
			driver.findElement(By.id(Pg_login.txt_password)).sendKeys(pwd);
			driver.findElement(By.id(Pg_login.btn_Login)).click();

			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.id(Pg_login.btn_oaapprove)).click();
			System.out.println("Login Successfull");

		} else {
			// do nothing
			System.out.println("Already Logged in skipping Login");
		}
		System.out.println("Waiting for Landing Page");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pg_calendar.btn_calendar)));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
