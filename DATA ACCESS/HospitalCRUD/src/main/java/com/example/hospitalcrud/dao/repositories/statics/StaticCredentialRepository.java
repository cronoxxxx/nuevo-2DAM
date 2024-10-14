package com.example.hospitalcrud.dao.repositories.statics;

import com.example.hospitalcrud.dao.model.Credential;
import com.example.hospitalcrud.dao.repositories.CredentialRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("this")
@Repository
public class StaticCredentialRepository implements CredentialRepository {
    private List<Credential> credentials = new ArrayList<>();

    public Credential get(Credential credential) {
        credentials.add(new Credential("admin", "admin"));
        return credentials.stream().filter(c -> c.getUserName().equals(credential.getUserName()) &&
                c.getPassword().equals(credential.getPassword())).findFirst().orElse(null);
    }


    // The register method can remain as is for now
    public boolean register(String username, String password) {
        //TODO
        return true;
    }
}
