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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import com.emc.vipr.services.s3.ViPRS3Client;
import org.apache.commons.codec.binary.Base64;

import com.amazonaws.auth.BasicAWSCredentials;

/**
 * Factory class to create the ViPR S3 client.  The client will be used in the examples for the
 * Java ViPR S3 interface.
 */
public class ViPRS3Factory {

  

	/* the S3 access key id - this is equivalent to the user */
	public static final String S3_ACCESS_KEY_ID = "root";
	
	/* the S3 secret key associated with the S3_ACCESS_KEY_ID */
    public static final String S3_SECRET_KEY = "lmKfrxxt+Cqzzt6B4Kimb3BznWNIW96g2RlXN27A";
    
    /* the end point of the ViPR S3 REST interface - this should take the form of
     * http[s]://object.vipronline.com:port */
    public static final String S3_ENDPOINT = "http://10.249.237.149:9020";
    
    /* a unique bucket name to store objects */
    public static final String S3_BUCKET = "ecs.s3.test.01";
    
    /* the optional namespace within ViPR - leave blank to use the default namespace */
//  public static final String S3_ViPR_NAMESPACE = "vipr.namespace";
//  public static final String S3_ViPR_NAMESPACE = null;	// use default namespace
    public static final String S3_ViPR_NAMESPACE = null;


    public static ViPRS3Client getS3Client() {
        BasicAWSCredentials creds = new BasicAWSCredentials(S3_ACCESS_KEY_ID, S3_SECRET_KEY);
		ViPRS3Client client = new ViPRS3Client(S3_ENDPOINT, creds);

		if (S3_ViPR_NAMESPACE != null) {
		    client.setNamespace(S3_ViPR_NAMESPACE);
		}

		return client;
    }


/*    private static void checkProxyConfig(AmazonS3Client client, Properties props) {
        String proxyHost = props.getProperty(PROP_PROXY_HOST);
        if (proxyHost != null && !proxyHost.isEmpty()) {
            int proxyPort = Integer.parseInt(props.getProperty(PROP_PROXY_PORT));
            ClientConfiguration config = new ClientConfiguration();
            config.setProxyHost(proxyHost);
            config.setProxyPort(proxyPort);
            client.setConfiguration(config);
        }
    }

*/
    
    // Generates a RSA key pair for testing.
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(1024, new SecureRandom());
            KeyPair myKeyPair = keyGenerator.generateKeyPair();

            // Serialize.
            byte[] pubKeyBytes = myKeyPair.getPublic().getEncoded();
            byte[] privKeyBytes = myKeyPair.getPrivate().getEncoded();

            String pubKeyStr = new String(Base64.encodeBase64(pubKeyBytes, false), "US-ASCII");
            String privKeyStr = new String(Base64.encodeBase64(privKeyBytes, false), "US-ASCII");

            System.out.println("Public Key: " + pubKeyStr);
            System.out.println("Private Key: " + privKeyStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
