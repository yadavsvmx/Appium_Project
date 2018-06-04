package FSAID.FSAAID.objectRepo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FSAID.FSAAID.initiator.Initiator;
import FSAID.FSAAID.wrapper.Wrapper;

public class Pg_login{

	 Wrapper wrpr = new Wrapper();
	 Initiator init = new Initiator();

	public  String btn_signin = "svmx_splash_signin";
	public  String txt_username = "username";
	public  String txt_password = "password";
	public  String btn_Login = "Login";
	public  String btn_oaapprove = "oaapprove";

	public void login(String un, String pwd) {
		if (init.driver.findElements(By.id(btn_signin)).size() != 0) {

			init.driver.findElement(By.id(btn_signin)).click();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// driver.findElement(By.id("username")).sendKeys("Vinod.tharavath@ge.com");
			// driver.findElement(By.id("password")).sendKeys("svmx123#");
			init.driver.findElement(By.id(txt_username)).sendKeys(un);
			init.driver.findElement(By.id(txt_password)).sendKeys(pwd);
			init.driver.findElement(By.id(btn_Login)).click();

			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			init.driver.findElement(By.id(btn_oaapprove)).click();
			System.out.println("Login Successfull");

		} else {
			// do nothing
			System.out.println("Already Logged in skipping Login");
		}
		System.out.println("Waiting for Landing Page");

		init.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pg_calendar.btn_calendar)));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
