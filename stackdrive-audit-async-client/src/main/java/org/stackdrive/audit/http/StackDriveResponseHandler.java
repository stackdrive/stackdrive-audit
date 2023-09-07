package org.stackdrive.audit.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class StackDriveResponseHandler implements ResponseHandler<String> {

    @Override
    public String handleResponse(HttpResponse response) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity httpEntity = response.getEntity();
            return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    }
}