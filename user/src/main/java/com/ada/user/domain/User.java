package com.ada.user.domain;

import java.util.UUID;

public record User (
    Long id,
    String name,
    String email,
     String password){}

