package com.jbacon.passwordstorage.backend.encryption;

public enum EncryptionType {

	AES_256("AES", 256, true), AES_128("AES", 128, true), PBE_WITH_MD5_AND_DES("PBE_WITH_MD5_AND_DES", 20, true),

	/** Unsupported - Test Value */
	UNSUPPORTED_TYPE("", 0, false);

	private String algorithm;
	private int keyLength;
	private boolean isSupported;
	private final String provider = "BC";

	private EncryptionType(final String algorithm, final Integer keyLength, final boolean isSupported) {
		this.algorithm = algorithm;
		this.keyLength = keyLength;
		this.isSupported = isSupported;
	}

	public String algorithm() {
		return algorithm;
	}

	public boolean isSupported() {
		return isSupported;
	}

	public int keyLength() {
		return keyLength;
	}

	public String provider() {
		return provider;
	}
}
