package com.xrtb.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.devicemap.DeviceMapClient;
import org.apache.devicemap.DeviceMapFactory;
import org.apache.devicemap.loader.LoaderOption;
import org.redisson.Config;
import org.redisson.Redisson;

import com.google.gson.Gson;
import com.vexigo.prediction.datatypes.consts.FileConsts;
import com.vexigo.prediction.utils.FileUtils;
import com.xrtb.bidder.RTBServer;
import com.xrtb.bidder.WebCampaign;
import com.xrtb.commands.BasicCommand;
import com.xrtb.commands.LogMessage;
import com.xrtb.geo.GeoTag;
import com.xrtb.pojo.BidRequest;

/**
 * The singleton class that makes up the Configuration object. A configuration is a JSON file that describes the campaigns and operational
 * parameters needed by the bidding engine.
 * 
 * All classes needing config data retrieve it here.
 * @author Ben M. Faul
 *
 */

public class Configuration {
	/** JSON parser for decoding configuration parameters */
	static Gson gson = new Gson();
	/** The singleton instance */
	static volatile Configuration theInstance;
	
	/** The Apache DeviceMapper object */
	public DeviceMapClient deviceMapper = DeviceMapFactory.getClient(LoaderOption.JAR);
	/** Geotag extension object */
	public GeoTag geoTagger = new GeoTag();
	/** The Nashhorn shell used by the bidder */
	JJS shell;
	/** The standard HTTP port the bidder uses */
	public int port = 8080;
	/** The url of this bidder */
	public String url;
	/** The log level of the bidding engine */
	public int logLevel = 4;
	/** Set to true to see why the bid response was not bid on */
	public boolean printNoBidReason = false;
	/** The campaign watchdog timer */
	public long timeout = 80;                 
	/** The standard name of this instance */
	public String instanceName = "default";
	/** The exchange seat ids */
	public Map<String,String> seats;
	/** The campaigns used to make bids */
	public List<Campaign> campaignsList = new ArrayList<Campaign>();
	
	/** Standard pixel tracking URL */
	public String pixelTrackingUrl;
	/** Standard win URL */
	public String winUrl;
	/** The redirect URL */
	public String redirectUrl;
	/** The time to live in seconds for REDIS keys */
	public int ttl = 300;
	/** Max number of connections the bidder will support, if exceeded, will NO bid */
	public int maxConnections = 100;
	
	/**
	 * REDIS LOGGING INFO
	 *
	 */
	/** The channel that raw requests are written to */
	public String BIDS_CHANNEL = null;
	/** The channel that wins are written to */
	public String WINS_CHANNEL = null;
	/** The channel the bid requests are written to */
	public String REQUEST_CHANNEL = null;
	/** The channel where log messages are written to */
	public String LOG_CHANNEL = null;
	/** The channel clicks are written to */
	public String CLICKS_CHANNEL = null;

	/** The host name where the REDIS lives */
	public static String cacheHost = "localhost";
	/** The REDIS TCP port */
	public static int cachePort = 8888;
    
	/**
	 * Redisson shared memory over redis
	 * 
	 */
	/** Redisson configuration object */
	Config redissonConfig = new Config();
	/** Redisson object */
	public Redisson redisson;
   
	/**
	 * Private constructor, class has no public constructor.
	 * @param fileName String. The filename of the configuration data.
	 */
	private Configuration() throws Exception {

	}
	
	/**
	 * Clear the config entries to default state,
	 */
	public void clear() {
		port = 8080;
		url = null;
		logLevel = 4;
		campaignsList.clear();
	}
	
	/**
	 * Read the Java Bean Shell file that initializes this constructor.
	 * @param path. String - The file name containing the Java Bean Shell code.
	 * @throws Exception on file errors.
	 */
	public void initialize(String path) throws Exception {
		
//		byte[] encoded = FileUtils.readAllBytes(Paths.get(path));
		byte[] encoded = FileUtils.readAllBytes(FileUtils.getInputStreamFromPath(path));
		String str = Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();
		
		Map<?, ?> m = gson.fromJson(str,Map.class);
		instanceName = (String)m.get("instance");

		seats = new HashMap<String, String>();
		
		/**
		 * Create the seats id map, and create the bin and win handler classes for each exchange
		 */
		List<Map> seatsList = (List<Map>)m.get("seats");
		for (int i=0;i<seatsList.size();i++) {
			Map x = seatsList.get(i);
			String name = (String)x.get("name");
			String id = (String)x.get("id");
			seats.put(name,id);
			
			String className = (String)x.get("bid");
			String parts [] = className.split("=");
			String uri = parts[0];
			className = parts[1];
			Class<?> c = Class.forName(className);
			BidRequest br = (BidRequest)c.newInstance();
			RTBServer.exchanges.put(uri,br);
		}
		
		m = (Map)m.get("app");
		Map verbosity = (Map)m.get("verbosity");
		if (verbosity != null) {
			logLevel = ((Double)verbosity.get("level")).intValue();
			printNoBidReason = (Boolean)verbosity.get("nobid-reason");
		}
		
		Map geotag = (Map)m.get("geotags");
		if (geotag != null) {
			String states = (String)geotag.get("states");
			String codes = (String)geotag.get("zipcodes");
			geoTagger.initTags(states,codes);
		}
		
		String value = null;
		Double dValue = 0.0;
		Boolean bValue = false;
		Map r = (Map)m.get("redis");
		if ((value=(String)r.get("host")) != null)
			cacheHost = value;
		if ((value=(String)r.get("bidchannel")) != null)
			BIDS_CHANNEL = value;
		if ((value=(String)r.get("winchannel")) != null)
			WINS_CHANNEL = value;
		if ((value=(String)r.get("requests")) != null)
			REQUEST_CHANNEL = value;
		if ((value=(String)r.get("logger")) != null)
			LOG_CHANNEL = value;
		if ((value=(String)r.get("clicks")) != null)
			CLICKS_CHANNEL = value;
		if ((dValue=(Double)r.get("port")) != null)
			cachePort = dValue.intValue();

		redissonConfig.useSingleServer()
        	.setAddress(cacheHost+":"+((int)cachePort))
        	.setConnectionPoolSize(10);
		redisson = Redisson.create(redissonConfig);
		
		campaignsList.clear();
		
		List<String> list = (List<String>)m.get("campaigns");
		if (list != null) {
			for (String ss : list) {
				addCampaign(ss);
			}
		}
			
		pixelTrackingUrl = (String)m.get("pixel-tracking-url");
		winUrl = (String)m.get("winurl");
		redirectUrl = (String)m.get("redirect-url");
		if (m.get("ttl") != null) {
			Double d = (Double)m.get("ttl");
			ttl = d.intValue();
		}
		if (m.get("connections") != null) {
			Double d = (Double)m.get("connections");
			maxConnections = d.intValue();
		}
	
	}
	
