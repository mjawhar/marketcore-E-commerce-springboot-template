package com.marketcore.service.impl;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import com.marketcore.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailjetEmailService implements EmailService {

    @Value("${mailjet.api-key}")
    private String apiKey;

    @Value("${mailjet.secret-key}")
    private String secretKey;

    @Value("${mailjet.sender-email}")
    private String senderEmail;

    @Value("${mailjet.sender-name}")
    private String senderName;

    @Value("${app.base-url}")
    private String baseUrl;

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        // explore this in complete version
    }

    @Override
    public void sendOrderConfirmationEmail(String toEmail, String orderNumber, String customerName,
                                          double totalAmount, String orderDetails) {
        // explore this in complete version
    }

    private String buildEmailHtml(String resetUrl) {
        // explore this in complete version
        return null;
    }

    private String buildOrderConfirmationHtml(String orderNumber, String customerName,
                                             double totalAmount, String orderDetails) {
        // explore this in complete version
        return null;
    }
}
