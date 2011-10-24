package com.jbacon.passwordstorage.backend.encryption.error;

public class UnlimitedJcePoliciesNotInstalledException extends AbstractEncrypterException {

	private static final long serialVersionUID = 4876978104021014048L;

	public UnlimitedJcePoliciesNotInstalledException() {
		super("Unlimited JCE Policies are not installed.");
	}

	public UnlimitedJcePoliciesNotInstalledException(final Throwable throwable) {
		super("Unlimited JCE Policies are not installed.", throwable);
	}

}
