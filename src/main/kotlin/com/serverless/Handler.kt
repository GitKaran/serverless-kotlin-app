package com.serverless

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.apache.logging.log4j.LogManager
import java.util.*

/*
* Class Handler implements RequestHandler interface which contains single function handleRequest.
* We need to implement this function because it is the starting point of lambda execution.
* AWS executes this function by passing input and context.
* These objects are needed if you want to read input from the request and context is to get the context of execution
* environment.
* handleRequest function uses two utility classes Response.kt and ApiGatewayResponse.kt to construct the Response.
* In this case we are building the response by setting statusCode to 200 and Response with some text and adding custom
*  header ‘X-Powered-By’.
* This is a simple function with out much functionality it just responds with some text.
*
* */

class Handler:RequestHandler<Map<String, Any>, ApiGatewayResponse> {
    override fun handleRequest(input:Map<String, Any>, context:Context):ApiGatewayResponse {
        LOG.info("received: " + input.keys.toString())

        val responseBody = Response("Go Serverless v1.x! Your Kotlin function executed successfully!", input)
        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = responseBody
            headers = Collections.singletonMap<String, String>("X-Powered-By", "AWS Lambda & serverless")
        }
    }
    companion object {
        private val LOG = LogManager.getLogger(Handler::class.java)
    }
}