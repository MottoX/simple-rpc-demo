package com.github.mottox.rpc.server.service.impl;

import com.github.mottox.rpc.server.service.ArithmeticService;

/**
 * @author Weixin(Robin) Wang
 *         Created on 16/8/8.
 */
public class ArithmeticServiceImpl implements ArithmeticService {
    @Override
    public int sum(int x, int y) {
        return x + y;
    }
}
