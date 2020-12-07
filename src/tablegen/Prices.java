package tablegen;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.json.JSONObject;


public class Prices {

	/**
	 * Returns the Lowes price. 
	 * @param n1 - appears to be the item ID (1001134500)
	 * @param n2 - appears to be the store ID (1803)
	 * @return
	 * @throws IOException
	 */
	public static double LowesGet(String n1,String n2) throws IOException
	{
		/* SOURCE: modified from stackoverflow.com and others */
		URL obj = new URL("https://www.lowes.com/pd/"+n1+"/productdetail/"+n2+"/Guest");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36");
		
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer(); //we have to use a string buffer because the response body comes piecemeal

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close(); //have the response body, now we need to parse it
			try
			{
				System.out.println(response.toString());//for debug because Lowes is not stable yet
				return new JSONObject(response.toString()).getJSONObject("productDetails").getJSONObject(n1+"").getJSONObject("price").getDouble("itemPrice"); // walk through the JSON schema until we hit the itemPrice value for Lowes
			}
			catch(org.json.JSONException e)
			{
				//a JSON key is likely missing here. API might have changed
				System.out.println(e);
				return 0.0;
			}
			
		} else {
			System.out.println("GET request not worked");
			return 0.0;
		}
		
	}
	
	/**
	 * A function to make a POST request to Home Depot
	 * @param itemID - the HomeDepot itemID 100070209
	 * @return the body of the response as a String
	 */
	public static double HomeDepotPost(String itemID) {
		  HttpURLConnection connection = null;

		  /* SOURCE: modified from stackoverflow.com and others */
		  try {
		    //Create connection
		    URL url = new URL("https://www.homedepot.com/product-information/model"); //we have to send a POST request
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("authority",   				// I had katie copy over all the headers from my reverse engineered request because Home Depot also had a little bit of anti-scraping technology
		        "www.homedepot.com");
		    connection.setRequestProperty("accept", 
			        "*/*");
		    connection.setRequestProperty("x-debug", 
			        "false");
		    connection.setRequestProperty("dnt", 
			        "1");
		    connection.setRequestProperty("x-experience-name", 
			        "general-merchandise");
		    connection.setRequestProperty("x-api-cookies", 
			        "{\"x-user-id\":\"dbb69e11-75fb-f0d1-a940-9926445ce257\"}");
		    connection.setRequestProperty("user-agent", 
			        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36"); //the request body is JSON
		    connection.setRequestProperty("content-type", 
			        "application/json");
		    connection.setRequestProperty("origin", 
			        "https://www.homedepot.com");
		    connection.setRequestProperty("sec-fetch-site", 
			        "same-origin");
		    connection.setRequestProperty("sec-fetch-mode", 
			        "cors");
		    connection.setRequestProperty("sec-fetch-dest", 
			        "empty");
		    connection.setRequestProperty("referer", 
			        "https://www.homedepot.com/p/Veranda-Melamine-White-Panel-Common-3-4-in-x-4-ft-x-8-ft-Actual-750-in-x-49-in-x-97-in-461877/100070209");
		    connection.setRequestProperty("accept-language", 
			        "en-US,en;q=0.9,es;q=0.8");
		    connection.setRequestProperty("cookie", 
			        "THD_CACHE_NAV_PERSIST=; thda.u=dbb69e11-75fb-f0d1-a940-9926445ce257; RES_TRACKINGID=54618987594440800; _abck=F543955C66F37ECFFF29B61FDDEDE54A~0~YAAQnpcwFxNLr1V0AQAAdm1htwRB4O475kWEjN9/zoaWxA/JIDA0AGCBg7yMeJaeoLeV+fJQmM7wQC+gaau65W+yhs3CEYsdYIQQOvV/QZjhxd7Y8elMM+dlGWznMdWpuUXaFPxRuRN8xLaK87V6ZJ2U4b/s5YSSWO3erF50OjRurDTLbaCn7y0VI6gRkNFuKM/A//tfzJT4eVZhrneWycMT2JQ/x147jzvtTouE0Ds1yqszgN1poj658wkc++BfAAunLdScoUE6dnFjxtH3uQ4C0VMtvYSHnncq1x7iOacsd6rBBx1NbdjEoQy0nBJ6E1iSiwcWwRWIUQ==~-1~-1~-1; _pxvid=90e7fa61-102b-11eb-bb68-0242ac12000c; _px_f394gi7Fvmc43dfg_user_id=OTEzNDE1YzAtMTAyYi0xMWViLTllMzctNGJkYmQ2NGNkZTM2; ftr_ncd=6; ajs_user_id=null; ajs_group_id=null; ajs_anonymous_id=%227face3ee-67e4-49df-88ed-ec14fd2a4bf9%22; _meta_bing_beaconFired=true; _meta_facebookPixel_beaconFired=true; _meta_merkle_securedVisitBeaconFired=true; _meta_merkle_rkdmsBeaconFired=true; _gcl_au=1.1.1148877102.1602906491; aam_mobile=seg%3D1131078; aam_uuid=25866415413445447774516779719598253283; LPVID=hiNTVjNThjMzhjM2VjNTI3; DELIVERY_ZIP_TYPE=AUTO; _meta_adobe_aam_uuid=25866415413445447774516779719598253283; _meta_metarouter_timezone_offset=300; QSI_SI_2lVW226zFt4dVJ3_intercept=true; _meta_mediaMath_iframe_counter=5; HD_DC=origin; check=true; AMCVS_F6421253512D2C100A490D45%40AdobeOrg=1; THD_FORCE_LOC=1; WORKFLOW=LOC_HISTORY_BY_IP; THD_INTERNAL=0; DELIVERY_ZIP=22030; thda.s=6bb6a9f5-1b1a-c08a-5e52-dc3f0af2ae4a; _meta_movableInk_mi_u=7face3ee-67e4-49df-88ed-ec14fd2a4bf9; thda.m=25588232375374905564524305436100528790; ResonanceSegment=1; THD_GUESTLIST=NwsKZgTE3jsZ6VHxi4AfWAa4oEh6B61+7wCHU6Xzhl3j1jpHSs7ubktOhY7LTL1fcY2CynpXaddF8r/GtGHSteq5PWw0q3LvQlE639gVXa4=; THD_SESSION=; THD_CACHE_NAV_SESSION=; SEARCH_HST=; QSI_HistorySession=https%3A%2F%2Fwww.homedepot.com%2Fp%2FVeranda-Melamine-White-Panel-Common-3-4-in-x-4-ft-x-8-ft-Actual-750-in-x-49-in-x-97-in-461877%2F100070209~1607037493184; bm_sz=4F080D0000C95A52EFF597ADB871A9B4~YAAQj5cwF3xopS12AQAAcVyZLgoLfVrdcY8ttTsrjVdeIDZBOMILR3ICiP60hp/HMvQ0LqIg2zH13okvVnPSs/MnK24HPZRMlU1u6IfawjYNOAXwJ+686/CEBwkH61S/RxOP2fwotb4jrPhOCzDpkR4w2sRYAJXAwLYK1/rZp+7iNkX2Fm5JBuzPKaXkRYnPKg+E; THD_NR=1; mbox=session#0cf173e2d24944e8ac8073efe390fa71#1607101432; THD_PERSIST=C4%3D4601%2BFairfax%20-%20Fairfax%2C%20VA%2B%3A%3BC4_EXP%3D1638400490%3A%3BC24%3D22030%3A%3BC24_EXP%3D1638400490%3A%3BC39%3D1%3B8%3A00-20%3A00%3B2%3B6%3A00-21%3A00%3B3%3B6%3A00-21%3A00%3B4%3B6%3A00-21%3A00%3B5%3B6%3A00-21%3A00%3B6%3B6%3A00-21%3A00%3B7%3B6%3A00-21%3A00%3A%3BC39_EXP%3D1607103176; THD_LOCALIZER=%7B%22WORKFLOW%22%3A%22LOC_HISTORY_BY_IP%22%2C%22THD_FORCE_LOC%22%3A%221%22%2C%22THD_INTERNAL%22%3A%220%22%2C%22THD_STRFINDERZIP%22%3A%2222030%22%2C%22THD_LOCSTORE%22%3A%224601%2BFairfax%20-%20Fairfax%2C%20VA%2B%22%2C%22THD_STORE_HOURS%22%3A%221%3B8%3A00-20%3A00%3B2%3B6%3A00-21%3A00%3B3%3B6%3A00-21%3A00%3B4%3B6%3A00-21%3A00%3B5%3B6%3A00-21%3A00%3B6%3B6%3A00-21%3A00%3B7%3B6%3A00-21%3A00%22%2C%22THD_STORE_HOURS_EXPIRY%22%3A1607103176%7D; ak_bmsc=FDE58CD0627454318BB6C7DC6F8CD3A61730978FF2210000B364CA5F2639FC30~plDCDjvby8rfRdA5EdkaeLNqHUKyF8X1qFDDGOgmRcv3rhxr99ImNxvTfX8G8g3tcD5QCjHFT6YUia4KnsBdgke0K7FEBsC8MZqiH17ZlXhy8TVOoIdBPXJJI2Z0lGDktglzyGrmcfZvi7zetlZpgsKQVgSYwde0wxVOc7Iix2a1sxW8rxwcxNmFLw1BwFaRk9cIqMAIXg1/OQ8iuzIJ+lnSSGCdfPOFRO/X7eYHXV7DL//5IqeMFj+j3dIXjYNFAQ; RES_SESSIONID=88890480788599070; AMCV_F6421253512D2C100A490D45%40AdobeOrg=1585540135%7CMCIDTS%7C18600%7CMCMID%7C25588232375374905564524305436100528790%7CMCAAMLH-1607704392%7C7%7CMCAAMB-1607704392%7C6G1ynYcLPuiQxYZrsz_pkqfLG9yMXBpb2zX5dvJdYQJzPXImdj0y%7CMCOPTOUT-1607106793s%7CNONE%7CMCCIDH%7C-1826263068%7CvVersion%7C4.4.0; LPSID-31564604=f7F3S2ZvSZWHsyVrg5NnEQ; IN_STORE_API_SESSION=TRUE; _pxff_cc=U2FtZVNpdGU9TGF4Ow==; _px=L505fATUKHSCXsTc2iNm9h2V9dOzpqI6ot+B8/y+OzEiTnCRUnV6SI/4AYVTfKI3/PXciiyTgHBJZWNDqindEA==:1000:sC0IyUmpqbbNXm4RRXsofN6zSigeyZpUQFU808jijzclEmhQat0/exeESkCQBQ8L/UzUKQWvmwB3g2GxxDNffVYrZMU/K/sXvnya70Dnk+CTQ3ACNWcHAPkIPITwCJpRb5fCSQgKFfZHqi6sXuSJtqCRIdR0JFmlDsXeROryknRBvpqZAH20umti6ylsLeJO7GyjEBXAay5AN9ZsVBnJv6vbOJILjvW1jM5iDybpNH/0dMXNxAsRoipgQAi9Cw0cHKu3JOBQ1OZYRtKMWv/VTg==; s_pers=%20productnum%3D7%7C1609457624832%3B%20s_nr365%3D1607100416650-Repeat%7C1638636416650%3B%20s_dslv%3D1607100416660%7C1701708416660%3B; s_sq=%5B%5BB%5D%5D; s_sess=%20stsh%3D%3B%20s_pv_pName%3Dproductdetails%253E100070209%3B%20s_pv_pType%3Dpip%3B%20s_pv_cmpgn%3D%3B%20s_pv_pVer%3D%3B%20s_cc%3Dtrue%3B; forterToken=84d5c11b77b442b4a48e83bf392e6bd8_1607100416721__UDF43_9ck; _pxde=69640180095360ab463b3d781a5bfe63a45dfd868454d44156828fb143c36fb4:eyJ0aW1lc3RhbXAiOjE2MDcxMDA0MzMyNTF9; _px_4946459675_cs=eyJpZCI6IjYyYjM1MTQwLTM2NGUtMTFlYi04YWZjLTczODgyODJhYmI4ZSIsInN0b3JhZ2UiOnt9LCJleHBpcmF0aW9uIjoxNjA3MTAyMjQzNDkxfQ==; akaau=1607100743~id=63463ce29e71859204f6ec3e0110c4c7; bm_sv=86EC464F67948CDB6190E2527EA627FB~baV42RiBZudpflhFfE/2bPZZrojLYDbIhvuO6zPqD+uspQL/yu72ouetcgcxeCWMDiJGvmTtjE+sV+5mbgJ6J62kOU4TdLgT8Wx+9VZekQOynoPPL6bb6K+23e+893GW+5dv/nVhnQFRojF6nAznVTbBMUgQ1bxmIERMRibRHVQ=");
		    connection.setRequestProperty("sec-gpc", 
			        "1");
		    

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream()); //the request body is JSON
		    wr.writeBytes("{\"operationName\":\"clientOnlyProduct\",\"variables\":{\"itemId\":\""+itemID+"\",\"storeId\":\"4601\",\"zipCode\":\"22030\"},\"query\":\"query clientOnlyProduct($storeId: String, $zipCode: String, $itemId: String!) {\\n  product(itemId: $itemId) {\\n    itemId\\n    dataSources\\n    info {\\n      recommendationFlags {\\n        visualNavigation\\n        __typename\\n      }\\n      replacementOMSID\\n      hasSubscription\\n      minimumOrderQuantity\\n      projectCalculatorEligible\\n      productDepartment\\n      classNumber\\n      subClassNumber\\n      calculatorType\\n      isLiveGoodsProduct\\n      protectionPlanSku\\n      hasServiceAddOns\\n      consultationType\\n      __typename\\n    }\\n    availabilityType {\\n      discontinued\\n      type\\n      status\\n      buyable\\n      __typename\\n    }\\n    fulfillment(storeId: $storeId, zipCode: $zipCode) {\\n      backordered\\n      fulfillmentOptions {\\n        type\\n        fulfillable\\n        services {\\n          type\\n          locations {\\n            isAnchor\\n            inventory {\\n              isLimitedQuantity\\n              isOutOfStock\\n              isInStock\\n              quantity\\n              isUnavailable\\n              maxAllowedBopisQty\\n              minAllowedBopisQty\\n              __typename\\n            }\\n            type\\n            storeName\\n            locationId\\n            curbsidePickupFlag\\n            isBuyInStoreCheckNearBy\\n            distance\\n            state\\n            storePhone\\n            __typename\\n          }\\n          deliveryTimeline\\n          deliveryDates {\\n            startDate\\n            endDate\\n            __typename\\n          }\\n          deliveryCharge\\n          dynamicEta {\\n            hours\\n            minutes\\n            __typename\\n          }\\n          hasFreeShipping\\n          freeDeliveryThreshold\\n          totalCharge\\n          __typename\\n        }\\n        __typename\\n      }\\n      anchorStoreStatus\\n      anchorStoreStatusType\\n      backorderedShipDate\\n      bossExcludedShipStates\\n      excludedShipStates\\n      seasonStatusEligible\\n      onlineStoreStatus\\n      onlineStoreStatusType\\n      inStoreAssemblyEligible\\n      __typename\\n    }\\n    seoDescription\\n    identifiers {\\n      brandName\\n      productLabel\\n      productType\\n      storeSkuNumber\\n      isSuperSku\\n      parentId\\n      sampleId\\n      modelNumber\\n      __typename\\n    }\\n    media {\\n      images {\\n        url\\n        sizes\\n        type\\n        subType\\n        __typename\\n      }\\n      __typename\\n    }\\n    pricing(storeId: $storeId) {\\n      value\\n      alternatePriceDisplay\\n      unitOfMeasure\\n      alternate {\\n        bulk {\\n          thresholdQuantity\\n          value\\n          __typename\\n        }\\n        unit {\\n          caseUnitOfMeasure\\n          unitsPerCase\\n          value\\n          __typename\\n        }\\n        __typename\\n      }\\n      original\\n      promotion {\\n        experienceTag\\n        subExperienceTag\\n        anchorItemList\\n        itemList\\n        reward {\\n          tiers {\\n            minPurchaseAmount\\n            minPurchaseQuantity\\n            rewardPercent\\n            rewardAmountPerOrder\\n            rewardAmountPerItem\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    reviews {\\n      ratingsReviews {\\n        averageRating\\n        totalReviews\\n        __typename\\n      }\\n      __typename\\n    }\\n    badges(storeId: $storeId) {\\n      color\\n      creativeImageUrl\\n      endDate\\n      label\\n      message\\n      name\\n      timerDuration\\n      __typename\\n    }\\n    installServices {\\n      scheduleAMeasure\\n      __typename\\n    }\\n    specificationGroup {\\n      specTitle\\n      specifications {\\n        specName\\n        specValue\\n        __typename\\n      }\\n      __typename\\n    }\\n    subscription {\\n      defaultfrequency\\n      discountPercentage\\n      subscriptionEnabled\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}' \n");
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    //now that we have the JSON response from the Home Depot endpoint, we gotta parse it
		    System.out.println(response.toString());
		    JSONObject obj=new JSONObject(response.toString());
		    return obj.getJSONObject("data").getJSONObject("product").getJSONObject("pricing").getDouble("value");
		  } catch (Exception e) {
		    e.printStackTrace();
		    return 0.0;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}
	
	/**
	 * Gets the price of a certain product, by productStirng 
	 * @param productString a string like tertial-work-lamp-with-led-bulb-dark-gray-00424985 that indicates which product to hit. Can be mined off of the URL
	 * @return the price of the item from ikea's website
	 * @throws IOException
	 */
	private static double getIkeaPrice(String productString) throws IOException
	{
		Document doc=Jsoup.connect("https://www.ikea.com/us/en/p/"+productString+"/").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36").referrer("https://www.google.com").get();
		Element dollars=doc.select(".js-price-package.range-revamp-pip-price-package .range-revamp-price__integer").get(0);
		Element cents=doc.select(".js-price-package.range-revamp-pip-price-package .range-revamp-price__decimals").get(0);
		return Double.parseDouble(dollars.text()+cents.text());
	}
	
	public static double getPrice(ApplianceStore store,Object product) throws IOException 
	{ //TODO: implement when we have a stable LowesGet, otherwise there's no point
		if(product instanceof Table)
		{
			switch(store)
			{
			case HomeDepot:
				return HomeDepotPost("100070209");
			case Ikea:
				return 0.0;
			case Lowes:
				//GET https://www.lowes.com/pd/1000405513/productdetail/1803/Guest
			default:
				break;
			}
		}
		if(product instanceof Slat || product instanceof Leg)
		{
			switch(store)
			{
			case HomeDepot:
				return HomeDepotPost("100070209");
			case Ikea:
				return 0.0;
			case Lowes:
				return LowesGet("1001134500","1803"); //an extra 2 feet of wood but not out-of-stack
				//GET https://www.lowes.com/pd/1000405513/productdetail/1803/Guest
			default:
				break;
			}
		}		
		return 0.0d;
	}

	public static void main(String[] args)
	{
		System.out.println("Most of our methods can support different Item IDs, and for Lowes store IDs");
		System.out.println("Get the price from Ikea for the Lamp:");
		try{
			System.out.println(getIkeaPrice("tertial-work-lamp-with-led-bulb-dark-gray-00424985"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		System.out.println("Get the price from Home Depot for the tabletop");
		System.out.println(HomeDepotPost("100070209"));
		
		System.out.println("Get the price from Lowes for some two-by-fours (sometimes fails)");
		try {
			System.out.println(LowesGet("1001134500","1803"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			getPrice(ApplianceStore.HomeDepot);
			//getPrice(ApplianceStore.HomeDepot);
			//System.out.println(getPrice(ApplianceStore.Ikea));
			System.out.println(getPrice(ApplianceStore.Lowes));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}

//https://www.lowes.com/pd/1000405513/productdetail/1803/Guest
//returns: {"productDetails":{"1000405513":{"product":{"omniItemId":"1000405513","barcode":"689110587559","categories":{"204616":"DOTCOM_DIMENSIONAL_LUMBER"},"createdDate":"2018-03-02T00:00:00.000Z","description":"2-in x 4-in x 8-ft Cedar Lumber","energyStar":false,"endDate":"9999-12-31T00:00:00.000Z","imageName":"689110587559","imageUrls":[{"key":"baseUrl","value":"/product/converted/689110/689110587559.jpg"},{"key":"sm","value":"/product/converted/689110/689110587559sm.jpg"},{"key":"md","value":"/product/converted/689110/689110587559md.jpg"},{"key":"lg","value":"/product/converted/689110/689110587559lg.jpg"},{"key":"xl","value":"/product/converted/689110/689110587559xl.jpg"}],"interpackDesc":"TEST","interPackQty":0,"interpackCode":"TE","isPublished":"Y","isBuyable":"Y","itemNumber":"58755","productStatus":true,"hazmatCode":"N","inventoryBuffer":1,"installAvailInd":false,"language":-1,"lastModifiedDate":"2020-11-19T10:04:31.316Z","lastModifiedBy":"PRCSYNC","leadCapture":false,"lowesExclusive":false,"marketingBullets":[{"key":"1","value":"Common: 2-in x 4-in x 8-ft; actual: 1.5-in x 3.5-in x 8-ft"},{"key":"2","value":"Natural beauty of Western Red Cedar"},{"key":"3","value":"Easily takes paints and stains"},{"key":"4","value":"Resists mold and decay"},{"key":"5","value":"Resists warping and cracking"}],"maxPurQty":0,"modelId":"2X48S4SEE","orderItemMulQty":0,"orderItemMinQty":0,"pdURL":"/pd/2-in-x-4-in-x-8-ft-Cedar-Deck-Board-Common-1-5-in-x-3-5-in-x-8-ft-Actual/1000405513","productTypeCode":11,"rating":"4.3243","reviewCount":"37","romanceCopy":"Cedar Decking adds a rich, natural beauty to your home and can be finished to complement any surrounding. Structurally sound and naturally resistant to rot and decay, Top Choice Cedar is a sustainable grown, non-composite and untreated product, and is 100% recyclable and renewable.","rollupIndicator":false,"status":"ACTIVE","startDate":"2018-03-02T00:00:00.000Z","sellingRestriction":false,"sosFreightType":"Pre-Paid","specs":[{"key":"Series Name","value":"N/A","seq":2},{"key":"Wood Species","value":"Cedar","seq":3},{"key":"Hardwood/Softwood","value":"Softwood","seq":4},{"key":"Type","value":"Deck board","seq":5},{"key":"Industry Standard Min Thickness (Inches)","value":"1.5","seq":9},{"key":"Industry Standard Minimum Width (Inches)","value":"3.5","seq":10},{"key":"Industry Standard Minimum Length (Feet)","value":"8","seq":11},{"key":"Common Measurement (T x W)","value":"2-in x 4-in","seq":12},{"key":"Grade","value":"N/A","seq":14},{"key":"Dressing","value":"S4S","seq":15},{"key":"For Use with Framing","value":"No","seq":16},{"key":"For Use with Floors & Ceilings","value":"No","seq":17},{"key":"For Use with Woodworking Projects","value":"Yes","seq":18},{"key":"For Use with Wall Paneling","value":"No","seq":19},{"key":"For Use with Furniture & Cabinets","value":"No","seq":20},{"key":"For Use with Shelving","value":"No","seq":21},{"key":"For Use with Landscaping","value":"Yes","seq":22},{"key":"For Use with Decking","value":"Yes","seq":23},{"key":"For Use with Fencing","value":"No","seq":24},{"key":"Recommended for Ground Contact","value":"No","seq":29},{"key":"Green or Kiln-Dried","value":"Green","seq":32},{"key":"Warranty","value":"None","seq":34},{"key":"CA Residents: Prop 65 Warning(s)","value":"No","seq":101},{"key":"UNSPSC","value":"30103600","seq":101},{"key":"Meets AWPA Standards","value":"No","seq":101},{"key":"Common Length Measurement","value":"8-ft","seq":101}],"type":"LOCALONLY","vendorNumber":"40503","vendorDirect":"0","vendorLeadTime":-1,"weight":"8.53","title":" 2-in x 4-in x 8-ft Cedar Lumber","isArchived":false,"isGiftCard":false,"isNotAvailable":false,"isCDPNotAvailable":false,"isSOS":false,"isOOS":true,"showATC":false,"isRestriction":false,"restrictionDetails":{},"zippedInExperience":"1803","isExclusive":false,"productImages":{"isNLP":false}},"itemInventory":{"requiredQuantity":1,"analyticsData":{"parcel":{"fullMtdMsg":"Parcel","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":1,"deliveryMethodName":"Parcel Shipping","availabileQuantity":0,"availableQuantity":0},"pickup":{"fullMtdMsg":"Pickup","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":2,"deliveryMethodName":"Store Pickup","availabileQuantity":0,"availableQuantity":0},"truck":{"fullMtdMsg":"Delivery","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":3,"deliveryMethodName":"Truck Delivery","availabileQuantity":0,"availableQuantity":0}},"inventoryDisplay":{"pickup":{"availabilityStatus":false},"delivery":{"availabilityStatus":false}},"totalAvailableQty":0},"price":{"displayPriceType":"selling","analyticsData":{"sellingPrice":11.77,"retailPrice":11.77},"isDisplayOnly":false,"displayType":"REGULAR","isRegular":true,"isSOS":false,"isLowerPriceInCart":false,"isStrikeThroughPriceIndicator":false,"mapPrice":null,"wasPrice":null,"displayPrice":{"cent":"77","dollar":"11"},"itemPrice":11.77},"additionalServices":false,"productAssoc":{},"restriction":{},"locator":{"lookupStatus":"OK","storeProductLocations":[{"storeId":1803,"productId":58755,"productLocation":[]}]},"lacPromotions":[{"promoId":"2500000001","promoType":"FinanceLevelDiscount","promoName":"GEN-3-6-2020","displayPriority":4,"promoGroupCode":"FinanceLevelPromotion","startDateTime":"2020-01-01 00:00:00","endDateTime":"2020-12-31 23:59:00","financeTermType":"GEN","financeTermId":"GEN-3-6-2020","financeTypeId":0,"financeThresholdAmount":299,"financeMonths":6,"financePromoPercent":"5","financeAnualPercentRate":0},{"promoId":"2500000002","promoType":"FinanceLevelDiscount","promoName":"PROJ-2000-84-2020","displayPriority":5,"promoGroupCode":"FinanceLevelPromotion","startDateTime":"2020-01-01 00:00:00","endDateTime":"2020-12-31 23:59:00","financeTermType":"GEN","financeTermId":"PROJ-2000-84-2020","financeTypeId":1,"financeThresholdAmount":2000,"financeMonths":84,"financePromoPercent":"5","financeAnualPercentRate":7.99}],"financeMobileBanner":"/content/banners/mobile/Credit/GEN-3-6-2020/_jcr_content/par.mobile.esi.html","financeDesktopBanner":"/content/banners/desktop/Credit/GEN-3-6-2020/_jcr_content/par.desktop.esi.html"}},"productId":"1000405513","storeDetails":{"city":"Culpeper","phone":"(540) 812-9000","state":"VA","lat":"38.48555423","long":"-77.97161802","zipCode":"22701","storeName":"Culpeper Lowe's","storeNumber":"1803"},"inventory":{"requiredQuantity":1,"analyticsData":{"parcel":{"fullMtdMsg":"Parcel","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":1,"deliveryMethodName":"Parcel Shipping","availabileQuantity":0,"availableQuantity":0},"pickup":{"fullMtdMsg":"Pickup","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":2,"deliveryMethodName":"Store Pickup","availabileQuantity":0,"availableQuantity":0},"truck":{"fullMtdMsg":"Delivery","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":3,"deliveryMethodName":"Truck Delivery","availabileQuantity":0,"availableQuantity":0}},"inventoryDisplay":{"pickup":{"availabilityStatus":false},"delivery":{"availabilityStatus":false}},"totalAvailableQty":0},"ratings":{"1000405513":{"rating":"4.5","reviewCount":"37"}},"associatedServices":[]}


//
//curl 'https://www.homedepot.com/product-information/model' \
//-H 'authority: www.homedepot.com' \
//-H 'accept: */*' \
//-H 'x-debug: false' \
//-H 'dnt: 1' \
//-H 'x-experience-name: general-merchandise' \
//-H 'x-api-cookies: {"x-user-id":"dbb69e11-75fb-f0d1-a940-9926445ce257"}' \
//-H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36' \
//-H 'content-type: application/json' \
//-H 'origin: https://www.homedepot.com' \
//-H 'sec-fetch-site: same-origin' \
//-H 'sec-fetch-mode: cors' \
//-H 'sec-fetch-dest: empty' \
//-H 'referer: https://www.homedepot.com/p/Veranda-Melamine-White-Panel-Common-3-4-in-x-4-ft-x-8-ft-Actual-750-in-x-49-in-x-97-in-461877/100070209' \
//-H 'accept-language: en-US,en;q=0.9,es;q=0.8' \
//-H 'sec-gpc: 1' \
//--data-binary $'{"operationName":"clientOnlyProduct","variables":{"itemId":"100070209","storeId":"4601","zipCode":"22030"},"query":"query clientOnlyProduct($storeId: String, $zipCode: String, $itemId: String\u0021) {\\n  product(itemId: $itemId) {\\n    itemId\\n    dataSources\\n    info {\\n      recommendationFlags {\\n        visualNavigation\\n        __typename\\n      }\\n      replacementOMSID\\n      hasSubscription\\n      minimumOrderQuantity\\n      projectCalculatorEligible\\n      productDepartment\\n      classNumber\\n      subClassNumber\\n      calculatorType\\n      isLiveGoodsProduct\\n      protectionPlanSku\\n      hasServiceAddOns\\n      consultationType\\n      __typename\\n    }\\n    availabilityType {\\n      discontinued\\n      type\\n      status\\n      buyable\\n      __typename\\n    }\\n    fulfillment(storeId: $storeId, zipCode: $zipCode) {\\n      backordered\\n      fulfillmentOptions {\\n        type\\n        fulfillable\\n        services {\\n          type\\n          locations {\\n            isAnchor\\n            inventory {\\n              isLimitedQuantity\\n              isOutOfStock\\n              isInStock\\n              quantity\\n              isUnavailable\\n              maxAllowedBopisQty\\n              minAllowedBopisQty\\n              __typename\\n            }\\n            type\\n            storeName\\n            locationId\\n            curbsidePickupFlag\\n            isBuyInStoreCheckNearBy\\n            distance\\n            state\\n            storePhone\\n            __typename\\n          }\\n          deliveryTimeline\\n          deliveryDates {\\n            startDate\\n            endDate\\n            __typename\\n          }\\n          deliveryCharge\\n          dynamicEta {\\n            hours\\n            minutes\\n            __typename\\n          }\\n          hasFreeShipping\\n          freeDeliveryThreshold\\n          totalCharge\\n          __typename\\n        }\\n        __typename\\n      }\\n      anchorStoreStatus\\n      anchorStoreStatusType\\n      backorderedShipDate\\n      bossExcludedShipStates\\n      excludedShipStates\\n      seasonStatusEligible\\n      onlineStoreStatus\\n      onlineStoreStatusType\\n      inStoreAssemblyEligible\\n      __typename\\n    }\\n    seoDescription\\n    identifiers {\\n      brandName\\n      productLabel\\n      productType\\n      storeSkuNumber\\n      isSuperSku\\n      parentId\\n      sampleId\\n      modelNumber\\n      __typename\\n    }\\n    media {\\n      images {\\n        url\\n        sizes\\n        type\\n        subType\\n        __typename\\n      }\\n      __typename\\n    }\\n    pricing(storeId: $storeId) {\\n      value\\n      alternatePriceDisplay\\n      unitOfMeasure\\n      alternate {\\n        bulk {\\n          thresholdQuantity\\n          value\\n          __typename\\n        }\\n        unit {\\n          caseUnitOfMeasure\\n          unitsPerCase\\n          value\\n          __typename\\n        }\\n        __typename\\n      }\\n      original\\n      promotion {\\n        experienceTag\\n        subExperienceTag\\n        anchorItemList\\n        itemList\\n        reward {\\n          tiers {\\n            minPurchaseAmount\\n            minPurchaseQuantity\\n            rewardPercent\\n            rewardAmountPerOrder\\n            rewardAmountPerItem\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    reviews {\\n      ratingsReviews {\\n        averageRating\\n        totalReviews\\n        __typename\\n      }\\n      __typename\\n    }\\n    badges(storeId: $storeId) {\\n      color\\n      creativeImageUrl\\n      endDate\\n      label\\n      message\\n      name\\n      timerDuration\\n      __typename\\n    }\\n    installServices {\\n      scheduleAMeasure\\n      __typename\\n    }\\n    specificationGroup {\\n      specTitle\\n      specifications {\\n        specName\\n        specValue\\n        __typename\\n      }\\n      __typename\\n    }\\n    subscription {\\n      defaultfrequency\\n      discountPercentage\\n      subscriptionEnabled\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n"}' \
//	--compressed

//{"data":{"product":{"itemId":"100070209","dataSources":"catalog","info":{"recommendationFlags":{"visualNavigation":false,"__typename":"RecommendationFlags"},"replacementOMSID":null,"hasSubscription":false,"minimumOrderQuantity":0,"projectCalculatorEligible":false,"productDepartment":"21","classNumber":"5","subClassNumber":"19","calculatorType":null,"isLiveGoodsProduct":false,"protectionPlanSku":null,"hasServiceAddOns":false,"consultationType":null,"__typename":"Info"},"availabilityType":{"discontinued":false,"type":"Store Only","status":false,"buyable":true,"__typename":"AvailabilityType"},"fulfillment":{"backordered":false,"fulfillmentOptions":[{"type":"pickup","fulfillable":true,"services":[{"type":"bopis","locations":[{"isAnchor":true,"inventory":{"isLimitedQuantity":false,"isOutOfStock":false,"isInStock":true,"quantity":27,"isUnavailable":false,"maxAllowedBopisQty":null,"minAllowedBopisQty":null,"__typename":"Inventory"},"type":"store","storeName":"Fairfax","locationId":"4601","curbsidePickupFlag":true,"isBuyInStoreCheckNearBy":null,"distance":null,"state":"VA","storePhone":"(703)266-9800","__typename":"Location"}],"deliveryTimeline":null,"deliveryDates":null,"deliveryCharge":null,"dynamicEta":null,"hasFreeShipping":false,"freeDeliveryThreshold":null,"totalCharge":null,"__typename":"Service"}],"__typename":"FulfillmentOption"},{"type":"delivery","fulfillable":true,"services":[{"type":"express delivery","locations":[{"isAnchor":false,"inventory":null,"type":"store","storeName":null,"locationId":"4601","curbsidePickupFlag":null,"isBuyInStoreCheckNearBy":null,"distance":null,"state":null,"storePhone":null,"__typename":"Location"}],"deliveryTimeline":"TOMORROW","deliveryDates":{"startDate":"2020-12-05","endDate":"2020-12-05","__typename":"DeliveryDate"},"deliveryCharge":"79.0","dynamicEta":{"hours":null,"minutes":null,"__typename":"DynamicEta"},"hasFreeShipping":false,"freeDeliveryThreshold":null,"totalCharge":79,"__typename":"Service"}],"__typename":"FulfillmentOption"}],"anchorStoreStatus":true,"anchorStoreStatusType":"ACTIVE","backorderedShipDate":null,"bossExcludedShipStates":"AK,GU,HI,PR,VI","excludedShipStates":"AK,GU,HI,PR,VI","seasonStatusEligible":null,"onlineStoreStatus":false,"onlineStoreStatusType":"DELETED","inStoreAssemblyEligible":false,"__typename":"Fulfillment"},"seoDescription":"Melamine White Panel is ideal for making cabinet carcass, desk tops, closets, store fixtures and furniture. Easy to maintain and clean.","identifiers":{"brandName":"Veranda","productLabel":"Melamine White Panel (Common: 3/4 in. x 4 ft. x 8 ft.; Actual: .750 in. x 49 in. x 97 in.)","productType":"MERCHANDISE","storeSkuNumber":"461877","isSuperSku":false,"parentId":null,"sampleId":null,"modelNumber":"461877","__typename":"Identifiers"},"media":{"images":[{"url":"https://images.homedepot-static.com/productImages/604278a9-44ba-40b7-b3b0-ed0d15b2cac1/svn/veranda-mdf-461877-64_<SIZE>.jpg","sizes":["65","100","145","300","400","600","1000"],"type":"IMAGE","subType":"PRIMARY","__typename":"Image"}],"__typename":"Media"},"pricing":{"value":28.97,"alternatePriceDisplay":false,"unitOfMeasure":"each","alternate":{"bulk":null,"unit":{"caseUnitOfMeasure":null,"unitsPerCase":null,"value":null,"__typename":"UnitPricing"},"__typename":"Alternate"},"original":null,"promotion":null,"__typename":"Pricing"},"reviews":{"ratingsReviews":{"averageRating":"4.346","totalReviews":"315","__typename":"RatingsReviews"},"__typename":"Reviews"},"badges":[],"installServices":{"scheduleAMeasure":false,"__typename":"InstallServices"},"specificationGroup":[{"specTitle":"Details","specifications":[{"specName":"Engineered Wood Type","specValue":"Particle Board/MDF","__typename":"Specification"},{"specName":"Span Rating","specValue":"Not Applicable","__typename":"Specification"},{"specName":"Weather Exposure","specValue":"Interior","__typename":"Specification"},{"specName":"Plywood Features","specValue":"None","__typename":"Specification"}],"__typename":"SpecificationGroup"},{"specTitle":"Warranty / Certifications","specifications":[{"specName":"Manufacturer Warranty","specValue":"See store for details.","__typename":"Specification"}],"__typename":"SpecificationGroup"},{"specTitle":"Dimensions","specifications":[{"specName":"Nominal Product Thickness (in.)","specValue":"3/4","__typename":"Specification"},{"specName":"Actual Product Length (in.)","specValue":"97.","__typename":"Specification"},{"specName":"Actual Product Thickness (in.)","specValue":".75","__typename":"Specification"},{"specName":"Actual Product Width (in.)","specValue":"49","__typename":"Specification"},{"specName":"Nominal Product Length (ft.)","specValue":"8","__typename":"Specification"},{"specName":"Nominal Product Width (ft.)","specValue":"4","__typename":"Specification"}],"__typename":"SpecificationGroup"}],"subscription":null,"__typename":"BaseProduct"}}}
	
