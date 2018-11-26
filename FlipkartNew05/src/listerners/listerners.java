package listerners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.flipkart.pages.flipkart_Screenshot;

public class listerners implements ITestListener
{
	
	flipkart_Screenshot FSshot= new flipkart_Screenshot(); 

	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println(result.getName()+" test case started");	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("The name of the testcase passed is :"+result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		
		
		try {
			FSshot.takeSnapShot(result.getName());
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		
		System.out.println("The name of the testcase failed is :"+result.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		
	}

}
