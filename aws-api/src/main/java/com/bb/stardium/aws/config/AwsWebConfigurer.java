package com.bb.stardium.aws.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.bb.stardium.aws.support.interceptor.MultipartRequestInterceptor;
import com.bb.stardium.aws.util.S3UploadUtils;
import com.bb.stardium.aws.util.media.FileConverter;
import com.bb.stardium.aws.util.media.S3FileUploader;
import com.bb.stardium.resolver.AuthorizePlayerArgumentResolver;
import com.bb.stardium.chat.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class AwsWebConfigurer implements WebMvcConfigurer {
    private final PlayerService playerService;

    @Value("${cloud.aws.region.static}")
    private String region;

    public AwsWebConfigurer(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizePlayerArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(multipartRequestInterceptor())
                .addPathPatterns("/static/profile");
    }

    @Bean
    public AuthorizePlayerArgumentResolver authorizePlayerArgumentResolver() {
        return new AuthorizePlayerArgumentResolver(playerService);
    }

    @Bean
    public MultipartRequestInterceptor multipartRequestInterceptor() {
        return new MultipartRequestInterceptor();
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .build();
    }

    @Bean
    public S3UploadUtils s3UploadUtils() {
        return new S3UploadUtils(s3FileUploader(), fileConverter());
    }

    @Bean
    public FileConverter fileConverter() {
        return new FileConverter();
    }

    @Bean
    public S3FileUploader s3FileUploader() {
        return new S3FileUploader(amazonS3());
    }
}
