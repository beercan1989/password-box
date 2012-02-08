package com.jbacon.passwordstorage.password;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.sql.Timestamp;

import org.junit.Test;

import com.jbacon.passwordstorage.encryption.EncryptionType;

public class PasswordObjectsTest {

	private static final String PASSWORD_NAME = "password Name";
	private static final String PASSWORD_NOTES = "Password Notes";
	private static final String PASSWORD = "password";
	private static final String SALT = "salt";
	private static final String ENCRYPTED_SECRET_KEY = "encryptedSecretKey";
	private static final String PROFILE_NAME = "profileName";

	private static Timestamp getCreatedAt() {
		return new Timestamp(0);
	}

	private static Timestamp getUpdatedAt() {
		return new Timestamp(0);
	}

	@Test
	public void shouldCreateAFilledInMasterPassword() {
		MasterPassword masterPassword = new MasterPassword(PROFILE_NAME, ENCRYPTED_SECRET_KEY, SALT, getCreatedAt(), getUpdatedAt(), 1, EncryptionType.AES_256,
				EncryptionType.PBE_WITH_MD5_AND_DES);
		assertThat(masterPassword, is(not(nullValue())));
		assertThat(masterPassword.getCreatedAt(), is(equalTo(getCreatedAt())));
		assertThat(masterPassword.getUpdatedAt(), is(equalTo(getUpdatedAt())));
		assertThat(masterPassword.getId(), is(equalTo(1)));
		assertThat(masterPassword.getProfileName(), is(equalTo(PROFILE_NAME)));
		assertThat(masterPassword.getEncryptedSecretKey(), is(equalTo(ENCRYPTED_SECRET_KEY)));
		assertThat(masterPassword.getSalt(), is(equalTo(SALT)));
	}

	@Test
	public void shouldCreateAFilledInStoredPassword() {
		StoredPassword storedPassword = new StoredPassword(PASSWORD_NAME, PROFILE_NAME, PASSWORD, PASSWORD_NOTES, getCreatedAt(), getUpdatedAt(), 1);
		assertThat(storedPassword, is(not(nullValue())));
		assertThat(storedPassword.getCreatedAt(), is(equalTo(getCreatedAt())));
		assertThat(storedPassword.getUpdatedAt(), is(equalTo(getUpdatedAt())));
		assertThat(storedPassword.getId(), is(equalTo(1)));
		assertThat(storedPassword.getProfileName(), is(equalTo(PROFILE_NAME)));
		assertThat(storedPassword.getEncryptedPasswordName(), is(equalTo(PASSWORD_NAME)));
		assertThat(storedPassword.getEncryptedPasswordNotes(), is(equalTo(PASSWORD_NOTES)));
		assertThat(storedPassword.getEncryptedPassword(), is(equalTo(PASSWORD)));
	}

	@Test
	public void shouldCreateBlankMasterPassword() {
		MasterPassword masterPassword = new MasterPassword();
		assertThat(masterPassword, is(not(nullValue())));
		assertThat(masterPassword.getCreatedAt(), is(nullValue()));
		assertThat(masterPassword.getUpdatedAt(), is(nullValue()));
		assertThat(masterPassword.getId(), is(nullValue()));
		assertThat(masterPassword.getProfileName(), is(nullValue()));
		assertThat(masterPassword.getEncryptedSecretKey(), is(nullValue()));
		assertThat(masterPassword.getSalt(), is(nullValue()));

		masterPassword.setCreatedAt(getCreatedAt());
		masterPassword.setUpdatedAt(getUpdatedAt());
		masterPassword.setEncryptedSecretKey(ENCRYPTED_SECRET_KEY);
		masterPassword.setId(1);
		masterPassword.setProfileName(PROFILE_NAME);
		masterPassword.setSalt(SALT);

		assertThat(masterPassword.getCreatedAt(), is(equalTo(getCreatedAt())));
		assertThat(masterPassword.getUpdatedAt(), is(equalTo(getUpdatedAt())));
		assertThat(masterPassword.getId(), is(equalTo(1)));
		assertThat(masterPassword.getProfileName(), is(equalTo(PROFILE_NAME)));
		assertThat(masterPassword.getEncryptedSecretKey(), is(equalTo(ENCRYPTED_SECRET_KEY)));
		assertThat(masterPassword.getSalt(), is(equalTo(SALT)));
	}

	@Test
	public void shouldCreateBlankMasterStoredPassword() {
		StoredPassword storedPassword = new StoredPassword();
		assertThat(storedPassword, is(not(nullValue())));
		assertThat(storedPassword.getCreatedAt(), is(nullValue()));
		assertThat(storedPassword.getUpdatedAt(), is(nullValue()));
		assertThat(storedPassword.getId(), is(nullValue()));
		assertThat(storedPassword.getProfileName(), is(nullValue()));
		assertThat(storedPassword.getEncryptedPasswordName(), is(nullValue()));
		assertThat(storedPassword.getEncryptedPasswordNotes(), is(nullValue()));
		assertThat(storedPassword.getEncryptedPassword(), is(nullValue()));

		storedPassword.setCreatedAt(getCreatedAt());
		storedPassword.setUpdatedAt(getUpdatedAt());
		storedPassword.setId(1);
		storedPassword.setProfileName(PROFILE_NAME);
		storedPassword.setEncryptedPassword(PASSWORD);
		storedPassword.setEncryptedPasswordName(PASSWORD_NAME);
		storedPassword.setEncryptedPasswordNotes(PASSWORD_NOTES);

		assertThat(storedPassword.getCreatedAt(), is(equalTo(getCreatedAt())));
		assertThat(storedPassword.getUpdatedAt(), is(equalTo(getUpdatedAt())));
		assertThat(storedPassword.getId(), is(equalTo(1)));
		assertThat(storedPassword.getProfileName(), is(equalTo(PROFILE_NAME)));
		assertThat(storedPassword.getEncryptedPasswordName(), is(equalTo(PASSWORD_NAME)));
		assertThat(storedPassword.getEncryptedPasswordNotes(), is(equalTo(PASSWORD_NOTES)));
		assertThat(storedPassword.getEncryptedPassword(), is(equalTo(PASSWORD)));
	}

	@Test
	public void shouldHaveCustomToString() {
		StoredPassword storedPassword = new StoredPassword();
		MasterPassword masterPassword = new MasterPassword();

		assertThat(storedPassword.toString(), is(not(nullValue())));
		assertThat(storedPassword.toString(), containsString("encryptedPasswordName"));
		assertThat(masterPassword.toString(), is(not(nullValue())));
		// assertThat(masterPassword.toString(),
		// containsString("encryptedSecretKey"));
	}
}
