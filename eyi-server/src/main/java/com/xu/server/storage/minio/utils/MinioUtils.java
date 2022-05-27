package com.xu.server.storage.minio.utils;

import com.xu.commons.utils.TikaUtils;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.ObjectLockConfiguration;
import io.minio.messages.RetentionDurationDays;
import io.minio.messages.RetentionMode;
import io.minio.messages.VersioningConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/27 11:20
 */
@Slf4j
public class MinioUtils {
	private static final MinioClient CLIENT;

	static {
		CLIENT = MinioClient.builder()
				.endpoint("http://localhost:19099")
				.credentials("admin", "@eyi0524")
				.build();
	}

	public static boolean bucketExists(String bucketName) {
		try {
			return CLIENT.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	public static boolean createBucket(String bucketName, boolean objectLocked) {
		if (bucketExists(bucketName)) {
			return true;
		} else {
			try {
				CLIENT.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(objectLocked).build());
				return true;
			} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
				log.error(e.getMessage(), e);
			}
			return false;
		}
	}

	public static void removeBucket(String bucketName) {
        RemoveBucketArgs removeArgs = RemoveBucketArgs.builder()
		    .bucket("ces")
		    .build();
		try {
			CLIENT.removeBucket(removeArgs);
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void setBucketObjectLock(String bucketName, int days) {
		// 设置Object-Lock https://docs.min.io/minio/baremetal/object-retention/minio-object-locking.html
		// 锁定的对象 只有超过设定的日期 才会真正删除
		VersioningConfiguration versionConfig = new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, true);
		SetBucketVersioningArgs versioningArgs = SetBucketVersioningArgs.builder()
				.bucket(bucketName)
				.config(versionConfig)
				.build();
		ObjectLockConfiguration objectLockConfiguration =
				new ObjectLockConfiguration(RetentionMode.COMPLIANCE, new RetentionDurationDays(days));
		SetObjectLockConfigurationArgs objectRetentionArgs = SetObjectLockConfigurationArgs.builder()
				.config(objectLockConfiguration).bucket(bucketName).build();
		try {
			CLIENT.setBucketVersioning(versioningArgs);
			CLIENT.setObjectLockConfiguration(objectRetentionArgs);
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void setBucketTags(String bucketName, Map<String, String> tags) {
		// 设置桶标签
		SetBucketTagsArgs tagsArgs = SetBucketTagsArgs.builder()
				.bucket("ces")
				.tags(tags)
				.build();
		try {
			CLIENT.setBucketTags(tagsArgs);
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static InputStream getObject(String bucketName, String objectName) {
		if (!bucketExists(bucketName)) {
			return null;
		}
		GetObjectArgs objectArgs = GetObjectArgs.builder()
				.bucket(bucketName)
				.object(objectName)
				.build();
		try {
			return CLIENT.getObject(objectArgs);
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	public static String getObjectUrl(String bucketName, String objName) {
		return getObjectUrl(bucketName, objName, 0, TimeUnit.HOURS);
	}

	public static String getObjectUrl(String bucketName, String objName, int expiry, TimeUnit timeUnit) {
		Map<String, String> reqParams = new HashMap<>();
		reqParams.put("response-content-type", TikaUtils.getContentTypeByFileName(objName));
		try {
			GetPresignedObjectUrlArgs.Builder builder = GetPresignedObjectUrlArgs.builder()
					.method(Method.GET)
					.bucket(bucketName)
					.object(objName)
					.extraQueryParams(reqParams);
			if (expiry > 0) {
				builder.expiry(expiry, timeUnit);
			}
			return
					CLIENT.getPresignedObjectUrl(builder.build());
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	public static void uploadObject(String bucketName, String filename, InputStream is) {
		if (!bucketExists(bucketName)) {
			createBucket(bucketName, false);
		}
		try {
			PutObjectArgs putObjectArgs = PutObjectArgs.builder()
					.bucket(bucketName)
					.object(filename)
					.stream(is, -1, 50*1024*1024)
					.contentType(TikaUtils.getContentTypeByFileName(filename))
					.build();
			CLIENT.putObject(putObjectArgs);
			String url = getObjectUrl(bucketName, filename);
			System.out.println("file access url " + url);
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
			log.error(e.getMessage(), e);

		}
	}
}
