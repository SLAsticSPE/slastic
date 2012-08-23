/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.trustsoft.slastic.plugins.slasticImpl.model;

import de.cau.se.slastic.metamodel.core.SLAsticModel;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractModelManager<T extends SLAsticModel> {

    private final T model;

    public T getModel() {
        return this.model;
    }

    public AbstractModelManager(final T model) {
        this.model = model;
    }

}
