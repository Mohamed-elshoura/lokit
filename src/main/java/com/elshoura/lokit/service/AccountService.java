package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.ChangePasswordRequest;
import com.elshoura.lokit.models.dto.request.UpdateAccountRequest;
import com.elshoura.lokit.models.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse getMyAccount(Long userId);

    AccountResponse updateMyAccount(Long userId, UpdateAccountRequest request);

    void changePassword(Long userId, ChangePasswordRequest request);

}
