package ru.emorozov.springbootapp.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import ru.emorozov.springbootapp.endpoint.PostEndpoint;

@EnableWs
@EnableWebMvc
@Configuration
public class WebConfig extends WsConfigurerAdapter  {

	@NotNull
	private static final String WS_MAPPING = "/soap/*";

	@NotNull
	private static final String WEB_MAPPING = "/*";

	@Bean
	@SuppressWarnings("unchecked")
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, WS_MAPPING);
	}

	@Bean
	@SuppressWarnings("unchecked")
	public ServletRegistrationBean webDispatcherServlet(ApplicationContext applicationContext) {
		final DispatcherServlet servlet = new DispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setDetectAllViewResolvers(true);
		servlet.setDetectAllHandlerMappings(true);
		return new ServletRegistrationBean(servlet, WEB_MAPPING);
	}

	@Bean(name = "PostService")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema postEndpointSchema) {
		final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName(PostEndpoint.PORT_TYPE_NAME);
		wsdl11Definition.setLocationUri(PostEndpoint.LOCATION_URI);
		wsdl11Definition.setTargetNamespace(PostEndpoint.NAMESPACE);
		wsdl11Definition.setSchema(postEndpointSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema postEndpointSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/postEndpoint.xsd"));
	}
}
