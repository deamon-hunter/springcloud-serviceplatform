package com.gmsj.core.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Ovrille
 * @date 2017/11/15
 */
@Component
@Slf4j
public class AliyunUtil {


    public static void uploadToOSS(byte[] data, String key, OSSClient ossClient, String bucketName) {
        File tmpFile = getTmpFile();

        try {
//            BufferedOutputStream ioe = new BufferedOutputStream(new FileOutputStream(tmpFile));
//            ioe.write(data);
//            ioe.flush();
//            ioe.close();

            RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw");
            raf.write(data);


            uploadTempFile(key, tmpFile, ossClient, bucketName);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            // 上传完成之后删除
            tmpFile.delete();
        }
    }


    private static File getTmpFile() {
        File tmpDir = FileUtils.getTempDirectory();
        String tmpFileName = String.valueOf(Math.random() * 10000.0D).replace(".", "");
        return new File(tmpDir, tmpFileName);
    }

    private static void uploadTempFile(String key, File tmpFile, OSSClient ossClient, String bucketName) {
        File targetFile = new File(key);

        if (targetFile.canWrite()) {
            throw new IllegalStateException("目录不可访问");
        }


        uploadTempFile(key, tmpFile.getAbsolutePath(), ossClient, bucketName);

    }

    private static void uploadTempFile(String key, String absolutePath, OSSClient ossClient, String bucketName) {

        try {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);


            // 待上传的本地文件
            uploadFileRequest.setUploadFile(absolutePath);
            // 设置并发下载数，默认1
            uploadFileRequest.setTaskNum(5);
            // 设置分片大小，默认100KB
            uploadFileRequest.setPartSize(1024 * 1024 * 1);
            // 开启断点续传，默认关闭
            uploadFileRequest.setEnableCheckpoint(true);

            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);

            CompleteMultipartUploadResult multipartUploadResult =
                    uploadResult.getMultipartUploadResult();
            System.out.println(multipartUploadResult.getETag());

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
