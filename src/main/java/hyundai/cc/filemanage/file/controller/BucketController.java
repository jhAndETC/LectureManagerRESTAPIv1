package hyundai.cc.filemanage.file.controller;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.ListObjects;
import com.oracle.bmc.objectstorage.model.ObjectSummary;
import com.oracle.bmc.objectstorage.requests.*;
import com.oracle.bmc.objectstorage.responses.GetBucketResponse;
import com.oracle.bmc.objectstorage.responses.GetNamespaceResponse;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import com.oracle.bmc.objectstorage.responses.ListObjectsResponse;
import com.oracle.bmc.objectstorage.transfer.DownloadConfiguration;
import com.oracle.bmc.objectstorage.transfer.DownloadManager;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@RestController
@RequestMapping("buckets")
@PropertySource("classpath:app.properties")
public class BucketController {
    final String compartmentId = "ocid1.tenancy.oc1..aaaaaaaalzc76alis4w3z7lcviziabckcmowydeojxikdrmdk5of5hr33sgq";
    final String bucket = "bucket-20231211";
    final String namespaceName = "axjmeq566vea";
    final String object = "";
    @Value("${configPath}")
    private String configPath;

    @GetMapping("/readBucket")
    public void Test() throws IOException {
        ConfigFileReader.ConfigFile config = ConfigFileReader.parse(configPath);
        log.info("readBucket configPath: " + configPath);

        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);

        ObjectStorage client = new ObjectStorageClient(provider);
        client.setRegion(Region.AP_SINGAPORE_1);

        log.info("Getting the namespace.");
        GetNamespaceResponse namespaceResponse = client.getNamespace(GetNamespaceRequest.builder().build());

        String namespaceName = namespaceResponse.getValue();

        log.info("Creating Get bucket request");
        List<GetBucketRequest.Fields> fieldsList = new ArrayList<>(2);
        fieldsList.add(GetBucketRequest.Fields.ApproximateCount);
        fieldsList.add(GetBucketRequest.Fields.ApproximateSize);
        GetBucketRequest request =
                GetBucketRequest.builder()
                        .namespaceName(namespaceName)
                        .bucketName(bucket)
                        .fields(fieldsList)
                        .build();

        log.info("Fetching bucket details");
        GetBucketResponse response = client.getBucket(request);

