package com.example.hospitalcrud.dao.repositories.statics;

import com.example.hospitalcrud.dao.model.Credential;
import com.example.hospitalcrud.dao.repositories.CredentialDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CredentialRepository implements CredentialDAO {
private List<Credential> credentials;
    public CredentialRepository() {
        this.credentials = new ArrayList<>();

        this.credentials.add(new Credential("admin", "admin"));
    }
    public Credential get(Credential credential) {
        return credentials.stream().filter(c -> c.getUserName().equals(credential.getUserName()) &&
                c.getPassword().equals(credential.getPassword())).findFirst().orElse(null);
    }



    // The register method can remain as is for now
    public boolean register(String username, String password) {
        //TODO
        return true;
    }
}
