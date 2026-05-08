package com.elshoura.lokit.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println(new BCryptPasswordEncoder().encode("mohamed_elshoura_lokit@20212026"));
	}
}
