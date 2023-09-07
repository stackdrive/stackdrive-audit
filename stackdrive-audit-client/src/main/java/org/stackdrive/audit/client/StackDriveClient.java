package org.stackdrive.audit.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.stackdrive.audit.dto.AuditDTO;
import org.stackdrive.audit.dto.InfoDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class StackDriveClient {

    private String auditUrl = "";

    public StackDriveClient() {
    }

    public StackDriveClient(String auditUrl) {
        this.auditUrl = auditUrl;
    }

    public InfoDTO send(AuditDTO auditDTO) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        InfoDTO result = new InfoDTO();
        try (CloseableHttpClient httpclient = HttpClients
                .custom()
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(TrustAllStrategy.INSTANCE).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build()
        ) {
            HttpPut httpPut = new HttpPut(auditUrl);

            ObjectMapper mapper = new ObjectMapper();
            StringEntity entity = new StringEntity(mapper.writeValueAsString(auditDTO), ContentType.APPLICATION_JSON);
            httpPut.setEntity(entity);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(
                        final HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            String responseBody = httpclient.execute(httpPut, responseHandler);
            result = mapper.readValue(responseBody, InfoDTO.class);
        }
        return result;
    }
}