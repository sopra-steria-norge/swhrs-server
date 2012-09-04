package no.steria.swhrs;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.builder.EqualsBuilder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static final String SALT_SEPARATOR = "_";
    private String digest;

    private String salt;
    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private Password() {
    }

    public String getDigest() {
        return digest;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public String toString() {
        return salt + SALT_SEPARATOR + digest;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Password) {
            Password other = (Password) obj;
            return new EqualsBuilder()
                    .append(this.salt, other.salt)
                    .append(this.digest, other.digest)
                    .isEquals();
        }
        return false;
    }

    public static Password fromPlaintext(String salt, String plaintext) {
        Password password = new Password();
        password.salt = salt;
        byte[] digestOfPasswordAndSalt = messageDigest.digest((salt + SALT_SEPARATOR + plaintext).getBytes());
        password.digest = DatatypeConverter.printBase64Binary(digestOfPasswordAndSalt);
        return password;
    }

    public static Password fromHashed(String hashed) {
        String[] split = hashed.split(SALT_SEPARATOR);
        Password password = new Password();
        password.salt = split[0];
        password.digest = split[1];
        return password;
    }
}
