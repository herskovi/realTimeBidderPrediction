package com.xrtb.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainFlowTest {
	
	@BeforeClass
	public static void setup() {
		try {
			Config.setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void stop() {
		Config.teardown();
	}
	
	@Test
	public void testMainFlow()
	{
		
		//prepareDataIntoDatabase();
		//trainTheModel(ModelConfig.);
		//runNewBid(BidRequest);
		//checkResultsOfModel
		//InsertIntoDB();
		
		
	}

}
