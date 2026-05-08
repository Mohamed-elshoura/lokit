package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.ForgotPasswordRequest;
import com.elshoura.lokit.models.dto.request.ResetPasswordRequest;
import com.elshoura.lokit.models.dto.request.VerifyResetCodeRequest;
import com.elshoura.lokit.models.dto.response.SimpleMessageResponse;

public interface PasswordResetService {



    SimpleMessageResponse forgotPassword(ForgotPasswordRequest request);

    SimpleMessageResponse verifyResetCode(VerifyResetCodeRequest request);

    SimpleMessageResponse resetPassword(ResetPasswordRequest request);
}
