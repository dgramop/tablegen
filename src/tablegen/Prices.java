package tablegen;

import java.io.IOException;

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
		}
		return Jsoup.connect(url).get();
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
			break;
		default:
			break;
		}
		return 0.0d;
	}
	
	public static void main(String args[])
	{
		try {
			//getPrice(ApplianceStore.HomeDepot);
			System.out.println(getPrice(ApplianceStore.Ikea));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
