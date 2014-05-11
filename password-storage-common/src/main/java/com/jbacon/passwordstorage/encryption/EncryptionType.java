package com.jbacon.passwordstorage.encryption;

import static com.jbacon.passwordstorage.utils.GenericValidationUtil.areNull;
import static com.jbacon.passwordstorage.utils.GenericValidationUtil.isNull;

import co.uk.baconi.cryptography.ciphers.pbe.PBECiphers;
import co.uk.baconi.cryptography.ciphers.symmetric.SymmetricCiphers;

public enum EncryptionType {
    
    AES_128(SymmetricCiphers.AES_FAST.toString(), true, 128), //
    AES_256(SymmetricCiphers.AES_FAST.toString(), true, 256), //
    TWOFISH_256(SymmetricCiphers.TWOFISH.toString(), true, 256), //
    
    @Deprecated
    PBE_WITH_MD5_AND_DES(PBECiphers.PBE_MD5_DES_CBC.toString(), true, 64), //
    @Deprecated
    PBE_WITH_SHA1_AND_256_AES_CBC_BC(PBECiphers.PBE_SHA1_AES_CBC.toString(), true, 256),
    
    PBE_WITH_SHA256_AND_128_AES_CBC(PBECiphers.PBE_SHA256_AES_CBC.toString(), true, 128), //
    PBE_WITH_SHA256_AND_256_AES_CBC(PBECiphers.PBE_SHA256_AES_CBC.toString(), true, 256), //
    PBE_WITH_SHA512_AND_256_AES_CBC(PBECiphers.PBE_SHA512_AES_CBC.toString(), true, 256), //
    PBE_WITH_WHIRLPOOL_AND_256_AES_CBC(PBECiphers.PBE_WHIRLPOOL_AES_CBC.toString(), true, 256), //
    
    PBE_WITH_SHA256_AND_TWOFISH_CBC(PBECiphers.PBE_SHA256_TWOFISH_CBC.toString(), true, 256), //
    PBE_WITH_SHA512_AND_TWOFISH_CBC(PBECiphers.PBE_SHA512_TWOFISH_CBC.toString(), true, 256), //
    PBE_WITH_WHIRLPOOL_AND_TWOFISH_CBC(PBECiphers.PBE_WHIRLPOOL_TWOFISH_CBC.toString(), true, 256);
    
    public static final int DEFAULT_SALT_SIZE = 8;
    
    public final String algorithmName;
    
    public final boolean isSupported;
    
    public final int keySize;
    
    private EncryptionType(final String algorithm, final boolean isSupported, final int keySize) {
        this.algorithmName = algorithm;
        this.isSupported = isSupported;
        this.keySize = keySize;
    }
    
    public static boolean areValid(final EncryptionType... encryptionTypes) {
        
        if (areNull(encryptionTypes)) {
            return false;
        }
        
        for (final EncryptionType encryptionType : encryptionTypes) {
            if (!isValid(encryptionType)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isValid(final EncryptionType encryptionType) {
        
        if (isNull(encryptionType)) {
            return false;
        }
        
        return encryptionType.isSupported;
    }
}
