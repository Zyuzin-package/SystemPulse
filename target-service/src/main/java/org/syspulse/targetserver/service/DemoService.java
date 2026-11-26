package org.syspulse.targetserver.service;

import org.springframework.stereotype.Service;
import org.syspulse.targetserver.utils.exception.DemoCustomException;

@Service
public class DemoService {

    public void throwCustomException() {
        throw new DemoCustomException("Сработало демонстрационное пользовательское исключение");
    }

    public void throwError() throws RuntimeException {
        throw new RuntimeException("Сработала демонстрационная ошибка уровня Error");
    }

    public void throwCheckedException() {
        throw new Error("Сработало демонстрационное проверяемое исключение");
    }
}
