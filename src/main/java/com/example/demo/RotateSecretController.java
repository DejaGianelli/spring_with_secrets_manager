package com.example.demo;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-secret-change")
public class RotateSecretController {

    @Autowired
    private EntityManager entityManager;

    @GetMapping
    @Transactional
    public void testRotation() {
        entityManager.createNativeQuery("SELECT 1 = 1").getSingleResult();
    }
}
