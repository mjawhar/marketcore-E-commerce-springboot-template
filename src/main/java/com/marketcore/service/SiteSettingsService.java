package com.marketcore.service;

import com.marketcore.repository.SiteSettingsRepository;
import com.marketcore.config.SiteConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteSettingsService {

    private final SiteSettingsRepository siteSettingsRepository;
    private final SiteConfig siteConfig;

    @Transactional(readOnly = true)
    public List<SiteSettings> getAllSettings() {
        return siteSettingsRepository.findAll();
    }

    @Transactional
    public SiteSettings saveSetting(SiteSettings settings) {
        return siteSettingsRepository.save(settings);
    }

    @Transactional(readOnly = true)
    public SiteSettings getDefaultSettings() {
        List<SiteSettings> settings = siteSettingsRepository.findAll();
        if (settings.isEmpty()) {
            SiteSettings defaultSettings = new SiteSettings();
            defaultSettings.setCurrencyCode(siteConfig.getCurrencyCode());
            defaultSettings.setCurrencySymbol(siteConfig.getCurrencySymbol());
            defaultSettings.setCurrencyRate(siteConfig.getCurrencyRate());
            defaultSettings.setSiteName(siteConfig.getSiteName());
            defaultSettings.setSiteDescription(siteConfig.getSiteDescription());
            return siteSettingsRepository.save(defaultSettings);
        }
        return settings.get(0);
    }

    @Transactional
    public void updateCurrencySettings(String code, String symbol, Double rate) {
        SiteSettings settings = getDefaultSettings();
        settings.setCurrencyCode(code);
        settings.setCurrencySymbol(symbol);
        settings.setCurrencyRate(rate);
        siteSettingsRepository.save(settings);
    }
}
