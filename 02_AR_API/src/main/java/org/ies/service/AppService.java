package org.ies.service;

import org.ies.bindings.AppForm;

import java.util.List;

public interface AppService {

    public String registerApp(AppForm form ,int userId);

    public List<AppForm> viewApp();
}
