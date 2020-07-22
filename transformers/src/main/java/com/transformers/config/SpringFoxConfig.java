package com.transformers.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.transformers.model.Transformer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for Swagger2 API documentation
 *  
 * @author parth.pandya
 *
 */
@Configuration
@EnableSwagger2
@Import({ springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class})
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
    	Class[] clazz= {
    			Transformer.class
    	};
    	
		ApiInfo apiInfo = new ApiInfo("Transformers API", 
				"Play Series Tranformers",
			    "1.0.0-RC1", "Terms of service", new Contact("Parth Pandya", "https://www.parthpandya.ca", "admin@parthpandya.ca"),
			    "License of API", "API license URL", Collections.emptyList());
		
    	Set<String> protocols = new HashSet<>();
        protocols.add("http");
        
        String error="Invalid OpenAPI file. Please fix the schema errors:\nerror: instance failed to match exactly one schema (matched 0 out of 2)\n    level: \"error\"\n    schema: {\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/parametersList/items\"}\n    instance: {\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0\"}\n    domain: \"validation\"\n    keyword: \"oneOf\"\n    matched: 0\n    nrSchemas: 2\n    reports: {\"/definitions/parametersList/items/oneOf/0\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/parameter\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0\"},\"domain\":\"validation\",\"keyword\":\"oneOf\",\"message\":\"instance failed to match exactly one schema (matched 0 out of 2)\",\"matched\":0,\"nrSchemas\":2,\"reports\":{\"/definitions/parameter/oneOf/0\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/bodyParameter\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0\"},\"domain\":\"validation\",\"keyword\":\"additionalProperties\",\"message\":\"object instance has properties which are not allowed by the schema: [\\\"collectionFormat\\\",\\\"items\\\",\\\"type\\\"]\",\"unwanted\":[\"collectionFormat\",\"items\",\"type\"]},{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/bodyParameter\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0\"},\"domain\":\"validation\",\"keyword\":\"required\",\"message\":\"object has missing required properties ([\\\"schema\\\"])\",\"required\":[\"in\",\"name\",\"schema\"],\"missing\":[\"schema\"]}],\"/definitions/parameter/oneOf/1\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/nonBodyParameter\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0\"},\"domain\":\"validation\",\"keyword\":\"oneOf\",\"message\":\"instance failed to match exactly one schema (matched 0 out of 4)\",\"matched\":0,\"nrSchemas\":4,\"reports\":{\"/definitions/nonBodyParameter/oneOf/0\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/collectionFormat\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0/collectionFormat\"},\"domain\":\"validation\",\"keyword\":\"enum\",\"message\":\"instance value (\\\"multi\\\") not found in enum (possible values: [\\\"csv\\\",\\\"ssv\\\",\\\"tsv\\\",\\\"pipes\\\"])\",\"value\":\"multi\",\"enum\":[\"csv\",\"ssv\",\"tsv\",\"pipes\"]},{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/headerParameterSubSchema/properties/in\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0/in\"},\"domain\":\"validation\",\"keyword\":\"enum\",\"message\":\"instance value (\\\"formData\\\") not found in enum (possible values: [\\\"header\\\"])\",\"value\":\"formData\",\"enum\":[\"header\"]}],\"/definitions/nonBodyParameter/oneOf/1\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/primitivesItems/properties/type\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0/items/type\"},\"domain\":\"validation\",\"keyword\":\"enum\",\"message\":\"instance value (\\\"file\\\") not found in enum (possible values: [\\\"string\\\",\\\"number\\\",\\\"integer\\\",\\\"boolean\\\",\\\"array\\\"])\",\"value\":\"file\",\"enum\":[\"string\",\"number\",\"integer\",\"boolean\",\"array\"]}],\"/definitions/nonBodyParameter/oneOf/2\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/queryParameterSubSchema/properties/in\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0/in\"},\"domain\":\"validation\",\"keyword\":\"enum\",\"message\":\"instance value (\\\"formData\\\") not found in enum (possible values: [\\\"query\\\"])\",\"value\":\"formData\",\"enum\":[\"query\"]}],\"/definitions/nonBodyParameter/oneOf/3\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/collectionFormat\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0/collectionFormat\"},\"domain\":\"validation\",\"keyword\":\"enum\",\"message\":\"instance value (\\\"multi\\\") not found in enum (possible values: [\\\"csv\\\",\\\"ssv\\\",\\\"tsv\\\",\\\"pipes\\\"])\",\"value\":\"multi\",\"enum\":[\"csv\",\"ssv\",\"tsv\",\"pipes\"]},{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/pathParameterSubSchema/properties/in\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0/in\"},\"domain\":\"validation\",\"keyword\":\"enum\",\"message\":\"instance value (\\\"formData\\\") not found in enum (possible values: [\\\"path\\\"])\",\"value\":\"formData\",\"enum\":[\"path\"]}]}}]}}],\"/definitions/parametersList/items/oneOf/1\":[{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/jsonReference\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0\"},\"domain\":\"validation\",\"keyword\":\"additionalProperties\",\"message\":\"object instance has properties which are not allowed by the schema: [\\\"collectionFormat\\\",\\\"description\\\",\\\"in\\\",\\\"items\\\",\\\"name\\\",\\\"required\\\",\\\"type\\\"]\",\"unwanted\":[\"collectionFormat\",\"description\",\"in\",\"items\",\"name\",\"required\",\"type\"]},{\"level\":\"error\",\"schema\":{\"loadingURI\":\"http://swagger.io/v2/schema.json#\",\"pointer\":\"/definitions/jsonReference\"},\"instance\":{\"pointer\":\"/paths/~1acrofields~1{template}/delete/parameters/0\"},\"domain\":\"validation\",\"keyword\":\"required\",\"message\":\"object has missing required properties ([\\\"$ref\\\"])\",\"required\":[\"$ref\"],\"missing\":[\"$ref\"]}]}";
        
    	return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo)
          .host("localhost")
          //.extensions(vendorExtensions)
          .protocols(protocols)
          ;
//          .ignoredParameterTypes(clazz);
    	
    }
}