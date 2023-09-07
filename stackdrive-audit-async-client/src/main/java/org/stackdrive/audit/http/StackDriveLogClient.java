package org.stackdrive.audit.http;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackdrive.audit.dto.AuditDTO;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class StackDriveLogClient {

    private static final Logger log = LoggerFactory.getLogger(StackDriveLogClient.class);

    private final String url;

    private final Gson mapper = new Gson();

    public StackDriveLogClient(String url) {
        this.url = url;
    }

    public void send(AuditDTO auditDTO) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, CertificateException {
        try (CloseableHttpClient httpclient = HttpClients
                .custom()
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(TrustAllStrategy.INSTANCE).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build()
        ) {
            HttpPut httpPut = new HttpPut(this.url);
            StringEntity entity = new StringEntity(mapper.toJson(auditDTO), ContentType.APPLICATION_JSON);
            httpPut.setEntity(entity);
            String responseBody = httpclient.execute(httpPut, new StackDriveResponseHandler());
            log.debug(responseBody);
        }
    }
}