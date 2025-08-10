package edu.only4.adapter.portal.api._share.configure

import com.only4.cap4k.ddd.domain.web.ClearDomainContextInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class DomainContextConfig(
    private val clearDomainContextInterceptor: ClearDomainContextInterceptor,
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(clearDomainContextInterceptor)
            .addPathPatterns("/**")
    }
}
