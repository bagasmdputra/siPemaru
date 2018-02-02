package com.kurikulum.service;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addSuccessMessage(String msg);
    void addErrorMessage(String msg);
}