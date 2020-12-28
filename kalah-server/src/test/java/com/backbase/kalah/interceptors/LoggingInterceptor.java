package com.backbase.kalah.interceptors;

import com.backbase.kalah.test.TestMediaTypeUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] requestBody,
                                        ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = null;
        byte[] responseBody = null;
        Throwable exception = null;
        try {
            response = execution.execute(request, requestBody);
            HttpStatus httpStatus = response.getStatusCode();
            responseBody = StreamUtils.copyToByteArray(response.getBody());
            return getClientHttpResponse(httpStatus, response, responseBody);
        } catch (IOException | RuntimeException e) {
            exception = e;
            throw e;
        } finally {
            log(request, requestBody, response, responseBody, exception);
        }
    }

    private ClientHttpResponse getClientHttpResponse(HttpStatus httpStatus, ClientHttpResponse response,
                                                     byte[] responseBody) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return httpStatus;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return response.getRawStatusCode();
            }

            @Override
            public String getStatusText() throws IOException {
                return response.getStatusText();
            }

            @Override
            public void close() {
                response.close();
            }

            @Override
            public InputStream getBody() {
                return new ByteArrayInputStream(responseBody);
            }

            @Override
            public HttpHeaders getHeaders() {
                return response.getHeaders();
            }
        };
    }

    private void log(HttpRequest request, byte[] requestBody,
                     @Nullable ClientHttpResponse response, @Nullable byte[] responseBody,
                     @Nullable Throwable exception) {
        StringBuilder sb = new StringBuilder(formatRequest(request, requestBody));
        try {
            if (response != null && responseBody != null) {
                sb.append('\n').append(formatResponse(response, responseBody));
            }
        } catch (IOException e) {
            sb.append(e);
        }

        log.info(sb.toString(), exception);
    }

    private static String formatRequest(HttpRequest request, byte[] requestBody) {
        val sb = new StringBuilder("Request\n");
        sb.append(request.getMethod()).append(' ').append(request.getURI()).append('\n')
                .append(formatHeaders(request.getHeaders())).append('\n');
        if (requestBody != null && requestBody.length > 0) {
            val requestBodyStr = new String(requestBody, UTF_8);
            sb.append(requestBodyStr).append('\n');
        }
        return sb.toString();
    }

    private static String formatResponse(ClientHttpResponse response, byte[] responseBody) throws IOException {
        val sb = new StringBuilder("Response\n")
                .append(response.getStatusCode()).append(' ').append(response.getStatusText()).append('\n')
                .append(formatHeaders(response.getHeaders())).append('\n');
        val contentMediaType = response.getHeaders().getContentType();
        val contentType = contentMediaType == null ? null : contentMediaType.toString();
        val responseBodyStr = new String(responseBody, UTF_8);
        if (TestMediaTypeUtils.isPrintable(contentType, responseBodyStr)) {
            sb.append(responseBodyStr).append('\n');
        } else {
            sb.append("binary_response_body_length=").append(responseBody.length).append(" bytes\n");
        }
        return sb.toString();
    }

    private static String formatHeaders(Map<String, List<String>> headers) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        headers.forEach((name, values) -> {
            values.forEach(value -> pw.println(name + ": " + value));
        });
        return sw.toString();
    }
}