        log.info("Bucket Name : " + response.getBucket().getName());
        log.info("Bucket Compartment : " + response.getBucket().getCompartmentId());
        log.info(
                "The Approximate total number of objects within this bucket : "
                        + response.getBucket().getApproximateCount());
        log.info(
                "The Approximate total size of objects within this bucket : "
                        + response.getBucket().getApproximateSize());

    }

    @GetMapping("/uploadObjectToBucket")
    public String UploadObject(@RequestBody String type, String id, String uploadFileName, String contentType, MultipartFile uploadFile) throws Exception {
        ConfigFileReader.ConfigFile config = ConfigFileReader.parse(configPath);
        log.info("UploadObject configPath: " + configPath);

        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);

        ObjectStorage client = new ObjectStorageClient(provider);
        client.setRegion(Region.AP_SINGAPORE_1);

        //upload object
        UploadConfiguration uploadConfiguration =
                UploadConfiguration.builder()
                        .allowMultipartUploads(true)
                        .allowParallelUploads(true)
                        .build();

        UploadManager uploadManager = new UploadManager(client, uploadConfiguration);

        String namespaceName = "axjmeq566vea";
        String bucketName = "bucket-20231211";
        String objectName = type + "/" + id + "/" +uploadFileName;
        Map<String, String> metadata = null;

        String contentEncoding = null;
        String contentLanguage = null;

        File body = new File(System.getProperty("java.io.tmpdir"), uploadFileName);
        log.info(body.getAbsolutePath());
        log.info("temp root" + System.getProperty("java.io.tmpdir"));
        uploadFile.transferTo(body);

        PutObjectRequest request =
                PutObjectRequest.builder()
                        .bucketName(bucketName)
                        .namespaceName(namespaceName)
                        .objectName(objectName)
                        .contentType(contentType)
                        .contentLanguage(contentLanguage)
                        .contentEncoding(contentEncoding)
                        .opcMeta(metadata)
                        .build();


        UploadManager.UploadRequest uploadDetails =
                UploadManager.UploadRequest.builder(body).allowOverwrite(true).build(request);

        UploadManager.UploadResponse response = uploadManager.upload(uploadDetails);
        log.info(response.toString());
        String FileURL = "https://objectstorage.ap-singapore-1.oraclecloud.com/n/axjmeq566vea/b/bucket-20231211/o/" + objectName;

        client.close();
        return FileURL;
    }

    @GetMapping("/getObjectOneTest")
    public void getObjectOneTest(@RequestBody String type, String id, String uploadFileName, String contentType) throws Exception {
        ConfigFileReader.ConfigFile config = ConfigFileReader.parse(configPath);
        log.info("getObjectOneTest configPath: " + configPath);

        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);

        ObjectStorage client = new ObjectStorageClient(provider);
        client.setRegion(Region.AP_SINGAPORE_1);

        String objectName = type + "/" + id + "/" +uploadFileName;

        GetObjectRequest request =
                GetObjectRequest.builder()
                        .namespaceName(namespaceName)
                        .bucketName(bucket)
                        .objectName(objectName)
                        .build();

        GetObjectResponse response = client.getObject(request);
        System.out.println(response);
        response.getInputStream();
        client.close();
    }

    @GetMapping("/getObjectListTest")
    public void getObjectListTest() throws Exception {
        ConfigFileReader.ConfigFile config = ConfigFileReader.parse(configPath);
        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);

        ObjectStorage client = new ObjectStorageClient(provider);
        client.setRegion(Region.AP_SINGAPORE_1);

        ListObjectsRequest request =
                ListObjectsRequest.builder()
                        .namespaceName(namespaceName)
                        .bucketName(bucket)
                        .fields("size, md5, timeCreated, timeModified")
                        .prefix("~~~~")
                        .build();

        ListObjectsResponse response = client.listObjects(request);

        ListObjects list = response.getListObjects();
        List<ObjectSummary> objectList = list.getObjects();


        for(int i=0; i<objectList.size(); i++) {
            System.out.println("====================");
            System.out.println("@@@@@@@@@@@@@@@@@ getName : " + objectList.get(i).getName());
            System.out.println("@@@@@@@@@@@@@@@@@ getArchivalState : " + objectList.get(i).getArchivalState());
            System.out.println("@@@@@@@@@@@@@@@@@ getSize : " + objectList.get(i).getSize());
            System.out.println("@@@@@@@@@@@@@@@@@ getTimeCreated : " + objectList.get(i).getTimeCreated());
            System.out.println("@@@@@@@@@@@@@@@@@ getTimeModified : " + objectList.get(i).getTimeModified());
        }

        System.out.println(response.getListObjects());
        client.close();
    }

    @GetMapping("/DownloadObjectTest")
    public void DownloadObjectTest(@RequestBody String type, String id, String uploadFileName, String contentType) throws Exception {
        ConfigFileReader.ConfigFile config = ConfigFileReader.parse(configPath);

        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);

        ObjectStorage client = new ObjectStorageClient(provider);
        client.setRegion(Region.AP_SINGAPORE_1);
        //end basic setting

        DownloadConfiguration downloadConfiguration =
                DownloadConfiguration.builder()
                        .parallelDownloads(3)
                        .maxRetries(3)
                        .multipartDownloadThresholdInBytes(6 * 1024 * 1024)
                        .partSizeInBytes(4 * 1024 * 1024)
                        .build();

        DownloadManager downloadManager = new DownloadManager(client, downloadConfiguration);

        String objectName = type + "/" + id + "/" +uploadFileName;
        String outputFileName = "~/uploadFileName.jpg";//다운로드할 경로와 파일명

        GetObjectRequest request =
                GetObjectRequest.builder()
                        .namespaceName(namespaceName)
                        .bucketName(bucket)
                        .objectName(objectName)
                        .build();

        // download request and print result
        GetObjectResponse response = downloadManager.getObject(request);
        System.out.println("Content length: " + response.getContentLength() + " bytes");

        // use the stream contents; make sure to close the stream, e.g. by using try-with-resources
        InputStream stream = response.getInputStream();
        OutputStream outputStream = new FileOutputStream(outputFileName);
        try {
            // use fileStream
            byte[] buf = new byte[8192];
            int bytesRead;
            while ((bytesRead = stream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
        } // try-with-resources automatically closes streams
        finally {
            stream.close();
            outputStream.close();
        }
        System.out.println("File size: " + new File(outputFileName).length() + " bytes");

        // or even simpler, if targetting a file:
        response = downloadManager.downloadObjectToFile(request, new File(outputFileName));
        System.out.println("Content length: " + response.getContentLength() + " bytes");
        System.out.println("File size: " + new File(outputFileName).length() + " bytes");
        client.close();
    }


}
