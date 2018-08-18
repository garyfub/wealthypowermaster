package com.zcxn.wp.cloud.common.bean.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lengleng
 * @date 2017/12/28
 * swagger 配置
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<Parameter>();
        tokenBuilder.name("Authorization")
                .defaultValue("去其他请求中获取heard中token参数格式：Bearer空格token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();


        parameterList.add(tokenBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("PROM Cloud API ")
                .description("http://eip.teamshub.com/g/70491809")
                .termsOfServiceUrl("http://eip.teamshub.com/g/70149015")
                .contact(new Contact("Athlon","http://eip.teamshub.com/g/70491809","wangchaof@si-tech.com.cn"))
                .version("1.0")
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(null, "list", "alpha", "schema",
            UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }

}