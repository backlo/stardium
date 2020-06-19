package com.bb.stardium.aws.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.bb.stardium.aws.util.S3UploadUtils;
import com.bb.stardium.aws.util.media.FileConverter;
import com.bb.stardium.aws.util.media.S3FileUploader;
import com.bb.stardium.interceptor.resolver.AuthorizePlayerArgumentResolver;
import com.bb.stardium.security.filter.JwtVerifyFilter;
import com.bb.stardium.security.service.SecurityService;
import com.bb.stardium.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class AwsSecurityConfigurer extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final SecurityService securityService;
    private final PlayerService playerService;

    @Value("${cloud.aws.region.static}")
    private String region;

    public AwsSecurityConfigurer(SecurityService securityService, PlayerService playerService) {
        this.securityService = securityService;
        this.playerService = playerService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizePlayerArgumentResolver());
    }

    @Bean
    public AuthorizePlayerArgumentResolver authorizePlayerArgumentResolver() {
        return new AuthorizePlayerArgumentResolver(playerService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/static/**").hasRole("USER")
                .antMatchers("/**").denyAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterAfter(awsJwtVerifyFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtVerifyFilter awsJwtVerifyFilter() {
        Map<String, String> ignoreUri = new HashMap<>();

        return new JwtVerifyFilter(securityService, ignoreUri);
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

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .build();
    }
}
