package com.jbacon.passwordstorage.encryption;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public abstract class Encrypter {
	public Encrypter() {
		Security.addProvider(new BouncyCastleProvider());
	}
}
