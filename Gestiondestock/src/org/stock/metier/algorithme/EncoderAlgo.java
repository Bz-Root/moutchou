package org.stock.metier.algorithme;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.security.MD5Encoder;

public class EncoderAlgo {
	public static String encodeMD5(String value) throws NoSuchAlgorithmException {
		byte[] bytesOfMessage = value.getBytes(StandardCharsets.UTF_8);
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		return MD5Encoder.encode(thedigest);
	}
}