	/**
	 * TODO: Needs work.
	 */
	@Override
	public String toString() {
		for (Field f : getClass().getDeclaredFields()) {

		    System.out.println(f);
		}
		return "na";
	}

	/**
	 * Return the instance of Configuration, and if necessary, instantiates it first.
	 * @param fileName String. The name of the initialization file.
	 * @return Configuration. The instance of this singleton.
	 * @throws Exception on JSON errors.
	 */
	public static Configuration getInstance(String fileName) throws Exception {
		if (theInstance == null) {
			synchronized (Configuration.class) {
				if (theInstance == null) {
					theInstance = new Configuration();
					theInstance.initialize(fileName);
					try {
						theInstance.shell = new JJS();
					} catch (Exception error) {
						
					}
				} else
					theInstance.initialize(fileName);
			}
		}
		return theInstance;
	}
	
	/**
	 * Return the configuration instance.
	 * @return The instance.
	 */
	public static Configuration getInstance()  {
		if (theInstance == null)
			throw new RuntimeException("Please initialize the Configuration instance first.");
		return theInstance;
	}
	
	/**
	 * Returns an input stream from the file of the given name.
	 * @param fname String. The fully qualified file name.
	 * @return InputStream. The stream to read from.
	 * @throws Exception on file errors.
	 */
	public static InputStream getInputStream(String fname) throws Exception {
		
		File f = new File(FileConsts.PROJECT_PATH_LOCATIOIN + fname);
		FileInputStream fis = new FileInputStream(f);
		return fis;
	}
	
	/**
	 * This deletes a campaign from the campaignsList (the running commands) this does not delete from the database
	 * @param id String. The id of the campaign to delete
	 * @return boolean. Returns true if the campaign was found, else returns false.
	 */
	public boolean deleteCampaign(String id) {
		Iterator<Campaign> it = campaignsList.iterator();
		while(it.hasNext()) {
			Campaign c = it.next();
			if (c.adId.equals(id)) {
				campaignsList.remove(c);
				
				recompile();
				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Recompile the bid attributes we will parse from bid requests, based on the aggregate of all
	 * campaign bid constraints.
	 */
	public void recompile() {
		int percentage = RTBServer.percentage;		// save the current throttle
		RTBServer.percentage = 0;					// throttle the bidder to 0
		try { Thread.sleep(1000); } catch (InterruptedException e) {}	// Wait for the working campaigns to drain
		BidRequest.compile();						// modify the Map of bid request components.
		RTBServer.percentage = percentage;			// restore the old percentage
	}
	
	/**
	 * Add a campaign to the list of campaigns we are running. Does not add to REDIS.
	 * @param c Campaign. The campaign to add into the accounting.
	 * @throws Exception if the encoding of the attributes fails.
	 */
	public void addCampaign(Campaign c) throws Exception  {
		c.encodeCreatives();
		c.encodeAttributes();
		campaignsList.add(c);
		
		recompile();
	}
	
	/**
	 * Returns a list of all the campaigns that are running
	 * @return List. The list of campaigns, byadIds, that are running.
	 */
	public List<String> getLoadedCampaignNames() {
		List<String> list = new ArrayList();
		for (Campaign c : campaignsList) {
			list.add(c.adId);
		}
		return list;
	}
	
	/**
	 * Add a campaign to the campaigns list using the shared map database of campaigns
	 * @param campId String. The campaign id of what to add.
	 * @throws Exception if the addition of this campaign fails.
	 */
	public void addCampaign(String campId) throws Exception  {
		deleteCampaign(campId);
		Campaign camp = WebCampaign.getInstance().db.getCampaign(campId);
		addCampaign(camp);
	}
}
