/*
 * Copyright 2013 EMC Corporation. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.emc.vipr.s3.sample;

import com.emc.vipr.services.s3.ViPRS3Client;

public class _0_GetServiceInfo {

	public static void main(String[] args) throws Exception {
    	// create the ViPR S3 Client
    	ViPRS3Client s3 = ViPRS3Factory.getS3Client();

    	// get service name
    	String service = s3.getServiceName();

    	// print out the service name and endpoint for validation
    	System.out.println( String.format("Successfully connected to ViPR using the [%s] service at [%s]", 
    			service, ViPRS3Factory.S3_ENDPOINT));
    }
}
