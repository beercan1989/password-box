package com.jbacon.passwordstorage.imported.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.jbacon.passwordstorage.imported.encryption.errors.AbstractEncrypterException;

public class EncrypterPBE extends Encrypter {

    private static final String ITERATION_COUNT = "iterationCount";
    private final EncryptionType encryptionType;

    public EncrypterPBE(final EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }

    public byte[] doCiper(final EncryptionMode encryptionMode, final byte[] salt, final byte[] cipherText, final char[] passPhrase)
            throws AbstractEncrypterException {
        try {
            final Integer iterationCount = encryptionType.getSpecification().get(ITERATION_COUNT);
            final PBEParameterSpec parameterSpecification = new PBEParameterSpec(salt, iterationCount);
            final PBEKeySpec keySpecification = new PBEKeySpec(passPhrase);
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(encryptionType.algorithmName);
            final SecretKey secretKey = keyFactory.generateSecret(keySpecification);
            final Cipher cipher = Cipher.getInstance(encryptionType.algorithmName);
            cipher.init(encryptionMode.mode, secretKey, parameterSpecification);
            return cipher.doFinal(cipherText);
        } catch (final Exception e) {
            throw new AbstractEncrypterException(e);
        }
        // catch (NoSuchAlgorithmException e) {
        // throw new NoSuchEncryptionException(e);
        // } catch (InvalidKeySpecException e) {
        // throw new NoSuchEncryptionException(e);
        // } catch (NoSuchPaddingException e) {
        // throw new NoSuchEncryptionException(e);
        // } catch (InvalidKeyException e) {
        // throw new NoSuchEncryptionException(e);
        // } catch (InvalidAlgorithmParameterException e) {
        // throw new NoSuchEncryptionException(e);
        // } catch (IllegalBlockSizeException e) {
        // throw new NoSuchEncryptionException(e);
        // } catch (BadPaddingException e) {
        // throw new NoSuchEncryptionException(e);
        // }
    }
}
