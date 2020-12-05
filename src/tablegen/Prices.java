package tablegen;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Prices {
	
// curl 'https://www.homedepot.com/product-information/model' \
//  -H 'authority: www.homedepot.com' \
//  -H 'accept: */*' \
//  -H 'x-debug: false' \
//  -H 'dnt: 1' \
//  -H 'x-experience-name: general-merchandise' \
//  -H 'x-api-cookies: {"x-user-id":"dbb69e11-75fb-f0d1-a940-9926445ce257"}' \
//  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36' \
//  -H 'content-type: application/json' \
//  -H 'origin: https://www.homedepot.com' \
//  -H 'sec-fetch-site: same-origin' \
//  -H 'sec-fetch-mode: cors' \
//  -H 'sec-fetch-dest: empty' \
//  -H 'referer: https://www.homedepot.com/p/Veranda-Melamine-White-Panel-Common-3-4-in-x-4-ft-x-8-ft-Actual-750-in-x-49-in-x-97-in-461877/100070209' \
//  -H 'accept-language: en-US,en;q=0.9,es;q=0.8' \
//  -H 'sec-gpc: 1' \
//  --data-binary $'{"operationName":"clientOnlyProduct","variables":{"itemId":"100070209","storeId":"4601","zipCode":"22030"},"query":"query clientOnlyProduct($storeId: String, $zipCode: String, $itemId: String\u0021) {\\n  product(itemId: $itemId) {\\n    itemId\\n    dataSources\\n    info {\\n      recommendationFlags {\\n        visualNavigation\\n        __typename\\n      }\\n      replacementOMSID\\n      hasSubscription\\n      minimumOrderQuantity\\n      projectCalculatorEligible\\n      productDepartment\\n      classNumber\\n      subClassNumber\\n      calculatorType\\n      isLiveGoodsProduct\\n      protectionPlanSku\\n      hasServiceAddOns\\n      consultationType\\n      __typename\\n    }\\n    availabilityType {\\n      discontinued\\n      type\\n      status\\n      buyable\\n      __typename\\n    }\\n    fulfillment(storeId: $storeId, zipCode: $zipCode) {\\n      backordered\\n      fulfillmentOptions {\\n        type\\n        fulfillable\\n        services {\\n          type\\n          locations {\\n            isAnchor\\n            inventory {\\n              isLimitedQuantity\\n              isOutOfStock\\n              isInStock\\n              quantity\\n              isUnavailable\\n              maxAllowedBopisQty\\n              minAllowedBopisQty\\n              __typename\\n            }\\n            type\\n            storeName\\n            locationId\\n            curbsidePickupFlag\\n            isBuyInStoreCheckNearBy\\n            distance\\n            state\\n            storePhone\\n            __typename\\n          }\\n          deliveryTimeline\\n          deliveryDates {\\n            startDate\\n            endDate\\n            __typename\\n          }\\n          deliveryCharge\\n          dynamicEta {\\n            hours\\n            minutes\\n            __typename\\n          }\\n          hasFreeShipping\\n          freeDeliveryThreshold\\n          totalCharge\\n          __typename\\n        }\\n        __typename\\n      }\\n      anchorStoreStatus\\n      anchorStoreStatusType\\n      backorderedShipDate\\n      bossExcludedShipStates\\n      excludedShipStates\\n      seasonStatusEligible\\n      onlineStoreStatus\\n      onlineStoreStatusType\\n      inStoreAssemblyEligible\\n      __typename\\n    }\\n    seoDescription\\n    identifiers {\\n      brandName\\n      productLabel\\n      productType\\n      storeSkuNumber\\n      isSuperSku\\n      parentId\\n      sampleId\\n      modelNumber\\n      __typename\\n    }\\n    media {\\n      images {\\n        url\\n        sizes\\n        type\\n        subType\\n        __typename\\n      }\\n      __typename\\n    }\\n    pricing(storeId: $storeId) {\\n      value\\n      alternatePriceDisplay\\n      unitOfMeasure\\n      alternate {\\n        bulk {\\n          thresholdQuantity\\n          value\\n          __typename\\n        }\\n        unit {\\n          caseUnitOfMeasure\\n          unitsPerCase\\n          value\\n          __typename\\n        }\\n        __typename\\n      }\\n      original\\n      promotion {\\n        experienceTag\\n        subExperienceTag\\n        anchorItemList\\n        itemList\\n        reward {\\n          tiers {\\n            minPurchaseAmount\\n            minPurchaseQuantity\\n            rewardPercent\\n            rewardAmountPerOrder\\n            rewardAmountPerItem\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    reviews {\\n      ratingsReviews {\\n        averageRating\\n        totalReviews\\n        __typename\\n      }\\n      __typename\\n    }\\n    badges(storeId: $storeId) {\\n      color\\n      creativeImageUrl\\n      endDate\\n      label\\n      message\\n      name\\n      timerDuration\\n      __typename\\n    }\\n    installServices {\\n      scheduleAMeasure\\n      __typename\\n    }\\n    specificationGroup {\\n      specTitle\\n      specifications {\\n        specName\\n        specValue\\n        __typename\\n      }\\n      __typename\\n    }\\n    subscription {\\n      defaultfrequency\\n      discountPercentage\\n      subscriptionEnabled\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n"}' \
// 	--compressed

// {"data":{"product":{"itemId":"100070209","dataSources":"catalog","info":{"recommendationFlags":{"visualNavigation":false,"__typename":"RecommendationFlags"},"replacementOMSID":null,"hasSubscription":false,"minimumOrderQuantity":0,"projectCalculatorEligible":false,"productDepartment":"21","classNumber":"5","subClassNumber":"19","calculatorType":null,"isLiveGoodsProduct":false,"protectionPlanSku":null,"hasServiceAddOns":false,"consultationType":null,"__typename":"Info"},"availabilityType":{"discontinued":false,"type":"Store Only","status":false,"buyable":true,"__typename":"AvailabilityType"},"fulfillment":{"backordered":false,"fulfillmentOptions":[{"type":"pickup","fulfillable":true,"services":[{"type":"bopis","locations":[{"isAnchor":true,"inventory":{"isLimitedQuantity":false,"isOutOfStock":false,"isInStock":true,"quantity":27,"isUnavailable":false,"maxAllowedBopisQty":null,"minAllowedBopisQty":null,"__typename":"Inventory"},"type":"store","storeName":"Fairfax","locationId":"4601","curbsidePickupFlag":true,"isBuyInStoreCheckNearBy":null,"distance":null,"state":"VA","storePhone":"(703)266-9800","__typename":"Location"}],"deliveryTimeline":null,"deliveryDates":null,"deliveryCharge":null,"dynamicEta":null,"hasFreeShipping":false,"freeDeliveryThreshold":null,"totalCharge":null,"__typename":"Service"}],"__typename":"FulfillmentOption"},{"type":"delivery","fulfillable":true,"services":[{"type":"express delivery","locations":[{"isAnchor":false,"inventory":null,"type":"store","storeName":null,"locationId":"4601","curbsidePickupFlag":null,"isBuyInStoreCheckNearBy":null,"distance":null,"state":null,"storePhone":null,"__typename":"Location"}],"deliveryTimeline":"TOMORROW","deliveryDates":{"startDate":"2020-12-05","endDate":"2020-12-05","__typename":"DeliveryDate"},"deliveryCharge":"79.0","dynamicEta":{"hours":null,"minutes":null,"__typename":"DynamicEta"},"hasFreeShipping":false,"freeDeliveryThreshold":null,"totalCharge":79,"__typename":"Service"}],"__typename":"FulfillmentOption"}],"anchorStoreStatus":true,"anchorStoreStatusType":"ACTIVE","backorderedShipDate":null,"bossExcludedShipStates":"AK,GU,HI,PR,VI","excludedShipStates":"AK,GU,HI,PR,VI","seasonStatusEligible":null,"onlineStoreStatus":false,"onlineStoreStatusType":"DELETED","inStoreAssemblyEligible":false,"__typename":"Fulfillment"},"seoDescription":"Melamine White Panel is ideal for making cabinet carcass, desk tops, closets, store fixtures and furniture. Easy to maintain and clean.","identifiers":{"brandName":"Veranda","productLabel":"Melamine White Panel (Common: 3/4 in. x 4 ft. x 8 ft.; Actual: .750 in. x 49 in. x 97 in.)","productType":"MERCHANDISE","storeSkuNumber":"461877","isSuperSku":false,"parentId":null,"sampleId":null,"modelNumber":"461877","__typename":"Identifiers"},"media":{"images":[{"url":"https://images.homedepot-static.com/productImages/604278a9-44ba-40b7-b3b0-ed0d15b2cac1/svn/veranda-mdf-461877-64_<SIZE>.jpg","sizes":["65","100","145","300","400","600","1000"],"type":"IMAGE","subType":"PRIMARY","__typename":"Image"}],"__typename":"Media"},"pricing":{"value":28.97,"alternatePriceDisplay":false,"unitOfMeasure":"each","alternate":{"bulk":null,"unit":{"caseUnitOfMeasure":null,"unitsPerCase":null,"value":null,"__typename":"UnitPricing"},"__typename":"Alternate"},"original":null,"promotion":null,"__typename":"Pricing"},"reviews":{"ratingsReviews":{"averageRating":"4.346","totalReviews":"315","__typename":"RatingsReviews"},"__typename":"Reviews"},"badges":[],"installServices":{"scheduleAMeasure":false,"__typename":"InstallServices"},"specificationGroup":[{"specTitle":"Details","specifications":[{"specName":"Engineered Wood Type","specValue":"Particle Board/MDF","__typename":"Specification"},{"specName":"Span Rating","specValue":"Not Applicable","__typename":"Specification"},{"specName":"Weather Exposure","specValue":"Interior","__typename":"Specification"},{"specName":"Plywood Features","specValue":"None","__typename":"Specification"}],"__typename":"SpecificationGroup"},{"specTitle":"Warranty / Certifications","specifications":[{"specName":"Manufacturer Warranty","specValue":"See store for details.","__typename":"Specification"}],"__typename":"SpecificationGroup"},{"specTitle":"Dimensions","specifications":[{"specName":"Nominal Product Thickness (in.)","specValue":"3/4","__typename":"Specification"},{"specName":"Actual Product Length (in.)","specValue":"97.","__typename":"Specification"},{"specName":"Actual Product Thickness (in.)","specValue":".75","__typename":"Specification"},{"specName":"Actual Product Width (in.)","specValue":"49","__typename":"Specification"},{"specName":"Nominal Product Length (ft.)","specValue":"8","__typename":"Specification"},{"specName":"Nominal Product Width (ft.)","specValue":"4","__typename":"Specification"}],"__typename":"SpecificationGroup"}],"subscription":null,"__typename":"BaseProduct"}}}
	
	
	public static String executePost(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;

		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("authority", 
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
			        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36");
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
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
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
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}
	
	
	private static Document getDocument(ApplianceStore store,Object item) throws IOException
	{
		String url="";
		switch(store)
		{
		case HomeDepot:
			url="https://www.homedepot.com/p/Veranda-Melamine-White-Panel-Common-3-4-in-x-4-ft-x-8-ft-Actual-750-in-x-49-in-x-97-in-461877/100070209";
			break;
		case Ikea:
			url="https://www.ikea.com/us/en/p/tertial-work-lamp-with-led-bulb-dark-gray-00424985/";
			break;
		case Lowes:
			//curl 'https://www.lowes.com/pd/2-in-x-4-in-x-8-ft-Cedar-Deck-Board-Common-1-5-in-x-3-5-in-x-8-ft-Actual/1000405513' \
			//  -H 'authority: www.lowes.com' \
			//  -H 'cache-control: max-age=0' \
			//  -H 'dnt: 1' \
			//  -H 'upgrade-insecure-requests: 1' \
			//  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36' \
			//  -H 'accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
			//  -H 'sec-fetch-site: same-origin' \
			//  -H 'sec-fetch-mode: navigate' \
			//  -H 'sec-fetch-user: ?1' \
			//  -H 'sec-fetch-dest: document' \
			//  -H 'referer: https://www.lowes.com/pd/Top-Choice-Common-2-in-x-4-in-x-8-ft-Actual-1-5-in-x-3-5-in-x-8-ft-Lumber/1000192663' \
			//  -H 'accept-language: en-US,en;q=0.9,es;q=0.8' \
			//  -H 'cookie: bm_sz=2D9C5A0B97494DAA9D1CDC3385D1239A~YAAQXqomF/eVgSp2AQAAPPbULgru9OpUdey+5JxKW4Osd5FhmxMhEFtPSLLcS7TxkqGSsNocVgNfhCDtgSWysnER7zfRIgmM8nnuYMY3u00rDGoBqtMjccoqu6iS3ASchmTekGIchXYld52PeVewjB6N18IWBltUTtmjmOGyxS3vIP0v2t0+2UTqHrGoXRc=; dbidv2=a7f7ac01-fe82-43be-ba56-80a072a7ba62; AKA_A2=A; al_sess=FuA4EWsuT07UWryyq/3foMcYhqk+yWCXN76nJA4JRBQTiM9HGfqGvy9P+wHVxEHE; region=east; bm_mi=B253E258CF2182464440826059A98C16~bxB1suMh2musqw/FQg3FD5cDqw7gh6/+WFjEvgHYJBGREAWs7UQqwY+TG7i0112vAHzs4kIKl2OkUs9AuP0EzzBGpScGOR/PSTIvB+2+Dor1MGml0nXT7B6e+kt2M7cC/W/vuCGGeVavjYFkjQXS5W8AouDjQcVqUA1SSGvuFY0u1DR1kqWmn+Mi4w3WNeg4qIaJ0thgaodeRR72f9roVY/xbgEJ4bH1eCxCHTCA05Jp/hGAUP0bnw69qMt2K7gxz1rGUTy3zMfk8rNuBF3ycA==; check=true; mbox=session#383fe22239e047babd370be69e0c6c39#1607105339; user=%7B%22zipPrompt%22%3Atrue%7D; _gcl_au=1.1.1403149344.1607103479; s_ecid=MCMID%7C80685876407384310550997798367322872038; _abck=EECDDD60DCE8DAAFDF8A90A781E62658~0~YAAQZKomF4/ACBB2AQAA0P/ULgWIhaUzYNTwB/E4rCeuwnwgi2oGhSiASMlv6vFVh3AOkbd8esDsk4eP8sOE0545QJouafoYtSgx9GIQuEFAz6wKMOtY/phAfWXHq3R8QXhFvr/1tLEDBjZCNC9lNdwHDSenVEPKIM0BQwGRvOgnkoeY+KqeWpaWRCmtlLBm2bp8T3numcKU3TO4WQYR+mWJ+VU6gM2DNIv0RN0KRLASXiFzChCrheCraEDCZsSJht+KAIt2tNKFF0h4j9g4dbOwrgZWYqHY/qnYHkECQ8jhOydeu2rfYmhxoT+MEHEUtNfjU4bwsDtmJLCRtB6tFKyzTyKr~-1~-1~-1; AMCVS_5E00123F5245B2780A490D45%40AdobeOrg=1; AMCV_5E00123F5245B2780A490D45%40AdobeOrg=-1176276602%7CMCIDTS%7C18601%7CMCMID%7C80685876407384310550997798367322872038%7CMCAID%7CNONE%7CMCOPTOUT-1607110680s%7CNONE%7CMCAAMLH-1607708280%7C7%7CMCAAMB-1607708280%7Cj8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI; s_visit=1; ak_bmsc=4D9DBCBBDEED131B2B57662EDD4E5CFB1726AA64BB410000F573CA5F485C1456~plSckRnGatzMAtNtHAH5wbX5cKTQku3RKYiEQcJzSZ/dWAYaLQo2j4Tqh8pDQXthAr8WD9RWw1+9r/CezPwmxIWXQSMLg0u0jR8IO6Gaq30CVdW5f3LYbWlKfofV9ynw+GI06sVukmcd3YaitfMT/bkKnT5hepYCwkCf7svtjchA1lVF4x9wFraR2g3bMht6PO0j4BQUORYdam5znzOM6OqYX1oD+slRVgHqfv8rm5cNvrfk9KutO7FM+Q6L5K3vEt; REVLIFTER={"w":"0l554347-da15-447f-9091-6e8941024cdc","u":"ff51f63b-6e71-421c-bb4a-175c9f06995c","s":"a59c80fe-39cc-4524-a515-ccfdb99da645","se":1609695483}; IR_gbd=lowes.com; cd_user_id=1762ed50d97188-044df7eba2ab74-18356153-1fa400-1762ed50d98538; _fbp=fb.1.1607103483400.2115436647; LPVID=A4NTlhYmNmZWYwZTg4YjU4; LPSID-22554410=58Pcu9luTdO5qz05VEo3cQ; BYM=; sn=1803; s_eVar0=shp-_-c-_-prd-_-mlw-_-google-_-lia-_-122-_-constructionboards-_-50423222-_-0; p13n={%22zipCode%22:%2222701%22%2C%22storeId%22:%221803%22%2C%22state%22:%22VA%22%2C%22userType%22:%22diy%22}; _gcl_aw=GCL.1607103838.Cj0KCQiA2af-BRDzARIsAIVQUOendMuByzk_dnfNAsrbwqCvjZiuE77iZ2GMEBhjUdLZHIwTJENn5eYaAqYbEALw_wcB; _gcl_dc=GCL.1607103838.Cj0KCQiA2af-BRDzARIsAIVQUOendMuByzk_dnfNAsrbwqCvjZiuE77iZ2GMEBhjUdLZHIwTJENn5eYaAqYbEALw_wcB; BVBRANDID=c2dba7d4-1132-4d61-89a5-94b69637dc9e; BVBRANDSID=3eb6e010-ac38-438f-b77f-8438a7525c19; kampyleUserSession=1607103898033; kampyleUserSessionsCount=11; kampyleUserPercentile=17.248880027480062; productnum=23; akavpau_default=1607104241~id=3117e28f212d4fff281d810f675a1151; notice_behavior=implied,eu; s_sess=%20s_sq%3D%3B%20s_cc%3Dtrue%3B; _uetsid=7496a760365711eb953657829880389e; _uetvid=749778a0365711eb8f95ebff08687e51; sc_length=1020; IR_12374=1607103943306%7C0%7C1607103483044%7C%7C; kampyleSessionPageCounter=3; akaalb_prod_dual=1607190346~op=PROD_GCP_EAST_CTRL_C:PROD_EAST_C|PROD_GCP_EAST_CTRL_DFLT:PROD_DEFAULT_EAST|~rv=3~m=PROD_EAST_C:0|PROD_DEFAULT_EAST:0|~os=352fb8a62db4e37e16b221fb4cefd635~id=589b4af120fd93bfb4728b65eda6fbfc; s_pers=%20s_vnum%3D1638639480805%2526vn%253D1%7C1638639480805%3B%20gpv_page%3Dlowes%253Adt%253Abuilding_supplies%253Alumber_composites%253Aframing_lumber%253Adimensional_lumber%253Apdp%7C1607105746670%3B%20gpv_pgtype%3Dproduct-display%7C1607105746676%3B%20gpv_sec%3Dbuilding_supplies%7C1607105746686%3B%20s_invisit%3Dtrue%7C1607105746696%3B%20s_lv%3D1607103946702%7C1701711946702%3B%20s_lv_s%3DFirst%2520Visit%7C1607105746702%3B; bm_sv=D50D6A71B66AA551F071EB882564D952~lJIphlW567GCwHW9jv49rWO53SyGmQ209e7JjSDjJb+LkqMwtkJZGCtceIu2ECmy3FL3Ye7L7fAPPxsGLxqUk5XQb4iZHFWGdmJt6Oq1kXncu2uYgYu6ndqT92NqIe1hGrZ8yJ8YR90Gz0K1CCl9TPsi5ky/fe135HPEnE0k5Bc=; RT="z=1&dm=lowes.com&si=e609b007-4a3a-4012-a87c-a5ac42bed4db&ss=kiajwzbn&sl=5&tt=kdn&bcn=%2F%2Fb855d71c.akstat.io%2F&ul=igu1"; clickTracking=%7B%22prop9%22%3A%2226%7C44%7C17%7C3146%22%2C%22eVar6%22%3A%22lowes%3Adt%3Abuilding_supplies%3Alumber_composites%3Aframing_lumber%3Adimensional_lumber%3Apdp%22%2C%22eVar7%22%3A%22product-display%22%2C%22eVar8%22%3A%22building_supplies%22%7D' \
			//  -H 'sec-gpc: 1' \
			//  --compressed
			url="https://www.lowes.com/pd/1000405513/productdetail/1803/Guest";
			break;
		}
		return Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36").referrer("https://www.google.com").get();
	}
	
	private static double getPrice(ApplianceStore store) throws IOException
	{
		switch(store)
		{
		case HomeDepot:
			Elements priceComponents=getDocument(store,null).select(".price-format__large.price-format__main-price");
			for(Element price : priceComponents)
			{
				System.out.println(price.text());
				return Integer.parseInt(price.text().substring(1))/100d;
			}
			break;
		case Ikea:
			Element dollars=getDocument(store,null).select(".js-price-package.range-revamp-pip-price-package .range-revamp-price__integer").get(0);
			Element cents=getDocument(store,null).select(".js-price-package.range-revamp-pip-price-package .range-revamp-price__decimals").get(0);
			return Double.parseDouble(dollars.text()+cents.text());
		case Lowes:
			//GET https://www.lowes.com/pd/1000405513/productdetail/1803/Guest
			//returns: {"productDetails":{"1000405513":{"product":{"omniItemId":"1000405513","barcode":"689110587559","categories":{"204616":"DOTCOM_DIMENSIONAL_LUMBER"},"createdDate":"2018-03-02T00:00:00.000Z","description":"2-in x 4-in x 8-ft Cedar Lumber","energyStar":false,"endDate":"9999-12-31T00:00:00.000Z","imageName":"689110587559","imageUrls":[{"key":"baseUrl","value":"/product/converted/689110/689110587559.jpg"},{"key":"sm","value":"/product/converted/689110/689110587559sm.jpg"},{"key":"md","value":"/product/converted/689110/689110587559md.jpg"},{"key":"lg","value":"/product/converted/689110/689110587559lg.jpg"},{"key":"xl","value":"/product/converted/689110/689110587559xl.jpg"}],"interpackDesc":"TEST","interPackQty":0,"interpackCode":"TE","isPublished":"Y","isBuyable":"Y","itemNumber":"58755","productStatus":true,"hazmatCode":"N","inventoryBuffer":1,"installAvailInd":false,"language":-1,"lastModifiedDate":"2020-11-19T10:04:31.316Z","lastModifiedBy":"PRCSYNC","leadCapture":false,"lowesExclusive":false,"marketingBullets":[{"key":"1","value":"Common: 2-in x 4-in x 8-ft; actual: 1.5-in x 3.5-in x 8-ft"},{"key":"2","value":"Natural beauty of Western Red Cedar"},{"key":"3","value":"Easily takes paints and stains"},{"key":"4","value":"Resists mold and decay"},{"key":"5","value":"Resists warping and cracking"}],"maxPurQty":0,"modelId":"2X48S4SEE","orderItemMulQty":0,"orderItemMinQty":0,"pdURL":"/pd/2-in-x-4-in-x-8-ft-Cedar-Deck-Board-Common-1-5-in-x-3-5-in-x-8-ft-Actual/1000405513","productTypeCode":11,"rating":"4.3243","reviewCount":"37","romanceCopy":"Cedar Decking adds a rich, natural beauty to your home and can be finished to complement any surrounding. Structurally sound and naturally resistant to rot and decay, Top Choice Cedar is a sustainable grown, non-composite and untreated product, and is 100% recyclable and renewable.","rollupIndicator":false,"status":"ACTIVE","startDate":"2018-03-02T00:00:00.000Z","sellingRestriction":false,"sosFreightType":"Pre-Paid","specs":[{"key":"Series Name","value":"N/A","seq":2},{"key":"Wood Species","value":"Cedar","seq":3},{"key":"Hardwood/Softwood","value":"Softwood","seq":4},{"key":"Type","value":"Deck board","seq":5},{"key":"Industry Standard Min Thickness (Inches)","value":"1.5","seq":9},{"key":"Industry Standard Minimum Width (Inches)","value":"3.5","seq":10},{"key":"Industry Standard Minimum Length (Feet)","value":"8","seq":11},{"key":"Common Measurement (T x W)","value":"2-in x 4-in","seq":12},{"key":"Grade","value":"N/A","seq":14},{"key":"Dressing","value":"S4S","seq":15},{"key":"For Use with Framing","value":"No","seq":16},{"key":"For Use with Floors & Ceilings","value":"No","seq":17},{"key":"For Use with Woodworking Projects","value":"Yes","seq":18},{"key":"For Use with Wall Paneling","value":"No","seq":19},{"key":"For Use with Furniture & Cabinets","value":"No","seq":20},{"key":"For Use with Shelving","value":"No","seq":21},{"key":"For Use with Landscaping","value":"Yes","seq":22},{"key":"For Use with Decking","value":"Yes","seq":23},{"key":"For Use with Fencing","value":"No","seq":24},{"key":"Recommended for Ground Contact","value":"No","seq":29},{"key":"Green or Kiln-Dried","value":"Green","seq":32},{"key":"Warranty","value":"None","seq":34},{"key":"CA Residents: Prop 65 Warning(s)","value":"No","seq":101},{"key":"UNSPSC","value":"30103600","seq":101},{"key":"Meets AWPA Standards","value":"No","seq":101},{"key":"Common Length Measurement","value":"8-ft","seq":101}],"type":"LOCALONLY","vendorNumber":"40503","vendorDirect":"0","vendorLeadTime":-1,"weight":"8.53","title":" 2-in x 4-in x 8-ft Cedar Lumber","isArchived":false,"isGiftCard":false,"isNotAvailable":false,"isCDPNotAvailable":false,"isSOS":false,"isOOS":true,"showATC":false,"isRestriction":false,"restrictionDetails":{},"zippedInExperience":"1803","isExclusive":false,"productImages":{"isNLP":false}},"itemInventory":{"requiredQuantity":1,"analyticsData":{"parcel":{"fullMtdMsg":"Parcel","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":1,"deliveryMethodName":"Parcel Shipping","availabileQuantity":0,"availableQuantity":0},"pickup":{"fullMtdMsg":"Pickup","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":2,"deliveryMethodName":"Store Pickup","availabileQuantity":0,"availableQuantity":0},"truck":{"fullMtdMsg":"Delivery","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":3,"deliveryMethodName":"Truck Delivery","availabileQuantity":0,"availableQuantity":0}},"inventoryDisplay":{"pickup":{"availabilityStatus":false},"delivery":{"availabilityStatus":false}},"totalAvailableQty":0},"price":{"displayPriceType":"selling","analyticsData":{"sellingPrice":11.77,"retailPrice":11.77},"isDisplayOnly":false,"displayType":"REGULAR","isRegular":true,"isSOS":false,"isLowerPriceInCart":false,"isStrikeThroughPriceIndicator":false,"mapPrice":null,"wasPrice":null,"displayPrice":{"cent":"77","dollar":"11"},"itemPrice":11.77},"additionalServices":false,"productAssoc":{},"restriction":{},"locator":{"lookupStatus":"OK","storeProductLocations":[{"storeId":1803,"productId":58755,"productLocation":[]}]},"lacPromotions":[{"promoId":"2500000001","promoType":"FinanceLevelDiscount","promoName":"GEN-3-6-2020","displayPriority":4,"promoGroupCode":"FinanceLevelPromotion","startDateTime":"2020-01-01 00:00:00","endDateTime":"2020-12-31 23:59:00","financeTermType":"GEN","financeTermId":"GEN-3-6-2020","financeTypeId":0,"financeThresholdAmount":299,"financeMonths":6,"financePromoPercent":"5","financeAnualPercentRate":0},{"promoId":"2500000002","promoType":"FinanceLevelDiscount","promoName":"PROJ-2000-84-2020","displayPriority":5,"promoGroupCode":"FinanceLevelPromotion","startDateTime":"2020-01-01 00:00:00","endDateTime":"2020-12-31 23:59:00","financeTermType":"GEN","financeTermId":"PROJ-2000-84-2020","financeTypeId":1,"financeThresholdAmount":2000,"financeMonths":84,"financePromoPercent":"5","financeAnualPercentRate":7.99}],"financeMobileBanner":"/content/banners/mobile/Credit/GEN-3-6-2020/_jcr_content/par.mobile.esi.html","financeDesktopBanner":"/content/banners/desktop/Credit/GEN-3-6-2020/_jcr_content/par.desktop.esi.html"}},"productId":"1000405513","storeDetails":{"city":"Culpeper","phone":"(540) 812-9000","state":"VA","lat":"38.48555423","long":"-77.97161802","zipCode":"22701","storeName":"Culpeper Lowe's","storeNumber":"1803"},"inventory":{"requiredQuantity":1,"analyticsData":{"parcel":{"fullMtdMsg":"Parcel","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":1,"deliveryMethodName":"Parcel Shipping","availabileQuantity":0,"availableQuantity":0},"pickup":{"fullMtdMsg":"Pickup","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":2,"deliveryMethodName":"Store Pickup","availabileQuantity":0,"availableQuantity":0},"truck":{"fullMtdMsg":"Delivery","isAvlSts":false,"itmLdTmDays":0,"productStockType":"NON","availabilityStatus":"Not Available","deliveryMethodId":3,"deliveryMethodName":"Truck Delivery","availabileQuantity":0,"availableQuantity":0}},"inventoryDisplay":{"pickup":{"availabilityStatus":false},"delivery":{"availabilityStatus":false}},"totalAvailableQty":0},"ratings":{"1000405513":{"rating":"4.5","reviewCount":"37"}},"associatedServices":[]}
		default:
			break;
		}
		return 0.0d;
	}

	public static void main(String[] args)
	{

		System.out.println("Hi");
		//System.out.println(executePost("https://www.homedepot.com/product-information/model", null));      
		    
		
		/*try {
			getPrice(ApplianceStore.HomeDepot);

		try {
			//getPrice(ApplianceStore.HomeDepot);
			//System.out.println(getPrice(ApplianceStore.Ikea));
			System.out.println(getPrice(ApplianceStore.Lowes));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
