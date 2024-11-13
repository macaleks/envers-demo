package com.example.demoenversaudited;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RevisionController {

    private final EntityManager entityManager;

    @GetMapping("/revision/{id}/{revision}")
    public Product getProductSnapshot(@PathVariable Long id, @PathVariable Number revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(Product.class, id, revision);
    }

    @GetMapping("/revision/{id}/revisions")
    public List<Number> getRevisions(@PathVariable Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.getRevisions(Product.class, id);
    }
}
