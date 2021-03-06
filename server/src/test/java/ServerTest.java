/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.seata.core.rpc.netty.RpcServer;
import io.seata.server.UUIDGenerator;
import io.seata.server.coordinator.DefaultCoordinator;

/**
 * The type Server test.
 *
 * @author jimin.jm @alibaba-inc.com
 * @date 2018 /12/4
 */
public class ServerTest {

    private static final ThreadPoolExecutor workingThreads = new ThreadPoolExecutor(100, 500, 500, TimeUnit.SECONDS,
            new LinkedBlockingQueue(20000), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        RpcServer rpcServer = new RpcServer(workingThreads);
        rpcServer.setHandler(new DefaultCoordinator(rpcServer));
        UUIDGenerator.init(1);
        rpcServer.init();

        System.exit(0);
    }

}
