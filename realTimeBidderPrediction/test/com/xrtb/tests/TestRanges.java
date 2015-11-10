package com.xrtb.tests;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vexigo.prediction.utils.FileUtils;
import com.xrtb.common.Campaign;
import com.xrtb.common.Configuration;
import com.xrtb.common.HttpPostGet;
import com.xrtb.common.Node;
import com.xrtb.pojo.BidRequest;

/**
 * Test Geo fencing
 * @author Ben M. Faul
 *
 */

public class TestRanges {
	/**
	 * Setup the RTB server for the test
	 */
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
	
	/**
	 * Shut the RTB server down.
	 */
	@AfterClass
	public static void testCleanup() {
		Config.teardown();
	}  
	
	/**
	 * Test distance calaculations
	 */
	@Test 
	public void testLosAngelesToSF() {
		Number laLat = 34.05;
		Number laLon =  -118.25;
		
		Number sfLat = 37.62;
		Number sfLon = -122.38;
		double dist = Node.getRange(laLat, laLon, sfLat, sfLon);
		assertTrue(dist==544720.8629416309);
	}
	
	/**
	 * Test a single geo fence region in an isolated node.
	 * @throws Exception on I/O errors.
	 */
	@Test
	public void testGeoInBidRequest() throws Exception {
		InputStream is = Configuration.getInputStream("SampleBids/smaato.json");
		BidRequest br = new BidRequest(is);
		assertEquals(br.getId(),"K6t8sXXYdM");
		
		Map m = new HashMap();
		m.put("lat", 34.05);
		m.put("lon",-118.25);
		m.put("range",600000);
		List list = new ArrayList();
		list.add(m);
		
		Node node = new Node("LATLON","device.geo", Node.INRANGE, list);
     	node.test(br);
		ObjectNode map = (ObjectNode)node.getBRvalue();
		assertTrue((Double)map.get("lat").getDoubleValue()==37.62);
		assertTrue((Double)map.get("lon").getDoubleValue()==-122.38);
		assertTrue((Double)map.get("type").getDoubleValue()==3);
		
		List<Map>test = new ArrayList();
		test.add(m);
		node = new Node("LATLON","device.geo", Node.INRANGE, test);
		node.test(br);

	}
	
	/**
	 * Test the geo fence on a valid bid request, but not in range
	 * @throws Exception on network errors.
	 */
	@Test
	public void testGeoSingleFenceNotInRange() throws Exception {
		HttpPostGet http = new HttpPostGet();
//		String s = Charset
//				.defaultCharset()
//				.decode(ByteBuffer.wrap(Files.readAllBytes(Paths
//						.get("SampleBids/nexage.txt")))).toString();
		String s = new String(FileUtils.readAllBytes(FileUtils.getInputStreamFromPath("SampleBids/nexageNoGeo.txt")));
		
		Map m = new HashMap();
		m.put("lat", 34.05);
		m.put("lon",-118.25);
		m.put("range",600000);
		List list = new ArrayList();
		list.add(m);
		
		Node node = new Node("LATLON","device.geo", Node.INRANGE, list);
		
		Campaign camp = Configuration.getInstance().campaignsList.get(0);
		camp.attributes.add(node);
		
		try {
			 s = http.sendPost("http://" + Config.testHost + "/rtb/bids/nexage", s);
		} catch (Exception error) {
			fail("Error");
		}
		assertTrue(http.getResponseCode()==204);
		System.out.println(s);
	} 
	
	/**
	 * Test the bid when there is a geo object AND it is in range.
	 * @throws Exception on configuration file errors and on network errors to the biddewr.
	 */
	@Test
	public void testGeoSingleFenceInRange() throws Exception {
		HttpPostGet http = new HttpPostGet();
//		String s = Charset
//				.defaultCharset()
//				.decode(ByteBuffer.wrap(Files.readAllBytes(Paths
//						.get("SampleBids/nexage.txt")))).toString();
//
		String s = new String(FileUtils.readAllBytes(FileUtils.getInputStreamFromPath("SampleBids/nexageNoGeo.txt")));
		Map m = new HashMap();
		m.put("lat", 42.05);
		m.put("lon",-71.25);
		m.put("range",600000);
		List list = new ArrayList();
		list.add(m);
		
		Node node = new Node("LATLON","device.geo", Node.INRANGE, list);
		
		Campaign camp = Configuration.getInstance().campaignsList.get(0);
		camp.attributes.add(node);
		
		try {
			 s = http.sendPost("http://" + Config.testHost + "/rtb/bids/nexage", s);
		} catch (Exception error) {
			fail("Error");
		}
		assertTrue(http.getResponseCode()==204);
		System.out.println(s);
	} 
	
	/**
	 * Test a valid bid, but geo is not present in bid, and the campaign requires it.
	 * @throws Exception on file errors in the config file and network errors to the bidder.
	 */
	@Test
	public void testGeoSingleFenceInRangeButNoGeoInBR() throws Exception {
		HttpPostGet http = new HttpPostGet();
//		String s = Charset
//				.defaultCharset()
//				.decode(ByteBuffer.wrap(Files.readAllBytes(Paths
//						.get("SampleBids/nexageNoGeo.txt")))).toString();
		String s = new String(FileUtils.readAllBytes(FileUtils.getInputStreamFromPath("SampleBids/nexageNoGeo.txt")));

		
		Map m = new HashMap();
		m.put("lat", 42.05);
		m.put("lon",-71.25);
		m.put("range",600000);
		List list = new ArrayList();
		list.add(m);
		
		Node node = new Node("LATLON","device.geo", Node.INRANGE, list);
		node.notPresentOk = false;
		
		Campaign camp = Configuration.getInstance().campaignsList.get(0);
		camp.attributes.add(node);
		
		try {
			 s = http.sendPost("http://" + Config.testHost + "/rtb/bids/nexage", s);
		} catch (Exception error) {
			fail("Error");
		}
		assertTrue(http.getResponseCode()==204);
		System.out.println(s);
	}
	
}
