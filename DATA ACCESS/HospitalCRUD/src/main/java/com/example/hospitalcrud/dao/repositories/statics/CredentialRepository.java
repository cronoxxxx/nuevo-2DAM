package com.example.hospitalcrud.dao.repositories.statics;

import com.example.hospitalcrud.dao.model.Credential;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CredentialRepository {

    public Credential get(String username) {
        return new Credential("admin","admin");
    }

    // The register method can remain as is for now
    public boolean register(String username, String password) {
        //TODO
        return true;
    }
}
