/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.jsr299.tck.tests.decorators.definition;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 * Only withdrawal is charged. Implicit implementation that calls the method on the delegate is provided for
 * {@link Account#deposit(int)}.
 * 
 * @author Martin Kouba
 */
@Decorator
public abstract class ChargeDecorator implements Account {

    private static final int WITHDRAVAL_CHARGE = 5;

    public static int charged = 0;

    @Inject
    @Delegate
    private Account account;

    @Override
    public void withdraw(int amount) {
        account.withdraw(amount + WITHDRAVAL_CHARGE);
        charged += WITHDRAVAL_CHARGE;
    }

    @Override
    public abstract void deposit(int amount);

    public static void reset() {
        charged = 0;
    }

}