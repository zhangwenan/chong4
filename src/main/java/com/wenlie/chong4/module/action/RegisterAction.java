/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wenlie.chong4.module.action;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;

import com.wenlie.chong4.Visitor;

public class RegisterAction {
    public void doRegister(@FormGroup("register") Visitor visitor, Navigator nav) {
        String name = visitor.getName();
        nav.redirectTo("appLink").withTarget("form/welcome").withParameter("name", name);
    }
}
