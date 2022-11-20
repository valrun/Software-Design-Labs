//package softwareDesign.lab2
//
//import com.twitter.clientlib.ApiException
//import com.twitter.clientlib.api.TwitterApi
//import com.twitter.clientlib.auth.*
//import com.twitter.clientlib.model.*
//import java.time.OffsetDateTime
//import java.util.*
//
//object Example {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        // Set the credentials based on the API's "security" tag values.
//        // Check the API definition in https://api.twitter.com/2/openapi.json
//        // When multiple options exist, the SDK supports only "OAuth2UserToken" or "BearerToken"
//
//        // Uncomment and set the credentials configuration
//
//        // Configure HTTP bearer authorization:
//        // TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(System.getenv("TWITTER_BEARER_TOKEN"));
//        val apiInstance = TwitterApi(credentials)
//
//        // Set the params values
//        val query =
//            "(from:TwitterDev OR from:TwitterAPI) has:media -is:retweet" // String | One query/rule/filter for matching Tweets. Refer to https://t.co/rulelength to identify the max query length.
//        val startTime =
//            OffsetDateTime.now() // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The oldest UTC timestamp (from most recent 7 days) from which the Tweets will be provided. Timestamp is in second granularity and is inclusive (i.e. 12:00:01 includes the first second of the minute).
//        val endTime =
//            OffsetDateTime.now() // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The newest, most recent UTC timestamp to which the Tweets will be provided. Timestamp is in second granularity and is exclusive (i.e. 12:00:01 excludes the first second of the minute).
//        val sinceId =
//            "sinceId_example" // String | Returns results with a Tweet ID greater than (that is, more recent than) the specified ID.
//        val untilId =
//            "untilId_example" // String | Returns results with a Tweet ID less than (that is, older than) the specified ID.
//        val nextToken =
//            "nextToken_example" // String | This parameter is used to get the next 'page' of results. The value used with the parameter is pulled directly from the response provided by the API, and should not be modified.
//        val paginationToken =
//            "paginationToken_example" // String | This parameter is used to get the next 'page' of results. The value used with the parameter is pulled directly from the response provided by the API, and should not be modified.
//        val granularity = "minute" // String | The granularity for the search counts results.
//        val searchCountFields: Set<String> =
//            HashSet(Arrays.asList()) // Set<String> | A comma separated list of SearchCount fields to display.
//        try {
//            val result: Get2TweetsCountsRecentResponse = apiInstance.tweets().tweetCountsRecentSearch(query)
//                .startTime(startTime)
//                .endTime(endTime)
//                .sinceId(sinceId)
//                .untilId(untilId)
//                .nextToken(nextToken)
//                .paginationToken(paginationToken)
//                .granularity(granularity)
//                .searchCountFields(searchCountFields)
//                .execute()
//            System.out.println(result)
//        } catch (e: ApiException) {
//            System.err.println("Exception when calling TweetsApi#tweetCountsRecentSearch")
//            System.err.println("Status code: " + e.getCode())
//            System.err.println("Reason: " + e.getResponseBody())
//            System.err.println("Response headers: " + e.getResponseHeaders())
//            e.printStackTrace()
//        }
//    }
//}