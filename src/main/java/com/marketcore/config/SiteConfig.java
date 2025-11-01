package com.marketcore.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SiteConfig {

    @Value("${app.site.name}")
    private String siteName;

    @Value("${app.site.description}")
    private String siteDescription;

    @Value("${app.site.phone}")
    private String sitePhone;

    @Value("${app.site.country}")
    private String siteCountry;

    @Value("${app.site.city}")
    private String siteCity;

    @Value("${app.site.email}")
    private String siteEmail;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.currency.code}")
    private String currencyCode;

    @Value("${app.currency.symbol}")
    private String currencySymbol;

    @Value("${app.currency.rate}")
    private Double currencyRate;
}

