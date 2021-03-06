/*
 * Copyright 2006-2012 The Scriptella Project Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package scriptella.jdbc;

import scriptella.AbstractTestCase;
import scriptella.execution.EtlExecutor;
import scriptella.execution.EtlExecutorException;
import scriptella.execution.ExecutionStatistics;

/**
 * Tests for nested elements handling.
 *
 * @author Fyodor Kupolov
 * @version 1.0
 */
public class NestedElementsPerfTest extends AbstractTestCase {
    /**
     * History:
     * 24.11.2008 - Lenovo T61 2GHz - 840 ms
     * 12.05.2007 - Duron 1.7Mhz - 1350 ms
     * 19.01.2007 - Duron 1.7Mhz - 1500 ms
     * @throws EtlExecutorException if ETL fails
     */
    public void test() throws EtlExecutorException {
        EtlExecutor exec = newEtlExecutor();
        ExecutionStatistics executionStatistics = exec.execute();
        System.out.println("executionStatistics = " + executionStatistics);
    }

    /**
     * History:
     * 24.11.2008 - Lenovo T61 2GHz - 630 ms
     * @throws EtlExecutorException if ETL fails
     */
    public void testNoStat() throws EtlExecutorException {
        EtlExecutor exec = newEtlExecutor();
        exec.setSuppressStatistics(true);
        ExecutionStatistics es = exec.execute();
        System.out.println("es = " + es);


    }


}
