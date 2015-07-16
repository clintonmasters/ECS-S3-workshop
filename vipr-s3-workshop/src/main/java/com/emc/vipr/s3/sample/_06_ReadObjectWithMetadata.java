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
import java.util.Map;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.emc.vipr.services.s3.ViPRS3Client;

public class _06_ReadObjectWithMetadata {

	public static void main(String[] args) throws Exception {
    	// create the ViPR S3 Client
    	ViPRS3Client s3 = ViPRS3Factory.getS3Client();

    	// retrieve the object key from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        
        // read the specified object from the demo bucket
        S3Object object = s3.getObject(ViPRS3Factory.S3_BUCKET, key);

        // get the metadata for the object
        ObjectMetadata metadata = object.getObjectMetadata();
        
        // print out the object key/value and metadata for validation
    	System.out.println( String.format("Metadata for [%s/%s]",
    			ViPRS3Factory.S3_BUCKET, key));
        Map<String,String> metadataList = metadata.getUserMetadata();
        for (Map.Entry<String, String> entry : metadataList.entrySet())
        {
        	System.out.println(String.format("    %s = %s", entry.getKey(), entry.getValue()));
        }
    }
}
