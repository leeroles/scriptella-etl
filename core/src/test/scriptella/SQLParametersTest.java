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
package scriptella;

import scriptella.execution.EtlExecutor;
import scriptella.execution.EtlExecutorException;
import scriptella.jdbc.QueryHelper;
import scriptella.spi.ParametersCallback;
import scriptella.spi.QueryCallback;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * TODO: Add documentation
 *
 * @author Fyodor Kupolov
 * @version 1.0
 */
public class SQLParametersTest extends DBTestCase {
    /**
     * Tests substitution of global and local properties.
     * See <a href="/resources/SQLParametersTest.xml">SQLParametersTest.xml</a> for details.
     */
    public void testSubstitution() throws EtlExecutorException {
        final Connection c = getConnection("sqlparamstst");
        EtlExecutor e = newEtlExecutor();
        e.execute();

        QueryHelper q = new QueryHelper("select * from test");
        q.execute(c,
                new QueryCallback() {
                    public void processRow(final ParametersCallback rowEvaluator) {
                        final Integer id = (Integer) rowEvaluator.getParameter("id");
                        final String text = (String) rowEvaluator.getParameter(
                                "text");

                        if (id.equals(1)) {
                            assertEquals("globalValue", text);
                        } else if (id.equals(2)) {
                            assertEquals("global2ValueThreeglobalValue", text);
                        } else {
                            fail("Unexpected id: " + id);
                        }
                    }
                });
    }

    public void testNumberedParameters() throws EtlExecutorException {
        final Connection c = getConnection("sqlparamstst2");
        EtlExecutor e = newEtlExecutor("SQLParametersTest2.xml");
        e.execute();

        QueryHelper q = new QueryHelper("select * from test");
        final Set expectedIds = new HashSet(Arrays.asList(
                new Integer[]{1, 2, 3, 10, 20, 30}));
        q.execute(c,
                new QueryCallback() {
                    public void processRow(final ParametersCallback rowEvaluator) {
                        final Integer id = (Integer) rowEvaluator.getParameter("id");
                        assertTrue("id must be one of " + expectedIds,
                                expectedIds.contains(id));
                    }
                });
    }
}
