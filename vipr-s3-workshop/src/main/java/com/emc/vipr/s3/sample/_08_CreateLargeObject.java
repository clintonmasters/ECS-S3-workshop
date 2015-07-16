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

import com.amazonaws.services.s3.transfer.TransferManager;
import com.emc.vipr.services.s3.ViPRS3Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class _08_CreateLargeObject {

	public static void main(String[] args) throws Exception {
    	// create the ViPR S3 Client
    	ViPRS3Client s3 = ViPRS3Factory.getS3Client();

    	// retrieve object key/value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        System.out.println( "Enter the file location (C:\\Users\\vandrk\\EMC\\NameSpaceList.zip) :" );
        String filePath = new BufferedReader( new InputStreamReader( System.in ) ).readLine();

        TransferManager manager = new TransferManager(s3);
        manager.upload(ViPRS3Factory.S3_BUCKET, key, new File(filePath)).waitForUploadResult();

        // print bucket key/value and content for validation
    	System.out.println( String.format("completed mulit-part upload for object [%s/%s] with file path: [%s]",
    			ViPRS3Factory.S3_BUCKET, key, filePath));
    }
}
