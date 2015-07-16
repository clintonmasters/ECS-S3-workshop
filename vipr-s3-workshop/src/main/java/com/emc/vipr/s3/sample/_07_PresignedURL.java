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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.emc.vipr.services.s3.ViPRS3Client;

public class _07_PresignedURL {

	public static void main(String[] args) throws Exception {
    	// create the ViPR S3 Client
    	ViPRS3Client s3 = ViPRS3Factory.getS3Client();

    	// retrieve the key value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        
        // retrieve the expiration time for the object from user
        System.out.print( "How many hours should this URL be valid? " );
        String hours = new BufferedReader( new InputStreamReader( System.in ) ).readLine();

        // convert hours to a date
        Date expiration = new Date();
        long curTime_msec = expiration.getTime();
        long nHours = Long.valueOf(hours);
        curTime_msec += 60 * 60 * 1000 * nHours;
        expiration.setTime(curTime_msec); 
        
        // generate the object's pre-signed URL
        GeneratePresignedUrlRequest presignedUrl = new GeneratePresignedUrlRequest(ViPRS3Factory.S3_BUCKET, key);
        presignedUrl.setMethod(HttpMethod.GET);
        presignedUrl.setExpiration(expiration);
        
        URL url = s3.generatePresignedUrl(presignedUrl);
        
        // print object's pre-signed URL
    	System.out.println( String.format("object [%s/%s] pre-signed URL: [%s]",
    			ViPRS3Factory.S3_BUCKET, key, url.toString()));
    }
}
