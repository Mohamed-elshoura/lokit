package com.elshoura.lokit.errors.dto;

public record GeneralResponse<T>(int code, T Body, String timeStamp) {
}
