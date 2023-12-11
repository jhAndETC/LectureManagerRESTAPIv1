package hyundai.cc.articlemanage.article.service;

import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import hyundai.cc.domain.OsClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails;
import com.oracle.bmc.objectstorage.model.PreauthenticatedRequest.BucketListingAction;
import com.oracle.bmc.objectstorage.requests.CreatePreauthenticatedRequestRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.CreatePreauthenticatedRequestResponse;

@Service
public class FileUploadService {

    String bucketName = "bucket-20231211";

    /*  you can find namespace in your bucket details page under
    Bucket information in OCI console */
    String namespaceName = "axjmeq566vea";

    @Autowired
    private OsClientConfiguration configuration;

    public void upload(MultipartFile file) throws Exception {

        String objectName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();


        //build upload request

        PutObjectRequest putObjectRequest =
                PutObjectRequest.builder()
                        .namespaceName(namespaceName)
                        .bucketName(bucketName)
                        .objectName(objectName)
                        .contentLength(file.getSize())
                        .putObjectBody(inputStream)
                        .build();

        //upload the file

        try {
            configuration.getObjectStorage().putObject(putObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            configuration.getObjectStorage().close();
        }
    }
}