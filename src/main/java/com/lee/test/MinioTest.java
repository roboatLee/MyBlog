package com.lee.test;

import com.lee.utils.MinioUtils;
import io.minio.MinioClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author KitenLee
 * * @date 2025/6/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MinioTest {
    @Autowired
    private MinioUtils minioUtils;

}
