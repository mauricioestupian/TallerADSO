package com.taller1.taller1.services;

import com.taller1.taller1.dtos.LoginDto;
import com.taller1.taller1.dtos.RespuestaLoginDTO;

public interface AutenticacionService {
    RespuestaLoginDTO iniciarSesion(LoginDto login);
}
