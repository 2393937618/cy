package com.example.lxc.cy.other;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.*;

public class OkhttpHelperTest {
    @Rule public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private MockWebServer server;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void getReturnsSuccessfulBody() throws Exception {
        server.enqueue(new MockResponse().setBody("travel posts"));
        assertEquals("travel posts", OkhttpHelper.Okhttp_Get(url("/main/recommend")));
        RecordedRequest request = server.takeRequest(1, TimeUnit.SECONDS);
        assertNotNull(request);
        assertEquals("GET", request.getMethod());
        assertEquals("/main/recommend", request.getPath());
    }

    @Test
    public void getReturnsEmptyBodyForNoContent() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(204));
        assertEquals("", OkhttpHelper.Okhttp_Get(url("/empty")));
    }

    @Test
    public void getRejectsServerErrorWithoutExposingBody() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(500).setBody("private diagnostic"));
        try {
            OkhttpHelper.Okhttp_Get(url("/main/recommend"));
            fail("HTTP 500 must not be returned as application data");
        } catch (IOException expected) {
            assertEquals("HTTP 500", expected.getMessage());
        }
    }

    @Test
    public void postPreservesJsonPayloadAndSuccessfulBody() throws Exception {
        server.enqueue(new MockResponse().setBody("created"));
        assertEquals("created", OkhttpHelper.postRequest(
                Collections.singletonMap("content_text", "travel note"), url("/publish/7")));
        RecordedRequest request = server.takeRequest(1, TimeUnit.SECONDS);
        assertNotNull(request);
        assertEquals("POST", request.getMethod());
        assertEquals("/publish/7", request.getPath());
        assertTrue(request.getHeader("Content-Type").startsWith("application/json"));
        assertEquals("{\"content_text\":\"travel note\"}", request.getBody().readUtf8());
    }

    @Test
    public void postRejectsClientError() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(401).setBody("unauthorized"));
        try {
            OkhttpHelper.postRequest(Collections.emptyMap(), url("/login"));
            fail("HTTP 401 must not be returned as application data");
        } catch (IOException expected) {
            assertEquals("HTTP 401", expected.getMessage());
        }
    }

    @Test
    public void uploadPreservesMultipartFile() throws Exception {
        File file = sampleFile();
        server.enqueue(new MockResponse().setBody("uploaded"));
        assertEquals("uploaded", OkhttpHelper.sendFile(
                file.getPath(), "note.jpg", "upload", url("/findImg_upload/9")));
        RecordedRequest request = server.takeRequest(1, TimeUnit.SECONDS);
        assertNotNull(request);
        assertEquals("POST", request.getMethod());
        assertEquals("/findImg_upload/9", request.getPath());
        assertTrue(request.getHeader("Content-Type").startsWith("multipart/form-data"));
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("name=\"upload\"; filename=\"note.jpg\""));
        assertTrue(body.contains("sample photo bytes"));
    }

    @Test
    public void uploadRejectsServerError() throws Exception {
        File file = sampleFile();
        server.enqueue(new MockResponse().setResponseCode(503).setBody("unavailable"));
        try {
            OkhttpHelper.sendFile(file.getPath(), "note.jpg", "upload", url("/upload/7"));
            fail("HTTP 503 must not be returned as a successful upload");
        } catch (IOException expected) {
            assertEquals("HTTP 503", expected.getMessage());
        }
    }

    @Test
    public void requestCanSucceedAfterHttpError() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(500));
        server.enqueue(new MockResponse().setBody("recovered"));
        try {
            OkhttpHelper.Okhttp_Get(url("/first"));
            fail("First request should fail");
        } catch (IOException expected) {
            assertEquals("HTTP 500", expected.getMessage());
        }
        assertEquals("recovered", OkhttpHelper.Okhttp_Get(url("/second")));
        assertEquals(2, server.getRequestCount());
    }

    private String url(String path) {
        return server.url(path).toString();
    }

    private File sampleFile() throws IOException {
        File file = temporaryFolder.newFile("photo.jpg");
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write("sample photo bytes".getBytes("UTF-8"));
        }
        return file;
    }
}
