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
import java.io.IOException;
import java.io.InputStreamReader;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.StringInputStream;
import com.emc.vipr.services.s3.ViPRS3Client;

public class _09_UpdateAppendExtensions {

	public static void main(String[] args) throws Exception {
    	// create the ViPR S3 Client
    	ViPRS3Client s3 = ViPRS3Factory.getS3Client();

    	// object key to create, update, and append
    	String key = "update-append.txt";
    	String bucketName = ViPRS3Factory.S3_BUCKET;	// bucket to create object in
    	String content = "Hello World!";				// initial object content
    	int worldIndex = content.indexOf("World");		// Starting index
    	StringInputStream stream;
    			
        // first create an initial object
    	stream = new StringInputStream(content);
        System.out.println(String.format("creating initial object %s/%s", bucketName, key));
        s3.putObject(bucketName, key, stream, null);
        
        // read object and print content
        System.out.println(String.format("initial object %s/%s with content: [%s]",
                bucketName, key, readObject(s3, bucketName, key)));
        
        // update the object in the middle
        String content2 = "Universe!";
        System.out.println(String.format("updating object at offset %d", worldIndex));
        s3.updateObject(bucketName, key, new StringInputStream(content2),
                null, worldIndex);

        // read object and print content
        System.out.println(String.format("updated object %s/%s with content: [%s]",
                bucketName, key, readObject(s3, bucketName, key)));

        // append to the object
        String content3 = " ... and all!!";
        System.out.println(String.format("appending object at offset %d", content.length()));
        s3.updateObject(bucketName, key, new StringInputStream(content3),
                null, content.length());

        // read object and print content
        System.out.println(String.format("appended object %s/%s with content: [%s]",
                bucketName, key, readObject(s3, bucketName, key)));

        // create a sparse object by appending past the end of the object
        String content4 = "#last byte#";
        System.out.println(String.format("sparse append object at offset %d", 40));
        s3.updateObject(bucketName, key, new StringInputStream(content4),
                null, 40);

        // read object and print content
        System.out.println(String.format("sparse append object %s/%s with content: [%s]",
                bucketName, key, readObject(s3, bucketName, key)));

    }
	
	private static String readObject(ViPRS3Client s3, String bucketName, String key) throws IOException
	{
		// read the object from the demo bucket
		S3Object object = s3.getObject(bucketName, key);

		// convert object to a text string
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				object.getObjectContent()));
		String content = reader.readLine();
		return content;
	}
}
