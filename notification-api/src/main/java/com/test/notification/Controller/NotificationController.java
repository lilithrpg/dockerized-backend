package com.test.notification.Controller;


import com.test.notification.Model.NotificationRequest;
import com.test.notification.Model.NotificationTemplate;
import com.test.notification.Service.NotificationService;
import com.test.notification.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TemplateService templateService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationTemplate> getTemplateById(@RequestBody String templateId) {
        return ResponseEntity.ok(templateService.getTemplatedById(templateId));
    }

    @PostMapping("/create-template")
    public ResponseEntity<NotificationTemplate> createTemplate(@RequestBody NotificationTemplate notificationTemplate) {
        return new ResponseEntity<>(templateService.createTemplate(notificationTemplate), HttpStatus.CREATED);
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestBody NotificationRequest notificationRequest) {
        notificationService.execute(notificationRequest);
    }
}
